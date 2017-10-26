package br.ufes.inf.nemo.marvin.core.application;

import java.util.Date;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.ufes.inf.nemo.marvin.core.domain.MarvinConfiguration;
import br.ufes.inf.nemo.marvin.core.domain.Role;
import br.ufes.inf.nemo.marvin.core.domain.User;
import br.ufes.inf.nemo.marvin.core.exceptions.SystemInstallFailedException;
import br.ufes.inf.nemo.marvin.core.persistence.MarvinConfigurationDAO;
import br.ufes.inf.nemo.marvin.core.persistence.RoleDAO;
import br.ufes.inf.nemo.marvin.core.persistence.UserDAO;

/**
 * TODO: document this type.
 *
 * @author Vítor E. Silva Souza (vitorsouza@gmail.com)
 * @version 1.0
 */
@Stateless
public class InstallSystemServiceBean implements InstallSystemService {
	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** The logger. */
	private static final Logger logger = Logger.getLogger(InstallSystemServiceBean.class.getCanonicalName());

	/** The DAO for User objects. */
	@EJB
	private UserDAO userDAO;
	
	@EJB
	private RoleDAO roleDAO;

	/** The DAO for MarvinConfiguration objects. */
	@EJB
	private MarvinConfigurationDAO marvinConfigurationDAO;

	/** Global information about the application. */
	@EJB
	private CoreInformation coreInformation;

	/**
	 * @see br.ufes.inf.nemo.marvin.core.application.InstallSystemService#installSystem(br.ufes.inf.nemo.marvin.core.domain.MarvinConfiguration,
	 *      br.ufes.inf.nemo.marvin.core.domain.Academic)
	 */
	@Override
	public void installSystem(MarvinConfiguration config, User admin) throws SystemInstallFailedException {
		logger.log(Level.FINER, "Installing system...");
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		try {
			logger.log(Level.FINER, "Creating Security Roles...");
			Role adminRole = new Role("ADMIN");
			Role userRole = new Role("USER");
			roleDAO.save(adminRole);
			roleDAO.save(userRole);
			logger.log(Level.FINER, "Giving the Administrator Roles...");
			HashSet<Role> adminRoles = new HashSet<Role>();
			adminRoles.add(userRole);
			adminRoles.add(adminRole);
			// Encodes the admin's password.
			admin.setPassword(encoder.encode(admin.getPassword()));

			// Register the last update date / creation date.
			Date now = new Date(System.currentTimeMillis());
			admin.setLastUpdateDate(now);
			admin.setCreationDate(now);
			config.setCreationDate(now);
			admin.setRoles(adminRoles);
			logger.log(Level.FINE, "Admin's last update date have been set as: {0}", new Object[] { now });

			// Saves the administrator.
			logger.log(Level.FINER, "Persisting admin data...\n\t- Short name = {0}\n\t- Last update date = {1}",
					new Object[] { admin.getShortName(), admin.getLastUpdateDate() });
			userDAO.save(admin);
			logger.log(Level.FINE, "The administrator has been saved: {0} ({1})",
					new Object[] { admin.getName(), admin.getEmail() });

			// Saves Marvin's configuration.
			logger.log(Level.FINER, "Persisting configuration data...\n\t- Date = {0}\n\t- Acronym = {1}",
					new Object[] { config.getCreationDate(), config.getInstitutionAcronym() });
			marvinConfigurationDAO.save(config);
			logger.log(Level.FINE, "The configuration has been saved");

			// Reloads the bean that holds the configuration and determines if
			// the system is installed.
			logger.log(Level.FINER, "Reloading core information...");
			coreInformation.init();
		} catch (Exception e) {
			// Logs and rethrows the exception for the controller to display the
			// error to the user.
			logger.log(Level.SEVERE, "Exception during system installation!", e);
			throw new SystemInstallFailedException(e);
		}
	}

}

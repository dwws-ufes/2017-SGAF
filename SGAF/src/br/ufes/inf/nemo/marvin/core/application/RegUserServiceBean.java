package br.ufes.inf.nemo.marvin.core.application;

import java.util.Date;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.ufes.inf.nemo.marvin.core.domain.Role;
import br.ufes.inf.nemo.marvin.core.domain.User;
import br.ufes.inf.nemo.marvin.core.exceptions.SystemInstallFailedException;
import br.ufes.inf.nemo.marvin.core.persistence.UserDAO;

@Stateless
public class RegUserServiceBean implements RegUserService {

	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** The logger. */
	private static final Logger logger = Logger.getLogger(InstallSystemServiceBean.class.getCanonicalName());

	/** The DAO for Academic objects. */
	@EJB
	private UserDAO userDAO;

	@Override
	public void registerUser(User user) throws SystemInstallFailedException {
		logger.log(Level.FINER, "Persisting User...");
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		try {
			// Encodes the admin's password.
			user.setPassword(encoder.encode(user.getPassword()));
			logger.log(Level.FINER, "Giving the user Role...");
			Role userRole = new Role("USER");
			HashSet<Role> userRoles = new HashSet<Role>();
			userRoles.add(userRole);
			// Register the last update date / creation date.
			Date now = new Date(System.currentTimeMillis());
			user.setLastUpdateDate(now);
			user.setCreationDate(now);
			user.setRoles(userRoles);
			// Saves the user.
			logger.log(Level.FINER, "Persisting user data...\n\t- Title = {0}", new Object[] { user.getName() });
			userDAO.save(user);

		} catch (Exception e) {
			// Logs and rethrows the exception for the controller to display the
			// error to the user.
			logger.log(Level.SEVERE, "Exception during user register!", e);
			throw new SystemInstallFailedException(e);
		}
	}

}

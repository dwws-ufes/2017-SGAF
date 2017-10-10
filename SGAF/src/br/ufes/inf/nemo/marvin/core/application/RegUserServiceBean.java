package br.ufes.inf.nemo.marvin.core.application;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.marvin.core.domain.User;
import br.ufes.inf.nemo.marvin.core.exceptions.SystemInstallFailedException;
import br.ufes.inf.nemo.marvin.core.persistence.MarvinConfigurationDAO;
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

	/** The DAO for MarvinConfiguration objects. */
	@EJB
	private MarvinConfigurationDAO marvinConfigurationDAO;

	/** Global information about the application. */
	@EJB
	private CoreInformation coreInformation;

	@Override
	public void registerUser(User user) throws SystemInstallFailedException {
		logger.log(Level.FINER, "Persisting User...");
		try {
			// Register the last update date / creation date.
			Date now = new Date(System.currentTimeMillis());
			user.setCreationDate(now);
			
			// Saves the user.
			logger.log(Level.FINER, "Persisting user data...\n\t- Title = {0}", new Object[] { user.getName() });
			userDAO.save(user);
			
			// Reloads the bean that holds the configuration and determines if the system is saved.
			logger.log(Level.FINER, "Reloading core information...");
			coreInformation.init();
		}
		catch (Exception e) {
			// Logs and rethrows the exception for the controller to display the error to the user.
			logger.log(Level.SEVERE, "Exception during user register!", e);
			throw new SystemInstallFailedException(e);
		}
	}

}

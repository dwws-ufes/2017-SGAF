
package br.ufes.inf.nemo.marvin.core.application;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.marvin.core.domain.Actor;
import br.ufes.inf.nemo.marvin.core.exceptions.SystemInstallFailedException;
import br.ufes.inf.nemo.marvin.core.persistence.ActorDAO;
import br.ufes.inf.nemo.marvin.core.persistence.MarvinConfigurationDAO;

@Stateless
public class RegActorServiceBean implements RegActorService {

	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** The logger. */
	private static final Logger logger = Logger.getLogger(InstallSystemServiceBean.class.getCanonicalName());

	/** The DAO for Actor objects. */
	@EJB
	private ActorDAO actorDAO;

	/** The DAO for MarvinConfiguration objects. */
	@EJB
	private MarvinConfigurationDAO marvinConfigurationDAO;

	/** Global information about the application. */
	@EJB
	private CoreInformation coreInformation;
	
	@Override
	public void registerActor(Actor actor) throws SystemInstallFailedException{
		logger.log(Level.FINER, "Persisting Actor...");
		try {
			// Saves the actor.
			logger.log(Level.FINER, "Persisting actor data...\n\t- Name = {0}", new Object[] { actor.getName() });
			actorDAO.save(actor);
			
			// Reloads the bean that holds the configuration and determines if the system is saved.
			logger.log(Level.FINER, "Reloading core information...");
			coreInformation.init();
		}
		catch (Exception e) {
			// Logs and rethrows the exception for the controller to display the error to the user.
			logger.log(Level.SEVERE, "Exception during movie register!", e);
			throw new SystemInstallFailedException(e);
		}
	}
	

}

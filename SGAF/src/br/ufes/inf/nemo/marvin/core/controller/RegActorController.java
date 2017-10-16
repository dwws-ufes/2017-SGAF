package br.ufes.inf.nemo.marvin.core.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.ufes.inf.nemo.jbutler.ejb.controller.JSFController;
import br.ufes.inf.nemo.marvin.core.application.RegActorService;
import br.ufes.inf.nemo.marvin.core.domain.Actor;
import br.ufes.inf.nemo.marvin.core.exceptions.SystemInstallFailedException;

@Named
@ConversationScoped
public class RegActorController extends JSFController {
	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/**
	 * Path to the folder where the view files (web pages) for this action are
	 * placed.
	 */
	private static final String VIEW_PATH = "/core/regActor/";

	/** The logger. */
	private static final Logger logger = Logger.getLogger(InstallSystemController.class.getCanonicalName());

	/** The JSF conversation. */
	@Inject
	private Conversation conversation;

	/** Input: the movie being registered. */
	private Actor actor = new Actor();
	
	/** The "Register Actor" service. */
	@EJB
	private RegActorService regActorService;

	public String saveActor() {
		
		try {
			regActorService.registerActor(actor);
		} catch (SystemInstallFailedException e) {
			logger.log(Level.SEVERE, "System installation threw exception", e);
			// addGlobalI18nMessage("msgsCore", FacesMessage.SEVERITY_FATAL,
			// "installSystem.error.installFailed.summary",
			// "installSystem.error.installFailed.detail");
			return null;
		}
		
		// Ends the conversation.
		conversation.end();

		// Proceeds to the final view.
		return VIEW_PATH + "done.xhtml?faces-redirect=true";

	}
	
	/** Getter for movie. */
	public Actor getActor() {
		return actor;
	}
}

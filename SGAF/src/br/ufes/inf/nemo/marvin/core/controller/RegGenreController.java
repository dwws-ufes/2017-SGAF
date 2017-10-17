package br.ufes.inf.nemo.marvin.core.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.ufes.inf.nemo.jbutler.ejb.controller.JSFController;
import br.ufes.inf.nemo.marvin.core.application.RegGenreService;
import br.ufes.inf.nemo.marvin.core.application.RegMovieService;
import br.ufes.inf.nemo.marvin.core.domain.Genre;
import br.ufes.inf.nemo.marvin.core.exceptions.SystemInstallFailedException;

@Named
@ConversationScoped
public class RegGenreController extends JSFController {
	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/**
	 * Path to the folder where the view files (web pages) for this action are
	 * placed.
	 */
	private static final String VIEW_PATH = "/core/regGenre/";

	/** The logger. */
	private static final Logger logger = Logger.getLogger(RegGenreController.class.getCanonicalName());

	/** The JSF conversation. */
	@Inject
	private Conversation conversation;

	/** Input: the movie being registered. */
	private Genre genre = new Genre();

	/** The "Register Movie" service. */
	@EJB
	private RegGenreService regGenreService;

	public String saveGenre() {

		try {
			regGenreService.registerGenre(genre);
		} catch (SystemInstallFailedException e) {
			logger.log(Level.SEVERE, "System installation threw exception", e);
			// addGlobalI18nMessage("msgsCore", FacesMessage.SEVERITY_FATAL,
			// "installSystem.error.installFailed.summary",
			// "installSystem.error.installFailed.detail");
			return null;
		}

		// Ends the conversation.
//		conversation.end();

		// If everything is OK, clears the form.
		return "/index.xhtml?faces-redirect=true";

	}
	
	/** Getter for genre. */
	public Genre getGenre() {
		return genre;
	}

}

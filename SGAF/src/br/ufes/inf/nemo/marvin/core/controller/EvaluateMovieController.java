package br.ufes.inf.nemo.marvin.core.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.inject.Inject;

import br.ufes.inf.nemo.jbutler.ejb.controller.JSFController;
import br.ufes.inf.nemo.marvin.core.application.EvaluateMovieService;
import br.ufes.inf.nemo.marvin.core.domain.Review;
import br.ufes.inf.nemo.marvin.core.exceptions.SystemInstallFailedException;

public class EvaluateMovieController extends JSFController {
	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/**
	 * Path to the folder where the view files (web pages) for this action are
	 * placed.
	 */
	private static final String VIEW_PATH = "/core/evalMovie/";

	/** The logger. */
	private static final Logger logger = Logger.getLogger(EvaluateMovieController.class.getCanonicalName());

	/** The JSF conversation. */
	@Inject
	private Conversation conversation;

	/** Input: the review being registered. */
	private Review review = new Review();

	/** The "Evaluate Movie" service. */
	@EJB
	private EvaluateMovieService EvaluateMovieService;

	public String evaluateMovie() {

		try {
			EvaluateMovieService.evaluateMovie(review);
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

	/** Getter for review. */
	public Review getReview() {
		return review;
	}
}

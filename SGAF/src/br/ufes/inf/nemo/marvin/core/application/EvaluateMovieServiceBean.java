package br.ufes.inf.nemo.marvin.core.application;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;

import br.ufes.inf.nemo.marvin.core.domain.Review;
import br.ufes.inf.nemo.marvin.core.exceptions.SystemInstallFailedException;
import br.ufes.inf.nemo.marvin.core.persistence.ReviewDAO;

public class EvaluateMovieServiceBean implements EvaluateMovieService {

	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** The logger. */
	private static final Logger logger = Logger.getLogger(InstallSystemServiceBean.class.getCanonicalName());

	/** The DAO for Review objects. */
	@EJB
	private ReviewDAO reviewDAO;

	@Override
	public void evaluateMovie(Review review) throws SystemInstallFailedException {
		logger.log(Level.FINER, "Persisting Review of Movie...");
		try {
			// Saves the review.
			logger.log(Level.FINER, "Persisting review data...\n\t- Score = {0}", new Object[] { review.getScore() });
			reviewDAO.save(review);

		} catch (Exception e) {
			// Logs and rethrows the exception for the controller to display the
			// error to the user.
			logger.log(Level.SEVERE, "Exception during review register!", e);
			throw new SystemInstallFailedException(e);
		}
	}
}

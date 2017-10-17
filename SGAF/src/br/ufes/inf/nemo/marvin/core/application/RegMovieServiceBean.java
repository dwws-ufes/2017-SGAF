package br.ufes.inf.nemo.marvin.core.application;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.marvin.core.domain.Movie;
import br.ufes.inf.nemo.marvin.core.exceptions.SystemInstallFailedException;
import br.ufes.inf.nemo.marvin.core.persistence.MovieDAO;

@Stateless
public class RegMovieServiceBean implements RegMovieService {

	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** The logger. */
	private static final Logger logger = Logger.getLogger(InstallSystemServiceBean.class.getCanonicalName());

	/** The DAO for Academic objects. */
	@EJB
	private MovieDAO movieDAO;

	@Override
	public void registerMovie(Movie movie) throws SystemInstallFailedException {
		logger.log(Level.FINER, "Persisting Movie...");
		try {
			// Register the last update date / creation date.
			Date now = new Date(System.currentTimeMillis());
			movie.setRegisterDate(now);

			// Saves the movie.
			logger.log(Level.FINER, "Persisting movie data...\n\t- Title = {0}", new Object[] { movie.getTitle() });
			movieDAO.save(movie);

		} catch (Exception e) {
			// Logs and rethrows the exception for the controller to display the
			// error to the user.
			logger.log(Level.SEVERE, "Exception during movie register!", e);
			throw new SystemInstallFailedException(e);
		}
	}

}

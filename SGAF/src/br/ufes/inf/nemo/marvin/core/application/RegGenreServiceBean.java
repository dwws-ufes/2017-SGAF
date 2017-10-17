package br.ufes.inf.nemo.marvin.core.application;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.marvin.core.domain.Genre;
import br.ufes.inf.nemo.marvin.core.exceptions.SystemInstallFailedException;
import br.ufes.inf.nemo.marvin.core.persistence.GenreDAO;

@Stateless
public class RegGenreServiceBean implements RegGenreService {

	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** The logger. */
	private static final Logger logger = Logger.getLogger(InstallSystemServiceBean.class.getCanonicalName());

	/** The DAO for Genre objects. */
	@EJB
	private GenreDAO genreDAO;
	
	@Override
	public void registerGenre(Genre genre) throws SystemInstallFailedException {
		logger.log(Level.FINER, "Persisting Genre...");
		try {
			// Register the last update date / creation date.
			Date now = new Date(System.currentTimeMillis());
			genre.setCreationDate(now);

			// Saves the movie.
			logger.log(Level.FINER, "Persisting genre data...\n\t- Nme = {0}", new Object[] { genre.getName()});
			genreDAO.save(genre);

		} catch (Exception e) {
			// Logs and rethrows the exception for the controller to display the
			// error to the user.
			logger.log(Level.SEVERE, "Exception during movie register!", e);
			throw new SystemInstallFailedException(e);
		}
	}

}

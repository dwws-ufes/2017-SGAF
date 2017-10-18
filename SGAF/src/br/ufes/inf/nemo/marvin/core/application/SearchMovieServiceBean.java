package br.ufes.inf.nemo.marvin.core.application;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.marvin.core.domain.Movie;
import br.ufes.inf.nemo.marvin.core.persistence.MovieDAO;

@Stateless
public class SearchMovieServiceBean implements SearchMovieService {

	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** The logger. */
	private static final Logger logger = Logger.getLogger(InstallSystemServiceBean.class.getCanonicalName());

	/** The DAO for Genre objects. */
	@EJB
	private MovieDAO movieDAO;
	
//	public List<Movie> getMovies(){
//		return movieDAO.
//	}

}

package br.ufes.inf.nemo.marvin.core.persistence;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseJPADAO;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.MultiplePersistentObjectsFoundException;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.PersistentObjectNotFoundException;
import br.ufes.inf.nemo.marvin.core.domain.Movie;
import br.ufes.inf.nemo.marvin.core.domain.Movie_;

@Stateless
public class MovieJPADAO extends BaseJPADAO<Movie> implements MovieDAO {
	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** The logger. */
	private static final Logger logger = Logger.getLogger(MovieJPADAO.class.getCanonicalName());

	/**
	 * The application's persistent context provided by the application server.
	 */
	@PersistenceContext
	private EntityManager entityManager;

	/** @see br.ufes.inf.nemo.util.ejb3.persistence.BaseJPADAO#getEntityManager() */
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}
	
	@Override
	public Movie retrieveByTitle(String title)
		throws
		 PersistentObjectNotFoundException,
		 MultiplePersistentObjectsFoundException {
		 logger.log(Level.FINE, "Retrieving movie by the title \" {0} \"...", title);
		 
		 // Constructs the query over the Movie class.
		 CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		 CriteriaQuery<Movie> cq = cb.createQuery(Movie.class);
		 Root<Movie> root = cq.from(Movie.class);
		
		 // Filters the query with the title.
		 cq.where(cb.equal(root.get(Movie_.title), title));
		 Movie result = executeSingleResultQuery(cq, title);
		 logger.log(Level.INFO, "Retrieve movie by the title \" {0} \" returned \"{1}\"", new Object[] { title, result });
		 return result;
	}
}

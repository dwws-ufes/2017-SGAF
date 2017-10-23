package br.ufes.inf.nemo.marvin.core.persistence;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.ufes.inf.nemo.jbutler.ejb.application.filters.Filter;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseJPADAO;
import br.ufes.inf.nemo.marvin.core.domain.Director;

@Stateless
public class DirectorJPADAO extends BaseJPADAO<Director> implements DirectorDAO {
	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** The logger. */
	private static final Logger logger = Logger.getLogger(DirectorJPADAO.class.getCanonicalName());

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
	
	/**
	 * Returns a list of directors based on the name filter and limits the number of results
	 * 
	 */
	@Override
	public List<Director> filterNameWith(Filter<?> filter, String value, int MaxResults) {
		// Builds the filtered query and returns the result.
		EntityManager em = getEntityManager();
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Director> cq = builder.createQuery(Director.class);
		Root<Director> from = cq.from(Director.class);
		Predicate predicate = builder.like(from.<String>get("name"), "%" + value + "%");
		List<Director> result = em.createQuery(cq.where(predicate)).setMaxResults(MaxResults).getResultList();
		logger.log(Level.INFO,
				"Retrieve with filter (filter \"{0}\" with value \"{1}\") for class \"{2}\" returned \"{3}\" objects",
				new Object[] { filter.getKey(), value, getDomainClass().getName(), result.size() });
		return result;
	}
}

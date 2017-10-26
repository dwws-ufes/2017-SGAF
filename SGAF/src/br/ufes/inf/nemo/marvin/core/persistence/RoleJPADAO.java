package br.ufes.inf.nemo.marvin.core.persistence;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.ufes.inf.nemo.marvin.core.domain.Role;

@Stateless
public class RoleJPADAO implements RoleDAO, Serializable {

	/** Serialization id. */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The application's persistent context provided by the application server.
	 */
	@PersistenceContext
	private EntityManager entityManager;

	protected EntityManager getEntityManager() {
		return entityManager;
	}

	/** The logger. */
	private static final Logger logger = Logger.getLogger(RoleJPADAO.class.getCanonicalName());

	public void save(Role role) {
		logger.log(Level.FINER, "Saving an object of class Role: \"{0}\"...", new Object[] { role.getName() });

		// Uses the Persistence Context to save an object.
		EntityManager em = getEntityManager();
		em.persist(role);
	}

	public void delete(Role role) {
		logger.log(Level.FINER, "Deleting an object of class Role: \"{0}\"...", new Object[] { role.getName() });

		// Uses the Persistence Context to delete an object.
		EntityManager em = getEntityManager();
		em.remove(em.merge(role));
	}

	/** @see br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO#retrieveAll() */

	public List<Role> retrieveAll() {
		logger.log(Level.FINER, "Retrieving all objects of class Role...");

		// Using the entity manager, create a criteria query to retrieve all
		// objects of the domain class.
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Role> cq = cb.createQuery(Role.class);
		Root<Role> root = cq.from(Role.class);
		cq.select(root);

		// Return the list of objects.
		List<Role> result = em.createQuery(cq).getResultList();
		logger.log(Level.INFO, "Retrieve all for class Role returned \"{0}\" objects", new Object[] { result.size() });
		return result;
	}

}

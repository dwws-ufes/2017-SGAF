package br.ufes.inf.nemo.marvin.core.persistence;

import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseJPADAO;
import br.ufes.inf.nemo.marvin.core.domain.User;

public class UserJPADAO extends BaseJPADAO<User> implements UserDAO {
	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** The logger. */
	private static final Logger logger = Logger.getLogger(AcademicJPADAO.class.getCanonicalName());

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

	/** @see br.ufes.inf.nemo.marvin.core.persistence.AcademicDAO#retrieveByEmail(java.lang.String) */
	// @Override
	// public User retrieveByEmail(String email) throws
	// PersistentObjectNotFoundException,
	// MultiplePersistentObjectsFoundException {
	// logger.log(Level.FINE, "Retrieving the academic whose e-mail is
	// \"{0}\"...", email);
	//
	// // Constructs the query over the Academic class.
	// CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	// CriteriaQuery<User> cq = cb.createQuery(User.class);
	// Root<User> root = cq.from(User.class);
	//
	// // Filters the query with the email.
	// cq.where(cb.equal(root.get(User_.email), email));
	// User result = executeSingleResultQuery(cq, email);
	// logger.log(Level.INFO, "Retrieve academic by the email \"{0}\" returned
	// \"{1}\"", new Object[] { email, result });
	// return result;
	// }
}

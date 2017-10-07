package br.ufes.inf.nemo.marvin.core.persistence;

import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseJPADAO;
import br.ufes.inf.nemo.marvin.core.domain.Director;

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
}

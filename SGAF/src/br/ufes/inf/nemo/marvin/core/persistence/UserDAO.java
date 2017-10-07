package br.ufes.inf.nemo.marvin.core.persistence;

import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import br.ufes.inf.nemo.marvin.core.domain.User;

public interface UserDAO extends BaseDAO<User> {
	/**
	 * TODO: document this method.
	 * 
	 * @param email
	 * @return
	 * @throws PersistentObjectNotFoundException
	 * @throws MultiplePersistentObjectsFoundException
	 */
	// User retrieveByEmail(String email)
	// throws PersistentObjectNotFoundException,
	// MultiplePersistentObjectsFoundException;
}
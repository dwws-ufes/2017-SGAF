package br.ufes.inf.nemo.marvin.core.application;

import javax.ejb.EJB;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudServiceBean;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import br.ufes.inf.nemo.marvin.core.domain.Actor;
import br.ufes.inf.nemo.marvin.core.persistence.ActorDAO;

public class ManageActorsServiceBean extends CrudServiceBean<Actor> implements ManageActorsService {

	/** TODO: document this field. */
	private static final long serialVersionUID = 1L;

	/** TODO: document this field. */
	@EJB
	private ActorDAO actorDAO;

	/** @see br.ufes.inf.nemo.jbutler.ejb.application.ListingService#getDAO() */
	@Override
	public BaseDAO<Actor> getDAO() {
		return actorDAO;
	}

}

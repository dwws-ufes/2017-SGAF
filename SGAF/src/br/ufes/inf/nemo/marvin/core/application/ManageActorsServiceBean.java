package br.ufes.inf.nemo.marvin.core.application;

import java.util.List;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudServiceBean;
import br.ufes.inf.nemo.jbutler.ejb.application.filters.Filter;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import br.ufes.inf.nemo.marvin.core.domain.Actor;
import br.ufes.inf.nemo.marvin.core.persistence.ActorDAO;

@Stateless
@PermitAll
public class ManageActorsServiceBean extends CrudServiceBean<Actor> implements ManageActorsService {

	/** The serialization id. */
	private static final long serialVersionUID = 1L;

	/** The DAO for Actor objects. */
	@EJB
	private ActorDAO actorDAO;
	
	/* @Resource
	private SessionContext sessionContext; */

	/** @see br.ufes.inf.nemo.jbutler.ejb.application.ListingService#getDAO() */
	@Override
	public BaseDAO<Actor> getDAO() {
		return actorDAO;
	}
	
	public List<Actor> filterNameWith(Filter<?> filter, String value, int MaxResults){
		return actorDAO.filterNameWith(filter,value,MaxResults);
	}

}

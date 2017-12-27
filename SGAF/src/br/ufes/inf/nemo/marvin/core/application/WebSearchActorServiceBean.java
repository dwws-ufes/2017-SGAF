package br.ufes.inf.nemo.marvin.core.application;

import java.util.List;
import java.util.logging.Logger;

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
import br.ufes.inf.nemo.marvin.core.persistence.WebSearchActorDAO;

@Stateless
@PermitAll
public class WebSearchActorServiceBean extends CrudServiceBean<Actor> implements WebSearchActorService {

	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** The logger. */
	private static final Logger logger = Logger.getLogger(InstallSystemServiceBean.class.getCanonicalName());
	
	/** The DAO for Actor objects. */
	@EJB
	private ActorDAO actorDAO;

	/** The DAO for Web Search of Actor Objects. */
	@EJB
	private WebSearchActorDAO webSearchActorDAO;

	/** TODO: document this field. */
	@Resource
	private SessionContext sessionContext;

	/** @see br.ufes.inf.nemo.jbutler.ejb.application.ListingService#getDAO() */
	@Override
	public BaseDAO<Actor> getDAO() {
		return actorDAO;
	}

	@Override
	public List<Actor> filter(Filter<?> filter, String filterParam, int ... interval) {
		List<Actor> entities = webSearchActorDAO.retrieveSomeWithFilter(filter, filterParam, interval);
		return entities;
	}
	
	@Override
	public List<Actor> list(int ... interval) {
		List<Actor> entities = webSearchActorDAO.retrieveSome(interval);
		return entities;
	}
}

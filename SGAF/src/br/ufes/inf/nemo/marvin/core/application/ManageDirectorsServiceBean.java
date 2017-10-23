package br.ufes.inf.nemo.marvin.core.application;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudServiceBean;
import br.ufes.inf.nemo.jbutler.ejb.application.filters.Filter;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import br.ufes.inf.nemo.marvin.core.domain.Actor;
import br.ufes.inf.nemo.marvin.core.domain.Director;
import br.ufes.inf.nemo.marvin.core.persistence.DirectorDAO;

@Stateless
@PermitAll
public class ManageDirectorsServiceBean extends CrudServiceBean<Director> implements ManageDirectorsService {

	/** The serialization id. */
	private static final long serialVersionUID = 1L;

	/** The DAO for Director objects. */
	@EJB
	private DirectorDAO directorDAO;
	

	/** @see br.ufes.inf.nemo.jbutler.ejb.application.ListingService#getDAO() */
	@Override
	public BaseDAO<Director> getDAO() {
		return directorDAO;
	}
	
	public List<Director> filterNameWith(Filter<?> filter, String value, int MaxResults){
		return directorDAO.filterNameWith(filter,value,MaxResults);
	}

}

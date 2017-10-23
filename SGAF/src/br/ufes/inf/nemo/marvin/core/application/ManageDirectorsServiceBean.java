package br.ufes.inf.nemo.marvin.core.application;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudServiceBean;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import br.ufes.inf.nemo.marvin.core.domain.Director;
import br.ufes.inf.nemo.marvin.core.persistence.DirectorDAO;

@Stateless
@PermitAll
public class ManageDirectorsServiceBean extends CrudServiceBean<Director> implements ManageDirectorsService {

	/** The serialization id. */
	private static final long serialVersionUID = 1L;

	/** TODO: document this field. */
	@EJB
	private DirectorDAO directorDAO;

	/** @see br.ufes.inf.nemo.jbutler.ejb.application.ListingService#getDAO() */
	@Override
	public BaseDAO<Director> getDAO() {
		return directorDAO;
	}

}

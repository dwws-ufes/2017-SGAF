package br.ufes.inf.nemo.marvin.core.application;

import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudServiceBean;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import br.ufes.inf.nemo.marvin.core.domain.User;
import br.ufes.inf.nemo.marvin.core.persistence.UserDAO;

@Stateless
@PermitAll
public class ManageUsersServiceBean extends CrudServiceBean<User> implements ManageUsersService {

	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** The logger. */
	private static final Logger logger = Logger.getLogger(ManageUsersServiceBean.class.getCanonicalName());

	/** The DAO for Movie objects. */
	@EJB
	private UserDAO userDAO;

	/** TODO: document this field. */
	@Resource
	private SessionContext sessionContext;

	/** @see br.ufes.inf.nemo.jbutler.ejb.application.ListingService#getDAO() */
	@Override
	public BaseDAO<User> getDAO() {
		return userDAO;
	}
}

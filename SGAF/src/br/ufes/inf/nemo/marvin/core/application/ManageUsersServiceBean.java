package br.ufes.inf.nemo.marvin.core.application;

import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudServiceBean;
import br.ufes.inf.nemo.jbutler.ejb.application.filters.SimpleFilter;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import br.ufes.inf.nemo.marvin.core.domain.Role;
import br.ufes.inf.nemo.marvin.core.domain.User;
import br.ufes.inf.nemo.marvin.core.persistence.RoleDAO;
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

	/** The DAO for Movie objects. */
	@EJB
	private RoleDAO roleDAO;

	/** TODO: document this field. */
	@Resource
	private SessionContext sessionContext;

	public boolean isAdmin(User user) {
		if (user == null)
			return false;
		User persistedUser = userDAO.retrieveById(user.getId());
		if (persistedUser != null) {
			return persistedUser.getRoles().contains(new Role("ROLE_USER"));
		} else {
			return false;
		}
	}

	public void promoteToAdmin(User user) {
//		SimpleFilter filter = new SimpleFilter("ManageUsers.filter.byName", "name",
//				null);
//		List<Role> userRoles = roleDAO.retrieveWithFilter(filter, "ROLE_USER");
////		Role adminRole = roleDAO.retrieveById("ROLE_ADMIN");
//		user = userDAO.retrieveById(user.getId());
//		if(user.getRoles().contains(adminRole)){
//			return;
//		}else{
//			user.getRoles().add(adminRole);
//			userDAO.merge(user);
//		}
		
	}

	/** @see br.ufes.inf.nemo.jbutler.ejb.application.ListingService#getDAO() */
	@Override
	public BaseDAO<User> getDAO() {
		return userDAO;
	}
}

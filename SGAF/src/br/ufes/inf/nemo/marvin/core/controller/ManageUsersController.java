package br.ufes.inf.nemo.marvin.core.controller;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.inf.nemo.jbutler.ejb.application.filters.SimpleFilter;
import br.ufes.inf.nemo.jbutler.ejb.controller.CrudController;
import br.ufes.inf.nemo.marvin.core.application.ManageUsersService;
import br.ufes.inf.nemo.marvin.core.domain.User;

@Named
@SessionScoped
public class ManageUsersController extends CrudController<User> {

	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** The "Manage Movies" service. */
	@EJB
	private ManageUsersService manageUsersService;

	/** Saves the condition to enable/disable the Make-Admin button **/
	private boolean giveAdminRoleEnable = false;

	/** @see br.ufes.inf.nemo.jbutler.ejb.controller.CrudController#getCrudService() */
	@Override
	protected CrudService<User> getCrudService() {
		return manageUsersService;
	}

	public void onRowSelect(SelectEvent event) {
		isSelectedEntityAdmin();
		System.out.print("Wuba!!");
	}

	public void onRowUnselect(SelectEvent event) {
		giveAdminRoleEnable = false;
	}

	public void isSelectedEntityAdmin() {
		giveAdminRoleEnable = manageUsersService.isAdmin(selectedEntity);
		System.out.print(giveAdminRoleEnable);
	}

	public void promoteToAdmin(){
		manageUsersService.promoteToAdmin(selectedEntity);
	}
	
	/** @see br.ufes.inf.nemo.jbutler.ejb.controller.ListingController#initFilters() */
	@Override
	protected void initFilters() {
		addFilter(new SimpleFilter("ManageUsers.filter.byName", "name",
				getI18nMessage("msgsCore", "manageUsers.text.filter.byName")));
	}

	public ManageUsersService getManageUsersService() {
		return manageUsersService;
	}

	public void setManageUsersService(ManageUsersService manageUsersService) {
		this.manageUsersService = manageUsersService;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public boolean getGiveAdminRoleEnable() {
		return giveAdminRoleEnable;
	}

	public void setGiveAdminRoleEnable(boolean giveAdminRoleEnable) {
		this.giveAdminRoleEnable = giveAdminRoleEnable;
	}

}

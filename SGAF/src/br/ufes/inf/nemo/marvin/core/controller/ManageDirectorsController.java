package br.ufes.inf.nemo.marvin.core.controller;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.inf.nemo.jbutler.ejb.application.filters.SimpleFilter;
import br.ufes.inf.nemo.jbutler.ejb.controller.CrudController;
import br.ufes.inf.nemo.marvin.core.application.ManageDirectorsService;
import br.ufes.inf.nemo.marvin.core.domain.Director;

@Named
@SessionScoped
public class ManageDirectorsController extends CrudController<Director> {
	/** The serialization id. */
	private static final long serialVersionUID = 1L;

	/** The "Manage Directors" service. */
	@EJB
	private ManageDirectorsService manageDirectorsService;

	/** @see br.ufes.inf.nemo.jbutler.ejb.controller.CrudController#getCrudService() */
	@Override
	protected CrudService<Director> getCrudService() {
		return manageDirectorsService;
	}

	/** @see br.ufes.inf.nemo.jbutler.ejb.controller.ListingController#initFilters() */
	@Override
	protected void initFilters() {
		addFilter(new SimpleFilter("manageDirectors.filter.byName", "name",
				getI18nMessage("msgsCore", "manageDirectors.text.filter.byName")));
	}
}

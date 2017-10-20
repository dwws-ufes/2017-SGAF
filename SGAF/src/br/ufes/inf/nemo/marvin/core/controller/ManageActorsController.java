package br.ufes.inf.nemo.marvin.core.controller;

import javax.ejb.EJB;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.inf.nemo.jbutler.ejb.application.filters.SimpleFilter;
import br.ufes.inf.nemo.jbutler.ejb.controller.CrudController;
import br.ufes.inf.nemo.marvin.core.application.ManageActorsService;
import br.ufes.inf.nemo.marvin.core.domain.Actor;

public class ManageActorsController extends CrudController<Actor> {

	/** TODO: document this field. */
	private static final long serialVersionUID = 1L;

	/** TODO: document this field. */
	@EJB
	private ManageActorsService manageActorsService;

	/** @see br.ufes.inf.nemo.jbutler.ejb.controller.CrudController#getCrudService() */
	@Override
	protected CrudService<Actor> getCrudService() {
		return manageActorsService;
	}

	/** @see br.ufes.inf.nemo.jbutler.ejb.controller.ListingController#initFilters() */
	@Override
	protected void initFilters() {
		addFilter(new SimpleFilter("manageActors.filter.byName", "name",
				getI18nMessage("msgsCore", "manageActors.text.filter.byName")));
	}

}

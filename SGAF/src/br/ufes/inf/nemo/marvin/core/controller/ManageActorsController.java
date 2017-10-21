package br.ufes.inf.nemo.marvin.core.controller;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.inf.nemo.jbutler.ejb.application.filters.SimpleFilter;
import br.ufes.inf.nemo.jbutler.ejb.controller.CrudController;
import br.ufes.inf.nemo.marvin.core.application.ManageActorsService;
import br.ufes.inf.nemo.marvin.core.domain.Actor;

@Named
@SessionScoped
public class ManageActorsController extends CrudController<Actor> {

	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** The "Manage Actors" service. */
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

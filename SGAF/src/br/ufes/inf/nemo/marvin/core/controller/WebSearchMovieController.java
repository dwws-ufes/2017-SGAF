package br.ufes.inf.nemo.marvin.core.controller;

import javax.ejb.EJB;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.inf.nemo.jbutler.ejb.application.filters.SimpleFilter;
import br.ufes.inf.nemo.jbutler.ejb.controller.CrudController;
import br.ufes.inf.nemo.marvin.core.application.ManageMoviesService;
import br.ufes.inf.nemo.marvin.core.domain.Movie;

public class WebSearchMovieController extends CrudController<Movie> {

	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** The "Manage Movies" service. */
	@EJB
	private ManageMoviesService manageMoviesService;

	/** @see br.ufes.inf.nemo.jbutler.ejb.controller.CrudController#getCrudService() */
	@Override
	protected CrudService<Movie> getCrudService() {
		return manageMoviesService;
	}

	/** @see br.ufes.inf.nemo.jbutler.ejb.controller.ListingController#initFilters() */
	@Override
	protected void initFilters() {
		addFilter(new SimpleFilter("ManageMovies.filter.byTitle", "title",
				getI18nMessage("msgsCore", "ManageMovies.text.filter.byTitle")));
	}

}

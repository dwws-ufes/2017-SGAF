package br.ufes.inf.nemo.marvin.core.controller;

import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.inf.nemo.jbutler.ejb.application.filters.SimpleFilter;
import br.ufes.inf.nemo.jbutler.ejb.controller.CrudController;
import br.ufes.inf.nemo.marvin.core.application.WebSearchLazyFilter;
import br.ufes.inf.nemo.marvin.core.application.WebSearchMovieService;
import br.ufes.inf.nemo.marvin.core.domain.Movie;

@Named
@SessionScoped
public class WebSearchMovieController extends CrudController<Movie> {

	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** The "Manage Movies" service. */
	@EJB
	private WebSearchMovieService webSearchMovieService;

	private LazyDataModel<Movie> model;
	
	private WebSearchLazyFilter filter = new WebSearchLazyFilter();

	/** @see br.ufes.inf.nemo.jbutler.ejb.controller.CrudController#getCrudService() */
	@Override
	protected CrudService<Movie> getCrudService() {
		return webSearchMovieService;
	}

	/** @see br.ufes.inf.nemo.jbutler.ejb.controller.ListingController#initFilters() */
	@Override
	protected void initFilters() {
		addFilter(new SimpleFilter("ManageMovies.filter.byTitle", "title",
				getI18nMessage("msgsCore", "ManageMovies.text.filter.byTitle")));
	}

	public WebSearchMovieController() {
		model = new LazyDataModel<Movie>() {
			private static final long serialVersionUID = 1L;

			@Override
			public List<Movie> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {

				filter.setPrimeiroRegistro(first);
				filter.setQuantidadeRegistros(pageSize);
				filter.setAscendente(SortOrder.ASCENDING.equals(sortOrder));
				filter.setPropriedadeOrdenacao(sortField);

				setRowCount(webSearchMovieService.retrieveCountWithFilter(filter));

				return webSearchMovieService.retrieveWithFilter(filter);
			}

		};
	}

	public WebSearchMovieService getWebSearchMovieService() {
		return webSearchMovieService;
	}

	public void setWebSearchMovieService(WebSearchMovieService webSearchMovieService) {
		this.webSearchMovieService = webSearchMovieService;
	}

	public LazyDataModel<Movie> getModel() {
		return model;
	}

	public void setModel(LazyDataModel<Movie> model) {
		this.model = model;
	}

	public void setFilter(WebSearchLazyFilter filter) {
		this.filter = filter;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	// @Override
	// /**
	// * Getter for lazyEntities.
	// *
	// * @return Primefaces lazy data model for use with a lazy p:dataTable
	// component.
	// */
	// public LazyDataModel<Movie> getLazyEntities() {
	// if (lazyEntities == null) {
	// count();
	// lazyEntities = new
	// PrimefacesLazyEntityDataModel<Movie>(getListingService().getDAO()) {/**
	// Serialization id. */
	// private static final long serialVersionUID = 1117380513193004406L;
	// @Override
	// public List<Movie> load(int first, int pageSize, String sortField,
	// SortOrder sortOrder, Map<String, Object> filters) {
	// firstEntityIndex = first;
	// lastEntityIndex = first + pageSize;
	// retrieveEntities();
	// return entities;
	// }
	// };
	// lazyEntities.setRowCount((int) entityCount);
	// }
	//
	// return lazyEntities;
	// }

	
	
}

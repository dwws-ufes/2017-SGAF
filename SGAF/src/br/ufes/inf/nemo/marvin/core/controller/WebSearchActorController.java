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
import br.ufes.inf.nemo.jbutler.ejb.controller.PrimefacesLazyEntityDataModel;
import br.ufes.inf.nemo.marvin.core.application.WebSearchActorService;
import br.ufes.inf.nemo.marvin.core.domain.Actor;

@Named
@SessionScoped
public class WebSearchActorController extends CrudController<Actor> {
	
	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** The "Web Search Actor" service. */
	@EJB
	private WebSearchActorService webSearchActorService;

	private LazyDataModel<Actor> model;

	// private WebSearchLazyFilter filter = new WebSearchLazyFilter();
	
	/** @see br.ufes.inf.nemo.jbutler.ejb.controller.CrudController#getCrudService() */
	@Override
	protected CrudService<Actor> getCrudService() {
		return webSearchActorService;
	}

	/** @see br.ufes.inf.nemo.jbutler.ejb.controller.ListingController#initFilters() */
	@Override
	protected void initFilters() {
		addFilter(new SimpleFilter("ManageActors.filter.byName", "name",
				getI18nMessage("msgsCore", "manageActors.text.filter.byName")));
	}

	// public WebSearchActorController() {
	// model = new LazyDataModel<Actor>() {
	// private static final long serialVersionUID = 1L;
	//
	// @Override
	// public List<Actor> load(int first, int pageSize, String sortField,
	// SortOrder sortOrder,
	// Map<String, Object> filters) {
	//
	// filter.setPrimeiroRegistro(first);
	// filter.setQuantidadeRegistros(pageSize);
	// filter.setAscendente(SortOrder.ASCENDING.equals(sortOrder));
	// filter.setPropriedadeOrdenacao(sortField);
	//
	// setRowCount(webSearchActorService.retrieveCountWithFilter(filter));
	//
	// return webSearchActorService.retrieveWithFilter(filter);
	// }
	//
	// };
	// }

	public WebSearchActorService getWebSearchActorService() {
		return webSearchActorService;
	}

	public void setWebSearchActorService(WebSearchActorService webSearchActorService) {
		this.webSearchActorService = webSearchActorService;
	}

	public LazyDataModel<Actor> getModel() {
		// model.setRowCount(108);
		return model;
	}

	public void setModel(LazyDataModel<Actor> model) {
		this.model = model;
	}

//	public void setFilter(WebSearchLazyFilter filter) {
//		this.filter = filter;
//	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	/**
	 * Getter for lazyEntities.
	 *
	 * @return Primefaces lazy data model for use with a lazy p:dataTable
	 *         component.
	 */
	public LazyDataModel<Actor> getLazyEntities() {
		if (lazyEntities == null) {
			count();
			lazyEntities = new PrimefacesLazyEntityDataModel<Actor>(getListingService().getDAO()) {
				/**
				 * Serialization id.
				 */
				private static final long serialVersionUID = 17380513193004406L;

				@Override
				public List<Actor> load(int first, int pageSize, String sortField, SortOrder sortOrder,
						Map<String, Object> filters) {
					firstEntityIndex = first;
					lastEntityIndex = first + pageSize;
					retrieveEntities();
					return entities;
				}
			};
			lazyEntities.setRowCount((int) entityCount);
		}

		return lazyEntities;
	}
	
	/**
	 * Retrieves a collection of entities, respecting the selected range. Makes the collection available to the view.
	 * This method is intended to be used internally.
	 */
	@Override
	protected void retrieveEntities() {
		// Checks if the last entity index is over the number of entities and correct it.
		if (lastEntityIndex > entityCount) lastEntityIndex = (int) entityCount;

		// Checks if there's an active filter.
		if (filtering) {
			// There is. Retrieve not only within range, but also with filtering.
			entities = webSearchActorService.filter(filter, filterParam, firstEntityIndex, lastEntityIndex);
		}
		else {
			// There's not. Retrieve all entities within range.
			entities = webSearchActorService.list(firstEntityIndex, lastEntityIndex);
		}

		// Adjusts the last entity index.
		lastEntityIndex = firstEntityIndex + entities.size();
	}
}

package br.ufes.inf.nemo.marvin.core.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.inf.nemo.jbutler.ejb.application.filters.SimpleFilter;
import br.ufes.inf.nemo.jbutler.ejb.controller.CrudController;
import br.ufes.inf.nemo.jbutler.ejb.controller.PrimefacesLazyEntityDataModel;
import br.ufes.inf.nemo.marvin.core.application.WebSearchMovieService;
import br.ufes.inf.nemo.marvin.core.domain.Actor;
import br.ufes.inf.nemo.marvin.core.domain.Movie;
import br.ufes.inf.nemo.marvin.core.persistence.WebSearchMovieJenaDAO;

@Named
@SessionScoped
public class WebSearchMovieController extends CrudController<Movie> {

	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** The "Manage Movies" service. */
	@EJB
	private WebSearchMovieService webSearchMovieService;

	private LazyDataModel<Movie> model;
	
	private Movie selectedEntityWeb;
	
	private String actorName;
	
	private boolean searchedByActor;

	// private WebSearchLazyFilter filter = new WebSearchLazyFilter();

	public String getActorName() {
		return actorName;
	}

	public void setActorName(String actorName) {
		this.actorName = actorName;
	}

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

	// public WebSearchMovieController() {
	// model = new LazyDataModel<Movie>() {
	// private static final long serialVersionUID = 1L;
	//
	// @Override
	// public List<Movie> load(int first, int pageSize, String sortField,
	// SortOrder sortOrder,
	// Map<String, Object> filters) {
	//
	// filter.setPrimeiroRegistro(first);
	// filter.setQuantidadeRegistros(pageSize);
	// filter.setAscendente(SortOrder.ASCENDING.equals(sortOrder));
	// filter.setPropriedadeOrdenacao(sortField);
	//
	// setRowCount(webSearchMovieService.retrieveCountWithFilter(filter));
	//
	// return webSearchMovieService.retrieveWithFilter(filter);
	// }
	//
	// };
	// }
	
	public List<Movie> searchMovieByActor() {
		List<Movie> resultList = new ArrayList<Movie>();
		
		String query = "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" + 
				"PREFIX movie: <http://data.linkedmdb.org/resource/movie/>\n" + 
				"\n" + 
				"SELECT DISTINCT ?filmTitle ?initial_release_date ?runtime WHERE {\n" + 
				"?actor a movie:actor;\n" + 
				"         movie:actor_name \"" + actorName + "\".\n" + 
				"?film a movie:film;\n" + 
				"        movie:actor ?actor;\n" + 
				"        rdfs:label ?filmTitle;\n" + 
				"        movie:initial_release_date ?initial_release_date;\n" + 
				"        movie:runtime ?runtime.\n" + 
				"}";

		QueryExecution queryExecution = QueryExecutionFactory.sparqlService("http://data.linkedmdb.org/sparql", query);
		ResultSet results = queryExecution.execSelect();

		while (results.hasNext()) {
			Movie e = new Movie();
			QuerySolution querySolution = results.next();
			e.setTitle(querySolution.getLiteral("filmTitle").toString());
			e.setLength(new Long(querySolution.getLiteral("runtime").toString()));
//			e.setLaunchDate(new Date());
			/**falta entender como parsear a data**/
			try {
				e.setLaunchDate(WebSearchMovieJenaDAO.readDate(querySolution.getLiteral("initial_release_date").getString()));
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			resultList.add(e);
		}

		return resultList;
	}
	
	public void searchByActor() {
		searchedByActor = true;
	}

	public WebSearchMovieService getWebSearchMovieService() {
		return webSearchMovieService;
	}

	public void setWebSearchMovieService(WebSearchMovieService webSearchMovieService) {
		this.webSearchMovieService = webSearchMovieService;
	}

	public LazyDataModel<Movie> getModel() {
		// model.setRowCount(108);
		return model;
	}

	public void setModel(LazyDataModel<Movie> model) {
		this.model = model;
	}

	// public void setFilter(WebSearchLazyFilter filter) {
	// this.filter = filter;
	// }

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String add() {

		// Sets the data as read-write.
		readOnly = false;

		// Resets the entity so we can create a new one.
//		selectedEntity = createNewEntity();

		// Goes to the form.
		return getViewPath() + "form.xhtml?faces-redirect=" + getFacesRedirect();
	}
	
	@Override
	/**
	 * Getter for lazyEntities.
	 *
	 * @return Primefaces lazy data model for use with a lazy p:dataTable
	 *         component.
	 */
	public LazyDataModel<Movie> getLazyEntities() {
		if (lazyEntities == null) {
			count();
			lazyEntities = new PrimefacesLazyEntityDataModel<Movie>(getListingService().getDAO()) {
				/**
				 * Serialization id.
				 */
				private static final long serialVersionUID = 1117380513193004406L;

				@Override
				public List<Movie> load(int first, int pageSize, String sortField, SortOrder sortOrder,
						Map<String, Object> filters) {
					firstEntityIndex = first;
					lastEntityIndex = first + pageSize;
					retrieveEntities();
					return entities;
				}
				
				@Override
				public Movie getRowData(String rowKey){
					return Movie.getMovieByUuid(rowKey, entities);
				}

			};
			lazyEntities.setRowCount((int) entityCount);
		}

		return lazyEntities;
	}

	
	
	/**
	 * Retrieves a collection of entities, respecting the selected range. Makes
	 * the collection available to the view. This method is intended to be used
	 * internally.
	 */
	@Override
	protected void retrieveEntities() {
		// Checks if the last entity index is over the number of entities and
		// correct it.
		if (lastEntityIndex > entityCount)
			lastEntityIndex = (int) entityCount;

		// Checks if there's an active filter.
		if (searchedByActor) {
			entities = searchMovieByActor();
		}
		else if (filtering) {
			// There is. Retrieve not only within range, but also with
			// filtering.
			entities = webSearchMovieService.filter(filter, filterParam, firstEntityIndex, lastEntityIndex);
		} else {
			// There's not. Retrieve all entities within range.
			entities = webSearchMovieService.list(firstEntityIndex, lastEntityIndex);
		}

		// Adjusts the last entity index.
		lastEntityIndex = firstEntityIndex + entities.size();
	}

	@Override
	protected void count() {
		// Checks if there's an active filter.
		if (filtering)
			// There is. Count only filtered entities.
			entityCount = webSearchMovieService.countFiltered(filter, filterParam);
		else
			// There's not. Count all entities.
			entityCount = webSearchMovieService.count();

		// Since the entity count might have changed, force reloading of the
		// lazy entity model.
		lazyEntities = null;

		// Updates the index of the last entity and checks if it has gone over
		// the limit.
		lastEntityIndex = firstEntityIndex + MAX_DATA_TABLE_ROWS_PER_PAGE;
		if (lastEntityIndex > entityCount)
			lastEntityIndex = (int) entityCount;
	}

	public Movie getSelectedEntityWeb() {
		return selectedEntityWeb;
	}

	public void setSelectedEntityWeb(Movie selectedEntityWeb) {
		this.selectedEntityWeb = selectedEntityWeb;
	}

}

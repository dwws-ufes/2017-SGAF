package br.ufes.inf.nemo.marvin.core.controller;

import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Named;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudException;
import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.inf.nemo.jbutler.ejb.application.CrudValidationError;
import br.ufes.inf.nemo.jbutler.ejb.application.filters.SimpleFilter;
import br.ufes.inf.nemo.jbutler.ejb.controller.CrudController;
import br.ufes.inf.nemo.marvin.core.application.ManageActorsService;
import br.ufes.inf.nemo.marvin.core.application.ManageMoviesService;
import br.ufes.inf.nemo.marvin.core.domain.Actor;
import br.ufes.inf.nemo.marvin.core.domain.Movie;

@Named
@SessionScoped
public class ManageMoviesController extends CrudController<Movie> {

	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** The "Manage Movies" service. */
	@EJB
	private ManageMoviesService manageMoviesService;

	/** The "Manage Movies" service. */
	@EJB
	private ManageActorsService manageActorsService;
	
	private static final Logger logger = Logger.getLogger(InstallSystemController.class.getCanonicalName());	

	private List<Actor> selectedActors;
	
	public List<Actor> completeActor(String query) {
		 return manageActorsService.filterNameWith((new SimpleFilter("manageMovies.filter.Actor.byName", "name",
				getI18nMessage("msgsCore", "ManageMovies.text.filter.Actor.byName"))), query, 10);
	 }
	
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
	
	@Override
	protected void prepEntity() {
		logger.log(Level.INFO, "Preping entity for saving, converting list of actor to a hashSet");
		selectedEntity.setActors(new HashSet<Actor>(selectedActors));
		System.out.println("um");
		selectedActors.clear();
	}

	public List<Actor> getSelectedActors() {
		return selectedActors;
	}

	public void setSelectedActors(List<Actor> selectedActors) {
		this.selectedActors = selectedActors;
	}

	public ManageMoviesService getManageMoviesService() {
		return manageMoviesService;
	}

	public void setManageMoviesService(ManageMoviesService manageMoviesService) {
		this.manageMoviesService = manageMoviesService;
	}

	public ManageActorsService getManageActorsService() {
		return manageActorsService;
	}

	public void setManageActorsService(ManageActorsService manageActorsService) {
		this.manageActorsService = manageActorsService;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}

package br.ufes.inf.nemo.marvin.core.controller;

import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.inf.nemo.jbutler.ejb.application.filters.SimpleFilter;
import br.ufes.inf.nemo.jbutler.ejb.controller.CrudController;
import br.ufes.inf.nemo.marvin.core.application.ManageActorsService;
import br.ufes.inf.nemo.marvin.core.application.ManageDirectorsService;
import br.ufes.inf.nemo.marvin.core.application.ManageGenresService;
import br.ufes.inf.nemo.marvin.core.application.ManageMoviesService;
import br.ufes.inf.nemo.marvin.core.domain.Actor;
import br.ufes.inf.nemo.marvin.core.domain.Director;
import br.ufes.inf.nemo.marvin.core.domain.Genre;
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
	
	@EJB
	private ManageDirectorsService manageDirectorsService;
	
	@EJB
	private ManageGenresService manageGenresService;

	private static final Logger logger = Logger.getLogger(InstallSystemController.class.getCanonicalName());

	private List<Actor> selectedActors;
	private List<Director> selectedDirectors;
	private List<Genre> selectedGenres;
	private List<Genre> genres;
	
	public List<Actor> completeActor(String query) {
		return manageActorsService.filterNameWith((new SimpleFilter("manageMovies.filter.Actor.byName", "name",
				getI18nMessage("msgsCore", "ManageMovies.text.filter.Actor.byName"))), query, 10);
	}
	
	public List<Director> completeDirector(String query) {
		return manageDirectorsService.filterNameWith((new SimpleFilter("manageMovies.filter.Director.byName", "name",
				getI18nMessage("msgsCore", "ManageMovies.text.filter.Director.byName"))), query, 10);
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
		logger.log(Level.INFO, "Preping entity for saving, converting list of actors to a hashSet");
		if(selectedActors != null && selectedActors.size() > 0 ){
			selectedEntity.setActors(new HashSet<Actor>(selectedActors));
			selectedActors.clear();
		}
		
		logger.log(Level.INFO, "Preping entity for saving, converting list of directors to a hashSet");
		if(selectedDirectors != null && selectedDirectors.size() > 0 ){
			selectedEntity.setDirectors(new HashSet<Director>(selectedDirectors));
			selectedDirectors.clear();
		}
		
		logger.log(Level.INFO, "Preping entity for saving, converting list of genres to a hashSet");
		if(selectedGenres != null && selectedGenres.size() > 0 ){
			selectedEntity.setGenres(new HashSet<Genre>(selectedGenres));
			selectedGenres.clear();
		}
		
		genres = manageGenresService.allGenres();
	}

	public List<Actor> getSelectedActors() {
		return selectedActors;
	}

	public void setSelectedActors(List<Actor> selectedActors) {
		this.selectedActors = selectedActors;
	}
	
	public List<Director> getSelectedDirectors() {
		return selectedDirectors;
	}

	public void setSelectedDirectors(List<Director> selectedDirectors) {
		this.selectedDirectors = selectedDirectors;
	}
	
	public List<Genre> getSelectedGenres() {
		return selectedGenres;
	}

	public void setSelectedGenres(List<Genre> selectedGenres) {
		this.selectedGenres = selectedGenres;
	}
	
	public List<Genre> getGenres() {
		return this.genres;
	}
	
	public void setGenres(List<Genre> genres) {
		this.genres = genres;
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

package br.ufes.inf.nemo.marvin.core.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.inf.nemo.jbutler.ejb.application.filters.SimpleFilter;
import br.ufes.inf.nemo.jbutler.ejb.controller.CrudController;
import br.ufes.inf.nemo.marvin.core.application.ManageReviewsService;
import br.ufes.inf.nemo.marvin.core.application.RegUserService;
import br.ufes.inf.nemo.marvin.core.domain.Movie;
import br.ufes.inf.nemo.marvin.core.domain.Review;

@Named
@SessionScoped
public class AddReviewController extends CrudController<Review> {

	/** TODO: document this field. */
	private static final long serialVersionUID = 1L;

	/** TODO: document this field. */
	@EJB
	private ManageReviewsService manageReviewsService;

	@EJB
	private RegUserService regUserService;

	private Review newReview = new Review();

	private Movie selectedMovie = new Movie();

	/** Information on the current visitor. */
	@Inject
	private SessionController sessionController;

	private static final Logger logger = Logger.getLogger(AddReviewController.class.getCanonicalName());

	private Integer scoreStars;

	/** @see br.ufes.inf.nemo.jbutler.ejb.controller.CrudController#getCrudService() */
	@Override
	protected CrudService<Review> getCrudService() {
		return manageReviewsService;
	}

	/** @see br.ufes.inf.nemo.jbutler.ejb.controller.ListingController#initFilters() */
	@Override
	protected void initFilters() {
		addFilter(new SimpleFilter("manageReviews.filter.byName", "name",
				getI18nMessage("msgsCore", "manageReviews.text.filter.byName")));
	}

	@Override
	protected void prepEntity() {
		selectedEntity = newReview;
		logger.log(Level.INFO, "Preping entity for saving");
		selectedEntity.setScore(Double.valueOf((scoreStars.intValue() * 20)));
		scoreStars = null;
		selectedEntity.setMovie(selectedMovie);
		logger.log(Level.INFO, "Preping entity for saving, Getting logged User");
		selectedEntity.setUser(sessionController.getCurrentUser());
	}

	public Integer getScoreStars() {
		return scoreStars;
	}

	public void setScoreStars(Integer scoreStars) {
		this.scoreStars = scoreStars;
	}

	public ManageReviewsService getManageReviewsService() {
		return manageReviewsService;
	}

	public void setManageReviewsService(ManageReviewsService manageReviewsService) {
		this.manageReviewsService = manageReviewsService;
	}

	public Review getNewReview() {
		return newReview;
	}

	public void setNewReview(Review newReview) {
		this.newReview = newReview;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static Logger getLogger() {
		return logger;
	}

	public Movie getSelectedMovie() {
		return selectedMovie;
	}

	public void setSelectedMovie(Movie selectedMovie) {
		this.selectedMovie = selectedMovie;
	}

}

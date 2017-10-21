package br.ufes.inf.nemo.marvin.core.controller;

import javax.ejb.EJB;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.inf.nemo.jbutler.ejb.application.filters.SimpleFilter;
import br.ufes.inf.nemo.jbutler.ejb.controller.CrudController;
import br.ufes.inf.nemo.marvin.core.application.ManageReviewsService;
import br.ufes.inf.nemo.marvin.core.domain.Review;

public class ManageReviewsController extends CrudController<Review> {

	/** TODO: document this field. */
	private static final long serialVersionUID = 1L;

	/** TODO: document this field. */
	@EJB
	private ManageReviewsService manageReviewsService;

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

}

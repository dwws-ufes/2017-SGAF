package br.ufes.inf.nemo.marvin.core.application;

import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudServiceBean;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import br.ufes.inf.nemo.marvin.core.domain.Review;
import br.ufes.inf.nemo.marvin.core.persistence.ReviewDAO;

public class ManageReviewsServiceBean extends CrudServiceBean<Review> implements ManageReviewsService {

	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** The logger. */
	private static final Logger logger = Logger.getLogger(ManageReviewsServiceBean.class.getCanonicalName());

	/** The DAO for Movie objects. */
	@EJB
	private ReviewDAO reviewDAO;

	/** TODO: document this field. */
	@Resource
	private SessionContext sessionContext;

	/** @see br.ufes.inf.nemo.jbutler.ejb.application.ListingService#getDAO() */
	@Override
	public BaseDAO<Review> getDAO() {
		return reviewDAO;
	}
}

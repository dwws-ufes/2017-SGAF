package br.ufes.inf.nemo.marvin.core.application;

import javax.ejb.Local;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.inf.nemo.marvin.core.domain.Review;

@Local
public interface ManageReviewsService extends CrudService<Review> {

}

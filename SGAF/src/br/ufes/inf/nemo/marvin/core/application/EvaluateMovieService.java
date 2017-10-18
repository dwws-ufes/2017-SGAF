package br.ufes.inf.nemo.marvin.core.application;

import java.io.Serializable;

import javax.ejb.Local;

import br.ufes.inf.nemo.marvin.core.domain.Review;
import br.ufes.inf.nemo.marvin.core.exceptions.SystemInstallFailedException;

@Local
public interface EvaluateMovieService extends Serializable {

	public void evaluateMovie(Review review) throws SystemInstallFailedException;
}

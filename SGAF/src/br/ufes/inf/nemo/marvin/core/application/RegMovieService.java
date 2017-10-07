package br.ufes.inf.nemo.marvin.core.application;

import java.io.Serializable;

import javax.ejb.Local;

import br.ufes.inf.nemo.marvin.core.domain.Movie;
import br.ufes.inf.nemo.marvin.core.exceptions.SystemInstallFailedException;

@Local
public interface RegMovieService extends Serializable {

	public void registerMovie(Movie movie)throws SystemInstallFailedException;
}

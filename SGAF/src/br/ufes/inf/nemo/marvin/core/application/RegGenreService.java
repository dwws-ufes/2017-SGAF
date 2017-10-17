package br.ufes.inf.nemo.marvin.core.application;

import java.io.Serializable;

import br.ufes.inf.nemo.marvin.core.domain.Genre;
import br.ufes.inf.nemo.marvin.core.exceptions.SystemInstallFailedException;

public interface RegGenreService extends Serializable {

	public void registerGenre(Genre genre) throws SystemInstallFailedException;
}

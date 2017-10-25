package br.ufes.inf.nemo.marvin.core.application;

import java.util.List;

import javax.ejb.Local;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.inf.nemo.marvin.core.domain.Genre;

@Local
public interface ManageGenresService extends CrudService<Genre> {
	public List<Genre> allGenres();
}

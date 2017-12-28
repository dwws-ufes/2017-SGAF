package br.ufes.inf.nemo.marvin.core.application;

import java.util.List;

import javax.ejb.Local;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.inf.nemo.jbutler.ejb.application.filters.Filter;
import br.ufes.inf.nemo.marvin.core.domain.Movie;

@Local
public interface WebSearchMovieService extends CrudService<Movie> {

	@Override
	public List<Movie> filter(Filter<?> filter, String filterParam, int... interval);

	@Override
	public List<Movie> list(int... interval);
	
	@Override
	public long count();
	
	@Override
	public long countFiltered(Filter<?> filter, String value);

}

package br.ufes.inf.nemo.marvin.core.persistence;

import java.util.List;

import javax.ejb.Local;

import br.ufes.inf.nemo.jbutler.ejb.application.filters.Filter;
import br.ufes.inf.nemo.marvin.core.application.WebSearchLazyFilter;
import br.ufes.inf.nemo.marvin.core.domain.Movie;

@Local
public interface WebSearchMovieDAO {

	
	public List<Movie> retrieveSomeWithFilter(Filter<?> filter, String value, int[] interval);
	
	public List<Movie> retrieveSome(int[] interval);
	
	public long retrieveCount();
	
	public long retrieveFilteredCount(Filter<?> filter, String value);
	
}

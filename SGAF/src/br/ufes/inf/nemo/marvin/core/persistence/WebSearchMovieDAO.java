package br.ufes.inf.nemo.marvin.core.persistence;

import java.util.List;

import javax.ejb.Local;

import br.ufes.inf.nemo.marvin.core.application.WebSearchLazyFilter;
import br.ufes.inf.nemo.marvin.core.domain.Movie;

@Local
public interface WebSearchMovieDAO {

	public List<Movie> retrieveWithFilter(WebSearchLazyFilter filter);
	
	public int retrieveCountWithFilter(WebSearchLazyFilter filter);
	
}
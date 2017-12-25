package br.ufes.inf.nemo.marvin.core.application;

import java.util.List;

import javax.ejb.Local;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.inf.nemo.marvin.core.domain.Movie;

@Local
public interface WebSearchMovieService extends CrudService<Movie>  {

	public List<Movie> retrieveWithFilter(WebSearchLazyFilter filter);
	
	public int retrieveCountWithFilter(WebSearchLazyFilter filter);
	
}

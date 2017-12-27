package br.ufes.inf.nemo.marvin.core.persistence;

import java.util.List;

import javax.ejb.Local;

import br.ufes.inf.nemo.jbutler.ejb.application.filters.Filter;
import br.ufes.inf.nemo.marvin.core.domain.Actor;

@Local
public interface WebSearchActorDAO {
	
	public List<Actor> retrieveSomeWithFilter(Filter<?> filter, String value, int[] interval);
	
	public List<Actor> retrieveSome(int[] interval);
}

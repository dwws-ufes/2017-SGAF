package br.ufes.inf.nemo.marvin.core.persistence;

import java.util.List;

import javax.ejb.Local;

import br.ufes.inf.nemo.jbutler.ejb.application.filters.Filter;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import br.ufes.inf.nemo.marvin.core.domain.Actor;

@Local
public interface ActorDAO extends BaseDAO<Actor> {
	
	public List<Actor> filterNameWith(Filter<?> filter, String value, int MaxResults);
	
}

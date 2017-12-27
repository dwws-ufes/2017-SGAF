package br.ufes.inf.nemo.marvin.core.application;

import java.util.List;

import javax.ejb.Local;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.inf.nemo.jbutler.ejb.application.filters.Filter;
import br.ufes.inf.nemo.marvin.core.domain.Actor;

@Local
public interface WebSearchActorService extends CrudService<Actor> {
	@Override
	public List<Actor> filter(Filter<?> filter, String filterParam, int... interval);
	
	@Override
	public List<Actor> list(int... interval);
}

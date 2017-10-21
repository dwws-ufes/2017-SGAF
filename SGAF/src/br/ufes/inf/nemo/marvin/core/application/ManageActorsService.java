package br.ufes.inf.nemo.marvin.core.application;

import java.util.List;

import javax.ejb.Local;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.inf.nemo.jbutler.ejb.application.filters.Filter;
import br.ufes.inf.nemo.marvin.core.domain.Actor;

@Local
public interface ManageActorsService extends CrudService<Actor> {

	public List<Actor> filterNameWith(Filter<?> filter, String value, int MaxResults);
	
}

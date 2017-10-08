package br.ufes.inf.nemo.marvin.core.persistence;

import javax.ejb.Local;

import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import br.ufes.inf.nemo.marvin.core.domain.Actor;

@Local
public interface ActorDAO extends BaseDAO<Actor> {

}

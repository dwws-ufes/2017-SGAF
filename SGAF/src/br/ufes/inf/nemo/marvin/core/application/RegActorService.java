package br.ufes.inf.nemo.marvin.core.application;

import java.io.Serializable;

import javax.ejb.Local;

import br.ufes.inf.nemo.marvin.core.domain.Actor;
import br.ufes.inf.nemo.marvin.core.exceptions.SystemInstallFailedException;

@Local
public interface RegActorService extends Serializable {
	public void registerActor(Actor actor) throws SystemInstallFailedException;
}

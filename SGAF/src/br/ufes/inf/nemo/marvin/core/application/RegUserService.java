package br.ufes.inf.nemo.marvin.core.application;

import java.io.Serializable;

import javax.ejb.Local;

import br.ufes.inf.nemo.marvin.core.domain.User;
import br.ufes.inf.nemo.marvin.core.exceptions.SystemInstallFailedException;

@Local
public interface RegUserService extends Serializable {
	public void registerUser(User user) throws SystemInstallFailedException;
}

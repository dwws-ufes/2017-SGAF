package br.ufes.inf.nemo.marvin.core.persistence;

import java.util.List;

import javax.ejb.Local;

import br.ufes.inf.nemo.marvin.core.domain.Role;

@Local
public interface RoleDAO {

	public void save(Role role);
	
	public void delete(Role role);
	
	public List<Role> retrieveAll();
	
	public Role retrieveById(String id);
	
}

package br.ufes.inf.nemo.marvin.core.domain;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;

@Entity
public class UserRole extends PersistentObjectSupport implements Comparable<UserRole> {

	/** Serialization id. */
	private static final long serialVersionUID = 1L;
	
	/** The genre's name. */
	@Basic
	@NotNull
	@Size(max = 100)
	protected String role;
	
	@ManyToOne
	@NotNull
	private User user;
	
	/** @see java.lang.Comparable#compareTo(java.lang.Object) */
	@Override
	public int compareTo(UserRole o) {
		// Compare the genres names
		if (role == null)
			return 1;
		if (o.role == null)
			return -1;
		int cmp = role.compareTo(o.role);
		if (cmp != 0)
			return cmp;

		// If it's the same name, check if it's the same entity.
		return uuid.compareTo(o.uuid);
	}
	
}

package br.ufes.inf.nemo.marvin.core.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class Role implements Comparable<Role> {

	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** The Roles's name. */
	@Id
	@Size(max = 100)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int compareTo(Role o) {
		// Compare the persons' names
		if (name == null)
			return 1;
		if (o.name == null)
			return -1;
		int cmp = name.compareTo(o.name);
		return cmp;

	}

	public Role(String name) {
		this.name = name;
	}

	public Role() {

	}

}

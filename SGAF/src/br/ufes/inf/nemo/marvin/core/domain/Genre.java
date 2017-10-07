package br.ufes.inf.nemo.marvin.core.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;

/**
 * Domain class that represents a genre of movies.
 * 
 * <i>This class is part of the Engenho de Software "Legal Entity" mini
 * framework for EJB3 (Java EE 6).</i>
 * 
 * @author Vitor E. Silva Souza (vitorsouza@gmail.com)
 */
@Entity
public class Genre extends PersistentObjectSupport implements Comparable<Genre> {

	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** The genre's name. */
	@Basic
	@NotNull
	@Size(max = 100)
	protected String name;

	/** Description of the genre of movies. */
	@Basic
	@NotNull
	@Size(max = 300)
	protected String description;

	/** The timestamp of the moment this genre was created. */
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;

	@ManyToMany(fetch = FetchType.LAZY)
	private Set<Movie> movies;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	/** @see java.lang.Comparable#compareTo(java.lang.Object) */
	@Override
	public int compareTo(Genre o) {
		// Compare the persons' names
		if (name == null)
			return 1;
		if (o.name == null)
			return -1;
		int cmp = name.compareTo(o.name);
		if (cmp != 0)
			return cmp;

		// If it's the same name, check if it's the same entity.
		return uuid.compareTo(o.uuid);
	}

}

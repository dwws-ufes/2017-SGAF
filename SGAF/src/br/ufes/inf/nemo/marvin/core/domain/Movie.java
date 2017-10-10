package br.ufes.inf.nemo.marvin.core.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;

/**
 * What is a movie anyways...
 * 
 * @author Rodolfo
 *
 */
@Entity
public class Movie extends PersistentObjectSupport implements Comparable<Movie> {

	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** The Movie's title. */
	@Basic
	@NotNull
	@Size(max = 100)
	private String title;

	/** The Movie's length. */
	@Basic
	@NotNull
	private Long length;

	/** The Movie's launch date. */
	@Temporal(TemporalType.DATE)
	private Date launchDate;
	
	/** The Movie's Register date. */
	@Temporal(TemporalType.DATE)
	private Date registerDate;

	/** The Movie's synopsis. */
	@Basic
	@NotNull
	@Size(max = 400)
	private String synopsis;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "movie")
	private Set<Review> reviews;

	@ManyToMany(fetch = FetchType.LAZY)
	private Set<Genre> genres;

	@ManyToMany(fetch = FetchType.LAZY)
	private Set<Director> directors;

	@ManyToMany(fetch = FetchType.LAZY)
	private Set<Actor> actors;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getLength() {
		return length;
	}

	public void setLength(Long length) {
		this.length = length;
	}

	public Date getLaunchDate() {
		return launchDate;
	}

	public void setLaunchDate(Date launchDate) {
		this.launchDate = launchDate;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	/** @see java.lang.Comparable#compareTo(java.lang.Object) */
	@Override
	public int compareTo(Movie o) {
		// Compare the persons' names
		if (title == null)
			return 1;
		if (o.title == null)
			return -1;
		int cmp = title.compareTo(o.title);
		if (cmp != 0)
			return cmp;

		// If it's the same name, check if it's the same entity.
		return uuid.compareTo(o.uuid);
	}

	/** @see br.ufes.inf.nemo.util.ejb3.persistence.PersistentObjectSupport#toString() */
	@Override
	public String toString() {
		return title;
	}
}

package br.ufes.inf.nemo.marvin.core.domain;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;

/**
 * The analysis of a movie by a user.
 * 
 * @author Rodolfo
 *
 */
@Entity
public class Review extends PersistentObjectSupport implements Comparable<Review> {

	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** The score of the user to a Movie. */
	@Basic
	@NotNull
	protected Double score;

	/** Analysis of the User to a Movie. **/
	@Basic
	@NotNull
	@Size(max = 400)
	protected String analysis;
	
	@ManyToOne
	@NotNull
	private User user;
	
	@ManyToOne
	@NotNull
	private Movie movie;

	public String getAnalysis() {
		return analysis;
	}

	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	/** @see java.lang.Comparable#compareTo(java.lang.Object) */
	@Override
	public int compareTo(Review o) {

		// If it's the same name, check if it's the same entity.
		return uuid.compareTo(o.uuid);
	}

}

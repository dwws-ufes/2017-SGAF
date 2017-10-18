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
	protected double score;

	/** Analysis of the User to a Movie. **/
	@Basic
	@NotNull
	@Size(max = 100)
	protected String analysis;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Movie movie;
	
	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public String getAnalysis() {
		return analysis;
	}



	public void setAnalysis(String analysis) {
		this.analysis = analysis;
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

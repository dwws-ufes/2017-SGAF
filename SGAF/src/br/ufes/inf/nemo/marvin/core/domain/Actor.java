package br.ufes.inf.nemo.marvin.core.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Domain class that represents a crew member specialization as a Actor.
 * 
 * @author Rodolfo
 */
@Entity
@DiscriminatorValue("A")
public class Actor extends CrewMember {
	/** Serialization id. */
	private static final long serialVersionUID = 1L;

}

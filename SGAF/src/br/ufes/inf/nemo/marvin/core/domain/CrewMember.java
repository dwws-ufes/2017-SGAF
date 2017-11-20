package br.ufes.inf.nemo.marvin.core.domain;

import javax.persistence.MappedSuperclass;

import br.ufes.inf.nemo.marvin.people.domain.Person;

/**
 * Domain class that represents a crew member of a movie and their most basic
 * attributes, nationality.
 * 
 * @author Rodolfo
 */
@MappedSuperclass
public class CrewMember extends Person {
	/** Serialization id. */
	private static final long serialVersionUID = 1L;

}

package br.ufes.inf.nemo.marvin.core.domain;

import java.util.Set;

import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;

import br.ufes.inf.nemo.marvin.people.domain.Person;

/**
 * Domain class that represents a crew member of a movie and their most basic attributes, nationality.
 * 
 * @author Rodolfo
 */
@MappedSuperclass
public class CrewMember extends Person {
	/** Serialization id. */
	private static final long serialVersionUID = 1L;
	
	@ManyToMany(fetch = FetchType.LAZY)
	private Set<Movie> movies;
	
}

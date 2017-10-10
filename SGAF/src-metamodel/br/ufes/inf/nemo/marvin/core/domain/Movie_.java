package br.ufes.inf.nemo.marvin.core.domain;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-10-09T22:27:32.684-0300")
@StaticMetamodel(Movie.class)
public class Movie_ {
	public static volatile SingularAttribute<Movie, String> title;
	public static volatile SingularAttribute<Movie, Long> length;
	public static volatile SingularAttribute<Movie, Date> launchDate;
	public static volatile SingularAttribute<Movie, Date> registerDate;
	public static volatile SingularAttribute<Movie, String> synopsis;
	public static volatile SetAttribute<Movie, Review> reviews;
	public static volatile SetAttribute<Movie, Genre> genres;
	public static volatile SetAttribute<Movie, Director> directors;
	public static volatile SetAttribute<Movie, Actor> actors;
}

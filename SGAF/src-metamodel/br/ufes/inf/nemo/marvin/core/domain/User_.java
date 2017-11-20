package br.ufes.inf.nemo.marvin.core.domain;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import br.ufes.inf.nemo.marvin.people.domain.Person_;

@Generated(value = "Dali", date = "2017-10-08T11:07:32.684-0300")
@StaticMetamodel(User.class)
public class User_ extends Person_ {

	public static volatile SingularAttribute<User, String> shortName;
	public static volatile SingularAttribute<User, String> email;
	public static volatile SingularAttribute<User, String> password;
	public static volatile SetAttribute<User, Review> reviews;
	public static volatile SingularAttribute<User, Date> creationDate;
	public static volatile SingularAttribute<User, Date> lastUpdateDate;
	public static volatile SingularAttribute<User, Date> lastLoginDate;

}

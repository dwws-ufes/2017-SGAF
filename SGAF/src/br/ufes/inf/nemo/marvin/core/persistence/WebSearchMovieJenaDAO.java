package br.ufes.inf.nemo.marvin.core.persistence;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;

import br.ufes.inf.nemo.jbutler.ejb.application.filters.Filter;
import br.ufes.inf.nemo.marvin.core.domain.Movie;

@Stateless
public class WebSearchMovieJenaDAO implements WebSearchMovieDAO, Serializable {

	private static final long serialVersionUID = 1L;

	public List<Movie> retrieveSomeWithFilter(Filter<?> filter, String value, int[] interval) {

		List<Movie> resultList = new ArrayList<Movie>();

		String queryFilteredTitle = "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\r\n" + //
				"PREFIX movie: <http://data.linkedmdb.org/resource/movie/>\r\n" + //
				"\r\n" + //
				"\r\n" + //
				"SELECT DISTINCT ?film ?filmTitle ?runtime ?initial_release_date WHERE {\r\n" + //
				"  ?film a movie:film;\r\n" + //
				"          rdfs:label ?filmTitle;\r\n" + //
				"          rdfs:label \"" + value + "\";\r\n" + //
				"          movie:runtime ?runtime;\r\n" + //
				"          movie:initial_release_date ?initial_release_date\r\n" + //
				"}\r\n" + //
				"ORDER BY ?filmTitle\r\n" + //
				"LIMIT " + (interval[1] - interval[0] + 1) + //
				"OFFSET " + interval[0];
		
		String queryFilteredActor = "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\r\n" + //
				"PREFIX movie: <http://data.linkedmdb.org/resource/movie/>\r\n" + //
				"\r\n" + //
				"\r\n" + //
				"SELECT DISTINCT ?film ?filmTitle ?runtime ?initial_release_date WHERE {\r\n" + //
				"?actor a movie:actor;\r\n" + //
				"           movie:actor_name  \"" + value + "\";\r\n" + //
				"           movie:actor_name ?actorName." + //
				"  ?film a movie:film;\r\n" + //
				"          rdfs:label ?filmTitle;\r\n" + //
				"          movie:runtime ?runtime;\r\n" + //
				"          movie:actor ?actor;\r\n" + //
				"          movie:initial_release_date ?initial_release_date\r\n" + //
				"}\r\n" + //
				"ORDER BY ?filmTitle\r\n" + //
				"LIMIT " + (interval[1] - interval[0] + 1) + //
				"OFFSET " + interval[0];

		String query = "";
		if(filter.getFieldName().compareTo("title")==0){
			query =  queryFilteredTitle;
		}else{
			query = queryFilteredActor;
		}
		
		QueryExecution queryExecution = QueryExecutionFactory.sparqlService("http://data.linkedmdb.org/sparql", query);
		ResultSet results = queryExecution.execSelect();

		while (results.hasNext()) {
			Movie e = new Movie();
			QuerySolution querySolution = results.next();
			e.setTitle(querySolution.getLiteral("filmTitle").toString());
			e.setLength(new Long(querySolution.getLiteral("runtime").toString()));
//			e.setLaunchDate(new Date());
			/**falta entender como parsear a data**/
			try {
				e.setLaunchDate(readDate(querySolution.getLiteral("initial_release_date").getString()));
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			resultList.add(e);
		}
		
		queryExecution.close();
		return resultList;
	}

	public List<Movie> retrieveSome(int[] interval) {
		List<Movie> resultList = new ArrayList<Movie>();

		String query = "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\r\n" + //
				"PREFIX movie: <http://data.linkedmdb.org/resource/movie/>\r\n" + //
				"\r\n" + //
				"\r\n" + //
				"SELECT DISTINCT ?film ?filmTitle ?runtime ?initial_release_date WHERE {\r\n" + //
				"  ?film a movie:film;\r\n" + //
				"          rdfs:label ?filmTitle;\r\n" + //
				"          movie:runtime ?runtime;\r\n" + //
				"          movie:initial_release_date ?initial_release_date\r\n" + //
				"}\r\n" + //
				"ORDER BY ?filmTitle\r\n" + //
				"LIMIT " + (interval[1] - interval[0] + 1) + //
				"OFFSET " + interval[0];

		QueryExecution queryExecution = QueryExecutionFactory.sparqlService("http://data.linkedmdb.org/sparql", query);
		ResultSet results = queryExecution.execSelect();

		while (results.hasNext()) {
			Movie e = new Movie();
			QuerySolution querySolution = results.next();
			e.setTitle(querySolution.getLiteral("filmTitle").toString());
			e.setLength(new Long(querySolution.getLiteral("runtime").toString()));
//			e.setLaunchDate(new Date());
			/**falta entender como parsear a data**/
			try {
				e.setLaunchDate(readDate(querySolution.getLiteral("initial_release_date").getString()));
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			resultList.add(e);
		}
		
		queryExecution.close();
		return resultList;
	}

	public long retrieveFilteredCount(Filter<?> filter, String value) {

		String queryFilteredTitle = "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\r\n" + //
				"PREFIX movie: <http://data.linkedmdb.org/resource/movie/>\r\n" + //
				"\r\n" + //
				"\r\n" + //
				"SELECT (COUNT(?film) as ?count) WHERE {\r\n" + //
				"  ?film a movie:film;\r\n" + //
				"          rdfs:label ?filmTitle;\r\n" + //
				"          rdfs:label \"" + value + "\";\r\n" + //
				"          movie:runtime ?runtime;\r\n" + //
				"          movie:initial_release_date ?initial_release_date\r\n" + //
				"}";

		String queryFilteredActor = "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\r\n" + //
				"PREFIX movie: <http://data.linkedmdb.org/resource/movie/>\r\n" + //
				"\r\n" + //
				"\r\n" + //
				"SELECT (COUNT(?film) as ?count) WHERE {\r\n" + //
				"?actor a movie:actor;\r\n" + //
				"           movie:actor_name  \"" + value + "\";\r\n" + //
				"           movie:actor_name ?actorName." + //
				"  ?film a movie:film;\r\n" + //
				"          rdfs:label ?filmTitle;\r\n" + //
				"          movie:runtime ?runtime;\r\n" + //
				"          movie:actor ?actor;\r\n" + //
				"          movie:initial_release_date ?initial_release_date\r\n" + //
				"}";
		String query = "";
		if(filter.getFieldName().compareTo("title")==0){
			query =  queryFilteredTitle;
		}else{
			query = queryFilteredActor;
		}
		QueryExecution queryExecution = QueryExecutionFactory.sparqlService("http://data.linkedmdb.org/sparql", query);
		ResultSet results = queryExecution.execSelect();

		QuerySolution querySolution = results.next();
		String number = querySolution.getLiteral("count").getString();
		queryExecution.close();
		return Long.valueOf(number);

	}

	public long retrieveCount() {

		String query = "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\r\n" + //
				"PREFIX movie: <http://data.linkedmdb.org/resource/movie/>\r\n" + //
				"\r\n" + //
				"\r\n" + //
				"SELECT (COUNT(?film) as ?count) WHERE {\r\n" + //
				"  ?film a movie:film;\r\n" + //
				"          rdfs:label ?filmTitle;\r\n" + //
				"          movie:runtime ?runtime;\r\n" + //
				"          movie:initial_release_date ?initial_release_date\r\n" + //
				"}";

		QueryExecution queryExecution = QueryExecutionFactory.sparqlService("http://data.linkedmdb.org/sparql", query);
		ResultSet results = queryExecution.execSelect();

		QuerySolution querySolution = results.next();
		String number = querySolution.getLiteral("count").getString();
		queryExecution.close();
		return Long.valueOf(number);

	}

	public static Date readDate(String dateStr) throws ParseException {
		/* pega a 1� data, caso duas data forem cadastradas */
		dateStr = dateStr.split(",")[0];
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date d = format.parse(dateStr);

		return d;
	}

}

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

		String query = "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\r\n"
				+ "PREFIX movie: <http://data.linkedmdb.org/resource/movie/>\r\n" + "\r\n" + "\r\n"
				+ "SELECT DISTINCT ?filmTitle WHERE {\r\n" + "  ?film a movie:film;\r\n"
				+ "          rdfs:label ?filmTitle\r\n" + "} LIMIT 100";

		QueryExecution queryExecution = QueryExecutionFactory.sparqlService("http://data.linkedmdb.org/sparql", query);
		ResultSet results = queryExecution.execSelect();

		while (results.hasNext()) {
			Movie e = new Movie();
			QuerySolution querySolution = results.next();
			e.setTitle(querySolution.getLiteral("filmTitle").toString());
			resultList.add(e);
		}

		return resultList;

		// // Builds the filtered query.
		// EntityManager em = getEntityManager();
		// CriteriaQuery<Movie> cq = buildFilteredCriteriaQuery(filter, value);
		//
		// // Determine the interval to retrieve and return the result.
		// TypedQuery<Movie> q = em.createQuery(cq);
		// q.setMaxResults(interval[1] - interval[0]);
		// q.setFirstResult(interval[0]);
		// List<Movie> result = q.getResultList();
		// return result;
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
				"LIMIT 100";

		QueryExecution queryExecution = QueryExecutionFactory.sparqlService("http://data.linkedmdb.org/sparql", query);
		ResultSet results = queryExecution.execSelect();

		while (results.hasNext()) {
			Movie e = new Movie();
			QuerySolution querySolution = results.next();
			e.setTitle(querySolution.getLiteral("filmTitle").toString());
			e.setLength(new Long(querySolution.getLiteral("runtime").toString()));
			e.setLaunchDate(new Date());
			/**falta entender como parsear a data**/
//			try {
//				e.setLaunchDate(readDate(querySolution.getLiteral("initial_release_date").getString()));
//			} catch (ParseException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}

			resultList.add(e);
		}

		return resultList;
	}

	public long retrieveFilteredCount(Filter<?> filter, String value) {

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
		return Long.valueOf(number);

	}

	public static Date readDate(String dateStr) throws ParseException {
		/* pega a 1º data, caso duas data forem cadastradas */
		dateStr = dateStr.split(",")[0];
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date d = format.parse(dateStr);

		return d;
	}

}

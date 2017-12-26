package br.ufes.inf.nemo.marvin.core.persistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;

import br.ufes.inf.nemo.jbutler.ejb.application.filters.Filter;
import br.ufes.inf.nemo.marvin.core.application.WebSearchLazyFilter;
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
	}

	public int retrieveCountWithFilter(WebSearchLazyFilter filter) {
		return 0;
	}

}

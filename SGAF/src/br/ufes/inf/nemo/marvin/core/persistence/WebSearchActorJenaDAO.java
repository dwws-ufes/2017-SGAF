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
import br.ufes.inf.nemo.marvin.core.domain.Actor;

@Stateless
public class WebSearchActorJenaDAO implements WebSearchActorDAO, Serializable {

	private static final long serialVersionUID = 1L;
	
	@Override
	public List<Actor> retrieveSomeWithFilter(Filter<?> filter, String value, int[] interval) {
		List<Actor> resultList = new ArrayList<Actor>();

		String query = "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
				+ "PREFIX movie: <http://data.linkedmdb.org/resource/movie/>\n" + "\n" + "\n"
				+ "SELECT DISTINCT ?actorName WHERE {\n" + "  ?actor a movie:actor;\n"
				+ "          movie:actor_name ?actorName\n" + "} LIMIT 100";

		QueryExecution queryExecution = QueryExecutionFactory.sparqlService("http://data.linkedmdb.org/sparql", query);
		ResultSet results = queryExecution.execSelect();

		while (results.hasNext()) {
			Actor e = new Actor();
			QuerySolution querySolution = results.next();
			e.setName(querySolution.getLiteral("actorName").toString());
			resultList.add(e);
		}

		return resultList;

		// // Builds the filtered query.
		// EntityManager em = getEntityManager();
		// CriteriaQuery<Actor> cq = buildFilteredCriteriaQuery(filter, value);
		//
		// // Determine the interval to retrieve and return the result.
		// TypedQuery<Actor> q = em.createQuery(cq);
		// q.setMaxResults(interval[1] - interval[0]);
		// q.setFirstResult(interval[0]);
		// List<Actor> result = q.getResultList();
		// return result;
	}

	@Override
	public List<Actor> retrieveSome(int[] interval) {
		List<Actor> resultList = new ArrayList<Actor>();

		String query = "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
				+ "PREFIX movie: <http://data.linkedmdb.org/resource/movie/>\n" + "\n" + "\n"
				+ "SELECT DISTINCT ?actorName WHERE {\n" + "  ?actor a movie:actor;\n"
				+ "          movie:actor_name ?actorName\n" + "} LIMIT 100";

		QueryExecution queryExecution = QueryExecutionFactory.sparqlService("http://data.linkedmdb.org/sparql", query);
		ResultSet results = queryExecution.execSelect();

		while (results.hasNext()) {
			Actor e = new Actor();
			QuerySolution querySolution = results.next();
			e.setName(querySolution.getLiteral("actorName").toString());
			resultList.add(e);
		}

		return resultList;
	}
}

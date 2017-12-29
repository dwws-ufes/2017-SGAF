package br.inf.ufes.nemo.marvin.rdf.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.ResourceFactory;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;

import br.ufes.inf.nemo.marvin.core.application.ManageMoviesService;
import br.ufes.inf.nemo.marvin.core.domain.Movie;

@WebServlet(urlPatterns = { "/data/movies" })
public class ListMoviesRdfServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/** The "Manage Movies" service. */
	@EJB
	private ManageMoviesService manageMoviesService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/xml");
		List<Movie> movies = manageMoviesService.getDAO().retrieveAll();

		Model model = ModelFactory.createDefaultModel();
		String myNS = "http://localhost:8080/SGAF-1.2.4/data/movie/";
		String movieNs = "http://data.linkedmdb.org/resource/film/";
		String dcNs = "http://purl.org/dc/terms/";
		model.setNsPrefix("movie", movieNs);
		model.setNsPrefix("dc", dcNs);
		model.setNsPrefix("sgaf", myNS);

		Property filmTitle = ResourceFactory.createProperty(dcNs, "title");
		Property runtime = ResourceFactory.createProperty(movieNs, "runtime");
		Property initial_release_date = ResourceFactory.createProperty(movieNs, "initial_release_date");
		Property synopsis = ResourceFactory.createProperty(myNS, "synopsis");

		for (Movie temp : movies) {

			Instant instant = Instant.ofEpochMilli(temp.getLaunchDate().getTime());
			LocalDateTime ldt = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
			String strDate = "" + ldt.getYear() + "-" + ldt.getMonthValue() + "-" + ldt.getDayOfMonth();

			model.createResource(myNS + temp.getId())//
					.addProperty(RDF.type, "movie:film")//
					.addProperty(RDFS.label, temp.getTitle())//
					.addProperty(filmTitle, temp.getTitle())//
					.addProperty(runtime, temp.getLength().toString())//
					.addProperty(initial_release_date, strDate)//
					.addProperty(synopsis, temp.getSynopsis())//
			;
		}

		try (PrintWriter out = resp.getWriter()) {
			model.write(out, "RDF/XML");
		}

	}

}

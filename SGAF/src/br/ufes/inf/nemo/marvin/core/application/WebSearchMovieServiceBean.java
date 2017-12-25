package br.ufes.inf.nemo.marvin.core.application;

import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import org.primefaces.model.LazyDataModel;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudServiceBean;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import br.ufes.inf.nemo.marvin.core.domain.Movie;
import br.ufes.inf.nemo.marvin.core.persistence.MovieDAO;
import br.ufes.inf.nemo.marvin.core.persistence.WebSearchMovieDAO;

@Stateless
@PermitAll
public class WebSearchMovieServiceBean extends CrudServiceBean<Movie> implements WebSearchMovieService {

	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** The logger. */
	private static final Logger logger = Logger.getLogger(InstallSystemServiceBean.class.getCanonicalName());

	/** The DAO for Movie objects. */
	@EJB
	private MovieDAO movieDAO;

	/** The DAO for Web Search of Movie Objects. */
	@EJB
	private WebSearchMovieDAO webSearchMovieDAO;

	private WebSearchLazyFilter filter = new WebSearchLazyFilter();

	private LazyDataModel<Movie> model;

	/** TODO: document this field. */
	@Resource
	private SessionContext sessionContext;

	/** @see br.ufes.inf.nemo.jbutler.ejb.application.ListingService#getDAO() */
	@Override
	public BaseDAO<Movie> getDAO() {
		return movieDAO;
	}

	public List<Movie> retrieveWithFilter(WebSearchLazyFilter filter) {
		return webSearchMovieDAO.retrieveWithFilter(filter);
	}

	public int retrieveCountWithFilter(WebSearchLazyFilter filter) {
		return webSearchMovieDAO.retrieveCountWithFilter(filter);
	}

	// public WebSearchMovieServiceBean() {
	// model = new LazyDataModel<Movie>() {
	// private static final long serialVersionUID = 1L;
	//
	// @Override
	// public List<Movie> load(int first, int pageSize,
	// String sortField, SortOrder sortOrder,
	// Map<String, Object> filters) {
	//
	// filter.setPrimeiroRegistro(first);
	// filter.setQuantidadeRegistros(pageSize);
	// filter.setAscendente(SortOrder.ASCENDING.equals(sortOrder));
	// filter.setPropriedadeOrdenacao(sortField);
	//
	// setRowCount(webSearchMovieDAO.retrieveCountWithFilter(filter));
	//
	// return webSearchMovieDAO.retrieveWithFilter(filter);
	// }
	//
	//
	// };
	// }

}

package br.ufes.inf.nemo.marvin.core.application;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import org.primefaces.model.LazyDataModel;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudOperation;
import br.ufes.inf.nemo.jbutler.ejb.application.CrudServiceBean;
import br.ufes.inf.nemo.jbutler.ejb.application.filters.Filter;
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

	/** TODO: document this field. */
	@Resource
	private SessionContext sessionContext;

	/** @see br.ufes.inf.nemo.jbutler.ejb.application.ListingService#getDAO() */
	@Override
	public BaseDAO<Movie> getDAO() {
		return movieDAO;
	}

	@Override
	public List<Movie> filter(Filter<?> filter, String filterParam, int ... interval) {
		List<Movie> entities = webSearchMovieDAO.retrieveSomeWithFilter(filter, filterParam, interval);
		return entities;
	}
	
	@Override
	public List<Movie> list(int ... interval) {
		List<Movie> entities = webSearchMovieDAO.retrieveSome(interval);
		return entities;
	}
	
	@Override
	public long count() {
		return webSearchMovieDAO.retrieveCount();
	}
	
	@Override
	public long countFiltered(Filter<?> filter, String value) {
		return webSearchMovieDAO.retrieveFilteredCount(filter, value);
	}

}

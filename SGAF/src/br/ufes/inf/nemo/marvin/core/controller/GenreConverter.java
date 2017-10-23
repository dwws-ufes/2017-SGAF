package br.ufes.inf.nemo.marvin.core.controller;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import br.ufes.inf.nemo.marvin.core.application.ManageGenresService;
import br.ufes.inf.nemo.marvin.core.domain.Genre;

@ManagedBean
@RequestScoped
public class GenreConverter implements Converter {
	
	@EJB
	private ManageGenresService manageGenresService;

	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		if (value != null && value.trim().length() > 0) {
			try {
				return manageGenresService.retrieve(Long.valueOf(value));
			} catch (NumberFormatException e) {
				throw new ConverterException(
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid genre id."));
			}
		} else {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object object) {
		return String.valueOf(((Genre) object).getId());
	}

}

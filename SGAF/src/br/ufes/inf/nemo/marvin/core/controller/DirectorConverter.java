package br.ufes.inf.nemo.marvin.core.controller;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import br.ufes.inf.nemo.marvin.core.application.ManageDirectorsService;
import br.ufes.inf.nemo.marvin.core.domain.Director;

@ManagedBean
@RequestScoped
public class DirectorConverter implements Converter {

	@EJB
	private ManageDirectorsService manageDirectorsService;
	
	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		if (value != null && value.trim().length() > 0) {
			try {
				return manageDirectorsService.retrieve(Long.valueOf(value));
			} catch (NumberFormatException e) {
				throw new ConverterException(
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid director id."));
			}
		} else {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object object) {
		return String.valueOf(((Director) object).getId());
	}

}

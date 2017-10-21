package br.ufes.inf.nemo.marvin.core.controller;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import br.ufes.inf.nemo.marvin.core.application.ManageActorsService;
import br.ufes.inf.nemo.marvin.core.domain.Actor;

@ManagedBean
@RequestScoped
public class ActorConverter implements Converter {

	@EJB
	ManageActorsService manageActorsService;
	
	@Override
	public Object getAsObject(FacesContext fc, UIComponent arg1, String value) {
		if (value != null && value.trim().length() > 0) {
			try {
				return manageActorsService.retrieve(Long.valueOf(value));
			} catch (NumberFormatException e) {
				throw new ConverterException(
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid actor id."));
			}
		} else {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object object) {
		return String.valueOf(((Actor) object).getId());
	}

}

package com.rbx.nhm.web.converters;

import javax.enterprise.context.Dependent;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;


import com.rbx.nhm.web.entities.Township;
import com.rbx.nhm.web.repositories.TownshipRepository;

@Named
@Dependent
public class TownshipConverter implements Converter<Township> {
	@Inject 
	private TownshipRepository townshipRepository;

	@Override
	public Township getAsObject(FacesContext context, UIComponent component, String value) {
		return value == null ? null : townshipRepository.findByIdWithErase(value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Township value) {
		return value == null ? null : ((Township) value).getId();
	}

}

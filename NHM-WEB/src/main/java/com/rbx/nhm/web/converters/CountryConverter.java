package com.rbx.nhm.web.converters;

import javax.enterprise.context.Dependent;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import com.rbx.nhm.web.entities.Country;
import com.rbx.nhm.web.repositories.CountryRepository;

@Named
@Dependent
public class CountryConverter implements Converter<Country> {

	@Inject
	private CountryRepository countryRepository;
	
	@Override
	public Country getAsObject(FacesContext context, UIComponent component, String value) {
		return value == null ? null : countryRepository.findByIdWithErase(value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Country value) {
		return value == null ? null : ((Country) value).getId();
	}

}

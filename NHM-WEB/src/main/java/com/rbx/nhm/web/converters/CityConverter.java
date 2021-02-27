package com.rbx.nhm.web.converters;

import javax.enterprise.context.Dependent;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import com.rbx.nhm.web.entities.City;
import com.rbx.nhm.web.repositories.CityRepository;
@Named
@Dependent
public class CityConverter implements Converter<City> {
	@Inject
	private CityRepository cityRepository;

	@Override
	public City getAsObject(FacesContext context, UIComponent component, String value) {
		return value == null ? null : cityRepository.findByIdWithErase(value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, City value) {
		return value == null ? null : ((City) value).getId();
	}

}

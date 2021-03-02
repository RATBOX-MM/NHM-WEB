package com.rbx.nhm.web.converters;

import javax.enterprise.context.Dependent;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import com.rbx.nhm.web.entities.Address;

import com.rbx.nhm.web.repositories.AddressRepository;

@Named
@Dependent
public class AddressConverter implements Converter<Address> {
	
	@Inject
	private AddressRepository addressRepository;
	
	@Override
	public Address getAsObject(FacesContext context, UIComponent component, String value) {
		return value == null ? null : addressRepository.findByIdWithErase(value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Address value) {
		return value == null ? null : ((Address) value).getId();
	}

}

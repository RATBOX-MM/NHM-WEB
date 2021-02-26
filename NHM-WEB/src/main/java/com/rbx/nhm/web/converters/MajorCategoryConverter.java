package com.rbx.nhm.web.converters;

import javax.enterprise.context.Dependent;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import com.rbx.nhm.web.entities.MajorCategory;
import com.rbx.nhm.web.repositories.MajorCategoryRepository;


@Named
@Dependent
public class MajorCategoryConverter implements Converter<MajorCategory> {

	@Inject
	private MajorCategoryRepository majorCategoryRepository;
	
	
	@Override
	public MajorCategory getAsObject(FacesContext context, UIComponent component, String value) {
		
		return value==null ? null :  majorCategoryRepository.findByIdWithErase(value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, MajorCategory value) {
		
		return value ==null ? null:  ((MajorCategory)value).getId();
	}
 
}

package com.rbx.nhm.web.converters;

import javax.enterprise.context.Dependent;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import com.rbx.nhm.web.entities.MinorCategory;
import com.rbx.nhm.web.repositories.MinorCategoryRepository;

@Named
@Dependent
public class MinorCategoryConverter implements Converter<MinorCategory> {
	
	
	@Inject
	private MinorCategoryRepository minorCategoryRespository;

	@Override
	public MinorCategory getAsObject(FacesContext context, UIComponent component, String value) {
		
		return value==null ? null : minorCategoryRespository.findByIdWithErase(value) ;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, MinorCategory value) {
		
		return value==null ? null : ((MinorCategory)value).getId();
	}

}

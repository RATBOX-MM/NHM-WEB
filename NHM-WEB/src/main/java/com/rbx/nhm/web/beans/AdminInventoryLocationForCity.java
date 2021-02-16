package com.rbx.nhm.web.beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.rbx.nhm.web.entities.City;
import com.rbx.nhm.web.entities.Country;

@Named
@ViewScoped
public class AdminInventoryLocationForCity implements Serializable {

	private static final long serialVersionUID = 1L;

	private String messageColor;
	
	private City city;
	
	private List<City> cities;
	
	private List<Country> countries;
	
	
	
	
	
}

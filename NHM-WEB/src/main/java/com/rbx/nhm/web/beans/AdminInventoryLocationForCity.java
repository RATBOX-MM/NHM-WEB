package com.rbx.nhm.web.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.rbx.nhm.web.entities.City;
import com.rbx.nhm.web.entities.Country;
import com.rbx.nhm.web.interceptors.MessageHandler;
import com.rbx.nhm.web.services.CityService;
import com.rbx.nhm.web.services.CountryService;
import com.rbx.nhm.web.utilities.NHMException;

@Named
@ViewScoped
public class AdminInventoryLocationForCity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String messageColor;
	
	private City city;
	
	private String longName;
	
	private Country country;
	
	private List<City> cities;
	
	private List<Country> countries;
	
	@Inject
	private CityService cityService;
	
	@Inject
	private CountryService countryService;
	
	@PostConstruct
	public void initialize () {
		messageColor = "bg-danger";
		city = new City();
		country = new Country();
		cities = cityService.findAll();
		countries = countryService.findAll();
	}
	
	@MessageHandler
	public void save () {
		if (city.getId() == null) {
			cityService.save(city);
			initialize();
			setMessageColor("bg-danger");
			throw new NHMException("MSG-001", "City");
		} else {
			cityService.update(city);
			initialize();
			setMessageColor("bg-danger");
			throw new NHMException("MSG-002", "City");
		}
	}

	public void update (City city) {
		this.city = city;
	}
	
	@MessageHandler
	public void delete (City city) {
		cityService.delete(city);
		initialize();
		setMessageColor("bg-danger");
		throw new NHMException("MSG-003", "City");
	}
	
	@MessageHandler
	public void search () {
		if (country == null && longName.isEmpty()) {
			throw new NHMException("MSG-006");
		}
		cities = new ArrayList<City>();
		if (country != null && !longName.isEmpty()) {
			System.out.println("1");
			cities = cityService.findByCountryAndLongName(country.getId(), longName);
		} else if (country != null) {
			System.out.println("2");
			cities = cityService.findByCountry(country.getId());
		} else {
			System.out.println("3");
			cities = cityService.findByLongName(longName);
		}
		setMessageColor("bg-success");
		throw new NHMException("MSG-007", cities.size());
	}

	public String getMessageColor() {
		return messageColor;
	}

	public void setMessageColor(String messageColor) {
		this.messageColor = messageColor;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public String getLongName() {
		return longName;
	}

	public void setLongName(String longName) {
		this.longName = longName;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	public List<Country> getCountries() {
		return countries;
	}

	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}
	
}
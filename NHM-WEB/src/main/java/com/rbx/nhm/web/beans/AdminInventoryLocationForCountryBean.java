package com.rbx.nhm.web.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.rbx.nhm.web.entities.Country;
import com.rbx.nhm.web.enums.AdditionalStatus;
import com.rbx.nhm.web.interceptors.MessageHandler;
import com.rbx.nhm.web.services.CountryService;
import com.rbx.nhm.web.utilities.NHMException;

/**
 * 
 * @author kaungsithu
 * @since 07-02-2021
 *
 */

@Named
@ViewScoped
public class AdminInventoryLocationForCountryBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String messageColor;

	private Country country;
	
	private List<Country> countries;
	
	@Inject
	private CountryService countryService;
	
	@PostConstruct
	public void initialize () {
		messageColor = "bg-danger";
		country = new Country ();
		countries = countryService.findAll();
	}
	
	@MessageHandler
	public void save () {
		if (country.getId() == null) {
			countryService.save(country);
			initialize();
			setMessageColor("bg-success");
			throw new NHMException("MSG-001", "Country");
		} else {
			countryService.update(country);
			initialize();
			setMessageColor("bg-primary");
			throw new NHMException("MSG-002", "Country");
		}
	}
	
	public void update (Country country) {
		this.country = country;
	}
	
	@MessageHandler
	public void delete (Country country) {
		countryService.delete(country);
		initialize();
		setMessageColor("bg-danger");
		throw new NHMException("MSG-003", "Country");
	}
	
	public String getStatusColor (AdditionalStatus status) {
		return status.equals(AdditionalStatus.Availiable) ? "text-success" : "text-danger";
	}

	public String getMessageColor() {
		return messageColor;
	}

	public void setMessageColor(String messageColor) {
		this.messageColor = messageColor;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public List<Country> getCountries() {
		return countries;
	}

	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}

	public CountryService getCountryService() {
		return countryService;
	}

	public void setCountryService(CountryService countryService) {
		this.countryService = countryService;
	}
	
}
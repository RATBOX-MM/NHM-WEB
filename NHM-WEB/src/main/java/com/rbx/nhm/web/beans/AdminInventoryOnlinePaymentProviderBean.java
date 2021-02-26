package com.rbx.nhm.web.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.rbx.nhm.web.entities.OnlinePaymentProvider;
import com.rbx.nhm.web.entities.Tag;
import com.rbx.nhm.web.enums.AdditionalStatus;
import com.rbx.nhm.web.interceptors.MessageHandler;
import com.rbx.nhm.web.services.CityService;
import com.rbx.nhm.web.services.CountryService;
import com.rbx.nhm.web.services.OnlinePaymentProviderService;
import com.rbx.nhm.web.services.TagService;
import com.rbx.nhm.web.utilities.NHMException;

@Named
@ViewScoped
public class AdminInventoryOnlinePaymentProviderBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String messageColor;
	
	private OnlinePaymentProvider onlinePaymentProvider;
	
	private String name;
	
	private String type;
	
	//private Country country;
	
	private List<OnlinePaymentProvider> onlinePaymentProviders;
	
	//private List<Country> countries;
	
	@Inject
	private OnlinePaymentProviderService onlinePaymentProviderService;
	
	//@Inject
	//private CountryService countryService;
	
	@PostConstruct
	public void initialize () {
		messageColor = "bg-danger";
		onlinePaymentProvider = new OnlinePaymentProvider();
		//country = new Country();
		onlinePaymentProviders = onlinePaymentProviderService.findAll();
		//countries = countryService.findAll();
	}
	
	@MessageHandler
	public void save () {
		if (onlinePaymentProvider.getId() == null) {
			onlinePaymentProviderService.save(onlinePaymentProvider);
			initialize();
			setMessageColor("bg-success");
			throw new NHMException("MSG-001", "OnlinePaymentProvider");
		} else {
			onlinePaymentProviderService.update(onlinePaymentProvider);
			initialize();
			setMessageColor("bg-success");
			throw new NHMException("MSG-002", "OnlinePaymentProvider");
		}
	}

	public void update (OnlinePaymentProvider onlinePaymentProvider) {
		this.onlinePaymentProvider = onlinePaymentProvider;
	}
	
	
	@MessageHandler
	public void delete (OnlinePaymentProvider onlinePaymentProvider) {
		onlinePaymentProviderService.delete(onlinePaymentProvider);
		initialize();
		setMessageColor("bg-danger");
		throw new NHMException("MSG-003", "OnlinePaymentProvider");
	}
	
	
	
	@MessageHandler
	public void search () {
		if (onlinePaymentProvider== null && name.isEmpty()) {
			throw new NHMException("MSG-006");
		}
		onlinePaymentProviders= new ArrayList<OnlinePaymentProvider>();
		
		/*if (!name.isEmpty()) {
			System.out.println("2");
			onlinePaymentProviders = onlinePaymentProviderService.findByName(name);
		}*/
		/*else if(type != null && !name.isEmpty()) {
			onlinePaymentProviders = onlinePaymentProviderService.findByNameAndType(name,type);
		}
		
		else {
			System.out.println("3");
			onlinePaymentProviders = onlinePaymentProvidersService.findByLongName(longName);
		}
		*/
		setMessageColor("bg-success");
		throw new NHMException("MSG-007", onlinePaymentProviders.size());
	}
/*
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
*/

	public String getMessageColor() {
		return messageColor;
	}

	public void setMessageColor(String messageColor) {
		this.messageColor = messageColor;
	}

	public String getStatusColor (AdditionalStatus status) {
		return status.equals(AdditionalStatus.Availiable) ? "text-success" : "text-danger";
	}

	public OnlinePaymentProvider getOnlinePaymentProvider() {
		return onlinePaymentProvider;
	}

	public void setOnlinePaymentProvider(OnlinePaymentProvider onlinePaymentProvider) {
		this.onlinePaymentProvider = onlinePaymentProvider;
	}

	public List<OnlinePaymentProvider> getOnlinePaymentProviders() {
		return onlinePaymentProviders;
	}

	public void setOnlinePaymentProviders(List<OnlinePaymentProvider> onlinePaymentProviders) {
		this.onlinePaymentProviders = onlinePaymentProviders;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
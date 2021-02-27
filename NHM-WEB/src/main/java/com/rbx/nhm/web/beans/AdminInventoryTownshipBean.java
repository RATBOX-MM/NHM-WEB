package com.rbx.nhm.web.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.rbx.nhm.web.entities.City;
import com.rbx.nhm.web.entities.Township;
import com.rbx.nhm.web.interceptors.MessageHandler;
import com.rbx.nhm.web.services.CityService;
import com.rbx.nhm.web.services.TownshipService;
import com.rbx.nhm.web.utilities.NHMException;

/**
 * 
 * @author wailinoo
 * @since
 *
 */

@Named
@ViewScoped
public class AdminInventoryTownshipBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String messageColor;
	
	private Township township;
	
	private String longName;
	
	private City city;
	
	private List<Township> townships;
	
	private List<City> cities;
	
	@Inject
	private TownshipService townshipService;
	
	@Inject
	private CityService cityService;
	
	@PostConstruct
	public void initialize () {
		messageColor = "bg-danger";
		township = new Township();
		city = new City();
		townships = townshipService.findAll();
		cities = cityService.findAll();
	}
	
	@MessageHandler
	public void save () {
		if (township.getId() == null) {
			townshipService.save(township);
			initialize();
			setMessageColor("bg-success");
			throw new NHMException("MSG-001", "Township");
		} else {
			townshipService.update(township);
			initialize();
			setMessageColor("bg-success");
			throw new NHMException("MSG-002", "Township");
		}
	}

	public void update (Township township) {
		this.township = township;
	}
	
	@MessageHandler
	public void delete (Township township) {
		townshipService.delete(township);
		initialize();
		setMessageColor("bg-danger");
		throw new NHMException("MSG-003", "Township");
	}
	
	@MessageHandler
	public void search () {
		if (city == null && longName.isEmpty()) {
			throw new NHMException("MSG-006");
		}
		townships = new ArrayList<Township>();
		if (city != null && !longName.isEmpty()) {
			System.out.println("1");
			townships = townshipService.findByCityAndLongName(city.getId(), longName);
		} else if (city != null) {
			System.out.println("2");
			townships = townshipService.findByCity(city.getId());
		} else {
			System.out.println("3");
			townships = townshipService.findByLongName(longName);
		}
		setMessageColor("bg-success");
		throw new NHMException("MSG-007", townships.size());
	}

	public String getMessageColor() {
		return messageColor;
	}

	public void setMessageColor(String messageColor) {
		this.messageColor = messageColor;
	}

	public Township getTownship() {
		return township;
	}

	public void setTownship(Township township) {
		this.township = township;
	}

	public String getLongName() {
		return longName;
	}

	public void setLongName(String longName) {
		this.longName = longName;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public List<Township> getTownships() {
		return townships;
	}

	public void setTownships(List<Township> townships) {
		this.townships = townships;
	}

	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}
}
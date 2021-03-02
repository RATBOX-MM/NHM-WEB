package com.rbx.nhm.web.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.rbx.nhm.web.entities.Address;
import com.rbx.nhm.web.entities.City;
import com.rbx.nhm.web.entities.Country;
import com.rbx.nhm.web.entities.Hotel;
import com.rbx.nhm.web.entities.Township;
import com.rbx.nhm.web.enums.HotelStatus;
import com.rbx.nhm.web.interceptors.MessageHandler;
import com.rbx.nhm.web.services.AddressService;
import com.rbx.nhm.web.services.CityService;
import com.rbx.nhm.web.services.CountryService;
import com.rbx.nhm.web.services.HotelService;
import com.rbx.nhm.web.services.TownshipService;
import com.rbx.nhm.web.utilities.NHMException;

/**
 * 
 * @author WaiLinOo
 * @since
 *
 */

@Named
@ViewScoped
public class AdminInventoryHotelBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String messageColor;
	private String name;
	private Hotel hotel;
	private Address address1;
	private Address address;
	private List<Country> countries;
	private List<City> cities;
	private List<Township> townships;
	private List<Hotel> hotels;
	private List<Address> addresses;
	
	private HotelStatus [] hotelStatuses;
	
	@Inject
	private CountryService countryService;
	@Inject
	private CityService cityService;
	@Inject
	private TownshipService townshipService;
	@Inject
	private HotelService hotelService;
	@Inject
	private AddressService addressService;
	
	
	@PostConstruct
	public void initialize () {
		messageColor = "bg-danger";
		hotel = new Hotel();
		address1 = new Address();
		address = new Address();
		
		addresses = addressService.findAll();
		countries = countryService.findAll();
		cities = cityService.findAll();
		townships=townshipService.findAll();
		hotels = hotelService.findAll();
		hotelStatuses = HotelStatus.values();
	}
	
	
	@MessageHandler
	public void save () {
		
		addressService.save(address1);
		if(address1.getId()==null) {
			System.out.println("address1ID is null(save) "+address1.getId());
		}else {
			addresses = addressService.findByAddressID(address1.getId());
			address = addressService.findByID(address1.getId());
			hotel.setAddress(address);
			
			Address address = addressService.findByID(address1.getId());
			System.out.println("addressID is completed for address.getaddressinfo(Save)"+address.getAddressInfo());
			
			
			
			
			
			
			
		}
		if (hotel.getId() == null) {
			
			hotelService.save(hotel);
			System.out.println("Address from hotel" + hotel.getAddress());
			initialize();
			setMessageColor("bg-success");
			throw new NHMException("MSG-001", "Hotel");
			
		} else {
			hotelService.update(hotel);
			initialize();
			setMessageColor("bg-success");
			throw new NHMException("MSG-002", "Hotel");
			}
		}
	
	
	public void update (Hotel hotel) {
		this.hotel = hotel;
		
		}
	
	@MessageHandler
	public void delete (Hotel hotel) {
		hotelService.delete(hotel);
		initialize();
		setMessageColor("bg-danger");
		throw new NHMException("MSG-003", "Hotel");
		
	}
	
	@MessageHandler
	public void search () {
		if (address == null && name.isEmpty()) {
			System.out.println("address.getID is null(search)"+address.getId());
			throw new NHMException("MSG-006");
			
		}
		hotels = new ArrayList<Hotel>();
		if (address != null && !name.isEmpty()) {
			System.out.println("1"+address.getId());
			hotels = hotelService.findByAddressAndLongName(address.getId(), name);
		} else if (address != null) {
			System.out.println("2"+address.getId());
			hotels = hotelService.findByAddress(address.getId());
		} 
		if(name !=null || !name.isEmpty()){
			System.out.println("3"+address.getId());
			hotels = hotelService.findByName(name);
			}
		setMessageColor("bg-success");
		throw new NHMException("MSG-007", hotels.size());
		
	}
	
	
	
	
	public String getMessageColor() {
		return messageColor;
	}


	public void setMessageColor(String messageColor) {
		this.messageColor = messageColor;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Address getAddress() {
		return address;
	}



	public void setAddress(Address address) {
		this.address = address;
	}



	public List<Address> getAddresses() {
		return addresses;
	}



	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}



	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}


	

	public List<Hotel> getHotels() {
		return hotels;
	}

	public void setHotels(List<Hotel> hotels) {
		this.hotels = hotels;
		
	}
	
	
	

	

	public Address getAddress1() {
		return address1;
	}


	public void setAddress1(Address address1) {
		this.address1 = address1;
	}


	public HotelStatus[] getHotelStatuses() {
		return hotelStatuses;
	}

	public void setHotelStatuses(HotelStatus[] hotelStatuses) {
		this.hotelStatuses = hotelStatuses;
	}


	public List<Country> getCountries() {
		return countries;
	}


	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}


	public List<City> getCities() {
		return cities;
	}


	public void setCities(List<City> cities) {
		this.cities = cities;
	}


	public List<Township> getTownships() {
		return townships;
	}


	public void setTownships(List<Township> townships) {
		this.townships = townships;
	}
	
}

package com.rbx.nhm.web.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.collections4.map.HashedMap;

import com.rbx.nhm.web.entities.Address;

import com.rbx.nhm.web.repositories.AddressRepository;
import com.rbx.nhm.web.utilities.CommonFunctionality;
import com.rbx.nhm.web.utilities.NHMException;
import com.rbx.nhm.web.utilities.NHMException.Message;





@LocalBean
@Stateless
public class AddressService {
	@Inject
	private AddressRepository addressRepository;
	private Address address;
	@PersistenceContext
	private EntityManager entityManager;
	public Address save(Address address1 ) {
		verify(address1);
		address1.setId(CommonFunctionality.generateID("ADS"));
		entityManager.persist(address1);
		return address;
	}
	public List<Address> findAll () {
		return addressRepository.findAllWithErase();
	}
	public long findAllCount() {
		return 0;
	}
	
	
	public Address findByID (String addressID) {
		HashedMap<String, Object> params = new HashedMap<String, Object>();
		params.put("addressID", addressID);
		return addressRepository.findSingleByNamedQuery("Address.FindByID", params);
	}
	public List<Address> findByAddressID (String addressID) {
		HashedMap<String, Object> params = new HashedMap<String, Object>();
		params.put("addressID", addressID);
		return addressRepository.findByNamedQuery("Address.FindByID", params);
	}
	public List<Address> findByCountry (String countryID) {
		HashedMap<String, Object> params = new HashedMap<String, Object>();
		params.put("countryID", countryID);
		return addressRepository.findByNamedQuery("Address.FindByCountry", params);
	}
	public List<Address> findByCity (String cityID) {
		HashedMap<String, Object> params = new HashedMap<String, Object>();
		params.put("cityID", cityID);
		return addressRepository.findByNamedQuery("Address.FindByCity", params);
	}
	public List<Address> findByTownship (String townshipID) {
		HashedMap<String, Object> params = new HashedMap<String, Object>();
		params.put("townshipID", townshipID);
		return addressRepository.findByNamedQuery("Address.FindByTownship", params);
	}
	public long findCountByAddressInfo (String addressInfo) {
		HashedMap<String, Object> params = new HashedMap<String, Object>();
		params.put("addressInfo", addressInfo);
		return addressRepository.findCountByNamedQuery("Address.FindCountByAddressInfo", params);
	}
	public void update(Address address) {
		addressRepository.merge(address);
		}
	public void delete(Address address) {
		address.setErase(true);
		update(address);
	}
	
	public void verify(Address address1) {
		List<Message> messages = new ArrayList<Message>();
		if (address1.getCountry() == null) {
			messages.add(new Message("MSG-005", "Country"));
		}
		if (address1.getCity() == null) {
			messages.add(new Message("MSG-005", "City"));
		}
		if (address1.getTownship() == null) {
			messages.add(new Message("MSG-005", "Township"));
		}
		
		if (address1.getAddressInfo() == null || address1.getAddressInfo().isEmpty()) {
			messages.add(new Message("MSG-005", "AddressInfo"));
		}
		if (address1.getPhone() == null || address1.getPhone().isEmpty()) {
			messages.add(new Message("MSG-005", "Phone Number"));
		}
		
		if (messages.size() > 0) {
			throw new NHMException(messages);
		}
	}
	
	
	
	

	

}

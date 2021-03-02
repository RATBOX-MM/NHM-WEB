package com.rbx.nhm.web.services;


import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.commons.collections4.map.HashedMap;


import com.rbx.nhm.web.entities.Hotel;
import com.rbx.nhm.web.repositories.HotelRepository;
import com.rbx.nhm.web.utilities.CommonFunctionality;
import com.rbx.nhm.web.utilities.NHMException;
import com.rbx.nhm.web.utilities.NHMException.Message;


@LocalBean
@Stateless
public class HotelService implements MainService<Hotel> {
	@Inject
	private HotelRepository hotelRepository;

	@Override
	public void save(Hotel t) {
		verify(t);
		t.setId(CommonFunctionality.generateID("HOL"));
		hotelRepository.persist(t);
	}

	@Override
	public void update(Hotel t) {
		hotelRepository.merge(t);
	}

	@Override
	public void delete(Hotel t) {
		t.setErase(true);
		update(t);
	}

	@Override
	public Hotel findByID(String id) {
		return hotelRepository.findByIdWithErase(id);
	}

	@Override
	public List<Hotel> findAll() {
		return hotelRepository.findAllWithErase();
	}
	
	
	

	@Override
	public long findAllCount() {
		return 0;
	}
	public long findCountByName (String name) {
		HashedMap<String, Object> params = new HashedMap<String, Object>();
		params.put("name", name);
		return hotelRepository.findCountByNamedQuery("Hotel.FindCountByName", params);
	}
	public List<Hotel> findByAddress (String addressID) {
		HashedMap<String, Object> params = new HashedMap<String, Object>();
		params.put("addressID", addressID);
		return hotelRepository.findByNamedQuery("Hotel.FindByAddress", params);
	}
	public List<Hotel> findByAddressAndLongName (String addressID, String name) {
		HashedMap<String, Object> params = new HashedMap<String, Object>();
		params.put("addressID", addressID);
		params.put("name", "%"+name+"%");
		return hotelRepository.findByNamedQuery("Hotel.FindByHotelAndAddress", params);
	}
	public List<Hotel> findByName (String name) {
		HashedMap<String, Object> params = new HashedMap<String, Object>();
		params.put("name", "%"+name+"%");
		return hotelRepository.findByNamedQuery("Hotel.FindByName", params);
	} 
	

	@Override
	public void verify(Hotel t) {
		List<Message> messages = new ArrayList<Message>();
		if (t.getAddress() == null) {
			messages.add(new Message("MSG-005", "Address"));
		}
		if (t.getName() == null || t.getName().isEmpty()) {
			messages.add(new Message("MSG-005", "Hotel name"));
		}
		
		if (t.getAdditionalStatus() == null) {
			messages.add(new Message("MSG-005", "Status"));
		}
		if (t.getHotelStatus() == null) {
			messages.add(new Message("MSG-005", "Hotel Status"));
		}
		if (findCountByName(t.getName()) > 0) {
			messages.add(new Message("MSG-004", "Hotel Name"));
		}
		
		if (messages.size() > 0) {
			throw new NHMException(messages);
		}
		
}
}

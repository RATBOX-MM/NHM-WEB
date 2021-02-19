package com.rbx.nhm.web.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.commons.collections4.map.HashedMap;

import com.rbx.nhm.web.entities.City;
import com.rbx.nhm.web.repositories.CityRepository;
import com.rbx.nhm.web.utilities.CommonFunctionality;
import com.rbx.nhm.web.utilities.NHMException;
import com.rbx.nhm.web.utilities.NHMException.Message;

@LocalBean
@Stateless
public class CityService implements MainService<City>{

	@Inject
	private CityRepository cityRepository;
	
	@Override
	public void save(City t) {
		verify(t);
		t.setId(CommonFunctionality.generateID("CTY"));
		cityRepository.persist(t);
	}

	@Override
	public void update(City t) {
		cityRepository.merge(t);
	}

	@Override
	public void delete(City t) {
		t.setErase(true);
		update(t);
	}

	@Override
	public City findByID(String id) {
		return cityRepository.findByIdWithErase(id);
	}

	@Override
	public List<City> findAll() {
		return cityRepository.findAllWithErase();
	}
	
	public long findCountByLongName (String longName) {
		HashedMap<String, Object> params = new HashedMap<String, Object>();
		params.put("longName", longName);
		return cityRepository.findCountByNamedQuery("City.FindCountByLongName", params);
	}
	
	public long findCountByShortName (String shortName) {
		HashedMap<String, Object> params = new HashedMap<String, Object>();
		params.put("shortName", shortName);
		return cityRepository.findCountByNamedQuery("City.FindCountByShortName", params);
	}
	
	public long findCountByCityCode (String cityCode) {
		HashedMap<String, Object> params = new HashedMap<String, Object>();
		params.put("cityCode", cityCode);
		return cityRepository.findCountByNamedQuery("City.FindCountByCityCode", params);
	}
	
	@Override
	public long findAllCount() {
		return 0;
	}

	@Override
	public void verify(City t) {
		List<Message> messages = new ArrayList<Message>();
		if (t.getCountry() == null) {
			messages.add(new Message("MSG-005", "Country"));
		}
		if (t.getLongName() == null || t.getLongName().isEmpty()) {
			messages.add(new Message("MSG-005", "Long Name"));
		}
		if (t.getShortName() == null || t.getShortName().isEmpty()) {
			messages.add(new Message("MSG-005", "Short Name"));
		}
		if (t.getCityCode() == null || t.getCityCode().isEmpty()) {
			messages.add(new Message("MSG-005", "Postal Code"));
		}
		if (t.getAdditionalStatus() == null) {
			messages.add(new Message("MSG-005", "Status"));
		}
		if (findCountByLongName(t.getLongName()) > 0) {
			messages.add(new Message("MSG-004", "Long Name"));
		}
		if (findCountByShortName(t.getShortName()) > 0) {
			messages.add(new Message("MSG-004", "Short Name"));
		}
		if (findCountByCityCode(t.getCityCode()) > 0) {
			messages.add(new Message("MSG-004", "Postal Code"));
		}
		if (messages.size() > 0) {
			throw new NHMException(messages);
		}
	}

}

package com.rbx.nhm.web.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.rbx.nhm.web.entities.City;
import com.rbx.nhm.web.repositories.CityRepository;
import com.rbx.nhm.web.utilities.CommonFunctionality;

@LocalBean
@Stateless
public class CityService implements MainService<City>{

	@Inject
	private CityRepository cityRepository;
	
	@Override
	public void save(City t) {
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

	@Override
	public long findAllCount() {
		return 0;
	}

	@Override
	public void verify(City t) {
		
	}

}

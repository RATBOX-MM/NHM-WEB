package com.hxo.nhm.web.services;

import java.util.List;
import java.util.Comparator;
import java.util.stream.Collectors;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.hxo.nhm.web.entities.City;
import com.hxo.nhm.web.repositories.CityRepository;

/**
 * 
 * @author kaungsithu
 * @since 07-02-2021
 *
 */

@LocalBean
@Stateless
public class CityService implements MainService<City> {

	@Inject
	private CityRepository cityRepository;
	
	@Override
	public void save(City t) {
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
		return cityRepository.findAllWithErase()
				.stream().sorted(Comparator.comparing(City::getId)
				.reversed()).collect(Collectors.toList());
	}

	@Override
	public long findAllCount() {
		return cityRepository.findAllCountWithErase();
	}

	@Override
	public void verify(City t) {
		
	}

	
	
}

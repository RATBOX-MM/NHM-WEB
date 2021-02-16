package com.rbx.nhm.web.services;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.commons.collections4.map.HashedMap;

import com.rbx.nhm.web.entities.Country;
import com.rbx.nhm.web.enums.Continent;
import com.rbx.nhm.web.repositories.CountryRepository;
import com.rbx.nhm.web.utilities.CommonFunctionality;
import com.rbx.nhm.web.utilities.NHMException;

/**
 * 
 * @author kaungsithu
 * @since 07-02-2021
 *
 */

@Stateless
@LocalBean
public class CountryService implements MainService<Country> {

	@Inject
	private CountryRepository countryRepository;
	
	@Override
	public void save(Country t) {
		verify(t);
		t.setId(CommonFunctionality.generateID("CTR"));
		countryRepository.persist(t);
	}

	@Override
	public void update(Country t) {
		countryRepository.merge(t);
	}

	@Override
	public void delete(Country t) {
		t.setErase(true);
		update(t);
	}

	@Override
	public Country findByID(String id) {
		return countryRepository.findByIdWithErase(id);
	}

	@Override
	public List<Country> findAll() {
		return countryRepository.findAllWithErase()
				.stream().sorted(Comparator.comparing(Country::getId)
				.reversed()).collect(Collectors.toList());
	}
	
	@Override
	public long findAllCount () {
		return countryRepository.findAllCountWithErase();
	}
	
	public List<Country> findByLongName (String longName) {
		HashedMap<String, Object> params = new HashedMap<String, Object>();
		params.put("longName", longName);
		return countryRepository.findByNamedQuery("Country.FindByLongName", params);
	}
	
	public List<Country> findByLongNameWithLike (String longName) {
		HashedMap<String, Object> params = new HashedMap<String, Object>();
		params.put("longName", longName);
		return countryRepository.findByNamedQuery("Country.FindBylongNameWithLike", params)
				.stream().sorted(Comparator.comparing(Country::getId)
				.reversed()).collect(Collectors.toList());
	}
	
	public List<Country> findByContinent (Continent continent) {
		HashedMap<String, Object> params = new HashedMap<String, Object>();
		params.put("continent", continent);
		return countryRepository.findByNamedQuery("Country.FindByContinent", params)
				.stream().sorted(Comparator.comparing(Country::getId)
				.reversed()).collect(Collectors.toList());
	}
	
	public List<Country> findByLongNameWithLikeAndContinent (String longName, Continent continent) {
		HashedMap<String, Object> params = new HashedMap<String, Object>();
		params.put("longName", longName);
		params.put("continent", continent);
		return countryRepository.findByNamedQuery("Country.FindByLongNameWithLikeAndContinent", params)
				.stream().sorted(Comparator.comparing(Country::getId)
				.reversed()).collect(Collectors.toList());
	}

	@Override
	public void verify(Country t) {
		List<Country> countries = findByLongName(t.getLongName());
		if (countries.size() > 0) {
			throw new NHMException("MSG-004", "Country Long Name");
		}
	}
	
}
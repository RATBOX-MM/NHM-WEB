package com.hxo.nhm.web.repositories;

import com.hxo.nhm.web.entities.Country;

/**
 * 
 * @author kaungsithu
 * @since 07-02-2021
 *
 */

public class CountryRepository extends MainRepository<Country>{

	public CountryRepository() {
		super(Country.class);
	}

}

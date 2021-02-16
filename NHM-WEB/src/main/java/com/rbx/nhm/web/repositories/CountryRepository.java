package com.rbx.nhm.web.repositories;

import com.rbx.nhm.web.entities.Country;

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

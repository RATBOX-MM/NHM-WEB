package com.rbx.nhm.web.producers;

import javax.enterprise.inject.Produces;
import javax.inject.Named;

import com.rbx.nhm.web.enums.AdditionalStatus;
import com.rbx.nhm.web.enums.CategoryGender;
import com.rbx.nhm.web.enums.Continent;
import com.rbx.nhm.web.enums.HotelStatus;

public class EnumProducer {
	
	@Named
	@Produces
	public Continent [] continents () {
		return Continent.values();
	}

	@Named
	@Produces
	public AdditionalStatus [] additionalStatuses () {
		return AdditionalStatus.values();
	}
	
	
	@Named
	@Produces
	public CategoryGender[]categoryGenders(){
		return CategoryGender.values();
	}
	@Named
	@Produces
	public HotelStatus[] hotelStatuses() {
		return HotelStatus.values();
	}
	
}

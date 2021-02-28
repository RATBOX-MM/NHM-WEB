package com.rbx.nhm.web.producers;

import javax.enterprise.inject.Produces;
import javax.inject.Named;
import com.rbx.nhm.web.enums.AdditionalStatus;
import com.rbx.nhm.web.enums.CategoryGender;
import com.rbx.nhm.web.enums.Continent;
import com.rbx.nhm.web.enums.OnlinePaymentProviderType;
import com.rbx.nhm.web.enums.VehicleStatus;
import com.rbx.nhm.web.enums.VehicleType;

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
	public OnlinePaymentProviderType[] onlinePaymentProviderTypes(){
		return OnlinePaymentProviderType.values();
	}
	
	@Named
	@Produces
	public VehicleType[] vehicleType(){
		return VehicleType.values();
	}
	
	@Named
	@Produces
	public VehicleStatus[] vehicleStatus(){
		return VehicleStatus.values();
	}
	
	
	
	
}

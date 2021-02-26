package com.rbx.nhm.web.producers;

import javax.enterprise.inject.Produces;
import javax.inject.Named;

import com.rbx.nhm.web.enums.AdditionalStatus;
import com.rbx.nhm.web.enums.Continent;
import com.rbx.nhm.web.enums.OnlinePaymentProviderType;

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
	public OnlinePaymentProviderType [] onlinePaymentProviderTypes () {
		return OnlinePaymentProviderType.values();
	}
	
}

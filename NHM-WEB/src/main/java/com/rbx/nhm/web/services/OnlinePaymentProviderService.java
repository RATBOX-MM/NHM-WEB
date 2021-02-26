package com.rbx.nhm.web.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.commons.collections4.map.HashedMap;

import com.rbx.nhm.web.entities.City;
import com.rbx.nhm.web.entities.OnlinePaymentProvider;
import com.rbx.nhm.web.entities.Tag;
import com.rbx.nhm.web.repositories.OnlinePaymentProviderRepository;
import com.rbx.nhm.web.utilities.CommonFunctionality;
import com.rbx.nhm.web.utilities.NHMException;
import com.rbx.nhm.web.utilities.NHMException.Message;

@LocalBean
@Stateless
public class OnlinePaymentProviderService implements MainService<OnlinePaymentProvider>{
	
	@Inject
	private OnlinePaymentProviderRepository onlinePaymentProviderRepository;

	@Override
	public void save(OnlinePaymentProvider t) {
		verify(t);
		t.setId(CommonFunctionality.generateID("CTY"));
		onlinePaymentProviderRepository.persist(t);
		
		
	}

	@Override
	public void update(OnlinePaymentProvider t) {
		onlinePaymentProviderRepository.merge(t);
		
	}

	@Override
	public void delete(OnlinePaymentProvider t) {
		t.setErase(true);
		update(t);
	}

	@Override
	public OnlinePaymentProvider findByID(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

	@Override
	public List<OnlinePaymentProvider> findAll() {
		return onlinePaymentProviderRepository.findAllWithErase();
	}

	@Override
	public long findAllCount() {
		// TODO Auto-generated method stub
		return 0;
	}
/*
	public long findCountByName (String name) {
		HashedMap<String, Object> params = new HashedMap<String, Object>();
		params.put("name", name);
		return onlinePaymentProviderRepository.findCountByNamedQuery("OnlinePaymentProvider.FindCountByName", params);
	}
	
	public List<OnlinePaymentProvider> findByName (String name) {
		HashedMap<String, Object> params = new HashedMap<String, Object>();
		params.put("name", "%"+name+"%");
		return onlinePaymentProviderRepository.findByNamedQuery("OnlinePaymentProvider.FindByName", params);
	} 
	*/
	
	/*public List<OnlinePaymentProvider> findByNameAndType (String name, String type) {
		HashedMap<String, Object> params = new HashedMap<String, Object>();
		params.put("name", name);
		params.put("type", "%"+type+"%");
		return onlinePaymentProviderRepository.findByNamedQuery("OnlinePaymentProviderType.FindByNameAndType", params);
	}
	*/
	@Override
	public void verify(OnlinePaymentProvider t) {
		List<Message> messages = new ArrayList<Message>();
		if (t.getName() == null || t.getName().isEmpty()) {
			messages.add(new Message("MSG-005", "Name"));
		}
		if (t.getOnlinePaymentProviderType() == null) {
			messages.add(new Message("MSG-005", "OnlinePaymentProviderType"));
		}
		if (t.getAdditionalStatus() == null) {
			messages.add(new Message("MSG-005", "Status"));
		}/*
		if (findCountByName(t.getName()) > 0) {
			messages.add(new Message("MSG-004", "Name"));
		}*/
		if (messages.size() > 0) {
			throw new NHMException(messages);
		}
	}

}

package com.rbx.nhm.web.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.commons.collections4.map.HashedMap;

import com.rbx.nhm.web.entities.AppColor;
import com.rbx.nhm.web.repositories.AppColorRepository;
import com.rbx.nhm.web.utilities.CommonFunctionality;
import com.rbx.nhm.web.utilities.NHMException;
import com.rbx.nhm.web.utilities.NHMException.Message;

@LocalBean
@Stateless
public class AppColorService implements MainService<AppColor>{
	
	@Inject
	private AppColorRepository appColorRepository;

	@Override
	public void save(AppColor t) {
		verify(t);
		t.setId(CommonFunctionality.generateID("APC"));
		appColorRepository.persist(t);
		
	}

	@Override
	public void update(AppColor t) {
		appColorRepository.merge(t);		
	}

	@Override
	public void delete(AppColor t) {
		t.setErase(true);
		update(t);
		
	}

	@Override
	public AppColor findByID(String id) {
		return appColorRepository.findByIdWithErase(id);
	}

	@Override
	public List<AppColor> findAll() {
		return appColorRepository.findAllWithErase();	
	}
	
	public long findCountbyAppColor (String appcolor) {
		HashedMap<String, Object> params = new HashedMap<String, Object>();
		params.put("appcolor", appcolor);
		return appColorRepository.findCountByNamedQuery("AppColor.FindCountByAppColor", params);
	}
	
	public List<AppColor> findByAppColor (String appcolor) {
		HashedMap<String, Object> params = new HashedMap<String, Object>();
		params.put("appcolor", "%" +appcolor+ "%" );
		return appColorRepository.findByNamedQuery("AppColor.FindByAppColor", params);
	}

	@Override
	public long findAllCount() {
		return 0;
	}

	@Override
	public void verify(AppColor t) {
		List<Message> messages = new ArrayList<Message>();
		if(t.getColorCode() == null || t.getColorCode().isEmpty()) {
			messages.add(new Message("MSG-005","ColorCode"));
		}
		if (findCountbyAppColor(t.getColorCode()) > 0 ) {
			messages.add(new Message("MSG-004","ColorCode"));
		}
		if (messages.size() > 0) {
			throw new NHMException(messages);
		}
		
	}

}

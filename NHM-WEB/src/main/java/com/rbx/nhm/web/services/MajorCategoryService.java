package com.rbx.nhm.web.services;


import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.commons.collections4.map.HashedMap;

import com.rbx.nhm.web.entities.MajorCategory;
import com.rbx.nhm.web.enums.AdditionalStatus;
import com.rbx.nhm.web.enums.CategoryGender;
import com.rbx.nhm.web.repositories.MajorCategoryRepository;
import com.rbx.nhm.web.utilities.CommonFunctionality;
import com.rbx.nhm.web.utilities.NHMException;
import com.rbx.nhm.web.utilities.NHMException.Message;



@LocalBean
@Stateless
public class MajorCategoryService  implements MainService<MajorCategory>{	
	
	@Inject
	private  MajorCategoryRepository majorcategoryRepository;

	@Override
	public void save(MajorCategory t) {
		System.out.println(t.getName());
		verify(t);
		t.setId(CommonFunctionality.generateID("MJC"));
		majorcategoryRepository.persist(t);
		
	}

	@Override
	public void update(MajorCategory t) {
		majorcategoryRepository.merge(t);
		
	}

	@Override
	public void delete(MajorCategory t) {
		t.setErase(true);
		update(t);
		
	}

	@Override
	public MajorCategory findByID(String id) {
		
		return majorcategoryRepository.findByIdWithErase(id);
	}

	@Override
	public List<MajorCategory> findAll() {
		
		return majorcategoryRepository.findAllWithErase();
	}
	
	
	
	@Override
	public long findAllCount() {
		
		return 0;
	}
	
	public List<MajorCategory> findbyGender(CategoryGender gender){
		HashedMap<String, Object>parms=new HashedMap<String, Object>();
		parms.put("gender", gender);
		return majorcategoryRepository.findByNamedQuery("MajorCategory.FindByGender", parms);
		
	}
	public List<MajorCategory> findbyStatus(AdditionalStatus status){
		HashedMap<String, Object>parms=new HashedMap<String, Object>();
		parms.put("status", status);
		return majorcategoryRepository.findByNamedQuery("MajorCategory.FindByStatus", parms);
		
	}
	
	public List<MajorCategory> findbyName(String name){
		HashedMap<String, Object>parms=new HashedMap<String, Object>();
		parms.put("name","%"+name+"%");
		return majorcategoryRepository.findByNamedQuery("MajorCategory.FindByName", parms);
		
	}
	
	public List<MajorCategory> findbyGenderandStatusandName(CategoryGender gender,String name,AdditionalStatus status){
		HashedMap<String, Object>parms=new HashedMap<String, Object>();
		parms.put("gender", gender);
		parms.put("name","%"+name+"%");
		parms.put("status", status);
		return majorcategoryRepository.findByNamedQuery("MajorCategory.FindByGenderandStatusandName", parms);
		
	}

	@Override
	public void verify(MajorCategory t) {
		
		List<Message> messages=new ArrayList<Message>();
		
		System.out.println(t.getName());
		if(t.getName()==null || t.getName().isEmpty()) {
			System.out.println("1");
			messages.add(new Message("MSG-005","Name"));
		}
		if(t.getCategoryGender()==null) {
			System.out.println("2");
			messages.add(new Message("MSG-005","Gender"));
		}
		if(t.getAdditionalStatus()==null) {
			System.out.println("3");
			messages.add(new Message("MSG-005","Status"));
		}
		if(messages.size() > 0) {
			throw new NHMException(messages);
		}
		
		
	}

}

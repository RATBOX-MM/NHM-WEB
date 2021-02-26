package com.rbx.nhm.web.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.commons.collections4.map.HashedMap;

import com.rbx.nhm.web.entities.MinorCategory;
import com.rbx.nhm.web.enums.AdditionalStatus;
import com.rbx.nhm.web.repositories.MinorCategoryRepository;
import com.rbx.nhm.web.utilities.CommonFunctionality;
import com.rbx.nhm.web.utilities.NHMException;
import com.rbx.nhm.web.utilities.NHMException.Message;

/**
 * 
 * @author htooauntshein
 *
 */

@LocalBean
@Stateless
public class MinorCategoryService implements MainService<MinorCategory> {

	@Inject
	private MinorCategoryRepository minorCategory;
		
	
	@Override
	public void save(MinorCategory t) {
		verify(t);
		t.setId(CommonFunctionality.generateID("MNC"));
		minorCategory.persist(t);
		
	}

	@Override
	public void update(MinorCategory t) {
		minorCategory.merge(t);
		
	}

	@Override
	public void delete(MinorCategory t) {
		t.setErase(true);
		update(t);
	}

	@Override
	public MinorCategory findByID(String id) {
		
		return minorCategory.findByIdWithErase(id);
	}

	@Override
	public List<MinorCategory> findAll() {
		
		return minorCategory.findAllWithErase();
	}
	
	public long findCountByName(String name) {
		HashedMap<String, Object>parms=new  HashedMap<String, Object>();
		parms.put("name", name);
		return  minorCategory.findCountByNamedQuery("MinorCategory.FindCountByName", parms);	
		
	}
	
	public List<MinorCategory> findbyMajor(String majorID){
		HashedMap<String, Object> parms=new HashedMap<String, Object>();
		parms.put("majorID", majorID);
		return  minorCategory.findByNamedQuery("MinorCategory.FindByMajor", parms);
		
	}
	
	public List<MinorCategory>findbyName(String name){
		HashedMap<String, Object>parms=new HashedMap<String, Object>();
		parms.put("name","%"+name+"%");
		return minorCategory.findByNamedQuery("MinorCategory.FindByName", parms);
		
	}
	
	public List<MinorCategory>findbyStatus(AdditionalStatus status ){
		HashedMap<String,Object>parms=new HashedMap<String, Object>();
		parms.put("status", status);
		return minorCategory.findByNamedQuery("MinorCategory.FindByStatus", parms);
		
	}
	
	public List<MinorCategory>findbyMajorandNameandStatus(String majorID,String name,AdditionalStatus status){
		HashedMap<String,Object>parms=new HashedMap<String, Object>();
		parms.put("majorID", majorID);
		parms.put("name", "%"+name+"%");
		parms.put("status", status);
		return minorCategory.findByNamedQuery("MinorCategory.FindByMajorandNameandStatus", parms);
	}

	@Override
	public long findAllCount() {
		
		return 0;
	}

	@Override
	public void verify(MinorCategory t) {
		List<Message>messages=new ArrayList<Message>();
		if(t.getMajorCategory()==null) {
			
			messages.add(new Message("MSG-005","Major Category"));
		}
		if(t.getName()==null || t.getName().isEmpty()) {
		
			messages.add(new Message("MSG-005","Name"));
		}
		if(t.getAdditionalStatus()==null) {
	
			messages.add(new Message("MSG-005","Status"));
		}
		
		if(findCountByName(t.getName())>0) {
			
			messages.add(new Message("MSG-004","Name"));
			
		}
		if(messages.size()>0) {
			throw new NHMException(messages);
		}
	}

}

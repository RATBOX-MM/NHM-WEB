package com.rbx.nhm.web.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.commons.collections4.map.HashedMap;

import com.rbx.nhm.web.entities.SubCategory;
import com.rbx.nhm.web.enums.AdditionalStatus;
import com.rbx.nhm.web.repositories.SubCategoryRespository;
import com.rbx.nhm.web.utilities.CommonFunctionality;
import com.rbx.nhm.web.utilities.NHMException;
import com.rbx.nhm.web.utilities.NHMException.Message;


@LocalBean
@Stateless
public class SubCategoryService implements MainService<SubCategory> {
	
	@Inject
	private  SubCategoryRespository subCategoryRespository;

	@Override
	public void save(SubCategory t) {
		
		verify(t);
		t.setId(CommonFunctionality.generateID("SUB"));
		subCategoryRespository.persist(t);
	}

	@Override
	public void update(SubCategory t) {
		
		subCategoryRespository.merge(t);
				
	}

	@Override
	public void delete(SubCategory t) {
		t.setErase(true);
		update(t);
	}

	@Override
	public SubCategory findByID(String id) {
		
		return  subCategoryRespository.findByIdWithErase(id);
	}

	@Override
	public List<SubCategory> findAll() {
		return subCategoryRespository.findAllWithErase();
	}
	
	public long findCountbyName(String name) {
		HashedMap<String,Object> parms=new HashedMap<String, Object>();
		parms.put("name",name);
		return subCategoryRespository.findCountByNamedQuery("SubCategory.FindCountByName", parms);
	}
	
	public List<SubCategory> findbyMinor(String minorID){
		HashedMap<String,Object> parms=new HashedMap<String, Object>();
		parms.put("minorID",minorID);
		return subCategoryRespository.findByNamedQuery("SubCategory.FindByMinor", parms);
		
		
	}
	public List<SubCategory>findbyName(String name){
		HashedMap<String,Object> parms=new HashedMap<String, Object>();
		parms.put("name","%"+name+"%");
		return subCategoryRespository.findByNamedQuery("SubCategory.FindByName", parms);
		

	}
	
	public List<SubCategory>findbyStatus(AdditionalStatus status){
		HashedMap<String,Object> parms=new HashedMap<String, Object>();
		parms.put("status", status);
		return subCategoryRespository.findByNamedQuery("SubCategory.FindByStatus", parms);
	}
	
	public List<SubCategory>findbyMinorandNameandStatus(String minorID,String name,AdditionalStatus status){
		HashedMap<String,Object> parms=new HashedMap<String, Object>();
		parms.put("minorID",minorID);
		parms.put("name","%"+name+"%");
		parms.put("status", status);
		return subCategoryRespository.findByNamedQuery("SubCategory.FindByMinorandNameandStatus", parms);
	}

	@Override
	public long findAllCount() {
				
		return 0;
	}

	@Override
	public void verify(SubCategory t) {
		List<Message>messages=new ArrayList<Message>();
		
		if(t.getName()==null || t.getName().isEmpty()) {
			messages.add(new Message("MSG-005","SubName"));
		}
		if(t.getMinorCategory()==null) {
			messages.add(new Message("MSG-005","Minor"));
		}
		if(t.getAdditionalStatus()==null) {
			messages.add(new Message("MSG-005","Status"));
		}
		
		if(findCountbyName(t.getName()) > 0) {
			messages.add(new Message("MSG-004","Name"));
		}
		if(messages.size()>0) {
			throw new NHMException(messages);
		}
		
	}

}

package com.rbx.nhm.web.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.commons.collections4.map.HashedMap;
import com.rbx.nhm.web.entities.Tag;
import com.rbx.nhm.web.repositories.TagRepository;
import com.rbx.nhm.web.utilities.CommonFunctionality;
import com.rbx.nhm.web.utilities.NHMException;
import com.rbx.nhm.web.utilities.NHMException.Message;

@LocalBean
@Stateless
public class TagService implements MainService<Tag>{
	
	@Inject
	private TagRepository tagRepository;

	@Override
	public void save(Tag t) {
		verify(t);
		t.setId(CommonFunctionality.generateID("TAG"));
		tagRepository.persist(t);
	}

	@Override
	public void update(Tag t) {
		tagRepository.merge(t);		
	}

	@Override
	public void delete(Tag t) {
		t.setErase(true);
		update(t);
	}

	@Override
	public Tag findByID(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Tag> findAll() {
		return tagRepository.findAllWithErase();
	}

	@Override
	public long findAllCount() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public long findCountByName (String name) {
		HashedMap<String, Object> params = new HashedMap<String, Object>();
		params.put("name", name);
		return tagRepository.findCountByNamedQuery("Tag.FindCountByName", params);
	}
	
	public List<Tag> findByName (String name) {
		HashedMap<String, Object> params = new HashedMap<String, Object>();
		params.put("name", "%"+name+"%");
		return tagRepository.findByNamedQuery("Tag.FindByName", params);
	} 

	@Override
	public void verify(Tag t) {
		List<Message> messages = new ArrayList<Message>();
		if (t.getName() == null || t.getName().isEmpty()) {
			messages.add(new Message("MSG-005", "Name"));
		}
		if (t.getAdditionalStatus() == null) {
			messages.add(new Message("MSG-005", "Status"));
		}
		if (findCountByName(t.getName()) > 0) {
			messages.add(new Message("MSG-004", "Name"));
		}
		if (messages.size() > 0) {
			throw new NHMException(messages);
		}
	}

}

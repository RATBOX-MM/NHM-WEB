package com.rbx.nhm.web.services;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.apache.commons.collections4.map.HashedMap;
import com.rbx.nhm.web.entities.Township;
import com.rbx.nhm.web.repositories.TownshipRepository;
import com.rbx.nhm.web.utilities.CommonFunctionality;
import com.rbx.nhm.web.utilities.NHMException;
import com.rbx.nhm.web.utilities.NHMException.Message;
@LocalBean
@Stateless
public class TownshipService implements MainService<Township> {
	@Inject
	private TownshipRepository townshipRepository;
	
	@Override
	public void save(Township t) {
		verify(t);
		t.setId(CommonFunctionality.generateID("TON"));
		townshipRepository.persist(t);
	}

	@Override
	public void update(Township t) {
		townshipRepository.merge(t);
		}

	@Override
	public void delete(Township t) {
		t.setErase(true);
		update(t);
	}

	@Override
	public Township findByID(String id) {
		return townshipRepository.findByIdWithErase(id);
	}

	@Override
	public List<Township> findAll() {
		return townshipRepository.findAllWithErase();
	}
	
	public long findCountByLongName (String longName) {
		HashedMap<String, Object> params = new HashedMap<String, Object>();
		params.put("longName", longName);
		return townshipRepository.findCountByNamedQuery("Township.FindCountByLongName", params);
	}
	
	public long findCountByShortName (String shortName) {
		HashedMap<String, Object> params = new HashedMap<String, Object>();
		params.put("shortName", shortName);
		return townshipRepository.findCountByNamedQuery("Township.FindCountByShortName", params);
	}
	
	public long findCountByTownshipCode (String townshipCode) {
		HashedMap<String, Object> params = new HashedMap<String, Object>();
		params.put("townshipCode", townshipCode);
		return townshipRepository.findCountByNamedQuery("Township.FindCountByTownshipCode", params);
	}
	
	public List<Township> findByCity (String cityID) {
		HashedMap<String, Object> params = new HashedMap<String, Object>();
		params.put("cityID", cityID);
		return townshipRepository.findByNamedQuery("Township.FindByCity", params);
	}
	
	public List<Township> findByLongName (String longName) {
		HashedMap<String, Object> params = new HashedMap<String, Object>();
		params.put("longName", "%"+longName+"%");
		return townshipRepository.findByNamedQuery("Township.FindByLongName", params);
	} 
	
	public List<Township> findByCityAndLongName (String cityID, String longName) {
		HashedMap<String, Object> params = new HashedMap<String, Object>();
		params.put("cityID", cityID);
		params.put("longName", "%"+longName+"%");
		return townshipRepository.findByNamedQuery("Township.FindByCityAndLongName", params);
	}

	@Override
	public long findAllCount() {
		return 0;
	}

	@Override
	public void verify(Township t) {
		List<Message> messages = new ArrayList<Message>();
		if (t.getCity() == null) {
			messages.add(new Message("MSG-005", "City"));
		}
		if (t.getLongName() == null || t.getLongName().isEmpty()) {
			messages.add(new Message("MSG-005", "Long Name"));
		}
		if (t.getShortName() == null || t.getShortName().isEmpty()) {
			messages.add(new Message("MSG-005", "Short Name"));
		}
		if (t.getTownshipCode() == null || t.getTownshipCode().isEmpty()) {
			messages.add(new Message("MSG-005", "Postal Code"));
		}
		if (t.getAdditionalStatus() == null) {
			messages.add(new Message("MSG-005", "Status"));
		}
		if (findCountByLongName(t.getLongName()) > 0) {
			messages.add(new Message("MSG-004", "Long Name"));
		}
		if (findCountByShortName(t.getShortName()) > 0) {
			messages.add(new Message("MSG-004", "Short Name"));
		}
		if (findCountByTownshipCode(t.getTownshipCode()) > 0) {
			messages.add(new Message("MSG-004", "Postal Code"));
		}
		if (messages.size() > 0) {
			throw new NHMException(messages);
		}
	}
}

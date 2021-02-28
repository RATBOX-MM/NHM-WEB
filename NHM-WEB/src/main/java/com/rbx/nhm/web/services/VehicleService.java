package com.rbx.nhm.web.services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.commons.collections4.map.HashedMap;

import com.rbx.nhm.web.entities.Vehicle;
import com.rbx.nhm.web.enums.AdditionalStatus;
import com.rbx.nhm.web.enums.VehicleStatus;
import com.rbx.nhm.web.enums.VehicleType;
import com.rbx.nhm.web.repositories.VehicleRepository;
import com.rbx.nhm.web.utilities.CommonFunctionality;
import com.rbx.nhm.web.utilities.NHMException;
import com.rbx.nhm.web.utilities.NHMException.Message;

@LocalBean
@Stateless
public class VehicleService implements MainService<Vehicle>{

	@Inject
	private VehicleRepository vehicleRepository;
	
	@Override
	public void save(Vehicle t) {
		verify(t);
		t.setId(CommonFunctionality.generateID("VHL"));
		vehicleRepository.persist(t);
		
	}

	@Override
	public void update(Vehicle t) {
		vehicleRepository.merge(t);
		
	}

	@Override
	public void delete(Vehicle t) {
		t.setErase(true);
		update(t);
		
	}

	@Override
	public Vehicle findByID(String id) {
		return vehicleRepository.findByIdWithErase(id);
	}

	@Override
	public List<Vehicle> findAll() {
		return vehicleRepository.findAllWithErase();
	}

	
	public long findCountByName(String name) {
		HashedMap<String, Object> params = new  HashedMap<String, Object>();
		params.put("name", name);
		return vehicleRepository.findCountByNamedQuery("Vehicle.FindCountByName", params);
	}
	
	public long findCountByPlateNumber(String plateNumber) {
		HashedMap<String, Object> params = new  HashedMap<String, Object>();
		params.put("plateNumber", plateNumber);
		return vehicleRepository.findCountByNamedQuery("Vehicle.FindCountByPlateNumber", params);
	}
	
	public List<Vehicle> findByName(String Name) {
		HashedMap<String, Object> params = new  HashedMap<String, Object>();
		params.put("name", "%"+Name+"%");
		return vehicleRepository.findByNamedQuery("Vehicle.FindByName", params);
	}
	
	public List<Vehicle> findByPlateNumber(String plateNumber) {
		HashedMap<String, Object> params = new  HashedMap<String, Object>();
		params.put("plateNumber", plateNumber);
		return vehicleRepository.findByNamedQuery("Vehicle.FindByPlateNumber", params);
	}
	
	public List<Vehicle> findByNameAndPlateNumber(String Name, String plateNumber) {
		HashedMap<String, Object> params = new  HashedMap<String, Object>();
		params.put("name", "%"+Name+"%");
		params.put("plateNumber", plateNumber);
		return vehicleRepository.findByNamedQuery("Vehicle.FindByNameAndPlateNumber", params);
	}
	
	public List<Vehicle> findByVehicleType (VehicleType vehicleType) {
		HashedMap<String, Object> params = new HashedMap<String, Object>();
		params.put("vehicleType", vehicleType);
		return vehicleRepository.findByNamedQuery("Vehicle.FindByVehicleType", params)
				.stream().sorted(Comparator.comparing(Vehicle::getId)
				.reversed()).collect(Collectors.toList());
	}
	public List<Vehicle> findByVehicleStatus (VehicleStatus vehicleStatus) {
		HashedMap<String, Object> params = new HashedMap<String, Object>();
		params.put("vehicleStatus", vehicleStatus);
		return vehicleRepository.findByNamedQuery("Vehicle.FindByVehicleStatus", params)
				.stream().sorted(Comparator.comparing(Vehicle::getId)
				.reversed()).collect(Collectors.toList());
	}
	
	public List<Vehicle> findByAdditionalStatus(AdditionalStatus additionalStatus){
		HashedMap<String, Object> params = new HashedMap<String, Object>();
		params.put("additionalStatus", additionalStatus);
		return vehicleRepository.findByNamedQuery("Vehicle.FindByAdditionalStatus", params)
				.stream().sorted(Comparator.comparing(Vehicle::getId)
						.reversed()).collect(Collectors.toList());

	}
	
	
	public List<Vehicle> findByNameAndPlateNumberAndVehicleType(String Name,String plateNumber,VehicleType vehicleType){
		HashedMap<String, Object> params = new HashedMap<String, Object>();
		params.put("name", "%"+Name+"%");
		params.put("plateNumber", plateNumber);
		params.put("vehicleType", vehicleType);
		return vehicleRepository.findByNamedQuery("Vehicle.FindByNameAndPlateNumberAndVehicleType", params)
				.stream().sorted(Comparator.comparing(Vehicle::getId)
						.reversed()).collect(Collectors.toList());

	}
	
	public List<Vehicle> findByNameAndPlateNumberAndVehicleTypeAndVehicleStatus(String Name,String plateNumber,VehicleType vehicleType,VehicleStatus vehicleStatus){
		HashedMap<String, Object> params = new HashedMap<String, Object>();
		params.put("name", "%"+Name+"%");
		params.put("plateNumber", plateNumber);
		params.put("vehicleType", vehicleType);
		params.put("vehicleStatus", vehicleStatus);
		return vehicleRepository.findByNamedQuery("Vehicle.FindByNameAndPlateNumberAndVehicleTpyeAndVehicleStatus", params)
				.stream().sorted(Comparator.comparing(Vehicle::getId)
						.reversed()).collect(Collectors.toList());
	}
	
	public List<Vehicle> findByNameAndPlateNumberAndVehicleTypeAndVehicleStatusAndAdditionalStatus(String Name,String plateNumber,VehicleType vehicleType,VehicleStatus vehicleStatus,AdditionalStatus additionalStatus){
		HashedMap<String, Object> params = new HashedMap<String, Object>();
		params.put("name", "%"+Name+"%");
		params.put("plateNumber", plateNumber);
		params.put("vehicleType", vehicleType);
		params.put("vehicleStatus", vehicleStatus);
		params.put("additionalStatus", additionalStatus);
		return vehicleRepository.findByNamedQuery("Vehicle.FindByNameAndPlateNumberAndVehicleTpyeAndVehicleStatusAndAdditionalStatus", params)
				.stream().sorted(Comparator.comparing(Vehicle::getId)
						.reversed()).collect(Collectors.toList());

	}
	
	

	
	@Override
	public long findAllCount() {
		return 0;
	}

	@Override
	public void verify(Vehicle t) {
		List<Message> messages = new ArrayList<Message>();
		if (t.getName()== null || t.getName().isEmpty()) {
			messages.add(new Message("MSG-005", "Name"));
		}
		if (t.getBrand()== null || t.getBrand().isEmpty()) {
			messages.add(new Message("MSG-005", "Brand"));
		}
		if (t.getModelNumber()== null || t.getModelNumber().isEmpty()) {
			messages.add(new Message("MSG-005", "ModelNumber"));
		}
		if (t.getPlateNumber()== null || t.getPlateNumber().isEmpty()) {
			messages.add(new Message("MSG-005", "PlateNumber"));
		}
		if (t.getVehicleType() == null) {
			messages.add(new Message("MSG-005", "VehicleType"));
		}
		if (t.getVehicleStatus() == null) {
			messages.add(new Message("MSG-005", "VehicleStatus"));
		}
		if (t.getAdditionalStatus() == null) {
			messages.add(new Message("MSG-005", "Status"));
		}
		if (findCountByName(t.getName()) > 0) {
			messages.add(new Message("MSG-004", "Name"));
		}
		if (findCountByPlateNumber(t.getPlateNumber()) > 0) {
			messages.add(new Message("MSG-004", "PlateNumber"));
		}
		if (messages.size() > 0) {
			throw new NHMException(messages);
		}
		
	}

	
}

	
	
	
	

	
	
	
	
	
	
	


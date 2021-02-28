package com.rbx.nhm.web.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import com.rbx.nhm.web.entities.Vehicle;
import com.rbx.nhm.web.enums.AdditionalStatus;
import com.rbx.nhm.web.enums.VehicleStatus;
import com.rbx.nhm.web.enums.VehicleType;
import com.rbx.nhm.web.interceptors.MessageHandler;
import com.rbx.nhm.web.services.VehicleService;
import com.rbx.nhm.web.utilities.NHMException;

@Named
@ViewScoped
public class AdminInventoryVehicleBean implements Serializable {

	/**
	 * 
	 *  @author thethtarlwin
	 *   @since
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String messageColor;
	
	private Vehicle vehicle;
	
	private List<Vehicle> vehicles;
	
	private String name;
	
	private String plateNumber;
	
	private VehicleType vehicleType;
	
	private VehicleStatus vehicleStatus;
	
	private AdditionalStatus additionalStatus;
	
	@Inject
	private VehicleService vehicleService;
	
	@PostConstruct
	public void initialize() {
		messageColor = "bg-danger";
		vehicle = new Vehicle();
		vehicles = vehicleService.findAll();
	}
	
	@MessageHandler
	public void save() {
		if (vehicle.getId() == null) {
			vehicleService.save(vehicle);
			initialize();
			setMessageColor("bg-success");
			throw new NHMException("MSG-001", "Vehicle");
		}
		else {
			vehicleService.update(vehicle);
			initialize();
			setMessageColor("bg-success");
			throw new NHMException("MSG-002", "Vehicle");
		}
	}
	
	public void update(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	
	@MessageHandler
	public void delete(Vehicle vehicle) {
		vehicleService.delete(vehicle);
		initialize();
		setMessageColor("bg-danger");
		throw new NHMException("MSG-003", "Vehicle");
	}
	
	
	@MessageHandler
	public void search() {
		if(name.isEmpty() && plateNumber.isEmpty() && vehicleType == null && vehicleStatus == null && additionalStatus == null) {
			throw new NHMException("MSG-006");
		}
		vehicles = new ArrayList<Vehicle>();
		if(!name.isEmpty() && !plateNumber.isEmpty() && vehicleType != null && vehicleStatus != null && additionalStatus != null) {
			vehicles = vehicleService.findByNameAndPlateNumberAndVehicleTypeAndVehicleStatusAndAdditionalStatus(name, plateNumber, vehicleType, vehicleStatus, additionalStatus);
		}
		else if(!name.isEmpty() && !plateNumber.isEmpty() && vehicleType != null && vehicleStatus != null) {
			vehicles =vehicleService.findByNameAndPlateNumberAndVehicleTypeAndVehicleStatus(name, plateNumber, vehicleType, vehicleStatus);
		}
		
		else if(!name.isEmpty() && !plateNumber.isEmpty() && vehicleType != null ) {
			vehicles = vehicleService.findByNameAndPlateNumberAndVehicleType(name, plateNumber, vehicleType);
		}
		
		else if (!name.isEmpty()  && !plateNumber.isEmpty() ) {
			vehicles = vehicleService.findByNameAndPlateNumber(name, plateNumber);
		}
		
		else if(!name.isEmpty()) {
			vehicles = vehicleService.findByName(name);
		}
		else if(!plateNumber.isEmpty()){
			vehicles = vehicleService.findByPlateNumber(plateNumber);
		}
		else if(vehicleType != null) {
			vehicles = vehicleService.findByVehicleType(vehicleType);
		}
		else if(vehicleStatus != null) {
			vehicles = vehicleService.findByVehicleStatus(vehicleStatus);
		}
		else if(additionalStatus != null) {
			vehicles = vehicleService.findByAdditionalStatus(additionalStatus);
		}
		setMessageColor("bg-success");
		throw new NHMException("MSG-007", vehicles.size());
	}
	
	

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public List<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

	public String getMessageColor() {
		return messageColor;
	}

	public void setMessageColor(String messageColor) {
		this.messageColor = messageColor;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public VehicleType getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}

	public VehicleStatus getVehicleStatus() {
		return vehicleStatus;
	}

	public void setVehicleStatus(VehicleStatus vehicleStatus) {
		this.vehicleStatus = vehicleStatus;
	}

	public AdditionalStatus getAdditionalStatus() {
		return additionalStatus;
	}

	public void setAdditionalStatus(AdditionalStatus additionalStatus) {
		this.additionalStatus = additionalStatus;
	}

	
	

	
	
	
	
	
	
	
	
	
	
	
}

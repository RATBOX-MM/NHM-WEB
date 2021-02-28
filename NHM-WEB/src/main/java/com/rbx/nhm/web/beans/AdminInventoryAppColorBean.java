package com.rbx.nhm.web.beans;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.rbx.nhm.web.entities.AppColor;
import com.rbx.nhm.web.entities.Vehicle;
import com.rbx.nhm.web.enums.VehicleStatus;
import com.rbx.nhm.web.enums.VehicleType;
import com.rbx.nhm.web.interceptors.MessageHandler;
import com.rbx.nhm.web.services.AppColorService;
import com.rbx.nhm.web.services.VehicleService;
import com.rbx.nhm.web.utilities.NHMException;

@Named
@ViewScoped
public class AdminInventoryAppColorBean implements Serializable {

	/**
	 * 
	 *  @author thethtarlwin
	 *   @since
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String messageColor;
	
	private AppColor appColor;
	
	private String color;
	
	private List<AppColor> appColors;
	
	@Inject
	private AppColorService appColorService;
	
	@PostConstruct
	public void initialize() {
		messageColor = "bg-danger";
		appColor = new AppColor();
		appColors = appColorService.findAll();
	}
	
	@MessageHandler
	public void save() {
		if (appColor.getId() == null) {
			appColorService.save(appColor);
			initialize();
			setMessageColor("bg-success");
			throw new NHMException("MSG-001", "ColorCode");
		}
		else {
			appColorService.update(appColor);
			initialize();
			setMessageColor("bg-success");
			throw new NHMException("MSG-002", "ColorCode");
		}
	}
	
	public void update(AppColor appColor) {
		this.appColor = appColor;
	}
	
	@MessageHandler
	public void delete(AppColor appColor) {
		appColorService.delete(appColor);
		initialize();
		setMessageColor("bg-danger");
		throw new NHMException("MSG-003", "Vehicle");
	}
	
	@MessageHandler
	public void search() {
		if (color.isEmpty()) {
			System.out.println("1");
			throw new NHMException("MSG-006");
		}
		appColors = new ArrayList<AppColor>();
		if(!color.isEmpty()) {
			appColors = appColorService.findByAppColor(color);
		}
		setMessageColor("bg-success");
		throw new NHMException("MSG-007", appColors.size());
		}

	public String getMessageColor() {
		return messageColor;
	}

	public void setMessageColor(String messageColor) {
		this.messageColor = messageColor;
	}

	public AppColor getAppColor() {
		return appColor;
	}

	public void setAppColor(AppColor appColor) {
		this.appColor = appColor;
	}

	public List<AppColor> getAppColors() {
		return appColors;
	}

	public void setAppColors(List<AppColor> appColors) {
		this.appColors = appColors;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	
	
	
}
	
	
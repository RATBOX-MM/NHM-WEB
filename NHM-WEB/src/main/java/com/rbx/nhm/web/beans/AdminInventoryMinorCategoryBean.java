package com.rbx.nhm.web.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.rbx.nhm.web.entities.MajorCategory;
import com.rbx.nhm.web.entities.MinorCategory;
import com.rbx.nhm.web.enums.AdditionalStatus;
import com.rbx.nhm.web.interceptors.MessageHandler;
import com.rbx.nhm.web.services.MajorCategoryService;
import com.rbx.nhm.web.services.MinorCategoryService;
import com.rbx.nhm.web.utilities.NHMException;

/**
 * 
 * @author htooaungshein
 * @since
 *
 */

@Named
@ViewScoped
public class AdminInventoryMinorCategoryBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String messageColour;
	
	private MajorCategory majorCategory;
	
	
	private String name;
	
	private AdditionalStatus status;
	

	

	private List<MajorCategory> majorCategories;
	
	private MinorCategory minorCategory;
	
	private List<MinorCategory> minorcategories;
	
	@Inject
	private MinorCategoryService minorCategoryService;
	
	@Inject
	private MajorCategoryService majorCategoryService;
	
	@PostConstruct
	public void intialize() {
		messageColour="bg-danger";
		minorCategory=new MinorCategory();
		majorCategory=new MajorCategory();
		minorcategories=minorCategoryService.findAll();
		majorCategories=majorCategoryService.findAll();
		
	}
	
	@MessageHandler
	public void save() {
		if(minorCategory.getId()==null) {
			minorCategoryService.save(minorCategory);
			intialize();
			setMessageColour("bg-success");
			throw new NHMException("MSG-001","MinorCategroy");
		}else {
			minorCategoryService.update(minorCategory);
			intialize();
			setMessageColour("bg-success");
			throw new NHMException("MSG-002","MinorCategory");
		}
	}
	
	public void update(MinorCategory minorCategory) {
		this.minorCategory=minorCategory;
		
	}
	
	@MessageHandler
	public void delete(MinorCategory minorCategory) {
		minorCategoryService.delete(minorCategory);
		intialize();
		setMessageColour("bg-danger");
		throw new NHMException("MSG-003","MinorCategory");
	}
	
	
	@MessageHandler
	public void search() {
		
		if(majorCategory==null && name.isEmpty() && status==null ) {
			throw new NHMException("MSG-006");
		}
		minorcategories=new ArrayList<MinorCategory>();
		
		if(majorCategory !=null && !name.isEmpty() && status!=null) {
			minorcategories=minorCategoryService.findbyMajorandNameandStatus(majorCategory.getId(), name, status);
			
			
		}else if (majorCategory !=null ) {
			
			minorcategories=minorCategoryService.findbyMajor(majorCategory.getId());
			
		}else if(!name.isEmpty()) {
			minorcategories=minorCategoryService.findbyName(name);
		}
		else {
			minorcategories=minorCategoryService.findbyStatus(status);
		}
		setMessageColour("bg-success");
		throw new NHMException("MSG-007",minorcategories.size());
	}
	
	

	public List<MajorCategory> getMajorCategories() {
		return majorCategories;
	}

	public void setMajorCategories(List<MajorCategory> majorCategories) {
		this.majorCategories = majorCategories;
	}

	public MinorCategory getMinorCategory() {
		return minorCategory;
	}

	public void setMinorCategory(MinorCategory minorCategory) {
		this.minorCategory = minorCategory;
	}

	public List<MinorCategory> getMinorcategories() {
		return minorcategories;
	}

	public void setMinorcategories(List<MinorCategory> minorcategories) {
		this.minorcategories = minorcategories;
	}
	
	public String getMessageColour() {
		return messageColour;
	}

	public void setMessageColour(String messageColour) {
		this.messageColour = messageColour;
	}
	

	public String getStatusColor (AdditionalStatus status) {
		return status.equals(AdditionalStatus.Availiable) ? "text-success" : "text-danger";
	}
	
	public MajorCategory getMajorCategory() {
		return majorCategory;
	}

	public void setMajorCategory(MajorCategory majorCategory) {
		this.majorCategory = majorCategory;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	public AdditionalStatus getStatus() {
		return status;
	}

	public void setStatus(AdditionalStatus status) {
		this.status = status;
	}


	
	
	

}

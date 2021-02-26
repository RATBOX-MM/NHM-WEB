package com.rbx.nhm.web.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.rbx.nhm.web.entities.MinorCategory;
import com.rbx.nhm.web.entities.SubCategory;
import com.rbx.nhm.web.enums.AdditionalStatus;
import com.rbx.nhm.web.interceptors.MessageHandler;
import com.rbx.nhm.web.services.MinorCategoryService;
import com.rbx.nhm.web.services.SubCategoryService;
import com.rbx.nhm.web.utilities.NHMException;

/**
 * 
 * @author htooaungshein
 * @since
 *
 */

@Named
@ViewScoped
public class AdminInventorySubCategoryBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String messagecolour;

	private String name;
	
	private AdditionalStatus status;


	


	private SubCategory subCategory;
	
	private MinorCategory minorCategory;
	
	private List<SubCategory> subCategories;
	
	private List<MinorCategory> minorCategories;
	
	
	@Inject
	private SubCategoryService subCategoryService;
	
	
	@Inject
	private MinorCategoryService minorCategoryService;
	
	@PostConstruct
	public void intialize() {
		messagecolour="bg-danger";
		subCategory=new SubCategory();
		minorCategory=new MinorCategory();
		
		subCategories=subCategoryService.findAll();
		minorCategories=minorCategoryService.findAll();
	}
	
	
	@MessageHandler
	public void save() {
		if(subCategory.getId()==null) {
			subCategoryService.save(subCategory);
			intialize();
			setMessagecolour("bg-success");
			throw new  NHMException("MSG-001","SubCategory");
		}else {
			subCategoryService.update(subCategory);
			intialize();
			setMessagecolour("bg-success");
			throw new NHMException("MSG-002","SubCategory");
		}
	}
	
	public void update(SubCategory subCategory) {
		
		this.subCategory=subCategory;
	}
	
	@MessageHandler
	public void delete(SubCategory subCategory) {
		
		subCategoryService.delete(subCategory);
		intialize();
		setMessagecolour("bg-danger");
		throw new NHMException("MSG-003","SubCategory");
		
	}
	
	@MessageHandler
	public void search() {
		
		if(minorCategory==null && name.isEmpty() && status==null) {
			
			throw new NHMException("MSG-006");
		}
		subCategories=new ArrayList<SubCategory>();
		
		if(minorCategory!=null && !name.isEmpty() &&  status!=null) {
		
			subCategories=subCategoryService.findbyMinorandNameandStatus(minorCategory.getId(), name, status);
			
		}
		else if(minorCategory!=null) {
			
			subCategories=subCategoryService.findbyMinor(minorCategory.getId());
			
		}else if(!name.isEmpty()) {
			subCategories=subCategoryService.findbyName(name);
		}
		else {
			subCategories=subCategoryService.findbyStatus(status);
		}
		setMessagecolour("bg-success");
		throw new NHMException("MSG-007",subCategories.size());
		
	}
	
	
	
	
	
	
	

	public SubCategory getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(SubCategory subCategory) {
		this.subCategory = subCategory;
	}

	public MinorCategory getMinorCategory() {
		return minorCategory;
	}

	public void setMinorCategory(MinorCategory minorCategory) {
		this.minorCategory = minorCategory;
	}

	public List<SubCategory> getSubCategories() {
		return subCategories;
	}

	public void setSubCategories(List<SubCategory> subCategories) {
		this.subCategories = subCategories;
	}

	public List<MinorCategory> getMinorCategories() {
		return minorCategories;
	}

	public void setMinorCategories(List<MinorCategory> minorCategories) {
		this.minorCategories = minorCategories;
	}
	
	public String getStatusColor (AdditionalStatus status) {
		return status.equals(AdditionalStatus.Availiable) ? "text-success" : "text-danger";
	}
	public String getMessagecolour() {
		return messagecolour;
	}


	public void setMessagecolour(String messagecolour) {
		this.messagecolour = messagecolour;
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

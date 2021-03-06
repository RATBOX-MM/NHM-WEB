package com.rbx.nhm.web.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.rbx.nhm.web.entities.MajorCategory;
import com.rbx.nhm.web.enums.AdditionalStatus;
import com.rbx.nhm.web.enums.CategoryGender;
import com.rbx.nhm.web.interceptors.MessageHandler;
import com.rbx.nhm.web.services.MajorCategoryService;
import com.rbx.nhm.web.utilities.NHMException;

/**
 * 
 * @author htooauntshein
 * @since
 *
 */

@Named
@ViewScoped
public class AdminInventoryMajorCategoryBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String messageColor;
	
	
	private String name;
	
	private CategoryGender gender;
	
	private AdditionalStatus status;


	

	private MajorCategory majorCategory;
	
	private List<MajorCategory> majorCategories;
	
	
	@Inject
	private MajorCategoryService majorCategoryService;
	
	
	
	@PostConstruct
	public void intialize() {
		messageColor="bg-danger";
		majorCategory= new MajorCategory();
		majorCategories=majorCategoryService.findAll();
	}
	
	
	@MessageHandler
	public void save() {
		System.out.println(majorCategory.getName());
		if(majorCategory.getId()==null) {
			majorCategoryService.save(majorCategory);
			intialize();
			setMessageColor("bg-success");
			throw new NHMException("MSG-001","Category");
		}else {
			majorCategoryService.update(majorCategory);
			intialize();
			setMessageColor("bg-success");
			throw new NHMException("MSG-002","Category");
		}
	}
	
	public void update(MajorCategory majorCategory) {
		this.majorCategory=majorCategory;
	}
	
	@MessageHandler
	public void delete(MajorCategory majorCategory) {
		majorCategoryService.delete(majorCategory);
		intialize();
		setMessageColor("bg-danger");
		throw new NHMException("MSG-003","Category");

	}
	
	


	@MessageHandler
	public void search() {
		System.out.println("1");
		if(gender==null && name.isEmpty() && status==null) {
			System.out.println("2");
			throw new NHMException("MSG-006");
		}
		majorCategories=new ArrayList<MajorCategory>();
		if(gender != null && !name.isEmpty() && status !=null) {
			System.out.println("3");
			majorCategories=majorCategoryService.findbyGenderandStatusandName(gender, name, status);
			
		}else if (gender !=null) {
			System.out.println("4");
			majorCategories=majorCategoryService.findbyGender(gender);
			
		}else if (!name.isEmpty()) {
			System.out.println("5");
			majorCategories=majorCategoryService.findbyName(name);
			
		}else {
			System.out.println("6");
			majorCategories=majorCategoryService.findbyStatus(status);
		}
		setMessageColor("bg-success");
		throw new NHMException("MSG-007",majorCategories.size());
	
	}
		



	public MajorCategory getMajorCategory() {
		return majorCategory;
	}


	public void setMajorCategory(MajorCategory majorCategory) {
		this.majorCategory = majorCategory;
	}


	public List<MajorCategory> getMajorCategories() {
		return majorCategories;
	}


	public void setMajorCategories(List<MajorCategory> majorCategories) {
		this.majorCategories = majorCategories;
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
	
	public CategoryGender getGender() {
		return gender;
	}


	public void setGender(CategoryGender gender) {
		this.gender = gender;
	}


	public AdditionalStatus getStatus() {
		return status;
	}


	public void setStatus(AdditionalStatus status) {
		this.status = status;
	}
	

}

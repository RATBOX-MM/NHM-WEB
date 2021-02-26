package com.rbx.nhm.web.beans;

import java.io.Serializable;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.rbx.nhm.web.entities.MajorCategory;

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
	

}

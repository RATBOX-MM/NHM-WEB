package com.rbx.nhm.web.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import com.rbx.nhm.web.entities.Tag;
import com.rbx.nhm.web.enums.AdditionalStatus;
import com.rbx.nhm.web.interceptors.MessageHandler;
import com.rbx.nhm.web.services.CityService;
import com.rbx.nhm.web.services.CountryService;
import com.rbx.nhm.web.services.TagService;
import com.rbx.nhm.web.utilities.NHMException;

@Named
@ViewScoped
public class AdminInventoryTagBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String messageColor;
	
	private Tag tag;
	
	private String name;
	
	private List<Tag> tages;
	
	@Inject
	private TagService tagService;
	
	@PostConstruct
	public void initialize () {
		messageColor = "bg-danger";
		tag = new Tag();
		tages = tagService.findAll();
	}
	
	@MessageHandler
	public void save () {
		if (tag.getId() == null) {
			tagService.save(tag);
			initialize();
			setMessageColor("bg-success");
			throw new NHMException("MSG-001", "Tag");
		} else {
			tagService.update(tag);
			initialize();
			setMessageColor("bg-success");
			throw new NHMException("MSG-002", "Tag");
		}
	}

	
	public void update (Tag tag) {
		this.tag = tag;
	}
	
	
	@MessageHandler
	public void delete (Tag tag) {
		tagService.delete(tag);
		initialize();
		setMessageColor("bg-danger");
		throw new NHMException("MSG-003", "Tag");
	}
	
	
	@MessageHandler
	public void search () {
		if (name.isEmpty()) {
			System.out.println("1");
		throw new NHMException("MSG-006");
		}
		tages = new ArrayList<Tag>();
		if (tag == null && !name.isEmpty()) {
			tages = tagService.findByName(name);
			System.out.println("2");
		setMessageColor("bg-success");
		throw new NHMException("MSG-007", tages.size());
		}
	}
	
	
	public String getMessageColor() {
		return messageColor;
	}

	public void setMessageColor(String messageColor) {
		this.messageColor = messageColor;
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	public List<Tag> getTages() {
		return tages;
	}

	public void setTages(List<Tag> tages) {
		this.tages = tages;
	}
	
	public String getStatusColor (AdditionalStatus status) {
		return status.equals(AdditionalStatus.Availiable) ? "text-success" : "text-danger";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
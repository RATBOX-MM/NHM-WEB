package com.hxo.nhm.web.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

/**
 * 
 * @author kaungsithu
 * @since 06-02-2021
 *
 */

@Entity
public class Notification implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
	@Lob
	private String image;
	
	private String title;
	
	@Lob
	private String description;
	
	@Column(columnDefinition = "tinyint(1) default 1")
	private boolean erase;
	
	@Embedded
	private Security security;
	
	public Notification () {
		security = new Security();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isErase() {
		return erase;
	}

	public void setErase(boolean erase) {
		this.erase = erase;
	}

	public Security getSecurity() {
		return security;
	}

	public void setSecurity(Security security) {
		this.security = security;
	}
	
}

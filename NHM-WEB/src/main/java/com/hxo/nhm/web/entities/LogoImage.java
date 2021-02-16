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
public class LogoImage implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;

	@Lob
	private String image;
	
	@Column(columnDefinition = "tinyint(1) default 1")
	private boolean erase;
	
	@Embedded
	private Security security;
	
	public LogoImage () {
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
package com.hxo.nhm.web.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 
 * @author kaungsithu
 * @since 06-02-2021
 *
 */

@Entity
public class AppColor implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
	private String colorCode;
	
	@Column(columnDefinition = "tinyint(1) default 1")
	private boolean erase;
	
	@Embedded
	private Security security;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getColorCode() {
		return colorCode;
	}

	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
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
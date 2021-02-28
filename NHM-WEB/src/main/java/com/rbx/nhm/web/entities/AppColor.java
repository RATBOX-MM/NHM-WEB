package com.rbx.nhm.web.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * 
 * @author kaungsithu
 * @since 06-02-2021
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name = "AppColor.FindCountByAppColor", query = "select count(a) from AppColor a where a.erase=false and a.colorCode = :appcolor"),
	@NamedQuery(name = "AppColor.FindByAppColor", query = "select a from AppColor a where a.erase= false and a.colorCode like :appcolor"),
})

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
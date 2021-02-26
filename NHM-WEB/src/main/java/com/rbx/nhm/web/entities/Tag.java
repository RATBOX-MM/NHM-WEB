package com.rbx.nhm.web.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;

import com.rbx.nhm.web.enums.AdditionalStatus;

/**
 * 
 * @author kaungsithu
 * @since 07-02-2021
 *
 */

@Entity
@NamedQueries({
	@NamedQuery(name = "Tag.FindCountByName", query = "select count(t) from Tag t where t.erase = false and t.name = :name"),
	@NamedQuery(name = "Tag.FindByName", query = "select t from Tag t where t.erase = false and t.name like :name"),
})
public class Tag implements Serializable {

	
	
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
	@Lob
	@NotNull(message = "Name is missing!")
	private String name;
	
	@Enumerated(EnumType.STRING)
	@NotNull(message = "Status is missing!")
	private AdditionalStatus additionalStatus;
	
	@Column(columnDefinition = "tinyint(1) default 1")
	private boolean erase;
	
	@Embedded
	private Security security;
	
	public Tag () {
		security = new Security();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AdditionalStatus getAdditionalStatus() {
		return additionalStatus;
	}

	public void setAdditionalStatus(AdditionalStatus additionalStatus) {
		this.additionalStatus = additionalStatus;
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
package com.hxo.nhm.web.entities;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.ManyToOne;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import com.hxo.nhm.web.enums.AdditionalStatus;

/**
 * 
 * @author kaungsithu
 * @since 06-02-2021
 *
 */

@Entity
public class SubCategory implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;

	@ManyToOne
	@NotNull(message = "Minor Category is missing!")
	private MinorCategory minorCategory;
	
	@NotNull(message = "Name is missing!")
	private String name;
	
	@Lob
	private String description;
	
	@Enumerated(EnumType.STRING)
	@NotNull(message = "Status is missing!")
	private AdditionalStatus additionalStatus;
	
	@Column(columnDefinition = "tinyint(1) default 1")
	private boolean erase;
	
	@Embedded
	private Security security;
	
	public SubCategory () {
		security = new Security();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public MinorCategory getMinorCategory() {
		return minorCategory;
	}

	public void setMinorCategory(MinorCategory minorCategory) {
		this.minorCategory = minorCategory;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((additionalStatus == null) ? 0 : additionalStatus.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + (erase ? 1231 : 1237);
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SubCategory other = (SubCategory) obj;
		if (additionalStatus != other.additionalStatus)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (erase != other.erase)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}
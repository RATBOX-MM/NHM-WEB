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
public class Township implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	
	@ManyToOne
	private City city;
	
	@NotNull(message = "Long Name is missing!")
	private String longName;
	
	@NotNull(message = "Short Name is missing!")
	private String shortName;
	
	private String townshipCode;
	
	@Lob
	private String description;
	
	@Enumerated(EnumType.STRING)
	private AdditionalStatus additionalStatus;
	
	@Column(columnDefinition = "tinyint(1) default 1")
	private boolean erase;
	
	@Embedded
	private Security security;
	
	public Township () {
		security = new Security();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public String getLongName() {
		return longName;
	}

	public void setLongName(String longName) {
		this.longName = longName;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getTownshipCode() {
		return townshipCode;
	}

	public void setTownshipCode(String townshipCode) {
		this.townshipCode = townshipCode;
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
		result = prime * result + ((longName == null) ? 0 : longName.hashCode());
		result = prime * result + ((shortName == null) ? 0 : shortName.hashCode());
		result = prime * result + ((townshipCode == null) ? 0 : townshipCode.hashCode());
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
		Township other = (Township) obj;
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
		if (longName == null) {
			if (other.longName != null)
				return false;
		} else if (!longName.equals(other.longName))
			return false;
		if (shortName == null) {
			if (other.shortName != null)
				return false;
		} else if (!shortName.equals(other.shortName))
			return false;
		if (townshipCode == null) {
			if (other.townshipCode != null)
				return false;
		} else if (!townshipCode.equals(other.townshipCode))
			return false;
		return true;
	}
	
}
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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.rbx.nhm.web.enums.AdditionalStatus;
import com.rbx.nhm.web.enums.Continent;

/**
 * 
 * @author kaungsithu
 * @since 06-02-2021
 *
 */

@Entity
@NamedQueries({
	@NamedQuery (name = "Country.FindAllCount", query = "select c from Country c where c.erase = false"),
	@NamedQuery (name = "Country.FindByLongName", query = "select c from Country c where c.erase = false and c.longName = :longName"),
	@NamedQuery (name = "Country.FindCountByLongName", query = "select count(c) from Country c where c.erase = false and c.longName = :longName"),
	@NamedQuery (name = "Country.FindCountByShortName", query = "select count(c) from Country c where c.erase = false and c.shortName = :shortName"),
	@NamedQuery (name = "Country.FindBylongNameWithLike", query = "select c from Country c where c.erase = false and c.longName like :longName"),
	@NamedQuery (name = "Country.FindByContinent", query = "select c from Country c where c.erase = false and c.continent = :continent"),
	@NamedQuery (name = "Country.FindByLongNameWithLikeAndContinent", query = "select c from Country c where c.erase = false and c.longName like :longName")
})
public class Country implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
	@Enumerated(EnumType.STRING)
	private Continent continent;
	
	@NotEmpty(message = "Long Name is missing!")
	private String longName;
	
	@NotEmpty(message = "Short Name is missing!")
	private String shortName;
	
	private String countryCode;
	
	@Lob
	private String description;
	
	@NotNull(message = "Status is missing!")
	@Enumerated(EnumType.STRING)
	private AdditionalStatus additionalStatus;
	
	@Column(columnDefinition = "tinyint(1) default 1")
	private boolean erase;
	
	@Embedded
	private Security security;
	
	public Country () {
		security = new Security();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Continent getContinent() {
		return continent;
	}

	public void setContinent(Continent continent) {
		this.continent = continent;
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

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
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
		result = prime * result + ((continent == null) ? 0 : continent.hashCode());
		result = prime * result + ((countryCode == null) ? 0 : countryCode.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + (erase ? 1231 : 1237);
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((longName == null) ? 0 : longName.hashCode());
		result = prime * result + ((shortName == null) ? 0 : shortName.hashCode());
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
		Country other = (Country) obj;
		if (additionalStatus != other.additionalStatus)
			return false;
		if (continent != other.continent)
			return false;
		if (countryCode == null) {
			if (other.countryCode != null)
				return false;
		} else if (!countryCode.equals(other.countryCode))
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
		return true;
	}
	
}
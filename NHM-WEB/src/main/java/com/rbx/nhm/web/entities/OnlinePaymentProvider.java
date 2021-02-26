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

import com.rbx.nhm.web.enums.AdditionalStatus;
import com.rbx.nhm.web.enums.OnlinePaymentProviderType;

/**
 * 
 * @author kaungsithu
 * @since 06-02-2021
 *
 */

@Entity
/*
@NamedQueries({
	@NamedQuery(name = "OnlinePaymentProvider.FindCountByName", query = "select count(o) from OnlinePaymentProvider o where o.erase = false and o.Name = :name"),
	@NamedQuery(name = "OnlinePaymentProvider.FindByName", query = "select o from OnlinePaymentProvider o where o.erase = false and o.Name like :name"),
	//@NamedQuery(name = "OnlinePaymentProvider.FindByNameAndType", query = "select c from City c where c.erase = false and c.country.id = :countryID and c.longName like :longName"),
})
*/
public class OnlinePaymentProvider implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
	private String name;
	
	@Lob
	private String image;
	
	@Lob
	private String description;
	
	@Enumerated(EnumType.STRING)
	private OnlinePaymentProviderType onlinePaymentProviderType;
	
	@Enumerated(EnumType.STRING)
	private AdditionalStatus additionalStatus;
	
	@Column(columnDefinition = "tinyint(1) default 1")
	private boolean erase;
	
	@Embedded
	private Security security;
	
	public OnlinePaymentProvider () {
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public OnlinePaymentProviderType getOnlinePaymentProviderType() {
		return onlinePaymentProviderType;
	}

	public void setOnlinePaymentProviderType(OnlinePaymentProviderType onlinePaymentProviderType) {
		this.onlinePaymentProviderType = onlinePaymentProviderType;
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
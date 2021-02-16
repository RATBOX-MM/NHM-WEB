package com.rbx.nhm.web.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.rbx.nhm.web.enums.AdditionalStatus;

/**
 * 
 * @author kaungsithu
 * @since 06-02-2021
 *
 */

@Entity
public class Item implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
	@Lob
	private String imagePath1;
	
	@Lob
	private String imagePath2;
	
	@Lob
	private String imagePath3;
	
	@Lob
	private String imagePath4;
	
	@Lob
	private String imagePath5;
	
	@NotNull(message = "Name is missing!")
	private String name;
	
	@OneToMany(mappedBy = "item", orphanRemoval = true, 
	cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private List<Size> sizes;
	
	@ManyToOne
	private MajorCategory majorCategory;
	
	@ManyToOne
	private MinorCategory minorCategory;
	
	@ManyToOne
	private SubCategory subCategory;
	
	@OneToMany(mappedBy = "item", orphanRemoval = true, 
	cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private List<ItemTag> itemTags;
	
	@OneToMany(mappedBy = "item", orphanRemoval = true, 
	cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private List<ExtraField> extraFields;
	
	@Lob
	private String description;
	
	private long quantity;
	
	private long limitQuantity;
	
	private double basePrice;
	
	@OneToMany(mappedBy = "item", orphanRemoval = true, 
	cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private List<ItemImage> itemImages;
	
	@Enumerated(EnumType.STRING)
	private AdditionalStatus additionalStatus;
	
	@Column(columnDefinition = "tinyint(1) default 1")
	private boolean erase;
	
	@Embedded
	private Security security;
	
	public Item () {
		security = new Security();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImagePath1() {
		return imagePath1;
	}

	public void setImagePath1(String imagePath1) {
		this.imagePath1 = imagePath1;
	}

	public String getImagePath2() {
		return imagePath2;
	}

	public void setImagePath2(String imagePath2) {
		this.imagePath2 = imagePath2;
	}

	public String getImagePath3() {
		return imagePath3;
	}

	public void setImagePath3(String imagePath3) {
		this.imagePath3 = imagePath3;
	}

	public String getImagePath4() {
		return imagePath4;
	}

	public void setImagePath4(String imagePath4) {
		this.imagePath4 = imagePath4;
	}

	public String getImagePath5() {
		return imagePath5;
	}

	public void setImagePath5(String imagePath5) {
		this.imagePath5 = imagePath5;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MajorCategory getMajorCategory() {
		return majorCategory;
	}

	public void setMajorCategory(MajorCategory majorCategory) {
		this.majorCategory = majorCategory;
	}

	public MinorCategory getMinorCategory() {
		return minorCategory;
	}

	public void setMinorCategory(MinorCategory minorCategory) {
		this.minorCategory = minorCategory;
	}

	public SubCategory getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(SubCategory subCategory) {
		this.subCategory = subCategory;
	}

	public List<ItemTag> getItemTags() {
		return itemTags;
	}

	public void setItemTags(List<ItemTag> itemTags) {
		this.itemTags = itemTags;
	}

	public List<ExtraField> getExtraFields() {
		return extraFields;
	}

	public void setExtraFields(List<ExtraField> extraFields) {
		this.extraFields = extraFields;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public long getLimitQuantity() {
		return limitQuantity;
	}

	public void setLimitQuantity(long limitQuantity) {
		this.limitQuantity = limitQuantity;
	}

	public double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}

	public List<ItemImage> getItemImages() {
		return itemImages;
	}

	public void setItemImages(List<ItemImage> itemImages) {
		this.itemImages = itemImages;
	}

	public List<Size> getSizes() {
		return sizes;
	}

	public void setSizes(List<Size> sizes) {
		this.sizes = sizes;
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
package com.rbx.nhm.web.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * 
 * @author kaungsithu
 * @since 06-02-2021
 *
 */

@Entity
public class ExtraField implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
	private String FieldKey;
	
	private String FieldValue;
	
	@ManyToOne
	private Item item;
	
	public ExtraField () {
		
	}
	
	public ExtraField (Item item) {
		this.item = item;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFieldKey() {
		return FieldKey;
	}

	public void setFieldKey(String fieldKey) {
		FieldKey = fieldKey;
	}

	public String getFieldValue() {
		return FieldValue;
	}

	public void setFieldValue(String fieldValue) {
		FieldValue = fieldValue;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

}
package com.rbx.nhm.web.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

/**
 * 
 * @author kaungsithu
 * @since 06-02-2021
 *
 */

@Entity
public class ItemImage implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
	@Lob
	private String imagePath;
	
	@ManyToOne
	private Item item;
	
	public ItemImage () {	
	}
	
	public ItemImage (Item item) {
		this.item = item;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
	
}
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
public class ItemTag implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
	@ManyToOne
	private Tag tag;
	
	@ManyToOne
	private Item item;
	
	public ItemTag () {
	}
	
	public ItemTag (Item item) {
		this.item = item;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

}
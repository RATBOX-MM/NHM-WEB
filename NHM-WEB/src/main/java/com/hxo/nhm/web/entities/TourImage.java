package com.hxo.nhm.web.entities;

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
public class TourImage implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
	@Lob
	private String image;
	
	@ManyToOne
	private Tour tour;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Tour getTour() {
		return tour;
	}

	public void setTour(Tour tour) {
		this.tour = tour;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
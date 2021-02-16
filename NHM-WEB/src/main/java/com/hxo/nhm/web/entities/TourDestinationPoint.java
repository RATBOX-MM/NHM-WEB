package com.hxo.nhm.web.entities;

import java.io.Serializable;
import java.time.LocalDate;

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
public class TourDestinationPoint implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
	private LocalDate arriveDate;
	
	private String arriveTime;
	
	@ManyToOne
	private Address address;
	
	@ManyToOne
	private Tour tour;
	
	@Lob
	private String image;
	
	@ManyToOne
	private Hotel hotel;
	
	private String destinationDescription;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LocalDate getArriveDate() {
		return arriveDate;
	}

	public void setArriveDate(LocalDate arriveDate) {
		this.arriveDate = arriveDate;
	}

	public String getArriveTime() {
		return arriveTime;
	}

	public void setArriveTime(String arriveTime) {
		this.arriveTime = arriveTime;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Tour getTour() {
		return tour;
	}

	public void setTour(Tour tour) {
		this.tour = tour;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public String getDestinationDescription() {
		return destinationDescription;
	}

	public void setDestinationDescription(String destinationDescription) {
		this.destinationDescription = destinationDescription;
	}
	
}
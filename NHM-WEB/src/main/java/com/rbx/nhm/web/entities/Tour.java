package com.rbx.nhm.web.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * 
 * @author kaungsithu
 * @since 06-02-2021
 *
 */

@Entity
public class Tour implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
	@Lob
	private String image;
	
	private String name;
	
	@ManyToOne
	private MajorGenre majorGenre;
	
	private String startTime;
	
	private LocalDate startDate;
	
	private String endTime;
	
	private LocalDate endDate;
	
	@ManyToOne
	private Address tourStartPoint;
	
	@OneToMany(mappedBy = "tour", 
	cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
	private List<TourDestinationPoint> tourDestinationPoints;
		
	@ManyToOne
	private Vehicle vehicle;
	
	private int longDay;
	
	private int limitPerson;
	
	private double price;
	
	@Lob
	private String description;
	
	@OneToMany(mappedBy = "tour", 
	cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
	private List<TourImage> tourImages;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MajorGenre getMajorGenre() {
		return majorGenre;
	}

	public void setMajorGenre(MajorGenre majorGenre) {
		this.majorGenre = majorGenre;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public Address getTourStartPoint() {
		return tourStartPoint;
	}

	public void setTourStartPoint(Address tourStartPoint) {
		this.tourStartPoint = tourStartPoint;
	}

	public List<TourDestinationPoint> getTourDestinationPoints() {
		return tourDestinationPoints;
	}

	public void setTourDestinationPoints(List<TourDestinationPoint> tourDestinationPoints) {
		this.tourDestinationPoints = tourDestinationPoints;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public int getLongDay() {
		return longDay;
	}

	public void setLongDay(int longDay) {
		this.longDay = longDay;
	}

	public int getLimitPerson() {
		return limitPerson;
	}

	public void setLimitPerson(int limitPerson) {
		this.limitPerson = limitPerson;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<TourImage> getTourImages() {
		return tourImages;
	}

	public void setTourImages(List<TourImage> tourImages) {
		this.tourImages = tourImages;
	}
	
}
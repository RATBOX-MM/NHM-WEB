package com.hxo.nhm.web.entities;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.hxo.nhm.web.enums.OrderState;
import com.hxo.nhm.web.enums.PaymentMethod;

/**
 * 
 * @author kaungsithu
 * @since 06-02-2021
 *
 */

@Entity
public class OrderTransition implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;

	@ManyToOne
	private Account account;
	
	@ManyToOne
	private Item item;
	
	@ManyToOne
	private Color color;
	
	@ManyToOne
	private Size size;
	
	private int quantity;
	
	private double uniquePrice;
	
	private double totalPrice;
	
	@Lob
	private String description;
	
	@Enumerated(EnumType.STRING)
	private OrderState orderState;
	
	@Enumerated(EnumType.STRING)
	private PaymentMethod paymentMethod;
	
	@ManyToOne
	private Payment payment;
	
	@ManyToOne
	private Address delieryInfo;
	
	@Embedded
	private Security security;
	
	public OrderTransition () {
		security = new Security();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getUniquePrice() {
		return uniquePrice;
	}

	public void setUniquePrice(double uniquePrice) {
		this.uniquePrice = uniquePrice;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public OrderState getOrderState() {
		return orderState;
	}

	public void setOrderState(OrderState orderState) {
		this.orderState = orderState;
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Security getSecurity() {
		return security;
	}

	public void setSecurity(Security security) {
		this.security = security;
	}
	
}
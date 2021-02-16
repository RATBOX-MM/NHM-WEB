package com.hxo.nhm.web.entities;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.hxo.nhm.web.enums.BookingState;
import com.hxo.nhm.web.enums.BookingStatus;
import com.hxo.nhm.web.enums.PaymentMethod;

/**
 * 
 * @author kaungsithu
 * @since 06-02-2021
 *
 */

@Entity
public class Payment implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
	@ManyToOne
	private OnlinePaymentProvider onlinePaymentProvider;
	
	@Enumerated(EnumType.STRING)
	private PaymentMethod paymentMethod;
	
	private String accountID;
	
	private String password;
	
	private double amount;
	
	@ManyToOne
	private Account account;
	
	@Enumerated(EnumType.STRING)
	private BookingStatus BookingStatus;
	
	@Enumerated(EnumType.STRING)
	private BookingState bookingState;
	
	@ManyToOne
	private Address pickAddress;
	
	@Embedded
	private Security security;
	
	public Payment () {
		security = new Security();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public OnlinePaymentProvider getBank() {
		return onlinePaymentProvider;
	}

	public void setBank(OnlinePaymentProvider onlinePaymentProvider) {
		this.onlinePaymentProvider = onlinePaymentProvider;
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getAccountID() {
		return accountID;
	}

	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public OnlinePaymentProvider getOnlinePaymentProvider() {
		return onlinePaymentProvider;
	}

	public void setOnlinePaymentProvider(OnlinePaymentProvider onlinePaymentProvider) {
		this.onlinePaymentProvider = onlinePaymentProvider;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Security getSecurity() {
		return security;
	}

	public void setSecurity(Security security) {
		this.security = security;
	}
	
}
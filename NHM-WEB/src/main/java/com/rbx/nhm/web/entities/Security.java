package com.rbx.nhm.web.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class Security {

	private String updatedTime;
	
	private LocalDate updatedDate;

	@ManyToOne
	private Account updatedAccount;

	public Security () {
		updatedTime = LocalTime.now().toString();
		updatedDate = LocalDate.now();
	}

	public String getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(String updatedTime) {
		this.updatedTime = updatedTime;
	}

	public LocalDate getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDate updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Account getUpdatedAccount() {
		return updatedAccount;
	}

	public void setUpdatedAccount(Account updatedAccount) {
		this.updatedAccount = updatedAccount;
	}
	
}
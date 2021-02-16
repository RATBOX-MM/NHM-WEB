package com.rbx.nhm.web.entities;

import java.time.LocalDate;
import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Embedded;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import com.rbx.nhm.web.enums.AccountGender;
import com.rbx.nhm.web.enums.AccountRole;
import com.rbx.nhm.web.enums.AccountStatus;

/**
 * 
 * @author kaungsithu
 * @since 06-02-2021
 *
 */

@Entity
public class Account implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
	@Lob
	private String profile;
	
	@NotNull(message = "Name is missing")
	private String name;
	
	private LocalDate birthday;
	
	@Enumerated(EnumType.STRING)
	private AccountGender accountGender;
	
	@NotNull(message = "Email is missing")
	private String email;
	
	@NotNull(message = "Phone is missing!")
	private String phone;
	
	@NotNull(message = "Password is missing!")
	@Column(columnDefinition = "varchar(255) character set 'utf8' collate 'utf8_bin'")
	private String password;
	
	@Enumerated(EnumType.STRING)
	private AccountRole accountRole;
	
	@Enumerated(EnumType.STRING)
	private AccountStatus accountStatus;
	
	@Column(columnDefinition = "tinyint(1) default 1")
	private boolean erase;
	
	@Embedded
	private Security security;
	
	public Account () {
		security = new Security();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public AccountGender getAccountGender() {
		return accountGender;
	}

	public void setAccountGender(AccountGender accountGender) {
		this.accountGender = accountGender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public AccountRole getAccountRole() {
		return accountRole;
	}

	public void setAccountRole(AccountRole accountRole) {
		this.accountRole = accountRole;
	}

	public AccountStatus getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(AccountStatus accountStatus) {
		this.accountStatus = accountStatus;
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
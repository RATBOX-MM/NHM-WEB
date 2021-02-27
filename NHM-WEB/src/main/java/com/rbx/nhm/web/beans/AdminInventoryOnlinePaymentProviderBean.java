package com.rbx.nhm.web.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import com.rbx.nhm.web.entities.OnlinePaymentProvider;
import com.rbx.nhm.web.enums.OnlinePaymentProviderType;
import com.rbx.nhm.web.interceptors.MessageHandler;
import com.rbx.nhm.web.services.OnlinePaymentProviderService;
import com.rbx.nhm.web.utilities.NHMException;

/**
 * 
 * @author myosandikyaw
 * @since 24-2-2021
 *
 */

@Named
@ViewScoped
public class AdminInventoryOnlinePaymentProviderBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String messageColor;
	
	private OnlinePaymentProvider onlinePaymentProvider;
	
	private String name;

	private OnlinePaymentProviderType onlinePaymentProviderType;
	
	private List<OnlinePaymentProvider> onlinePaymentProviders;
	
	@Inject
	private OnlinePaymentProviderService onlinePaymentProviderService;
	
	@PostConstruct
	public void initialize () {
		messageColor = "bg-danger";
		onlinePaymentProvider = new OnlinePaymentProvider();
		onlinePaymentProviders = onlinePaymentProviderService.findAll();
	}
	
	@MessageHandler
	public void save () {
		if (onlinePaymentProvider.getId() == null) {
			onlinePaymentProviderService.save(onlinePaymentProvider);
			initialize();
			setMessageColor("bg-success");
			throw new NHMException("MSG-001", "OnlinePaymentProvider");
		} else {
			onlinePaymentProviderService.update(onlinePaymentProvider);
			initialize();
			setMessageColor("bg-primary");
			throw new NHMException("MSG-002", "OnlinePaymentProvider");
		}
	}
	
	public void update (OnlinePaymentProvider onlinePaymentProvider) {
		this.onlinePaymentProvider = onlinePaymentProvider;
	}
	
	@MessageHandler
	public void delete (OnlinePaymentProvider onlinePaymentProvider) {
		onlinePaymentProviderService.delete(onlinePaymentProvider);
		initialize();
		setMessageColor("bg-danger");
		throw new NHMException("MSG-003", "Tag");
	}
	
	@MessageHandler
	public void search () {
		if (onlinePaymentProviderType == null && name.isEmpty()) {
			throw new NHMException("MSG-006");
		}
		onlinePaymentProviders = new ArrayList<OnlinePaymentProvider>();
		if(onlinePaymentProviderType != null && !name.isEmpty()){
			System.out.println("1");
			onlinePaymentProviders = onlinePaymentProviderService.findByNameAndOnlinePaymentProviderType(name,onlinePaymentProviderType);
		}else if (!name.isEmpty()) {
			onlinePaymentProviders = onlinePaymentProviderService.findByName(name);
		}else if(onlinePaymentProviderType != null) {
			onlinePaymentProviders = onlinePaymentProviderService.findByOnlinePaymentProviderType(onlinePaymentProviderType);
		}
		setMessageColor("bg-success");
		throw new NHMException("MSG-007", onlinePaymentProviders.size());
	}


	public String getMessageColor() {
		return messageColor;
	}

	public void setMessageColor(String messageColor) {
		this.messageColor = messageColor;
	}

	public OnlinePaymentProvider getOnlinePaymentProvider() {
		return onlinePaymentProvider;
	}

	public void setOnlinePaymentProvider(OnlinePaymentProvider onlinePaymentProvider) {
		this.onlinePaymentProvider = onlinePaymentProvider;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<OnlinePaymentProvider> getOnlinePaymentProviders() {
		return onlinePaymentProviders;
	}

	public void setOnlinePaymentProviders(List<OnlinePaymentProvider> onlinePaymentProviders) {
		this.onlinePaymentProviders = onlinePaymentProviders;
	}
	
	public OnlinePaymentProviderType getOnlinePaymentProviderType() {
		return onlinePaymentProviderType;
	}

	public void setOnlinePaymentProviderType(OnlinePaymentProviderType onlinePaymentProviderType) {
		this.onlinePaymentProviderType = onlinePaymentProviderType;
	}
	
	
}

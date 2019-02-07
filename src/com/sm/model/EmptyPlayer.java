package com.sm.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class EmptyPlayer implements SMPlayer, Serializable {

	private String buyUrl;
	private String sellUrl;	
	private String cancelUrl;
	private String replaceUrl;
	
	@Override
	public String getName() {
		return "PUESTO LIBRE";
	}

	@Override
	public String getTeam() {
		return "";
	}

	@Override
	public String getPoints() {
		return "0";
	}

	@Override
	public String getPrice() {
		return "";
	}

	@Override
	public String getImageUrl() {
		return "";
	}

	@Override
	public boolean isNational() {
		return false;
	}

	@Override
	public boolean isExtracommunity() {
		return false;
	}

	@Override
	public boolean isInjured() {
		return false;
	}

	@Override
	public boolean isDoubtful() {
		return false;
	}

	@Override
	public String getBuyUrl() {
		return buyUrl;
	}

	@Override
	public String getSellUrl() {
		return sellUrl;
	}

	@Override
	public String getCancelUrl() {
		return cancelUrl;
	}

	@Override
	public String getReplaceUrl() {
		return replaceUrl;
	}

	@Override
	public BigDecimal getPointsAsNumber() {
		return BigDecimal.ZERO;
	}

	public void setBuyUrl(String buyUrl) {
		this.buyUrl = buyUrl;
	}

	public void setSellUrl(String sellUrl) {
		this.sellUrl = sellUrl;
	}

	public void setCancelUrl(String cancelUrl) {
		this.cancelUrl = cancelUrl;
	}

	public void setReplaceUrl(String replaceUrl) {
		this.replaceUrl = replaceUrl;
	}
	
	
}

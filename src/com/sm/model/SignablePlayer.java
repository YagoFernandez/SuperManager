package com.sm.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class SignablePlayer implements Comparable<SignablePlayer>, Serializable {

	private String name;
	private String team;
	private String value;
	private String signUrl;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getSignUrl() {
		return signUrl;
	}
	public void setSignUrl(String signUrl) {
		this.signUrl = signUrl;
	}

	@Override
	public String toString() {
		
		return name 
				+ System.getProperty("line.separator") + " Valor: " + value
				+ System.getProperty("line.separator") + " URL: " + signUrl;
	}
	
	@Override
	public int compareTo(SignablePlayer anotherPlayer) {
		
		BigDecimal thisPlayerValue = new BigDecimal(this.getValue().replace(".", ""));
		BigDecimal anotherPlayerValue = new BigDecimal(anotherPlayer.getValue().replace(".", ""));
		
		return anotherPlayerValue.compareTo(thisPlayerValue);
	}
}

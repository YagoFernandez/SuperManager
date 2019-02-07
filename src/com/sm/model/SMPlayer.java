package com.sm.model;

import java.math.BigDecimal;

public interface SMPlayer {

	public String getName();
	public String getTeam();
	public String getPoints();
	public String getPrice();
	public String getImageUrl();
	public boolean isNational();
	public boolean isExtracommunity();
	public boolean isInjured();
	public boolean isDoubtful();
	public String getBuyUrl();
	public String getSellUrl();
	public String getCancelUrl();
	public String getReplaceUrl();
	public BigDecimal getPointsAsNumber();
	
}

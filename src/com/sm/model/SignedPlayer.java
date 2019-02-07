package com.sm.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class SignedPlayer implements SMPlayer, Serializable {

	private String name;
	private String team;
	private String points;
	private String price;
	private String imageUrl;
	
	private boolean national;
	private boolean extracommunity;
	private boolean injured;
	private boolean doubtful;
	
	private String buyUrl;
	private String sellUrl;	
	private String cancelUrl;
	private String replaceUrl;
	
	private PointsConverter pointsConverter;
	
	public SignedPlayer() {
		this.pointsConverter = new PointsConverter();
	}
	
	@Override
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	@Override
	public String getPoints() {
		return points;
	}
	public void setPoints(String points) {
		this.points = points;
	}
	@Override
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	@Override
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String url) {
		this.imageUrl = url;
	}
	@Override
	public boolean isNational() {
		return national;
	}
	public void setNational(boolean national) {
		this.national = national;
	}
	@Override
	public boolean isExtracommunity() {
		return extracommunity;
	}
	public void setExtracommunity(boolean extracommunity) {
		this.extracommunity = extracommunity;
	}
	@Override
	public boolean isInjured() {
		return injured;
	}
	public void setInjured(boolean injured) {
		this.injured = injured;
	}
	@Override
	public boolean isDoubtful() {
		return doubtful;
	}
	public void setDoubtful(boolean doubtful) {
		this.doubtful = doubtful;
	}
	@Override
	public String getBuyUrl() {
		return buyUrl;
	}
	public void setBuyUrl(String buyUrl) {
		this.buyUrl = buyUrl;
	}
	@Override
	public String getSellUrl() {
		return sellUrl;
	}
	public void setSellUrl(String sellUrl) {
		this.sellUrl = sellUrl;
	}
	@Override
	public String getCancelUrl() {
		return cancelUrl;
	}
	public void setCancelUrl(String cancelUrl) {
		this.cancelUrl = cancelUrl;
	}
	@Override
	public String getReplaceUrl() {
		return replaceUrl;
	}
	public void setReplaceUrl(String replaceUrl) {
		this.replaceUrl = replaceUrl;
	}
	@Override
	public BigDecimal getPointsAsNumber() {
		return pointsConverter.convert(points);
	}
	
	@Override
	public String toString() {
		return new StringBuffer(name)
				.append(System.getProperty("line.separator")).append(" Equipo: ").append(team)
				.append(System.getProperty("line.separator")).append(" Foto: ").append(imageUrl)
				.append(System.getProperty("line.separator")).append(" Nacional: ").append(national)
				.append(System.getProperty("line.separator")).append(" Extracomunitario: ").append(extracommunity)
				.append(System.getProperty("line.separator")).append(" Lesionado: ").append(injured)
				.append(System.getProperty("line.separator")).append(" Duda: ").append(doubtful)
				.append(System.getProperty("line.separator")).append(" Puntos: ").append(points)
				.append(System.getProperty("line.separator")).append(" Precio: ").append(price)
				.append(System.getProperty("line.separator")).append(" Anular: ").append(cancelUrl)
				.append(System.getProperty("line.separator")).append(" Fichar: ").append(buyUrl)
				.append(System.getProperty("line.separator")).append(" Vender: ").append(sellUrl)
				.append(System.getProperty("line.separator")).append(" Sustituir: ").append(replaceUrl)
				.toString();
	}
	
	public class PointsConverter implements Serializable {
		
		public BigDecimal convert(String points) {
			points = sanitizeEmptyPoints(points);
			points = sanitizeVictorySignal(points);
			points = sanitizeDecimalSeparator(points);
			points = points.trim();
			return new BigDecimal(points);
		}
		
		private String sanitizeEmptyPoints(String points) {
			return points.replace("-", "0");
		}
		
		private String sanitizeVictorySignal(String points) {
			return points.replace("(+)", "");
		}
		
		private String sanitizeDecimalSeparator(String points) {
			return points.replace(",", ".");
		}
	}
}

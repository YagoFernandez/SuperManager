package com.sm.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


public class Team implements Serializable {

	private String name, teamUrl;
	private int id;
	private List<SMPlayer> players;
	
	public List<SMPlayer> getPlayers() {
		return players;
	}
	public void setPlayers(List<SMPlayer> players) {
		this.players = players;
	}
	public BigDecimal getPoints() {
		
		BigDecimal points = BigDecimal.ZERO;
		
		for (SMPlayer player : players) {
			points = points.add(player.getPointsAsNumber());
		}
		
		return points;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTeamUrl() {
		return teamUrl;
	}
	public void setTeamUrl(String teamUrl) {
		this.teamUrl = teamUrl;
	}
	
	@Override
	public String toString() {
		return new StringBuilder(name).append("/").append(teamUrl).toString();
	}
}

package com.sm.parser;

import java.util.List;

import org.jsoup.nodes.Document;

import com.sm.model.Team;


public interface ManagerParser {

	public List<Team> getTeams(Document doc);
}

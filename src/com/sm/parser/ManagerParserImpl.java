package com.sm.parser;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sm.model.Team;


public class ManagerParserImpl implements ManagerParser {

	public ManagerParserImpl() {
	}
	
	@Override
	public List<Team> getTeams(Document doc) {
		
		List<Team> teams = new ArrayList<Team>();
		Elements elements = doc.getElementsByClass("miequipo");
		
		for (Element element : elements) {
			teams.add(getTeam(element));
		}
		
		return teams;
	}
	
	private Team getTeam(Element element) {
		Elements elements = element.getElementsByTag("a");
		Element teamElement = elements.first();
		
		Team team = new Team(); 
		team.setName(teamElement.text());
		team.setTeamUrl(teamElement.attr("href"));
		
		return team;
	}

}

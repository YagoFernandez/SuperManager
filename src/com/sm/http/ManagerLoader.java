package com.sm.http;

import java.io.IOException;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.sm.http.UrlNames;
import com.sm.model.Manager;
import com.sm.model.Team;
import com.sm.parser.ManagerParser;
import com.sm.parser.ManagerParserImpl;
import com.sm.parser.TeamParser;
import com.sm.parser.TeamParserImpl;

/*
 * 
 */
public class ManagerLoader {

	private Connection con;
	
	public Manager load(String user, String pass) throws IOException {
		Manager manager = new Manager(user, pass);
		con = doLogin(manager);
		
		List<Team> teams = loadTeams(con.execute().parse());
		loadPlayers(teams);
		
		manager.setTeams(teams);
		return manager;	
	}

	private Connection doLogin(Manager manager) {
		return Jsoup.connect(UrlNames.SM_LOGIN)
				.data("email", manager.getUser()).data("clave", manager.getPass())
				.data("entrar", "Entrar").followRedirects(true)
				.method(Method.POST);
	}
	
	private List<Team> loadTeams(Document doc) {
		ManagerParser parser = new ManagerParserImpl();
		return parser.getTeams(doc);
	}
	
	private void loadPlayers(List<Team> teams) throws IOException {
		
		TeamParser parser = new TeamParserImpl();
		
		for (Team team : teams) {
			Document doc = con.url(new StringBuffer(UrlNames.SM_BASE).append(team.getTeamUrl()).toString()).get();
			team.setPlayers(parser.getPlayers(doc));
		}
		
	}
	
	public void sellPlayer(String sellUrl) throws IOException {
		con.url(UrlNames.SM_BASE + sellUrl).get();
	}
	
	public Document buyPlayerStepOne(String buyUrl) throws IOException {
		return con.url(UrlNames.SM_BASE + buyUrl).get();
	}
	
	public void buyPlayerStepTwo(String buyUrl) throws IOException {
		con.url(UrlNames.SM_BASE + buyUrl).get();
	}
}

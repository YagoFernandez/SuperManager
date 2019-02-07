package com.sm.parser;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.sm.model.SMPlayer;


public class TeamParserImpl implements TeamParser {

	private static final String PUESTO = "puesto";
	
	
	@Override
	public List<SMPlayer> getPlayers(Document doc) {
		
		List<SMPlayer> players = new ArrayList<SMPlayer>();
		
		for (int puesto=1; puesto < 12; puesto++) {
			players.add(getPlayer(doc, puesto));
		}
			
		return players;
	}
	
	private SMPlayer getPlayer(Document doc, int puesto) {
		
		Element element = doc.getElementById(PUESTO + puesto);
		
		if (element.text().contains("PUESTO LIBRE")) {
			return new EmptyPlayerParser().parse(element, puesto);
		}
		else {
			return new SignedPlayerParser().parse(element, puesto);
		}
		
		
	}
}

package com.sm.parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sm.model.SignablePlayer;

public class SignablePlayersParser {

	public List<SignablePlayer> parse(Element doc) {
		
		List<SignablePlayer> result = new ArrayList<SignablePlayer>();
		
		Element playersList = doc.getElementById("listajugadores");
		
		Elements players = playersList.getElementsByClass("par");
		Elements playersOdd = playersList.getElementsByClass("impar");
		
		players.addAll(playersOdd);
		
		
		for (Element player : players) {
			
			if (!player.attr("class").equals("impar inactiva") && !player.attr("class").equals("par inactiva")) {
			
				String name = player.getElementsByClass("jugequipo").first().getElementsByTag("a").first().text();
				String value = player.getElementsByClass("cotizacion").first().text();
				String points = player.getElementsByClass("promedio").first().text();
				String signUrl = player.getElementsByClass("botones").first().getElementsByTag("a").attr("href");
				
				SignablePlayer signablePlayer = new SignablePlayer();
				signablePlayer.setName(name);
				signablePlayer.setValue(value);
				signablePlayer.setSignUrl(signUrl);
				
				result.add(signablePlayer);
			}
		}
		
		Collections.sort(result);
		return result;
	}

}

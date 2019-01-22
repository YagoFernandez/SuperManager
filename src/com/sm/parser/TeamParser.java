package com.sm.parser;

import java.util.List;

import org.jsoup.nodes.Document;

import com.sm.model.Player;

public interface TeamParser {

	public List<Player> getPlayers(Document doc);
}

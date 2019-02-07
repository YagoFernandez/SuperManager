package com.sm.parser;

import java.util.List;

import org.jsoup.nodes.Document;

import com.sm.model.SMPlayer;

public interface TeamParser {

	public List<SMPlayer> getPlayers(Document doc);
}

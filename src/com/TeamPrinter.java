package com;

import com.sm.model.Player;
import com.sm.model.Team;

public class TeamPrinter {

	public static void print(Team team) {
		System.out.println(team);
		for (Player player : team.getPlayers())
			System.out.println(player);
		System.out.println();
	}

}

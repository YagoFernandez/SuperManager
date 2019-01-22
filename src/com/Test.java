package com;

import java.io.IOException;
import java.util.List;

import org.jsoup.nodes.Document;

import com.sm.http.ManagerLoader;
import com.sm.model.Manager;
import com.sm.model.Player;
import com.sm.model.SignablePlayer;
import com.sm.model.Team;
import com.sm.parser.SignablePlayersParser;

public class Test {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		imprimirEquipos();
//		venderJugador();
//		ficharJugador();
	}

	private static void imprimirEquipos() throws IOException {

		ManagerLoader ml = new ManagerLoader();
		Manager m = ml.load("yaguito", "papoce");

		for (Team team : m.getTeams()) {
			TeamPrinter.print(team);
		}
	}


	private static void venderJugador() throws IOException {

		ManagerLoader ml = new ManagerLoader();
		Manager m = ml.load("yaguito", "papoce");

		Team primerEquipo = m.getTeams().get(0);

		for (Player player : primerEquipo.getPlayers()) {

			if (!player.getSellUrl().isEmpty()) {

				ml.sellPlayer(player.getSellUrl());
				return;
			}

		}
	}

	private static void ficharJugador() throws IOException {

		ManagerLoader ml = new ManagerLoader();
		Manager m = ml.load("yaguito", "papoce");

		Team primerEquipo = m.getTeams().get(0);

		for (Player player : primerEquipo.getPlayers()) {

			if (!player.getBuyUrl().isEmpty()) {

				// desplegamos la lista de jugadores fichables
				Document jugadores = ml.buyPlayerStepOne(player.getBuyUrl());

				// los parseamos
				SignablePlayersParser parser = new SignablePlayersParser();
				List<SignablePlayer> fichables = parser.parse(jugadores);
				System.out.println(fichables);

				// fichamos el primero
				SignablePlayer fichaje = fichables.get(0);
				ml.buyPlayerStepTwo(fichaje.getSignUrl());

				return;
			}

		}
	}
}
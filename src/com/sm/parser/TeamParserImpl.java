package com.sm.parser;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sm.model.Player;

/**
 * 
 * @author usuario
 *
 * 
 * JUGADOR 
 *******************************************************************************************************************************
 *
 * <tr id="puesto1" class="impar">
 * 							
 * 	 <td class="iconos"> 
 * 	 	<img src="/images/web/mercado/ico-espanol.png" alt="Icono de espa�ol" title="Espa�ol" > 
 * 		<img src="/images/web/mercado/ico-extracomunitario.png" alt="Icono de extracomunitario" title="Extracomunitario">
 * 	 </td>
 * 	 
 * 	 <td class="fotos"> 
 * 	 	<a href="http://kiaenzona.com/jugador-liga-endesa/sergio-llull/" target="_blank">
 * 	 		<img src="http://www.acb.com/fotos_cara/jugadores/JB7GLACB61.jpg" width="33px" alt="jugador" title="jugador">
 * 	 	</a>
 * 	 </td>
 * 	 
 * 	 <td class="jugequipo">
 * 	 	<span class="negrita">
 * 	 		<a href="http://kiaenzona.com/jugador-liga-endesa/sergio-llull/" target="_blank">Llull, Sergio</a>
 * 	 	</span>
 * 	 	<br />
 * 	 	Real Madrid
 * 	 </td>
 * 	 
 * 	 <td class="cotizacion">1.534.296</td>
 * 	 <td class="puntosj colorweb_6 oculta">-</td>
 * 	 <td class="rebotesj colorweb_6 oculta">-</td>
 * 	 <td class="triplesj colorweb_6 oculta">-</td>
 * 	 <td class="asistenciasj colorweb_6 oculta">-</td>
 * 	 <td class="valoracionj colorweb_6">-</td>
 * 	 <td class="promedio">1.534.296 (J5)</td>
 * 	 
 * 	 <td class="rival">
 * 	 	<img src="/images/web/mercado/ico-partidofuera.png" />
 * 	 	<img src="/documentos/logos/logo-187.png" />
 * 	 </td>
 * 	 
 * 	 <td class="botones">
 * 	 	<nav class="compraventa"></nav> 
 * 	 	<nav class="resetuno"> 
 * 	 		<a href="/equipos/reset-uno/equipo_id/701103/puesto/1" class="gradient-azul gradient">Anular</a> 
 * 	 	</nav>
 * 	 </td>
 * 
 * </tr>
 * 
 * JUGADOR VAC�O
 ******************************************************************************************************************************* 
 * 
 * <tr id="puesto7" class="par">
 * 
 * 	 <td class="iconos"></td>
 *   
 * 	 <td class="fotos">
 * 	 	<img
 * 	 	src="/images/web/mercado/jugador_33x40.png" alt="jugador"
 * 	 	title="jugador">
 * 	 </td>
 *   
 * 	 <td class="jugequipo">
 * 	 	<span class="negrita">PUESTO LIBRE</span>
 * 	 </td>
 * 	 
 * 	 <td class="cotizacion"></td>
 * 	 <td class="puntosj oculta"></td>
 * 	 <td class="rebotesj oculta"></td>
 * 	 <td class="triplesj oculta"></td>
 * 	 <td class="asistenciasj oculta"></td>
 * 	 <td class="valoracionj"></td>
 * 	 <td class="promedio">&nbsp;</td>
 * 	 <td class="rival"></td>
 * 	 
 * 	 <td class="botones">
 * 	 	
 * 	 	<nav class="compraventa">
 * 	 		<a href="/equipos/muestra-jugadores/equipo_id/701103/posicion_id/3/puesto/7"
 * 	 		   class="popfichar gradient-azul gradient">Fichar</a>
 * 	 	</nav> 
 * 	 	
 * 	 	<nav class="resetuno"> 
 * 	 		<a href="/equipos/reset-uno/equipo_id/701103/puesto/7"
 * 	 		   class="gradient-azul gradient">Anular</a> 
 * 	 	</nav>
 * 	 	
 * 	 </td>
 * 
 * </tr>
 *
 */
public class TeamParserImpl implements TeamParser {

	private static final String PUESTO = "puesto";
	private static final String JUGEQUIPO = "jugequipo";
	
	@Override
	public List<Player> getPlayers(Document doc) {
		
		List<Player> players = new ArrayList<Player>();
		
		for (int puesto=1; puesto < 12; puesto++) {
			players.add(getPlayer(doc, puesto));
		}
			
		return players;
	}
	
	private Player getPlayer(Document doc, int puesto) {
		
		Element element = doc.getElementById(PUESTO + puesto);
		
		Player player = new Player();
		player.setName(parseName(element));
		player.setTeam(parseTeam(element));
		player.setPoints(parsePoints(element));
		player.setPrice(parsePrice(element));
		player.setImageUrl(parseImage(element));
		
		player.setNational(parseNational(element));
		player.setExtracommunity(parseExtracommunity(element));
		
		player.setInjured(parseInjured(element));
		player.setDoubtful(parseDoubtful(element));
		
		player.setCancelUrl(getCancelUrl(element));
		player.setBuyUrl(getBuyUrl(element));
		player.setSellUrl(getSellUrl(element));
		player.setReplaceUrl(getReplaceUrl(element));
		
		return player;
	}
	
	private String parseName(Element element) {
		
		String name = null;
		
		Element tdJugEquipo = element.getElementsByClass(JUGEQUIPO).first();
		Elements aLinkPlayer = tdJugEquipo.getElementsByTag("a");
		
		if (aLinkPlayer.isEmpty()) { //EmptyPlayer
			name = tdJugEquipo.text();
		}else {
			name = aLinkPlayer.first().text();
		}
		
		return name; 
	}
	
	private String parseTeam(Element element) {
		
		String name = null;
		
		Element tdJugEquipo = element.getElementsByClass(JUGEQUIPO).first();
		name = tdJugEquipo.ownText();
		
		return name; 
	}
	
	private String parsePoints(Element element) {
		
		String points = "-";
		
		Elements valoraciones = element.getElementsByClass("valoracionj");
		
		if (!valoraciones.isEmpty()) {
			points = valoraciones.first().text();
		}
		
		return points;
	}
	
	private String parsePrice(Element element) {
		
		String price = "0.0";
		
		Elements cotizaciones = element.getElementsByClass("cotizacion");
		
		if (!cotizaciones.isEmpty()) {
			price = cotizaciones.first().text();
		}
		
		return price;
	}
	
	private String parseImage(Element element) {
		
		String imageUrl = "";
		
		Elements fotos = element.getElementsByClass("fotos");
		
		if (!fotos.isEmpty()) {
			imageUrl = fotos.first().getElementsByTag("img").first().attr("src");
		}
		
		return imageUrl;
	}
	
private boolean parseNational(Element element) {
		
		boolean national = false;
		
		Elements iconos = element.getElementsByClass("iconos");
		
		if (!iconos.isEmpty()) {
			
			Element iconosTag = iconos.first();
			Elements result = iconosTag.getElementsByAttributeValue("src", "/images/web/mercado/ico-espanol.png");
			
			if (!result.isEmpty()) {
				national = true;
			}
		}
		
		return national;
	}
	
	private boolean parseExtracommunity(Element element) {
		
		boolean extracommunity = false;
		
		Elements iconos = element.getElementsByClass("iconos");
		
		if (!iconos.isEmpty()) {

			Element iconosTag = iconos.first();
			Elements result = iconosTag.getElementsByAttributeValue("src", "/images/web/mercado/ico-extracomunitario.png");
			
			if (!result.isEmpty()) {
				extracommunity = true;
			}
		}
		
		return extracommunity;
	}
	
	private boolean parseInjured(Element element) {
		
		boolean injured = false;
		
		Elements iconos = element.getElementsByClass("iconos");
		
		if (!iconos.isEmpty()) {
			
			Element iconosTag = iconos.first();
			Elements result = iconosTag.getElementsByAttributeValue("src", "/images/web/mercado/ico-lesionado.png");
			
			if (!result.isEmpty()) {
				injured = true;
			}
		}
		
		return injured;
	}
	
	private boolean parseDoubtful(Element element) {
		
		boolean doubtful = false;
		
		Elements iconos = element.getElementsByClass("iconos");
		
		if (!iconos.isEmpty()) {
			
			Element iconosTag = iconos.first();
			Elements result = iconosTag.getElementsByAttributeValue("src", "/images/web/mercado/ico-masinfo.png");
			
			if (!result.isEmpty()) {
					doubtful = true;
			}
		}
		
		return doubtful;
	}
	
	/*
	 * <nav class="resetuno"> 
	 * 	<a href="/equipos/reset-uno/equipo_id/701103/puesto/1" class="gradient-azul gradient">Anular</a> 
	 * </nav> 
	 */
	private String getCancelUrl(Element player) {
		
		String url = "";
		
		if (! player.getElementsByClass("resetuno").isEmpty() && 
				! player.getElementsByClass("resetuno").first().getElementsByTag("a").isEmpty()) {
			url = player.getElementsByClass("resetuno").first().getElementsByTag("a").first().attr("href");
		}
		
		return url;
	}
	
	
	/*
	 * <nav class="compraventa">
	 * 	<a 	href="/equipos/muestra-jugadores/equipo_id/701103/posicion_id/3/puesto/7"
	 * 		class="popfichar gradient-azul gradient">Fichar</a>
	 * </nav>
	 */
	private String getBuyUrl(Element player) {
		
		String url = "";
		
		Elements navsCompraVenta = player.getElementsByClass("compraventa"); 
		
		if (!navsCompraVenta.isEmpty()) {
			
			if (navsCompraVenta.text().equals("Fichar")) {
			
				if (! player.getElementsByClass("compraventa").first().getElementsByTag("a").isEmpty()) {
					url = player.getElementsByClass("compraventa").first().getElementsByTag("a").first().attr("href");
				}
			}
		}
		
		return url;
	}
	
	/*
	 * <nav class="compraventa">
	 * 	<a 	href="/equipos/vender/equipo_id/701103/jugador_id/1500/puesto/1/posicion_id/1" 
	 * 		class="popfichar gradient-azul gradient">Vender</a>
	 * </nav>
	 */
	private String getSellUrl(Element player) {
		
		String url = "";
		
		Elements navsCompraVenta = player.getElementsByClass("compraventa"); 
		
		if (!navsCompraVenta.isEmpty()) {
			
			if (navsCompraVenta.text().equals("Vender")) {
			
				if (! player.getElementsByClass("compraventa").first().getElementsByTag("a").isEmpty()) {
					url = player.getElementsByClass("compraventa").first().getElementsByTag("a").first().attr("href");
				}
			}
		}
		
		return url;
	}
	
	/*
	 *  <nav class="compraventa">
	 *   <a href="/equipos/vender/equipo_id/701103/jugador_id/1583/puesto/5/posicion_id/3/sustituir/1" 
	 *   	class="popfichar gradient-verde gradient">Sustituir</a>
	 *  </nav>
	 */
	private String getReplaceUrl(Element player) {
		
		String url = "";
		
		Elements navsCompraVenta = player.getElementsByClass("compraventa"); 
		
		if (!navsCompraVenta.isEmpty()) {
			
			if (navsCompraVenta.text().equals("Sustituir")) {
			
				if (! player.getElementsByClass("compraventa").first().getElementsByTag("a").isEmpty()) {
					url = player.getElementsByClass("compraventa").first().getElementsByTag("a").first().attr("href");
				}
			}
		}
		
		return url;
	}

}

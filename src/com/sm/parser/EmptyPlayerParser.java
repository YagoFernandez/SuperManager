package com.sm.parser;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sm.model.EmptyPlayer;


/** 
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
 
public class EmptyPlayerParser {

	public EmptyPlayer parse(Element element, int puesto) {
		
		EmptyPlayer player = new EmptyPlayer();

		player.setCancelUrl(getCancelUrl(element));
		player.setBuyUrl(getBuyUrl(element));
		player.setSellUrl(getSellUrl(element));
		player.setReplaceUrl(getReplaceUrl(element));
		
		return player;
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

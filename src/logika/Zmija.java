/**
 * 
 */
package logika;

import java.util.ArrayList;

/**
 * @author Nudzejma
 * Posljednja izmjena: 04.06.2017.g.
 * Predmet: Komparativna analiza programskih jezika
 * Profesor: Vedad Letiæ
 */

/**
 * Zmija.java - klasa koja predstavlja zmiju
 * Ova klasa sadrži ArrayList-u objekata tipa JednoPolje koja 
 * predstavlja tijelo zmije.
 */
public class Zmija {
	
	private ArrayList<JednoPolje> tijeloZmije;
	
	public Zmija(){
		super();
		tijeloZmije = new ArrayList<JednoPolje>();
		tijeloZmije.add(new JednoPolje(1));
	}

	public ArrayList<JednoPolje> getTijeloZmije() {
		return tijeloZmije;
	}

	public void setTijeloZmije(ArrayList<JednoPolje> tijeloZmije) {
		this.tijeloZmije = tijeloZmije;
	}
	
	public JednoPolje getGlavuZmije(){
		return tijeloZmije.get(0);
	}
}

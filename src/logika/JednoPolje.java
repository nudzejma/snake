/**
 * 
 */
package logika;

/**
 * @author Nudzejma
 * Posljednja izmjena: 04.06.2017.g.
 * Predmet: Komparativna analiza programskih jezika
 * Profesor: Vedad Letiæ
 */

 /** 
 *JednoPolje.java - klasa koja nam predstavlja jedno polje poljane.
 * Ova klasa ima atribute:
 * - x i y koji æe predstavljati koordinate polja u tabeli
 * - STATUS_IMA_ZMIJE, STATUS_NEMA_ZMIJE, STATUS_IMA_HRANE koji nam 
 * 	 služe da znamo da li je polje prazno, ili se na njemu nalaze zmija ili hrana
 * - statusPolja koja uzima vrijednost neke od final atributa
 */
public class JednoPolje {
	
	public static final int STATUS_NEMA_ZMIJE = 0;
	public static final int STATUS_IMA_ZMIJE = 1;
	public static final int STATUS_IMA_HRANE = 2;
	public static final int STATUS_GLAVA_JE_TU = 3;
	
	private int x;
	private int y;
	private int statusPolja; 

	public JednoPolje(){
		super();
	}
	
	public JednoPolje(int status){
		super();
		statusPolja = status;
	}
	
	public JednoPolje(int x, int y, int status){
		super();
		this.x = x;
		this.y = y;
		this.statusPolja = status;
	}
	
	public char prikaziPolje(){
		if(statusPolja == STATUS_GLAVA_JE_TU)
			return 'O';
		else if ( statusPolja == STATUS_IMA_ZMIJE)
			return 'o';
		else if(statusPolja == STATUS_IMA_HRANE)
			return '*';
		return ' ';
	}

	public int getStatusPolja() {
		return statusPolja;
	}

	public void setStatusPolja(int statusPolja) {
		this.statusPolja = statusPolja;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}


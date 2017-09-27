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
 * Poljana.java - klasa koja predstalja poljanu na kojoj igramo
 * Ova klasa sadrži atribute:
 * - visina i širina koji predstavljaju velièinu poljane,
 * - tabela koja predstavlja prostor na kojem se igra,
 * - igraJeAktivna koja nam govori da li je igra u toku ili ne,
 * - zmija koji je objekat tipa Zmija koji predstavlja zmiju s kojom æemo igrati.
 */

//konstruktor klase Poljana
public class Poljana {
	private int visina = 0; 
	private int sirina = 0;
	private boolean igraJeAktivna;
	private JednoPolje[][] tabela;
	private Zmija zmija;
	
	private int hranaX = 0, hranaY = 0;
	
	public Poljana(int visina, int sirina){
		super();
		this.visina = visina; 
		this.sirina = sirina;
		this.igraJeAktivna = true;
		
		this.tabela = new JednoPolje[this.visina][this.sirina];
		for (int i = 0; i < this.visina; i++) {
			for (int j = 0; j < this.sirina; j++) {
				tabela[i][j] = new JednoPolje(JednoPolje.STATUS_NEMA_ZMIJE);
			}
		}
		
		this.zmija = new Zmija();
		//postavi glavu zmije na sredinu
		zmija.getTijeloZmije().get(0).setX(this.visina/2);
		zmija.getTijeloZmije().get(0).setY(this.sirina/2);
		tabela[zmija.getTijeloZmije().get(0).getX()][zmija.getTijeloZmije().get(0).getY()].setStatusPolja(JednoPolje.STATUS_GLAVA_JE_TU);
		
		//random generisi hranu
		generisiNovuHranu();
	}
	
	

	public int getVisina() { return visina; }

	public void setVisina(int visina) { this.visina = visina; }

	public int getSirina() { return sirina; }

	public void setSirina(int sirina) { this.sirina = sirina; }

	public boolean isIgraJeAktivna() { return igraJeAktivna; }

	public void setIgraJeAktivna(boolean igraJeAktivna) { this.igraJeAktivna = igraJeAktivna; }

	public char[][] getPoljana() {
		char[][] poljana = new char[visina][sirina];
		for (int i = 0; i < visina; i++) {
			for (int j = 0; j < sirina; j++) {
				poljana[i][j] = tabela[i][j].prikaziPolje();
			}
		}
		return poljana;
	}
	
	public char getPoljana(int i, int j){ return tabela[i][j].prikaziPolje(); }
	
	public Zmija getZmija(){ return zmija;}
	
	public int getHranaX() { return hranaX; }
	public int getHranaY() { return hranaY; }

	private void setGlavuZmije(int x, int y){
		zmija.getTijeloZmije().get(0).setX(x);
		zmija.getTijeloZmije().get(0).setY(y);
		tabela[x][y].setStatusPolja(JednoPolje.STATUS_GLAVA_JE_TU);
	}
	
	/* 
	 * Funkcija odigraj() koja kao parametar prima objekat tipa String 
	 * koji govori gdje æe se zmija kretati (desno, gore, lijevo, dolje).
	 *  
	 * Najprije provjeravamo da li je sljedeæe polje na koje korisnik želi pomjeriti zmiju
	 * unutar tabele, te da li se na tom polju veæ nalazi zmija. Ukoliko jedan od ova dva uslova
	 * zadovoljen, varijablu igraJeAktivna postavljamo na false, te je tu kraj igre.
	 * Dalje, ukoliko nijedan od ova dva uslova nije zadovoljen, onda prvo provjeravamo da li se 
	 * na tom polju nalazi hrana. Ako se nalazi, u tijelo zmije dodajemo jos jedan objekat tipa JednoPolje
	 * te generišemo novu hranu. U suprotnom, pomjeramo tijelo zmije za jedno polje u tabeli.
	 * Dalje, postavljamo glavu zmije na novo željeno polje.
	*/
	public void odigraj(String potez){
		int duzinaZmije = zmija.getTijeloZmije().size();
		potez = potez.toUpperCase();
		if(potez.equals("D")){
			if(zmija.getTijeloZmije().get(0).getY()+1 >= sirina){
				igraJeAktivna = false;
			}else{
				int statusNovogPolja = tabela[zmija.getTijeloZmije().get(0).getX()][zmija.getTijeloZmije().get(0).getY()+1].getStatusPolja();
				if(statusNovogPolja != JednoPolje.STATUS_IMA_ZMIJE ){
					if(statusNovogPolja == JednoPolje.STATUS_IMA_HRANE){
						zmija.getTijeloZmije().add(new JednoPolje(zmija.getTijeloZmije().get(duzinaZmije-1).getX(),zmija.getTijeloZmije().get(duzinaZmije-1).getY(), JednoPolje.STATUS_IMA_ZMIJE));
						pomjeriTijelo();
						generisiNovuHranu();
					}else{
						tabela[zmija.getTijeloZmije().get(duzinaZmije-1).getX()][zmija.getTijeloZmije().get(duzinaZmije-1).getY()].setStatusPolja(JednoPolje.STATUS_NEMA_ZMIJE);
						pomjeriTijelo();
					}
					setGlavuZmije(zmija.getTijeloZmije().get(0).getX(), zmija.getTijeloZmije().get(0).getY()+1);
				}else{
					igraJeAktivna = false;
				}
			}
		}else if(potez.equals("W")){
			if(zmija.getTijeloZmije().get(0).getX()-1 < 0){
				igraJeAktivna = false;
			}else{
				int statusNovogPolja = tabela[zmija.getTijeloZmije().get(0).getX()-1 ][zmija.getTijeloZmije().get(0).getY()].getStatusPolja();
				if(statusNovogPolja != JednoPolje.STATUS_IMA_ZMIJE ){
					if(statusNovogPolja == JednoPolje.STATUS_IMA_HRANE){
						zmija.getTijeloZmije().add(new JednoPolje(zmija.getTijeloZmije().get(duzinaZmije-1).getX(),zmija.getTijeloZmije().get(duzinaZmije-1).getY(), JednoPolje.STATUS_IMA_ZMIJE));
						pomjeriTijelo();
						generisiNovuHranu();
					}else{
						tabela[zmija.getTijeloZmije().get(duzinaZmije-1).getX()][zmija.getTijeloZmije().get(duzinaZmije-1).getY()].setStatusPolja(JednoPolje.STATUS_NEMA_ZMIJE);
						pomjeriTijelo();
					}
					setGlavuZmije(zmija.getTijeloZmije().get(0).getX()-1, zmija.getTijeloZmije().get(0).getY());
				}else{
					igraJeAktivna = false;
				}
			}
		}else if(potez.equals("A")){
			if(zmija.getTijeloZmije().get(0).getY()-1 < 0){
				igraJeAktivna = false;
			}else{
				int statusNovogPolja = tabela[zmija.getTijeloZmije().get(0).getX()][zmija.getTijeloZmije().get(0).getY()-1].getStatusPolja();
				if(statusNovogPolja != JednoPolje.STATUS_IMA_ZMIJE ){
					if(statusNovogPolja == JednoPolje.STATUS_IMA_HRANE){
						zmija.getTijeloZmije().add(new JednoPolje(zmija.getTijeloZmije().get(duzinaZmije-1).getX(),zmija.getTijeloZmije().get(duzinaZmije-1).getY(), JednoPolje.STATUS_IMA_ZMIJE));
						pomjeriTijelo();
						generisiNovuHranu();
					}else{
						tabela[zmija.getTijeloZmije().get(duzinaZmije-1).getX()][zmija.getTijeloZmije().get(duzinaZmije-1).getY()].setStatusPolja(JednoPolje.STATUS_NEMA_ZMIJE);
						pomjeriTijelo();
					}
					setGlavuZmije(zmija.getTijeloZmije().get(0).getX(), zmija.getTijeloZmije().get(0).getY()-1);
				}else{
					igraJeAktivna = false;
				}
			}
		}else if(potez.equals("S")){
			if(zmija.getTijeloZmije().get(0).getX()+1 >= visina){
				igraJeAktivna = false;
			}else{
				int statusNovogPolja = tabela[zmija.getTijeloZmije().get(0).getX()+1][zmija.getTijeloZmije().get(0).getY()].getStatusPolja();
				if(statusNovogPolja != JednoPolje.STATUS_IMA_ZMIJE ){
					if(statusNovogPolja == JednoPolje.STATUS_IMA_HRANE){
						zmija.getTijeloZmije().add(new JednoPolje(zmija.getTijeloZmije().get(duzinaZmije-1).getX(),zmija.getTijeloZmije().get(duzinaZmije-1).getY(), JednoPolje.STATUS_IMA_ZMIJE));
						pomjeriTijelo();
						generisiNovuHranu();
					}else{
						tabela[zmija.getTijeloZmije().get(duzinaZmije-1).getX()][zmija.getTijeloZmije().get(duzinaZmije-1).getY()].setStatusPolja(JednoPolje.STATUS_NEMA_ZMIJE);
						pomjeriTijelo();
					}
					setGlavuZmije(zmija.getTijeloZmije().get(0).getX()+1, zmija.getTijeloZmije().get(0).getY());
				}else{
					igraJeAktivna = false;
				}
			}
		}
	}
	
	/*
	 * Funckija pomjeriTijelo() koja pomjera tijelo zmije,
	 * na naèin da jedan dio tijela stavljamo tamo gdje je bio prethodni.
	 */
	private void pomjeriTijelo(){
		for(int i=zmija.getTijeloZmije().size()-1; i>=1; i--){
			zmija.getTijeloZmije().get(i).setX(zmija.getTijeloZmije().get(i-1).getX());
			zmija.getTijeloZmije().get(i).setY(zmija.getTijeloZmije().get(i-1).getY());
			tabela[zmija.getTijeloZmije().get(i).getX()][zmija.getTijeloZmije().get(i).getY()].setStatusPolja(JednoPolje.STATUS_IMA_ZMIJE);
		}
	}

	/*
	 * Funckija koja generise random novu hranu pri èemu pazimo
	 *  da ne generišemo tamo gdje je zmija.
	 */
	private void generisiNovuHranu(){
		int randomX = (int)(Math.random() * this.visina);
		int randomY = (int)(Math.random() * this.sirina);
		while(tabela[randomX][randomY].getStatusPolja() == JednoPolje.STATUS_IMA_ZMIJE){
			 randomX = (int)(Math.random() * this.visina);
			 randomY = (int)(Math.random() * this.sirina);
		}
		hranaX = randomX; hranaY = randomY;
		tabela[randomX][randomY].setStatusPolja(JednoPolje.STATUS_IMA_HRANE);
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
}

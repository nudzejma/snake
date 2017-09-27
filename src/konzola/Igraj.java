package konzola;

import java.util.Scanner;

import logika.Poljana;

/**
 * @author Nudzejma
 * Posljednja izmjena: 04.06.2017.g.
 * Predmet: Komparativna analiza programskih jezika
 * Profesor: Vedad Letiæ
 */

/**
 * Igraj.java - klasa koja nam omoguæava igranje igrice u konzoli
 */
public class Igraj {
	public static void main(String[] args) {
		Poljana poljana = new Poljana(10, 10);
		System.out.println("Snake 1.0.0\nIgricu igrate kreæuæi se:\n\tD - desno\n\tW - gore\n\tA - lijevo\n\tS - dolje\nSretno!");
		Scanner in = new Scanner(System.in);
		String potez;
		
		//while petlja koja se izvrašava sve dok igraè ne odluèi da ne igra više
		while(true){
			
			//do-while petlja koja se izvršava sve dok je igra u toku
			do{
				System.out.println(prikaz(poljana));
				System.out.print("Unesite potez: ");
				potez = in.nextLine();
				poljana.odigraj(potez);
				System.out.println(prikaz(poljana));
			}while(poljana.isIgraJeAktivna());
			System.out.println("Igra je završena!\nDa li želite igrati ponovo? (Y/N)");
			
			//do-while petlja koja sse izvršava dok igraè ne unese taèno što se od njega traži
			do{
				potez = in.nextLine();
				potez = potez.toUpperCase();
			}while(!potez.equals("N") && !potez.equals("Y"));
			
			if(potez.equals("N")){
				System.out.println("Hvala Vam na igranju. Zdravo!");
				break;
			}else{
				poljana = new Poljana(10, 10);
			}
		}
		in.close();
	}
	
	/*
	 * Funkcija prikaz(Poljana) nam omugaæava željeni prikaz 
	 * poljane na kojoj igramo, tako što konstruišemo String.
	 */
	private static String prikaz(Poljana poljana) {
		String str, linija = "+", prvaLinija="+";
		
		for (int i = 0; i < poljana.getSirina(); i++) {
			prvaLinija = String.format("%s%s",  prvaLinija, "----");
			linija = String.format("%s%4s",  linija, "");
		}
		prvaLinija = String.format("%s%s",  prvaLinija, "+");
		linija = String.format("%s%s",  linija, "+");
		str = prvaLinija;
		for (int i = 0; i < poljana.getVisina(); i++) {
			str = String.format("%s%s", str, "\n|");
			for (int j = 0; j < poljana.getSirina(); j++) {
				str = String.format("%s %s %s", str, poljana.getPoljana(i, j), " "); // str = str + " " + (tabela[i][j]) + " |";
			}
			if(i == poljana.getVisina() -1)
				str = String.format("%s%s\n%s", str,"|", prvaLinija);
			else
				str = String.format("%s%s\n%s", str, "|", linija); // str += "\n" + linija
		}
		return str;
	}
}

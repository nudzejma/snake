package konzola;

import java.util.Scanner;

import logika.Poljana;

/**
 * @author Nudzejma
 * Posljednja izmjena: 04.06.2017.g.
 * Predmet: Komparativna analiza programskih jezika
 * Profesor: Vedad Leti�
 */

/**
 * Igraj.java - klasa koja nam omogu�ava igranje igrice u konzoli
 */
public class Igraj {
	public static void main(String[] args) {
		Poljana poljana = new Poljana(10, 10);
		System.out.println("Snake 1.0.0\nIgricu igrate kre�u�i se:\n\tD - desno\n\tW - gore\n\tA - lijevo\n\tS - dolje\nSretno!");
		Scanner in = new Scanner(System.in);
		String potez;
		
		//while petlja koja se izvra�ava sve dok igra� ne odlu�i da ne igra vi�e
		while(true){
			
			//do-while petlja koja se izvr�ava sve dok je igra u toku
			do{
				System.out.println(prikaz(poljana));
				System.out.print("Unesite potez: ");
				potez = in.nextLine();
				poljana.odigraj(potez);
				System.out.println(prikaz(poljana));
			}while(poljana.isIgraJeAktivna());
			System.out.println("Igra je zavr�ena!\nDa li �elite igrati ponovo? (Y/N)");
			
			//do-while petlja koja sse izvr�ava dok igra� ne unese ta�no �to se od njega tra�i
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
	 * Funkcija prikaz(Poljana) nam omuga�ava �eljeni prikaz 
	 * poljane na kojoj igramo, tako �to konstrui�emo String.
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

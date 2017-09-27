package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import logika.Poljana;

/**
 * @author Nudzejma
 * Posljednja izmjena: 30.6.2017.g.
 * Predmet: Komparativna analiza programskih jezika
 * Profesor: Vedad Letiæ
 */

 /** 
 * PanelZaIgru.java - klasa koja nam služi da definišemo panel koji æe biti
 * unutrašnjost samog okvira Igra
 * Ova klasa sadrži dva panela - prvi, koji æemo koristiti za postavljanje labele na kojoj
 * æe se nalaziti slika i drugog, koji æemo biti tipa PoljanaZaIgru i koji æemo
 * koristiti za samu igricu i igranje.
 */
public class PanelZaIgru extends JPanel{
	private JPanel panel1;
	private PoljanaZaIgru panel2;
	private ImageIcon naslov;
	private JLabel labela_naslov;
	
	public PanelZaIgru(Poljana p){
		super();
		this.setBackground(Color.DARK_GRAY);
		this.setSize(new Dimension(550, 620));
		this.setLayout(null);
		
		//postavljanje prvog panela
		panel1 = new JPanel();
		panel1.setLayout(null);
		panel1.setSize(new Dimension(550, 70));
		naslov = new ImageIcon("slike/button_snake.png");
		labela_naslov = new JLabel(naslov);
		labela_naslov.setBounds(new Rectangle(new Point(0, 0), labela_naslov.getPreferredSize()));
		panel1.add(labela_naslov);
		panel1.setBounds(new Rectangle(new Point(0, 0), panel1.getPreferredSize()));
		
		//postavljanje drugog panela
		panel2 = new PoljanaZaIgru(p);
		panel2.setBounds(new Rectangle(new Point(0, panel1.getHeight()), panel2.getPreferredSize()));
		this.add(panel1);
		this.add(panel2);
	}
}

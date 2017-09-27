package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;

import logika.Poljana;

/**
 * @author Nudzejma
 * Posljednja izmjena: 30.6.2017.g.
 * Predmet: Komparativna analiza programskih jezika
 * Profesor: Vedad Letiæ
 */

 /** 
 * Igra.java - klasa koja nam služi da prikažemo ekran na kojem se nalazi igra.
 * Ova klasa je podklasa klase JFrame i u njenom konstruktoru unutrašnjost ovog okvira
 * postavljamo na objekat tipa PanelZaIgru, kojem prosljeðujemo objekat tipa Poljana
 * koji nam služi za samo igranje.
 */
public class Igra extends JFrame{
	
	public static void main(String[] args) {
		Igra igra = new Igra(new Poljana(22,22));
	}
	
	public Igra(Poljana p) throws HeadlessException {
		super();
		this.setContentPane(new PanelZaIgru(p));
		this.setUndecorated(true);
		this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		this.setResizable(false);
		this.setVisible(true);		
		this.setSize(new Dimension(550,620));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2 - this.getWidth()/2, dim.height/2-this.getHeight()/2);
	}

}

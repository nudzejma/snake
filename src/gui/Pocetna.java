/**
 * 
 */
package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;

import logika.Poljana;

/**
 * @author Nudzejma
 * Posljednja izmjena: 30.6.2017.g.
 * Predmet: Komparativna analiza programskih jezika
 * Profesor: Vedad Letiæ
 */

 /** 
 * Pocetna.java - klasa koja nam služi da prikažemo poèetni ekran.
 * Ova klasa igraèu nudi dvije opcije, da igra ili da napusti, odnosno
 * zatvori igricu.
 * Sadrži objekat tipa JPanel koji æemo koristiti da željeni sadržaj prikažemo.
 */
public class Pocetna extends JFrame{
	private JPanel pocetniPanel;
	
	private ImageIcon naslov;
	private ImageIcon igraj;
	private ImageIcon napusti;
	
	private JLabel labela_igraj;
	private JLabel labela_napusti;
	private JLabel labela_naslov;
	
	public static void main(String[] args) {
		Pocetna pocetna = new Pocetna();
	}
	
	private void Dispose(){
		this.dispose();
	}
	
	public Pocetna(){
		super();
		
		//postavljanje okvira
		this.setUndecorated(true);
		this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		this.setResizable(false);
		this.setSize(new Dimension(200, 300));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2 - this.getWidth()/2, dim.height/2-this.getHeight()/2);
		
		//postavljanje panela
		pocetniPanel = new JPanel();
		pocetniPanel.setBackground(Color.DARK_GRAY);
		pocetniPanel.setPreferredSize(new Dimension(200, 300));
		
		//inicijaliziranje objekata tipa ImageIcon koje æemo koristiti da stavimo na labele 
		igraj = new ImageIcon("slike/button_igraj.png");
		napusti = new ImageIcon("slike/button_napusti.png");
		naslov = new ImageIcon("slike/button_snake_manje.png");
		
		//inicijaliziranje objekata tipa JLabel i stavljanje osluškivaèa na njih
		labela_naslov = new JLabel(naslov);
		labela_igraj = new JLabel(igraj);
		labela_igraj.addMouseListener(new MouseAdapter() 
        {
            @Override
            public void mouseClicked(MouseEvent e) 
            {
            	/*Ako je korisnik kliknuo na labelu "Igraj" kreiramo igru tako
            	 * da pravimo objekat tipa Igra koji predstavlja novi objekat
            	 * podklase JFrame, te ovaj okvir zatvaramo.
            	 */
                 new Igra(new Poljana(24,24));
                 Dispose();
            }
        });
        
        labela_napusti = new JLabel(napusti);
        labela_napusti.addMouseListener(new MouseAdapter() 
        {
            @Override
            public void mouseClicked(MouseEvent e) 
            {
            	/*Ako je korisnik kliknuo na labelu "Napusti" zatvaramo potpuno igricu.*/
            	System.exit(0); 
            }
        });
        
        //Postavljanje layout-a panela na null kako bismo labele postavili gdje hoæemo.
        pocetniPanel.setLayout(null);
    
        labela_naslov.setBounds(new Rectangle(new Point(0, 0), labela_naslov.getPreferredSize()));
        labela_igraj.setBounds(new Rectangle(new Point(50, 100), labela_igraj.getPreferredSize()));
        labela_napusti.setBounds(new Rectangle(new Point(50,150), labela_napusti.getPreferredSize()));
        
        //Na kraju sve dodajemo na panel, a unutrašnjost okvira na dati panel.
        pocetniPanel.add(labela_naslov);
        pocetniPanel.add(labela_igraj);
        pocetniPanel.add(labela_napusti);
		this.setContentPane(pocetniPanel);
		this.setVisible(true);
	}
}

/**
 * 
 */
package gui;

import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import logika.Poljana;

/**
 * @author Nudzejma
 * Posljednja izmjena: 30.6.2017.g.
 * Predmet: Komparativna analiza programskih jezika
 * Profesor: Vedad Letiæ
 */

 /** 
 * PoljanaZaIgru.java - klasa koja nam predstavlja panel za igru.
 * Ova klasa sadrži objekte tipa: Poljana koji predstavlja tabelu na kojoj se igra,
 * String u kojem æemo pamtiti koji je igraè potez odigrao, Timer koji nam služi
 * da se zmija kreæe u vremenu, te ImageIcon koji nam služe da predstavimo glavu zmije,
 * tijelo zmije i hranu preko slika.
 * Takoðer klasa koristi ActionListener i KeyListener interfejse koji omoguæavaju
 * igraèu korištenje tastature za igru.
 */
public class PoljanaZaIgru extends JPanel implements ActionListener,KeyListener{
	private Poljana p;
	
	private String trenutniPotez;
	private Timer timer;

	private ImageIcon glavaDolje;
	private ImageIcon glavaDesno;
	private ImageIcon glavaLijevo;
	private ImageIcon glavaGore;
	private ImageIcon tijeloZmije;
	private ImageIcon hrana;
	
	public PoljanaZaIgru(Poljana p){
		super();
		postavi(p);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
	}
	
	private void postavi(Poljana p){
		this.p = p;
		trenutniPotez = "";
		timer = new Timer(100, this);
		timer.start();
		this.setBackground(Color.DARK_GRAY);
		this.setPreferredSize(new Dimension(600, 620));
		this.setLayout(null);
	
	}
	
	/*
	 * (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		
		//inicijalizacija slika
		glavaDolje = new ImageIcon("slike/downmouth.png");
		glavaDesno = new ImageIcon("slike/rightmouth.png");
		glavaLijevo = new ImageIcon("slike/leftmouth.png");
		glavaGore = new ImageIcon("slike/upmouth.png");
		tijeloZmije = new ImageIcon("slike/snakeimage.png");
		
		//na poèetku postavljamo glavu zmije u desnom smjeru
		glavaDesno.paintIcon(this, g, p.getZmija().getTijeloZmije().get(0).getX()*25, p.getZmija().getTijeloZmije().get(0).getY()*25);
		
		//u odnosu koji potez igraè odigra glava zmije je u tom smjeru
		if(trenutniPotez == "W")
			glavaLijevo.paintIcon(this, g, p.getZmija().getTijeloZmije().get(0).getX()*25, p.getZmija().getTijeloZmije().get(0).getY()*25);
		else if(trenutniPotez == "S")
			glavaDesno.paintIcon(this, g, p.getZmija().getTijeloZmije().get(0).getX()*25, p.getZmija().getTijeloZmije().get(0).getY()*25);
		else if(trenutniPotez == "A")
			glavaGore.paintIcon(this, g, p.getZmija().getTijeloZmije().get(0).getX()*25, p.getZmija().getTijeloZmije().get(0).getY()*25);
		else if(trenutniPotez == "D")
			glavaDolje.paintIcon(this, g, p.getZmija().getTijeloZmije().get(0).getX()*25, p.getZmija().getTijeloZmije().get(0).getY()*25);
		
		//postavljamo i izgled tijela zmije i hrane
		for(int i=1; i<p.getZmija().getTijeloZmije().size(); i++){
			tijeloZmije.paintIcon(this, g, p.getZmija().getTijeloZmije().get(i).getX()*25, p.getZmija().getTijeloZmije().get(i).getY()*25);
		}

		hrana = new ImageIcon("slike/enemy.png");
		hrana.paintIcon(this, g, p.getHranaX()*25, p.getHranaY()*25);
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		if(p.isIgraJeAktivna()){
			//dok je igra aktivna igramo igru i zmija se kreæe dok je timer postavljen
			p.odigraj(trenutniPotez);
			repaint();
		}else{
			//kada je kraj igre zaustavljamo timer, zatvaramo trenutni prozor i otvaramo novi tipa NoaIgra
			timer.stop();
			JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
			topFrame.dispose();
			new NovaIgra();
		}
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		//u zavisnoti koju tipku je igraè pritisnuo tako postavljamo potez
		int i = e.getKeyCode();
		if(i == KeyEvent.VK_LEFT && trenutniPotez != "S"){
			trenutniPotez = "W";
		}else if(i == KeyEvent.VK_RIGHT && trenutniPotez != "W"){
			trenutniPotez = "S";
		}else if(i == KeyEvent.VK_UP && trenutniPotez != "D"){
			trenutniPotez = "A";
		}else if(i == KeyEvent.VK_DOWN && trenutniPotez != "A"){
			trenutniPotez = "D";
		}
	}

	/*
	 * (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	/*
	 * (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent arg0) {
	}

	
}

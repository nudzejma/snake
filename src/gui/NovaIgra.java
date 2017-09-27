package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
 * NovaIgra.java - klasa koja nam služi da prikažemo novi ekran nakon što igraè završi igru.
 * Ova klasa sadrži objekat klase JPanel koji nam služi postavljanje unutrašnjosti okvira
 */
public class NovaIgra extends JFrame {
	private JPanel panel;
	private ImageIcon naslov;
	private ImageIcon igraj;
	private ImageIcon napusti;
	private JLabel labela_igraj;
	private JLabel labela_napusti;
	private JLabel labela_naslov;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NovaIgra novaIgra = new NovaIgra();
	}
	
	public NovaIgra(){
		super();
		
		//postavljanje okvira
		this.setResizable(false);
		this.setSize(new Dimension(200, 300));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2 - this.getWidth()/2, dim.height/2-this.getHeight()/2);
		this.setUndecorated(true);
		this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		
		//postavljanje panela
		panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setPreferredSize(new Dimension(200, 300));
		panel.setLayout(null);
		
		//inicijalizacija objekata tipa ImageIcon i JLabel na koje æemo postaviti date slike
		igraj = new ImageIcon("slike/button_nova_igra.png");
		napusti = new ImageIcon("slike/button_napusti.png");
		labela_igraj = new JLabel(igraj);
		labela_igraj.setBounds(new Rectangle(new Point(50, 100), labela_igraj.getPreferredSize()));
		labela_igraj.addMouseListener(new MouseAdapter() 
        {
            @Override
            public void mouseClicked(MouseEvent e){
                	dispose();
                    new Igra(new Poljana(22,22));
                           
            }
        });
		labela_napusti = new JLabel(napusti);
        labela_napusti.setBounds(new Rectangle(new Point(50, 150), labela_napusti.getPreferredSize()));
        labela_napusti.addMouseListener(new MouseAdapter() 
        {
            @Override
            public void mouseClicked(MouseEvent e){
            	System.exit(0);
            }
        });
        
        naslov = new ImageIcon("slike/button_snake_manje.png");
		labela_naslov = new JLabel(naslov);
		labela_naslov.setBounds(new Rectangle(new Point(0, 0), labela_naslov.getPreferredSize()));
		
		panel.add(labela_naslov);
        panel.add(labela_igraj);
		panel.add(labela_napusti);
		this.setContentPane(panel);
		this.setVisible(true);
	}
}

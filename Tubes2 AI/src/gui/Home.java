package gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Home extends JPanel {

	private BufferedImage background;
	private BufferedImage title;
	private JButton helpbtn;
	private JButton startbtn;
	private JButton aboutbtn;
	
	public Home() {
       try {                
          title = ImageIO.read(new File("images/Title.png"));
          background = ImageIO.read(new File("images/background.jpg"));
          
          //help button
          helpbtn = new JButton(new ImageIcon("images/helpbutton.png"));
          setLayout(null);
          helpbtn.setLocation(200, 390);
          helpbtn.setSize(150, 180);
          helpbtn.setOpaque(false);
          helpbtn.setContentAreaFilled(false);
          helpbtn.setBorderPainted(false);
          helpbtn.setFocusPainted(false);
          helpbtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
          
          //start button
          startbtn = new JButton(new ImageIcon("images/startbutton.png"));
          setLayout(null);
          startbtn.setLocation(320, 390);
          startbtn.setSize(150, 180);
          startbtn.setOpaque(false);
          startbtn.setContentAreaFilled(false);
          startbtn.setBorderPainted(false);
          startbtn.setFocusPainted(false);
          startbtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
          
          //about button
          aboutbtn = new JButton(new ImageIcon("images/aboutbutton.png"));
          setLayout(null);
          aboutbtn.setLocation(440, 390);
          aboutbtn.setSize(150, 180);
          aboutbtn.setOpaque(false);
          aboutbtn.setContentAreaFilled(false);
          aboutbtn.setBorderPainted(false);
          aboutbtn.setFocusPainted(false);
          aboutbtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
          
          //add button
          add(helpbtn);
          add(startbtn);
          add(aboutbtn);
          
          helpbtn.addMouseListener(new java.awt.event.MouseAdapter() {
      	    public void mouseEntered(java.awt.event.MouseEvent evt) {
      	        helpbtn.setSize(155,185);
      	    }

      	    public void mouseExited(java.awt.event.MouseEvent evt) {
      	        helpbtn.setSize(150,180);
      	    }
      	});
          
          startbtn.addMouseListener(new java.awt.event.MouseAdapter() {
        	    public void mouseEntered(java.awt.event.MouseEvent evt) {
        	        startbtn.setSize(155,185);
        	    }

        	    public void mouseExited(java.awt.event.MouseEvent evt) {
        	        startbtn.setSize(150,180);
        	    }
        	});
          
          aboutbtn.addMouseListener(new java.awt.event.MouseAdapter() {
      	    public void mouseEntered(java.awt.event.MouseEvent evt) {
      	        aboutbtn.setSize(155,185);
      	    }

      	    public void mouseExited(java.awt.event.MouseEvent evt) {
      	        aboutbtn.setSize(150,180);
      	    }
      	});
          
       } catch (IOException ex) {
            // handle exception...
       }
    }
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background,-20,1,null);
		g.drawImage(title,200,20,null);
	}
	
	//getter and setter
	public JButton getHelpbtn() {
		return helpbtn;
	}

	public void setHelpbtn(JButton helpbtn) {
		this.helpbtn = helpbtn;
	}

	public JButton getStartbtn() {
		return startbtn;
	}

	public void setStartbtn(JButton startbtn) {
		this.startbtn = startbtn;
	}

	public JButton getAboutbtn() {
		return aboutbtn;
	}

	public void setAboutbtn(JButton aboutbtn) {
		this.aboutbtn = aboutbtn;
	}

	
	
}

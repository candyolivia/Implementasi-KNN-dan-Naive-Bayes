package gui;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class About extends JPanel {
	
	private BufferedImage background;
	private BufferedImage aboutframe;
	private JButton backbtn;
	
	public About() {
       try {   
          background = ImageIO.read(new File("images/background.jpg"));
          aboutframe = ImageIO.read(new File("images/aboutframe.png"));
          
        //Back button
          backbtn = new JButton(new ImageIcon("images/backbutton.png"));
          setLayout(null);
          backbtn.setLocation(640, 440);
          backbtn.setSize(150, 180);
          backbtn.setOpaque(false);
          backbtn.setContentAreaFilled(false);
          backbtn.setBorderPainted(false);
          backbtn.setFocusPainted(false);
          backbtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
          
        //add button
          add(backbtn);
       } catch (IOException ex) {
            // handle exception...
       }
       
       backbtn.addMouseListener(new java.awt.event.MouseAdapter() {
     	    public void mouseEntered(java.awt.event.MouseEvent evt) {
     	        backbtn.setSize(155,185);
     	    }

     	    public void mouseExited(java.awt.event.MouseEvent evt) {
     	        backbtn.setSize(150,180);
     	    }
     	});
    }
		
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background,-20,1,null);
		g.drawImage(aboutframe,110,-40,null);
	    
	}
	
	//setter and getter
	public JButton getBackbtn() {
		return backbtn;
	}

	public void setBackbtn(JButton backbtn) {
		this.backbtn = backbtn;
	}

}

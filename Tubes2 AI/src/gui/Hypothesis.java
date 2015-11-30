package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Hypothesis extends JPanel {
	private BufferedImage background;
	private JButton hypothesislabel;
	private JButton backbtn;
	private JTextArea areaText;
	private StringBuffer content = new StringBuffer();
	
	
	public Hypothesis() {
		try {
			background = ImageIO.read(new File("images/background.jpg"));
	        
	      //Back button
	        backbtn = new JButton(new ImageIcon("images/backbutton.png"));
	        backbtn.setSize(150, 50);
	        backbtn.setOpaque(false);
	        backbtn.setContentAreaFilled(false);
	        backbtn.setBorderPainted(false);
	        backbtn.setFocusPainted(false);
	        backbtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
	        
	        hypothesislabel = new JButton(new ImageIcon("images/hypothesislabel.png"));
	        add(hypothesislabel, BorderLayout.PAGE_START);
	        hypothesislabel.setOpaque(false);
	        hypothesislabel.setContentAreaFilled(false);
	        hypothesislabel.setBorderPainted(false);
	        hypothesislabel.setFocusPainted(false);
	        
	        areaText = new JTextArea(content.toString(), 23, 60);
		    JScrollPane sp = new JScrollPane(areaText);
		    
		    add(sp, BorderLayout.CENTER);
	
		    setVisible(true);
			
			//add button
		    add(backbtn);
		    
		    backbtn.addMouseListener(new java.awt.event.MouseAdapter() {
	    	    public void mouseEntered(java.awt.event.MouseEvent evt) {
	    	        backbtn.setSize(155,55);
	    	    }

	    	    public void mouseExited(java.awt.event.MouseEvent evt) {
	    	        backbtn.setSize(150,50);
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
		g.drawRect(8,8,774,539);  
	    g.setColor(new Color(0,0,0));;  
	    g.fillRect(8,8,774,539);
		g.drawRect(10,10,770,535);  
	    g.setColor(new Color(238,221,130));;  
	    g.fillRect(10,10,770,535);
	    
	}

	public JButton getBackbtn() {
		return backbtn;
	}

	public void setBackbtn(JButton backbtn) {
		this.backbtn = backbtn;
	}

	public JTextArea getAreaText() {
		return areaText;
	}

	public void setAreaText(JTextArea areaText) {
		this.areaText = areaText;
	}

	public StringBuffer getContent() {
		return content;
	}

	public void setContent(StringBuffer content) {
		this.content = content;
	}
	
	
}
package gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Classify extends JPanel {
	private BufferedImage background;
	private JButton preprocessbtn;
	private JButton classifybtn;
	private JButton backbtn;
	private JComboBox algorithm;
	private JComboBox schema;
	
	
	public Classify() {
       try {   
          background = ImageIO.read(new File("images/background.jpg"));
          
        //Preprocess button
          preprocessbtn = new JButton(new ImageIcon("images/preprocessbutton.png"));
          setLayout(null);
          preprocessbtn.setLocation(20, 20);
          preprocessbtn.setSize(150, 50);
          preprocessbtn.setOpaque(false);
          preprocessbtn.setContentAreaFilled(false);
          preprocessbtn.setBorderPainted(false);
          preprocessbtn.setFocusPainted(false);
          preprocessbtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
          
        //Classify button
          classifybtn = new JButton(new ImageIcon("images/classifybutton.png"));
          setLayout(null);
          classifybtn.setLocation(20, 70);
          classifybtn.setSize(150, 50);
          classifybtn.setOpaque(false);
          classifybtn.setContentAreaFilled(false);
          classifybtn.setBorderPainted(false);
          classifybtn.setFocusPainted(false);
          classifybtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
          
        //Back button
          backbtn = new JButton(new ImageIcon("images/backbutton.png"));
          setLayout(null);
          backbtn.setLocation(20, 120);
          backbtn.setSize(150, 50);
          backbtn.setOpaque(false);
          backbtn.setContentAreaFilled(false);
          backbtn.setBorderPainted(false);
          backbtn.setFocusPainted(false);
          backbtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
          
        //Combo List
          String[] algolist = { "--Please Select Your Algorithm--", "Naive-Bayes", "k-Nearest Neighbours" };
          algorithm = new JComboBox();
          for (int i = 0; i < algolist.length; i++)
        	  algorithm.addItem(algolist[i]);
          setLayout(null);
          algorithm.setLocation(350, 30);
          algorithm.setSize(370, 40);
          
        //Combo List
          String[] schemalist = { "--Please Select Your Schema--", "Full Training", "10 Fold Cross Validation" };
          schema = new JComboBox();
          for (int i = 0; i < schemalist.length; i++)
        	  schema.addItem(schemalist[i]);
	        
          setLayout(null);
          schema.setLocation(350, 80);
          schema.setSize(370, 40);
          
        //Add to Panel
          add(preprocessbtn);
          add(classifybtn);
          add(backbtn);
          add(algorithm);
          add(schema);
          
       } catch (IOException ex) {
            // handle exception...
       }

       preprocessbtn.addMouseListener(new java.awt.event.MouseAdapter() {
    	    public void mouseEntered(java.awt.event.MouseEvent evt) {
    	        preprocessbtn.setSize(155,55);
    	    }

    	    public void mouseExited(java.awt.event.MouseEvent evt) {
    	        preprocessbtn.setSize(150,50);
    	    }
    	});
       

       classifybtn.addMouseListener(new java.awt.event.MouseAdapter() {
    	    public void mouseEntered(java.awt.event.MouseEvent evt) {
    	        classifybtn.setSize(155,55);
    	    }

    	    public void mouseExited(java.awt.event.MouseEvent evt) {
    	        classifybtn.setSize(150,50);
    	    }
    	});
       

       backbtn.addMouseListener(new java.awt.event.MouseAdapter() {
    	    public void mouseEntered(java.awt.event.MouseEvent evt) {
    	        backbtn.setSize(155,55);
    	    }

    	    public void mouseExited(java.awt.event.MouseEvent evt) {
    	        backbtn.setSize(150,50);
    	    }
    	});
       
       algorithm.addActionListener(new java.awt.event.ActionListener() {
	      public void actionPerformed(java.awt.event.ActionEvent e) {
	          System.out.println("index: " + algorithm.getSelectedIndex() + "   "
	              + ((JComboBox) e.getSource()).getSelectedItem());
	        }
	      });
       
       schema.addActionListener(new java.awt.event.ActionListener() {
 	      public void actionPerformed(java.awt.event.ActionEvent e) {
 	          System.out.println("index: " + schema.getSelectedIndex() + "   "
 	              + ((JComboBox) e.getSource()).getSelectedItem());
 	        }
 	      });
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
	
	//getter and setter
	public JButton getBackbtn() {
		return backbtn;
	}

	public void setBackbtn(JButton backbtn) {
		this.backbtn = backbtn;
	}

	public JButton getPreprocessbtn() {
		return preprocessbtn;
	}

	public void setPreprocessbtn(JButton preprocessbtn) {
		this.preprocessbtn = preprocessbtn;
	}

	public JButton getClassifybtn() {
		return classifybtn;
	}

	public void setClassifybtn(JButton classifybtn) {
		this.classifybtn = classifybtn;
	}
}

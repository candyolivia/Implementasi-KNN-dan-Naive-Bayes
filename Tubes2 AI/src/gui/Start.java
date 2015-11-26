package gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JTextArea;

public class Start extends JPanel {
	private BufferedImage background;
	private JButton preprocessbtn;
	private JButton classifybtn;
	private JButton backbtn;
	private JButton filebtn;
	private JButton analysisbtn;
	private JTextField fileChosen;
	private JTextArea attrList;
	private String filename = new String();
	
	public Start() {
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
          
        //Open File button
          filebtn = new JButton(new ImageIcon("images/openfilebutton.png"));
          setLayout(null);
          filebtn.setLocation(600, 20);
          filebtn.setSize(70, 70);
          filebtn.setOpaque(false);
          filebtn.setContentAreaFilled(false);
          filebtn.setBorderPainted(false);
          filebtn.setFocusPainted(false);
          filebtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
          
        //Analysis button
          analysisbtn = new JButton(new ImageIcon("images/analysisbutton.png"));
          setLayout(null);
          analysisbtn.setLocation(660, 20);
          analysisbtn.setSize(70, 70);
          analysisbtn.setOpaque(false);
          analysisbtn.setContentAreaFilled(false);
          analysisbtn.setBorderPainted(false);
          analysisbtn.setFocusPainted(false);
          analysisbtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
          
        //Text Field
          fileChosen = new JTextField();
          fileChosen.setEditable(false);
          fileChosen.setBackground(new Color (238,238,224));
          fileChosen.setText("");
          setLayout(null);
          fileChosen.setLocation(190, 30);
          fileChosen.setSize(400, 40);
          
        //Text Area
          attrList = new JTextArea();
          attrList.setEditable(false);
          attrList.setBackground(new Color (255,245,157));
          attrList.setFont(new Font("Arial", Font.BOLD, 12));
          setLayout(null);
          Border border = BorderFactory.createLineBorder(Color.BLACK);
          attrList.setBorder(BorderFactory.createCompoundBorder(border, 
                      BorderFactory.createEmptyBorder(10, 10, 10, 10)));
          attrList.setLocation(20, 180);
          attrList.setSize(170, 350);
          attrList.setLineWrap(true);
          attrList.setWrapStyleWord(true);
          JScrollPane scroll = new JScrollPane (attrList, 
        		   JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
          
        //ADD TO PANEL
        //add button
          add(preprocessbtn);
          add(classifybtn);
          add(backbtn);
          add(filebtn);
          add(analysisbtn);
          add(fileChosen);
          add(attrList);
          
          
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
       
       filebtn.addActionListener(new ActionListener() {
 		  public void actionPerformed(ActionEvent evt) {
 			  //Create a file chooser
 		     	JFileChooser chooser = new JFileChooser();
 		         FileNameExtensionFilter filter = new FileNameExtensionFilter(
 		             "Text Files", "arff");
 		         chooser.setFileFilter(filter);
 		         int returnVal = chooser.showOpenDialog(null);
 		         if(returnVal == JFileChooser.APPROVE_OPTION) {
 		            System.out.println("You choose to open this file: " +
 		                 chooser.getSelectedFile().getAbsolutePath());
 		            fileChosen.setText(chooser.getSelectedFile().getAbsolutePath());
 		            filename = chooser.getSelectedFile().getAbsolutePath();
 		         }
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
	
	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	public JButton getAnalysisbtn() {
		return analysisbtn;
	}

	public void setAnalysisbtn(JButton analysisbtn) {
		this.analysisbtn = analysisbtn;
	}
	
	public JTextArea getAttrList() {
		return attrList;
	}

	public void setAttrList(JTextArea attrList) {
		this.attrList = attrList;
	}
}

package gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;

import knn.Process;
import model.AttrInfo;
import naivebayes.*;

public class Classify extends JPanel {
	private BufferedImage background;
	private BufferedImage dales;
	private JButton preprocessbtn;
	private JButton classifybtn;
	private JButton generatebtn;
	private JButton backbtn;
	private JComboBox algorithm;
	private JComboBox schema;
	private JLabel algolabel;
	private JLabel schemalabel;
	private JTextArea areaResult;
	private final static String newline = "\n";
	private String filename = new String();

	
	public Classify() {
       try {   
          background = ImageIO.read(new File("images/background.jpg"));
          dales = ImageIO.read(new File("images/daleshowresult.png"));
          
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
          
        //Generate button
          generatebtn = new JButton();
          setLayout(null);
          generatebtn.setText("Generate");
          generatebtn.setFont(new Font("Arial", Font.BOLD, 12));
          generatebtn.setLocation(675, 30);
          generatebtn.setSize(90, 90);
          generatebtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
          
        //Combo List
          String[] algolist = { "--Please Select Your Algorithm--", "Naive-Bayes", "k-Nearest Neighbours" };
          algorithm = new JComboBox();
          for (int i = 0; i < algolist.length; i++)
        	  algorithm.addItem(algolist[i]);
          setLayout(null);
          algorithm.setLocation(330, 30);
          algorithm.setSize(340, 40);
          
        //Combo List
          String[] schemalist = { "--Please Select Your Schema--", "Full Training", "10 Fold Cross Validation" };
          schema = new JComboBox();
          for (int i = 0; i < schemalist.length; i++)
        	  schema.addItem(schemalist[i]);
	        
          setLayout(null);
          schema.setLocation(330, 80);
          schema.setSize(340, 40);
          
          //Label
          algolabel = new JLabel("Algorithm   : ");
          algolabel.setFont(new Font("Arial", Font.BOLD, 18));
          setLayout(null);
          algolabel.setLocation(200, 30);
          algolabel.setSize(120, 40);
          
          schemalabel = new JLabel("Schema      : ");
          schemalabel.setFont(new Font("Arial", Font.BOLD, 18));
          setLayout(null);
          schemalabel.setLocation(200, 80);
          schemalabel.setSize(120, 40);
          
        //Text Area
          areaResult = new JTextArea();
          areaResult.setEditable(false);
          areaResult.setBackground(new Color (255,255,255));
          areaResult.setFont(new Font("Arial", Font.BOLD, 12));
          setLayout(null);
          areaResult.setLocation(220, 150);
          areaResult.setSize(540, 350);
          areaResult.setLineWrap(true);
          areaResult.setWrapStyleWord(true);
          JScrollPane scroll = new JScrollPane (areaResult, 
        		   JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
          
        //Add to Panel
          add(preprocessbtn);
          add(classifybtn);
          add(backbtn);
          add(algorithm);
          add(schema);
          add(schemalabel);
          add(algolabel);
          add(areaResult);
          add(generatebtn);
          add(scroll);
          
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
       
       //OnClick Generate Button
 		generatebtn.addActionListener(new ActionListener() {
 		  public void actionPerformed(ActionEvent evt) {
 		    // ... called when button clicked
 			 System.out.println("algo: " + algorithm.getSelectedIndex() + "   "
 	 	              + "schema  : " + schema.getSelectedIndex());
 			 System.out.println(filename);
 			try {
				PrintStream printStream = new PrintStream(new FileOutputStream("output.txt"));
				System.setOut(printStream);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
 			
 			if (algorithm.getSelectedIndex() == 2) {
 				Process p = new Process(filename);
 				p.setMode(schema.getSelectedIndex());
 				
 				if (p.getMode() == 2) {
 					areaResult.setText("\n\t\t                        RESULT \n"+
		 					 "  ==========================================================================  \n" +
		 					 "  \t\tTest Mode : KNN 10-fold Cross Validation                 \n" + 
		 					 "  ==========================================================================  \n");
 				} else if (p.getMode() == 1){
 					areaResult.setText("\n\t\t                        RESULT \n"+
		 					 "  ==========================================================================  \n" +
		 					 "  \t\t Test Mode : KNN Full Training                        \n" + 
		 					 "  ==========================================================================  \n");
 				}
 				p.doKNN();
 				
 			} else if (algorithm.getSelectedIndex() == 1) {
 				
 				if (schema.getSelectedIndex() == 2) {
 					areaResult.setText("\n      RESULT :\n"+
		 					 "  ==========================================================================  \n" +
		 					 "                        Test Mode : Naive-Bayes 10-fold Cross Validation                 \n" + 
		 					 "  ==========================================================================  \n");
 					NaiveBayes nbFCV = new NaiveBayes(filename);
 					nbFCV.predictFoldCV(filename);
 				} else if (schema.getSelectedIndex() == 1){
 					NaiveBayes nbFull = new NaiveBayes(filename);
 					areaResult.setText("\n      RESULT :\n"+
		 					 "  ==========================================================================  \n" +
		 					 "                        Test Mode : Naive-Bayes 10-fold Cross Validation                 \n" + 
		 					 "  ==========================================================================  \n");
 					nbFull.predictFullTraining();
 				}	
 			}
 			
 			
 			String str = new String();
 			FileInputStream fstream;
			try {
				fstream = new FileInputStream(new File("output.txt"));
				BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
	 			String strLine;
	 			
	 			//Read File Line By Line
	 			while ((strLine = br.readLine()) != null)   {
	 				areaResult.append("\n");
	 				areaResult.append(strLine);
				}
	 			areaResult.append("\n  ==========================================================================  \n");
				//Close the input stream
				br.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
		g.drawImage(dales,5,200,null);
	    
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
}

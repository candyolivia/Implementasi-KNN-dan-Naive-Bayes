package gui;

import javax.swing.*;

import java.awt.event.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;

import model.*;

public class Main extends JFrame {
	public Main() {
		setTitle ("Naive Chip and The Neighbours");
		setSize (800,590);
		setLocation (270,60);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		// Window Listeners
		addWindowListener(new WindowAdapter() {
		  public void windowClosing(WindowEvent e) {
		    int confirmed = JOptionPane.showConfirmDialog(null, 
		        "Are you sure you want to exit the program?", "Exit Program",
		        JOptionPane.YES_NO_OPTION);

		    if (confirmed == JOptionPane.YES_OPTION) {
		      dispose(); 
		    }
		  }
		});
		
		//Add Home Panel
		Container contentPane = getContentPane();
		Home home = new Home();
		contentPane.add(home);
		
		About about = new About();
		Help help = new Help();
		Start start = new Start();
		Classify classify = new Classify();
		Determine detclass = new Determine();
		Hypothesis hypo = new Hypothesis();
		
		//OnClick About Button
		home.getAboutbtn().addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent evt) {
		    // ... called when button clicked
			  contentPane.remove(home);
			  contentPane.add(about);
              contentPane.revalidate();
              contentPane.repaint();
              pack();
              setSize (800,590);
              setResizable(false);			  
		  }
		});
		
		//OnClick Start Button
		home.getStartbtn().addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent evt) {
		    // ... called when button clicked
			  contentPane.remove(home);
			  contentPane.add(start);
              contentPane.revalidate();
              contentPane.repaint();
              pack();
              setSize (800,590);
              setResizable(false);			  
		  }
		});
		
		//OnClick Help Button
		home.getHelpbtn().addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent evt) {
		    // ... called when button clicked
			  contentPane.remove(home);
			  contentPane.add(help);
              contentPane.revalidate();
              contentPane.repaint();
              pack();
              setSize (800,590);
              setResizable(false);
		  }
		});
		
		//OnClick Back Button
		about.getBackbtn().addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent evt) {
		    // ... called when button clicked
			  contentPane.remove(about);
			  contentPane.add(home);
	            contentPane.revalidate();
	            contentPane.repaint();
	            pack();
	            setSize (800,590);
	            setResizable(false);
		  }
		});
		
		//OnClick Back Button
		help.getBackbtn().addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent evt) {
		    // ... called when button clicked
			  contentPane.remove(help);
			  contentPane.add(home);
	            contentPane.revalidate();
	            contentPane.repaint();
	            pack();
	            setSize (800,590);
	            setResizable(false);
		  }
		});
		
		//OnClick Back Button
		start.getBackbtn().addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent evt) {
		    // ... called when button clicked
		  	contentPane.remove(start);
			contentPane.add(home);
            contentPane.revalidate();
            contentPane.repaint();
            pack();
            setSize (800,590);
            setResizable(false);
		  }
		});
		
		//OnClick Back Button
		classify.getBackbtn().addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent evt) {
		    // ... called when button clicked
			  classify.getHypobtn().setVisible(false);
			  classify.getPredictclassbtn().setVisible(false);
			  contentPane.remove(classify);
			  contentPane.add(home);
	            contentPane.revalidate();
	            contentPane.repaint();
	            pack();
	            setSize (800,590);
	            setResizable(false);
		  }
		});
		
		//OnClick Classify Button
		start.getClassifybtn().addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent evt) {
		    // ... called when button clicked
			  if (start.getFilename().equals("")) {
				  JOptionPane.showMessageDialog(start,
                  	    "Error! File hasn't been chosen!",
                  	    "Error",
                  	    JOptionPane.ERROR_MESSAGE);
			  } else {
				  classify.setFilename(start.getFilename());
				  System.out.println(classify.getFilename());
				  contentPane.remove(start);
				  contentPane.add(classify);
				  contentPane.revalidate();
				  contentPane.repaint();
				  pack();
				  setSize (800,590);
				  setResizable(false);  
			  }
			  
		  }
		});
		
		start.getAnalysisbtn().addActionListener(new ActionListener() {
	  		  public void actionPerformed(ActionEvent evt) {
	  			if(start.getChartPanel() != null){
                                    contentPane.remove(start.getChartPanel());
                                    contentPane.revalidate();
	  			}
	  			  
	  			if (start.getFilename().equals("")) {
	  				JOptionPane.showMessageDialog(start,
	          	    "Error! File hasn't been chosen!",
	          	    "Error",
	          	    JOptionPane.ERROR_MESSAGE);
	  			} else {
	  				fileConverter fc = new fileConverter();
	  				try {
	  					fc.dataToMatrix(start.getFilename());
	  				} catch (IOException e) {
	  					// TODO Auto-generated catch block
	  					e.printStackTrace();
	  				}
	  				
	  				start.getAttrList().setText("List of Attributes");
	  				
	  				String str = new String();
	  	 			FileInputStream fstream;
	  	 			
	  	 			try {
	  					PrintStream printStream = new PrintStream(new FileOutputStream("output.txt"));
	  					System.setOut(printStream); 
	  				} catch (FileNotFoundException e) {
	  					// TODO Auto-generated catch block
	  					e.printStackTrace();
	  				}

	  				fc.printMatrixAttrInfo();
	  				
	  				// empty the list
	  				start.getAttributelist().clear();
	  				
	  				try {
	  					fstream = new FileInputStream(new File("output.txt"));
	  					BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
	  		 			String strLine;
	  		 			start.getAttrList().append("\n");
	  		 			//Read File Line By Line
	  		 			while ((strLine = br.readLine()) != null)   {
	  		 				start.getAttrList().append("\n");
	  		 				start.getAttrList().append(strLine);
	  		 				if(strLine.endsWith(":"))
	  		 					// insert the attribute without ':' character
	  		 					start.getAttributelist().add(strLine.substring(0, strLine.length()-1));
	  					}
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
	  			contentPane.repaint();
	  		  }
	  	});
		
				
		//OnClick Preprocess Button
		classify.getPreprocessbtn().addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent evt) {
		    // ... called when button clicked
				contentPane.remove(classify);
				classify.getHypobtn().setVisible(false);
				classify.getPredictclassbtn().setVisible(false);
				contentPane.add(start);
				contentPane.remove(classify.getPredictclassbtn());
	            contentPane.revalidate();
	            contentPane.repaint();
	            pack();
	            setSize (800,590);
	            setResizable(false);
		  }
		});
		
		//OnClick Predict Class Button
		classify.getPredictclassbtn().addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent evt) {
                    // ... called when button clicked
                    detclass.setFilename(start.getFilename());
                    contentPane.remove(classify);
                    contentPane.add(detclass);
	            contentPane.revalidate();
	            contentPane.repaint();
	            pack();
	            setSize (800,590);
	            setResizable(false);
		  }
		});
		
		
		//OnClick Hypothesis Button
		classify.getHypobtn().addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent evt) {
                    // ... called when button clicked
                    contentPane.remove(classify);
                    hypo.setContent(classify.getHypothesis());
                    hypo.getAreaText().setText(hypo.getContent().toString());
                    classify.getAreaResult().setText("");
                    contentPane.add(hypo);
				
	            contentPane.revalidate();
	            contentPane.repaint();
	            pack();
	            setSize (800,590);
	            setResizable(false);
		  }
		});
		

		//OnClick Back Button
		hypo.getBackbtn().addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent evt) {
			  // ... called when button clicked
				contentPane.remove(hypo);
				contentPane.add(start);
	            contentPane.revalidate();
	            contentPane.repaint();
	            pack();
	            setSize (800,590);
	            setResizable(false);
		  }
		});
                
                //Onlick Back Button
                //OnClick Back Button
		detclass.getBackbtn().addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent evt) {
                    // ... called when button clicked
                    contentPane.remove(detclass);
                    contentPane.add(classify);
	            contentPane.revalidate();
	            contentPane.repaint();
	            pack();
	            setSize (800,590);
	            setResizable(false);
		  }
		});
		
	}
	
	public static void main (String[] args) {
		ImageIcon icon = new ImageIcon("images/icon.png");
		JFrame f = new Main();
		f.setIconImage(icon.getImage());
		f.show();	
	}
}

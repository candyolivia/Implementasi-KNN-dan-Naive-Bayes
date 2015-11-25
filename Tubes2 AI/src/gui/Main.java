package gui;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Main extends JFrame {
	public Main() {
		setTitle ("Naive Chip and The Neighbours");
		setSize (800,590);
		setLocation (270,60);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		// Window Listeners
		addWindowListener(new WindowAdapter() {
		  	public void windowClosing(WindowEvent e) {
			   System.exit(0);
		  	} //windowClosing
		} );
		
		//Add Home Panel
		Container contentPane = getContentPane();
		Home home = new Home();
		contentPane.add(home);
		
		About about = new About();
		Help help = new Help();
		
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
              setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
              setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
	            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
	            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

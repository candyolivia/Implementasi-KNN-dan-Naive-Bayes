package gui;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Main extends JFrame {
	public Main() {
		setTitle ("Naive Chip and The Neighbours");
		setSize (800,600);
		setLocation (270,60);
		// Window Listeners
		addWindowListener(new WindowAdapter() {
		  	public void windowClosing(WindowEvent e) {
			   System.exit(0);
		  	} //windowClosing
		} );
		
		//Add panels
		Container contentPane = getContentPane();
		contentPane.add(new Home());
	}
	
	public static void main (String[] args) {
		JFrame f = new Main();
		f.show();
		
	}
}

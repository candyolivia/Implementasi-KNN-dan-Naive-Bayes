package gui;

import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class Knn extends JFrame{
	public Knn(){
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
		Container contentPanel = getContentPane();
		contentPanel.add(new Home());
	}
	
	public static void main (String[] args){
		
	}
}

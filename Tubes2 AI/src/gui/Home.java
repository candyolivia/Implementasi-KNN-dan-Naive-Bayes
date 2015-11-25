package gui;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Home extends JPanel {
	
	private BufferedImage title;
	
	public Home() {
       try {                
          title = ImageIO.read(new File("images/Title.png"));
       } catch (IOException ex) {
            // handle exception...
       }
    }
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(title,150,40,null);
		
	}
	
}

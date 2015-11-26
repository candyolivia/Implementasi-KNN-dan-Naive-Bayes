package gui;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset; 
import org.jfree.data.category.DefaultCategoryDataset; 
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RefineryUtilities; 

public class Chart extends JFrame
{
	public Chart() {
	     BarChart_AWT chartCanvas = new BarChart_AWT("Title");
	     JPanel pan = chartCanvas.createDemoPanel();
	     JFrame frame = new JFrame();
	     // ... set attributes for frame
	     frame.getContentPane().add(pan);
	     frame.show();
	}
   
   public static void main( String[ ] args )
   { 
	   Chart c = new Chart();
	   
   }
}

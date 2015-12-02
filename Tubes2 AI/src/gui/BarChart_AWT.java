package gui;

import java.io.IOException;

import javax.swing.JPanel;

import model.fileConverter;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset; 
import org.jfree.data.category.DefaultCategoryDataset; 
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RefineryUtilities; 

public class BarChart_AWT extends ApplicationFrame
{
   public BarChart_AWT(String applicationTitle)
   {
      super( applicationTitle );        
      
   }
   
   private static JFreeChart createChart(CategoryDataset dataset, String chartTitle) {
       
	   JFreeChart barChart = ChartFactory.createBarChart(
		         chartTitle,           
		         "Category",            
		         "Score",            
		         dataset,          
		         PlotOrientation.VERTICAL,           
		         true, true, false);
	   
	         
      return barChart;
        
   }
   
   private static CategoryDataset createDataset()
   {
      final String fiat = "FIAT";        
      final String audi = "AUDI";        
      final String ford = "FORD";        
      final String speed = "Speed";        
      final String millage = "Millage";        
      final String userrating = "User Rating";        
      final String safety = "safety";        
      final DefaultCategoryDataset dataset = 
      new DefaultCategoryDataset( );  

      dataset.addValue( 1.0 , fiat , speed );        
      dataset.addValue( 3.0 , fiat , userrating );        
      dataset.addValue( 5.0 , fiat , millage ); 
      dataset.addValue( 5.0 , fiat , safety );           

      dataset.addValue( 5.0 , audi , speed );        
      dataset.addValue( 6.0 , audi , userrating );       
      dataset.addValue( 10.0 , audi , millage );        
      dataset.addValue( 4.0 , audi , safety );

      dataset.addValue( 4.0 , ford , speed );        
      dataset.addValue( 2.0 , ford , userrating );        
      dataset.addValue( 3.0 , ford , millage );        
      dataset.addValue( 6.0 , ford , safety );               

      return dataset; 
   }
   
   public static JPanel createDemoPanel() {
       JFreeChart chart = createChart(createDataset(),"Which car do you like?");
       return new ChartPanel(chart);
   }
   
   private static CategoryDataset createDataset(fileConverter fc, String filename)
   {
	   final DefaultCategoryDataset dataset = 
			      new DefaultCategoryDataset();
	   fc = new fileConverter();
		try {
			fc.dataToMatrix(filename);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   fc.convertDataToInteger();
	   fc.dataToChartMatrix();
	   int i = 0;
	   //for (int i = 0; i < fc.getMatrixAttrInfo().get(fc.getMatrixAttrInfo().size()-1).getNumAttr(); i++) {
			for (int j = 0; j < fc.getMatrixAttrInfo().size()-1; j++) {
				for (int k = 0; k < fc.getMatrixAttrInfo().get(j).getNumAttr(); k++) {
					System.out.println(fc.getMatrixAttrInfo().get(fc.getMatrixAttrInfo().size()-1).getAttrListN(i) + " " + fc.getMatrixAttrInfo().get(j).getAttrListN(k) + " ");
					dataset.addValue( fc.getMatrixChartData()[i][j][k] , fc.getMatrixAttrInfo().get(fc.getMatrixAttrInfo().size()-1).getAttrListN(i) , fc.getMatrixAttrInfo().get(j).getAttrListN(k) );
				}
			}
		//}
     /* final String fiat = "FIAT";        
      final String audi = "AUDI";        
      final String ford = "FORD";        
      final String speed = "Speed";        
      final String millage = "Millage";        
      final String userrating = "User Rating";        
      final String safety = "safety";        
        

      dataset.addValue( 1.0 , fiat , speed );        
      dataset.addValue( 3.0 , fiat , userrating );        
      dataset.addValue( 5.0 , fiat , millage ); 
      dataset.addValue( 5.0 , fiat , safety );           

      dataset.addValue( 5.0 , audi , speed );        
      dataset.addValue( 6.0 , audi , userrating );       
      dataset.addValue( 10.0 , audi , millage );        
      dataset.addValue( 4.0 , audi , safety );

      dataset.addValue( 4.0 , ford , speed );        
      dataset.addValue( 2.0 , ford , userrating );        
      dataset.addValue( 3.0 , ford , millage );        
      dataset.addValue( 6.0 , ford , safety );   */            

      return dataset; 
   }
   
   public static JPanel createDemoPanel(String chartTitle, CategoryDataset dataset) {
       JFreeChart chart = createChart(dataset,chartTitle);
       return new ChartPanel(chart);
   }
   
   public static void main( String[ ] args )
   {
      BarChart_AWT chart = new BarChart_AWT("Car Usage Statistics");
      chart.pack( );
      RefineryUtilities.centerFrameOnScreen( chart );
      chart.setVisible( true ); 
   }
}

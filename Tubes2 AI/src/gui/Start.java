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
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RefineryUtilities;

import model.fileConverter;

import javax.swing.JTextArea;

public class Start extends JPanel {
	private BufferedImage background;
	private JButton preprocessbtn;
	private JButton classifybtn;
	private JButton backbtn;
	private JButton filebtn;
	private JButton analysisbtn;
	private JButton graphbtn;
	private JTextField fileChosen;
	private JTextArea attrList; //output
	private String filename = new String();
	private JComboBox attribute;
	private List<String> attributelist = new ArrayList<String>();
	private ChartPanel chartPanel;
	private ArrayList<ArrayList<Integer>> graphContainer;
	private JFreeChart chart;
	private DefaultCategoryDataset dataset;
	private CategoryPlot catPlot;
	
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
          backbtn = new JButton(new ImageIcon("images/homebutton.png"));
          setLayout(null);
          backbtn.setLocation(20, 120);
          backbtn.setSize(150, 50);
          backbtn.setOpaque(false);
          backbtn.setContentAreaFilled(false);
          backbtn.setBorderPainted(false);
          backbtn.setFocusPainted(false);
          backbtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
          
        //Open File button
          filebtn = new JButton(new ImageIcon("images/openfilebtn.png"));
          setLayout(null);
          filebtn.setLocation(600, 15);
          filebtn.setSize(180, 60);
          filebtn.setOpaque(false);
          filebtn.setContentAreaFilled(false);
          filebtn.setBorderPainted(false);
          filebtn.setFocusPainted(false);
          filebtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
          
        //Analysis button
          analysisbtn = new JButton(new ImageIcon("images/analysisbutton.png"));
          setLayout(null);
          analysisbtn.setLocation(600, 70);
          analysisbtn.setSize(180, 60);
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
          
        //Combo List
          attributelist.add("--Please Select Your Attribute--");
          attribute = new JComboBox();
          setLayout(null);
          attribute.setLocation(190, 80);
          attribute.setSize(300, 40);
          
        //Graph Button
          graphbtn = new JButton();
          setLayout(null);
          graphbtn.setText("Graph");
          graphbtn.setFont(new Font("Arial", Font.BOLD, 12));
          graphbtn.setLocation(500, 80);
          graphbtn.setSize(90, 40);
          graphbtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
          
          
        //ADD TO PANEL
        //add button
          add(preprocessbtn);
          add(classifybtn);
          add(backbtn);
          add(filebtn);
          add(analysisbtn);
          add(fileChosen);
          add(attrList);
          add(attribute);
          add(graphbtn);
          
          
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
       
       analysisbtn.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			//empty the container first
 		   attribute.removeAllItems();
 		   
 		   for (int i = 0; i < attributelist.size()-1; i++)
 			   attribute.addItem(attributelist.get(i)); //insert the element into container
		}
	});
       
       graphbtn.addActionListener(new ActionListener(){
    	   public void actionPerformed(ActionEvent evt){  
    		  
    		   if (chartPanel != null) {
    			   remove(chartPanel);
    			   System.out.println("chart panel null");
    		   }
    		   
    		   dataset = new DefaultCategoryDataset();
    		   //System.out.println("datattaaa");
    		   fileConverter fc = new fileConverter();
    		   try {
    			   fc.dataToMatrix(filename);
    		   } catch (IOException e) {
					// TODO Auto-generated catch block
    			   e.printStackTrace();
    		   }
    		   
    		   fc.convertDataToInteger();
    		   fc.dataToChartMatrix();    
    		   
    		   graphContainer = new ArrayList<ArrayList<Integer>>();
    		   int i = 0;
    		   
    		   int j = attribute.getSelectedIndex();
    		   System.out.println("SelectedIndex: "+j);
    		   for (int k = 0; k < fc.getMatrixAttrInfo().get(j).getNumAttr(); k++) {
    			   //System.out.println(fc.getMatrixAttrInfo().get(fc.getMatrixAttrInfo().size()-1).getAttrListN(i) + " " + fc.getMatrixAttrInfo().get(j).getAttrListN(k) + " ");
    			   dataset.addValue( fc.getMatrixChartData()[i][j][k] , fc.getMatrixAttrInfo().get(fc.getMatrixAttrInfo().size()-1).getAttrListN(i) , fc.getMatrixAttrInfo().get(j).getAttrListN(k) );
    			   
    		   }
    		   
			   chart = ChartFactory.createBarChart("", "", "", dataset, PlotOrientation.VERTICAL, false, false, false);
			   catPlot = chart.getCategoryPlot();
			   catPlot.setRangeGridlinePaint(Color.BLACK);
			
			   chartPanel = new ChartPanel(chart);
			   chartPanel.setLocation(300, 170);
			   chartPanel.setSize(400, 350);
			   chartPanel.setOpaque(false);
			   chartPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
			   add(chartPanel);
			   chartPanel.setVisible(true);
			   revalidate();
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
	
	public JTextArea getAttrList(){
		return attrList;
	}
	
	public void setAttrList(){
		
	}
	
	public List<String> getAttributelist(){
		return attributelist;
	}
	
	public ChartPanel getChartPanel(){
		return chartPanel;
	}



	public JButton getGraphbtn() {
		return graphbtn;
	}



	public void setGraphbtn(JButton graphbtn) {
		this.graphbtn = graphbtn;
	}



	public JComboBox getAttribute() {
		return attribute;
	}



	public void setAttribute(JComboBox attribute) {
		this.attribute = attribute;
	}



	public ArrayList<ArrayList<Integer>> getGraphContainer() {
		return graphContainer;
	}



	public void setGraphContainer(ArrayList<ArrayList<Integer>> graphContainer) {
		this.graphContainer = graphContainer;
	}



	public JFreeChart getChart() {
		return chart;
	}



	public void setChart(JFreeChart chart) {
		this.chart = chart;
	}



	public DefaultCategoryDataset getDataset() {
		return dataset;
	}



	public void setDataset(DefaultCategoryDataset dataset) {
		this.dataset = dataset;
	}



	public CategoryPlot getCatPlot() {
		return catPlot;
	}



	public void setCatPlot(CategoryPlot catPlot) {
		this.catPlot = catPlot;
	}



	public void setChartPanel(ChartPanel chartPanel) {
		this.chartPanel = chartPanel;
	}
	
	
}

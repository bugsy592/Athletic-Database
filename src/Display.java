import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.Array;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 */

/**
 * @author Nick Ballback
 *
 */
@SuppressWarnings("unused")
public class Display {

	boolean 	searchBarVis 	= false;
	boolean 	infoDisplayVis 	= false;
	String 		outputSport 	= "";
	String 		outputName		= "";
	String		outputYear		= "";
	String 		outputLetter	= "";
	String[]	sportsList 		= { "<Select A Sport>"};
	String[]	yearsList 		= {"<Select A Year>"};
	String[]	letters 		= {"<Select A Letter>", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
	String[]	name 			= {"Firstname", "Lastname"};
	
	
	//Set Methods
	public void setSearchVis(boolean onOff){
		searchBarVis = onOff;
	}
	
	public boolean getSearchVis(){
		return searchBarVis;
	}
	
	public void setDisplayVis(boolean onOff){
		infoDisplayVis = onOff;
	}
	
	public boolean getDisplayVis(){
		return infoDisplayVis;
	}
	
	public void setOutputName(String input){
		outputName = input;
	}
	
	public void setOutputYear(String input){
		outputYear = input;
	}
	
	public void setOutputLetter(String input){
		outputLetter = input;
	}
	
	public void setOutputSport(String input){
		outputSport = input;
	}
	
	public void setSportsList(String[] list, int i){
		sportsList = new String[i];
		
		sportsList = list;
	}
	
	public void setYearsList(String[] list,int i){
		yearsList =  new String[i];
		
		yearsList = list;
	}
	
	
	
	
	
	public void searchBar(){
		JFrame SearchBar = new JFrame();
		
		String textBtn = "Search";
		
		int xLength = 1000, yLength = 80;
		
		
		
		SearchBar.setSize(xLength, yLength);
		SearchBar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		int rows = 1;
		int cols = 0;
		int gap = 15;
		
		//Panels
		JPanel search = new JPanel(new GridLayout(rows, cols, gap, gap));
		
		//Labels
		JLabel lbSports = new JLabel(" Sports:");
		JLabel lbYear = new JLabel(" Years:");
		JLabel lbLetter = new JLabel(" Letters:");
		JLabel lbName = new JLabel(" First/Last:");
		
		//ComboBoxes
		JComboBox<String> cbSports = new JComboBox<String>(sportsList);
		JComboBox<String> cbYears = new JComboBox<String>(yearsList);
		JComboBox<String> cbLetters = new JComboBox<String>(letters);
		JComboBox<String> cbName = new JComboBox<String>(name);
		
		//Buttons
		JButton btnSearch = new JButton(textBtn);
		btnSearch.addActionListener(new Action());
		
		//adding content to panels
		search.add(lbSports);
		search.add(cbSports);
		search.add(lbYear);
		search.add(cbYears);
		search.add(lbLetter);
		search.add(cbLetters);
		search.add(lbName);
		search.add(cbName);
		search.add(btnSearch);
		
		SearchBar.getContentPane().add(search);
		
		System.out.println(outputSport);
		System.out.println(outputYear);
		System.out.println(outputLetter);
		System.out.println(outputName);
		
		SearchBar.setVisible(getSearchVis());
		DatabaseConnection database = new DatabaseConnection();
		int i = 0;
		do{
			setOutputSport(cbSports.getSelectedItem().toString());
			setOutputYear(cbYears.getSelectedItem().toString());
			setOutputLetter(cbLetters.getSelectedItem().toString());
			setOutputName(cbName.getSelectedItem().toString());
			database.setOutput(outputSport, outputYear, outputLetter, outputName);
			
		} while (i == -8);
	}
		
	
	public void infoDisplay(){
		
		JFrame display = new JFrame();
		
		
		int xLength = 1000, yLength = 400;
		display.setSize(xLength, yLength);
		display.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		//Panel
		JPanel main = new JPanel();
		
		//Table		
		JTable infoTable = new JTable(new TableRender());
		infoTable.createDefaultColumnsFromModel();
		
		//adding content to panel
		main.setLayout(new BorderLayout());
		main.add(infoTable.getTableHeader(), BorderLayout.PAGE_START);
		main.add(infoTable, BorderLayout.CENTER);
		infoTable.setAutoResizeMode(5);
		
		display.add(main);
		
		display.setVisible(getDisplayVis());
	}
	
}

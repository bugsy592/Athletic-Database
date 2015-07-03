import java.sql.*;
import java.util.ArrayList;


public class sqltests {
	
	public 	String[] 	years			= null; //String for the sport years
	public 	String[] 	sports			= null; //String for the sport types
	public	String[]	columnHeader	= null; //String for the column Headers
	public 	String 		tableNames		= "players";	//table of peoples names for SQL statements
	public	String		tableInfo		= null; //table to pull info about people
	private String 		connect 		= "jdbc:postgresql://localhost:5432/Athletic_Database"; //The url for connecting to the database
	private String 		user			= "postgres"; //The database user login
	private String 		pass 			= "test123";	//The database user password
	public 	String[][] 	data; 			//The String for collecting the search results to display in a table
	public 	String		searchYear		= null;
	public	String		searchSport		= null;
	public	String		searchLetter	= null;
	public	String		searchName		= null;
	
	String search = null;
	
	public void makeSearch(String sport,String name,String year,String letter){
		if (name == "Firstname"){
			search = "SELECT * FROM " + tableNames + " WHERE sport='" + sport + "' AND year='" + year + "' AND first_name LIKE '" + letter + "%'" ;
		} else if (name == "Lastname"){
			search = "SELECT * FROM " + tableNames + " WHERE sport='" + sport + "' AND year='" + year + "' AND last_name LIKE '" + letter + "%'" ;
		} else {
			System.out.println("Error creating search querry");
		}
	}
	
	ArrayList<String> dataYear = new ArrayList<String>();
	ArrayList<String> dataSport = new ArrayList<String>();
	ArrayList<String> dataFirst = new ArrayList<String>();
	ArrayList<String> dataLast = new ArrayList<String>();
	int high = 0;
	ArrayList<String> dataSportTypes = new ArrayList<String>();
	ArrayList<String> dataYearTypes = new ArrayList<String>();
	
	int amountYear = 0;
	int amountSport = 0;
	
	//Set Methods
	public void setSportType(String s,int i){
		dataSport.add(s);
	}
	
	public void setYearType(String y,int i){
		dataSport.add(y);
		System.out.println(y + " add to Arraylist");
	}
	
	public void setDataYear(String y,int i){
		dataYear.add(y);
		if (i > high){
			high = i;
		}
	}
	
	public void setDataSport(String s,int i){
		dataSport.add(s);
		if (i > high){
			high = i;
		}
	}
	
	public void setDataFirst(String f,int i){
		dataFirst.add(f);
		if (i > high){
			high = i;
		}
	}
	
	public void setDataLast(String l,int i){
		dataLast.add(l);
		if (i > high){
			high = i;
		}
	}
	
	public void setConnect(String c){
		connect = c;
	}
	
	
	//Get Methods 
	public String[] getYears(){
		return years;
	}
	
	public String[] getSports(){
		return sports;
	}
	
	public String[] getHeader(){
		return columnHeader;
	}
	
	public String[][] getData(){
		return data;
	}
	
	public String getConnect(){
		return connect;
	}
	
	
	//Sql Methods
	public Connection openConnection(Connection c){
		/*
		 *  --creates a connection to the database to pass to the other methods
		 */
		try {
			c = DriverManager.getConnection(connect, user, pass);
			System.out.println("Connected To Database");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}
	
	public void closeConnection(Connection c){
		/*
		 *  --closes the connection to the database once called
		 */
		try {
			c.close();
			System.out.println("Connection Closed");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void pullSports(Connection c) throws SQLException{
		//Querys the database for the various sports
		/*
		 *  --creates a SQL statement to query the database to collect the various sports
		 *  --closes the statement after it collects them and writes them to an String
		 */
		int i = 0;
		Statement stmtSport = null;
		String query 		= "SELECT sport FROM " + tableNames;
		openConnection(c);
		try { 
			stmtSport = c.createStatement();
			System.out.println("Statment Crated");
			ResultSet rs = stmtSport.executeQuery(query);
			String temp = null;
			while (rs.next()){
				temp = rs.getString("sport");
				System.out.println("sport " + i + " : " + temp);
				setSportType(temp,i);
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmtSport != null) { stmtSport.close(); System.out.println("Statement Closed"); }
		}
	}
	
	public void pullYears(Connection c) throws SQLException{
		//Querys the database for the years 
		/*
		 *  --creates a SQL statement to query the database to collect the various sport years
		 *  --closes the statement after it collects them and writes them to an String
		 */
		int i = 1;
		Statement stmtYear 	= null;
		String query		= "SELECT year FROM " + tableNames;
		openConnection(c);
		String temp = null;
		try {
			stmtYear = c.createStatement();
			System.out.println("Statment Crated");
			ResultSet rs = stmtYear.executeQuery(query);
			System.out.println("ResultSet gotten");
			while (rs.next()){
				temp = rs.getString("year");
				//System.out.println("ResultSet " + i + " Written to years" + " :  " + temp);
				setYearType(temp,i);
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmtYear != null) { stmtYear.close(); }
		}
	}
	
	public void pullSearch(Connection c) throws SQLException{
		//Querys the database based on the users search terms
		/*
		 *  --creates a SQL statement to query the database to collect the users search input
		 *  --closes the statement after it collects them and writes them to an String
		 */
		int i = 0;
		Statement stmtSearch 	= null;
		String query			= search;
		openConnection(c);
		try {
 			stmtSearch = c.createStatement();
			System.out.println("Statment Crated");
			ResultSet rs = stmtSearch.executeQuery(query);
			String tempFN;
			String tempLN;
			String tempS;
			String tempY;
			while (rs.next()){
				tempFN = rs.getString("first_name");
				tempLN = rs.getString("last_name");
				tempS  = rs.getString("sport");
				tempY  = rs.getString("year");
				
				setDataLast(tempFN,i);
				setDataLast(tempLN,i);
				setDataSport(tempS,i);
				setDataYear(tempY,i);
				
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmtSearch != null) { stmtSearch.close(); }
		}
	}
	
	/*Hello -Sean C*/
	
	public void pullPersonInfo(Connection c,String uID) throws SQLException{
		Statement stmtHeader 	= null;
		String query		= "SELECT * FROM " + tableInfo;
		openConnection(c);
		try {
			stmtHeader = c.createStatement();
			System.out.println("Statment Crated");
			@SuppressWarnings("unused")
			ResultSet rs = stmtHeader.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmtHeader != null) { stmtHeader.close(); }
		}
	}
	
	public String createUID(String first,String last,String year,String sport){
		String uID = null;
		uID = first.trim().substring(0, 1) + last.trim().substring(0, 3) + year + sport;
		uID = uID.toLowerCase();
		return uID;
	}
}

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Nick Ballback
 *
 */

public class DatabaseConnection {

	/**
	 * @param args
	 */
	
	public 			String 		sport	= "";
	public 			String 		year	= "";
	public 			String 		letter	= "";
	public 			String 		name	= "";
	public static	Connection 	main 	= null;
	public static	String[]	header	= {"First Name","Last Name","Sport","Year"};
	public 			String[][] 	data	= null;
	
	public static 	String test = null;
	
	
	
	public static void main(String[] args) throws SQLException{
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		databaseConnect();
		System.out.println("String display");
		display(true);
	}
	
	
	
	
	
	//Database connection info
	//DBInfo = protocol[jdbc] + semicolon + subprotocol[postgresql] + semicolon + name[URL];
	
	
	public static void display(boolean onOff){
		Display main    = new Display();
		
		
		main.setSearchVis(onOff);
		main.setDisplayVis(onOff);
		main.searchBar();
		main.infoDisplay();
	}
	
	public void setOutput(String s, String y, String l, String n){
		//System.out.println(sport + ", " + year + ", " + letter + ", " + name);
		sport 	= s;
		year 	= y;
		letter 	= l;
		name 	= n;
	}
	
	public static void databaseConnect() throws SQLException{
		sqltests sql    = new sqltests();
		TableRender ren = new TableRender();
		
		
		main = sql.openConnection(main);
		sql.pullSports(main);
		sql.pullYears(main);
		
		ren.setSearchBar();
	}
	
	public void databaseSearch() throws SQLException{
		sqltests sql    = new sqltests();
		TableRender ren = new TableRender();
		
		sql.makeSearch(sport, name, year, letter);
		sql.pullSearch(main);
		
		ren.setData();
		
	}
}

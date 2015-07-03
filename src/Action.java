import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;


public class Action implements ActionListener {

	public void actionPerformed(ActionEvent e) {
		DatabaseConnection database = new DatabaseConnection();
		
		try {
			database.databaseSearch();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}

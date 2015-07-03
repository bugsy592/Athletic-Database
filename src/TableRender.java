import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;


@SuppressWarnings({ "serial" })
public class TableRender extends AbstractTableModel implements TableCellRenderer {
	
	String[]	columnNames	= {"First Name", "Last Name", "Sport", "Year"};
	String[][]	data 		= {{"0","1","2","3",},{"0","1","2","3"},{"0","1","2","3"},{"0","1","2","3"}};
	
	public void setData(){
		sqltests sql = new sqltests();
		int num = sql.high;
		
		String[][] temp = new String[num][3];
		
		for (int i = 0;i < num;i++){
			temp[i][3] = sql.dataYear.get(i);
			temp[i][2] = sql.dataSport.get(i);
			temp[i][1] = sql.dataLast.get(i);
			temp[i][0] = sql.dataFirst.get(i);
		}
		
		temp = data;
	}
	
	public void setSearchBar(){
		System.out.println("Started setSearchBar");
		sqltests sql = new sqltests();
		int numS = sql.amountSport;
		int numY = sql.amountYear;
		
		String[] tempS = new String[numS + 1];
		String[] tempY = new String[numY + 1];
		
		tempS[0] = "<Select a Sport>";
		tempY[0] = "<Select a Year>";
		System.out.println("Set temp pos 0");
		System.out.println(sql.dataYearTypes.get(1));
		
		for (int i = 1;i != numS;i++){
			tempS[i] = sql.dataSportTypes.get(i);
		}
		
		for (int i = 1;i != numY;i++){
			tempY[i] = sql.dataYearTypes.get(i);
			System.out.println(sql.dataYearTypes.get(i) + "put into temp:" + (i + 1));
		}
		
		
		Display dis = new Display();
		
		dis.setSportsList(tempS, numS + 1);
		dis.setYearsList(tempY, numY + 1);
	}
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int col) {
		return null;
	}

	@Override
	public int getColumnCount() {
		int col = columnNames.length;
		return col;
	}

	@Override
	public int getRowCount() {
		int row = data.length;
		return row;
	}

	@Override
	public Object getValueAt(int row, int col) {
		return data[row][col];
	}

	public String getColumnName(int col) {
        return columnNames[col];
    }
}

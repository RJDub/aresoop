package view;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class ColonistTableModel implements TableModel {

	private String[][] data;
	private String[] columnNames;
	private int row;
	
	public ColonistTableModel(String[][] d, String[] c, int i) {
		data = d;
		columnNames = c;
		row = i;
	}
	
	@Override
	public int getRowCount() {
		return row;
	}

	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public String getColumnName(int columnIndex) {
		if (columnIndex == 0)
			return "Name";
		else if (columnIndex == 1)
			return "Hunger";
		else if (columnIndex == 2)
			return "Thirst";
		else 
			return "Fatigue";
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return data[rowIndex][columnIndex];
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub

	}

}

package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

import model.*;
import buildings.*;

public class BuildingPanel extends JPanel{
	
	private ArrayList<Building> buildingArray;
	private String[] columnNames;
	private ArrayList<String[]> data;
	private static JTable table;
	
	
	public BuildingPanel(ArrayList<Building> b) {
		buildingArray = b;
		layoutGUI();
	}

	private void layoutGUI() {
//		buildingArray.add(new StorageBuilding(4,4));
		columnNames = new String[]{"Name", "Row", "Column"};
		data = new ArrayList<String[]>();
		for(int i =0; i < buildingArray.size(); i++){
			String[] temp = new String[3];
			temp[0] = buildingArray.get(i).getType().toString();
			temp[1] = Integer.toString(buildingArray.get(i).getR());
			temp[2] = Integer.toString(buildingArray.get(i).getC());
			data.add(temp);
		}
//		for (int j = buildingArray.size(); j < 10; j++) {
//			data[j][0] = "";
//			data[j][1] = "";
//			data[j][2] = "";
//		}
		table = new JTable(new BuildingTableModel(data,columnNames));
		table.setPreferredSize(new Dimension(400,1000));
		table.setFont(AresFrame.FONT);
		table.setForeground(AresFrame.F_COLOR);
		table.setBackground(Color.BLACK);
		table.setGridColor(Color.BLACK);
		table.setFocusable(false);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBackground(Color.BLACK);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setPreferredSize(new Dimension((int) (Toolkit.getDefaultToolkit().getScreenSize().width * .333),(int) (Toolkit.getDefaultToolkit().getScreenSize().height * .13)));
		this.add(scrollPane);
	}
	
	private String[] toArrayInString(ArrayList<Building> b) {
		String temp[] = new String[b.size()];
		for (int i = 0; i < b.size(); i++) {
			temp[i] = b.get(i).getType().toString();
		}
		return temp;
	}

	public static JTable getBuildingTable() {
		return table;
	}

	public ArrayList<Building> getArrBuildings() {
		return buildingArray;
	}

	public void updateBuildingList(ArrayList<Building> in){
		buildingArray = in;
	}

	public void update(ArrayList<Building> b) {
		for (int row = 0; row < b.size(); row++) {
			for (int col = 0; col < 3; col++) {
				if (col == 0)
					table.setValueAt(b.get(row).getType().toString(), row, col);
				else if (col == 1)
					table.setValueAt(Integer.toString(b.get(row).getR()), row, col);
				else 
					table.setValueAt(Integer.toString(b.get(row).getC()), row, col);
			}
		}
		repaint();
	}
}

class BuildingTableModel extends AbstractTableModel {

	private ArrayList<String[]> data;
	private String[] columnNames;
	
	public BuildingTableModel(ArrayList<String[]> d, String[] c) {
		data = d;
		columnNames = c;
	}
	
	@Override
	public int getRowCount() {
		return data.size()-1;
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public String getColumnName(int columnIndex) {
		if (columnIndex == 0)
			return "Name";
		else if (columnIndex == 1)
			return "Row";
		else
			return "Column";
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
		return data.get(rowIndex)[columnIndex];
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		data.get(rowIndex)[columnIndex] = (String) aValue;
		fireTableCellUpdated(rowIndex, columnIndex);
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



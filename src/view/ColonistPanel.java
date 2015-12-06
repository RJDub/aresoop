package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.TableModelListener;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.AbstractTableModel;

import enums.*;
import model.Colonist;

public class ColonistPanel extends JPanel{
	
	private ArrayList<Colonist> colonists;
	private String[] columnNames;
	private String[][] data;
	private JTable table;
	
	public ColonistPanel(ArrayList<Colonist> c) {
		colonists = c;
		layoutGUI();
	}
	
	public void updateColonistList(ArrayList<Colonist> in){
		colonists = in;
	}

	private void layoutGUI() {
		columnNames = new String[]{"Name", "Hunger", "Thirst", "Fatigue"};
		data = new String[20][6];
		for(int i =0; i < colonists.size(); i++){
			data[i][0] = colonists.get(i).getName();
			data[i][1] = Integer.toString(colonists.get(i).getHungerLevel());
			data[i][2] = Integer.toString(colonists.get(i).getThirstLevel());
			data[i][3] = Integer.toString(colonists.get(i).getFatigueLevel());
			data[i][4] = colonists.get(i).getTask().toString();
			data[i][5] = colonists.get(i).getAction().toString();
		}
		for (int j = colonists.size(); j < 20; j++) {
			data[j][0] = "";
			data[j][1] = "";
			data[j][2] = "";
			data[j][3] = "";
			data[j][4] = "";
			data[j][5] = "";
		}
		table = new JTable(new ColonistTableModel(data,columnNames,20));
		table.setPreferredSize(new Dimension(600,1500));
		table.setFont(AresFrame.FONT);
		table.setForeground(AresFrame.F_COLOR);
		table.setBackground(Color.BLACK);
		table.setGridColor(Color.BLACK);
		table.setFocusable(false);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBackground(Color.BLACK);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI()
	    {   
			@Override 
	        protected void configureScrollBarColors(){
	            this.thumbColor = AresFrame.F_COLOR;
	            this.trackColor = Color.BLACK;
	        }
			
			@Override
	        protected JButton createDecreaseButton(int orientation) {
	            return createZeroButton();
	        }

	        @Override    
	        protected JButton createIncreaseButton(int orientation) {
	            return createZeroButton();
	        }

	        private JButton createZeroButton() {
	            JButton jbutton = new JButton();
	            jbutton.setPreferredSize(new Dimension(0, 0));
	            jbutton.setMinimumSize(new Dimension(0, 0));
	            jbutton.setMaximumSize(new Dimension(0, 0));
	            return jbutton;
	        }
	    });
		scrollPane.setPreferredSize(new Dimension((int) (Toolkit.getDefaultToolkit().getScreenSize().width * .333),(int) (Toolkit.getDefaultToolkit().getScreenSize().height * .333)));
		this.add(scrollPane, BorderLayout.NORTH);
		
	}

	public void update(ArrayList<Colonist> c) {
		for (int row = 0; row < c.size(); row++) {
			for (int col = 0; col < 6; col++) {
				if (col == 0)
					table.setValueAt(c.get(row).getName(), row, col);
				else if (col == 1)
					table.setValueAt(Integer.toString(c.get(row).getHungerLevel()), row, col);
				else if (col == 2)
					table.setValueAt(Integer.toString(c.get(row).getThirstLevel()), row, col);
				else if (col == 3)
					table.setValueAt(Integer.toString(c.get(row).getFatigueLevel()), row, col);
				else if (col == 4)
					table.setValueAt(c.get(row).getTask().toString(), row, col);
				else 
					table.setValueAt(c.get(row).getAction().toString(), row, col);
			}
		}
		repaint();
	}

	public JTable getTable() {
		return table;
	}

	public String[][] getData() {
		return data;
	}

	public void addANewRow() {
//		String[][] newData = new String[data.length + 1][6];
//		for (int i = 0; i < data.length; i++){
//			newData[i][0] = colonists.get(i).getName();
//			newData[i][1] = Integer.toString(colonists.get(i).getHungerLevel());
//			newData[i][2] = Integer.toString(colonists.get(i).getThirstLevel());
//			newData[i][3] = Integer.toString(colonists.get(i).getFatigueLevel());
//			newData[i][4] = colonists.get(i).getTask().toString();
//			newData[i][5] = colonists.get(i).getAction().toString();
//		}
//		for (int j = 0; j < 6; j++)
//			newData[data.length][j] = "";
//		data = newData;
//		System.out.println(data.length);
//		table = new JTable(new ColonistTableModel(data,columnNames,data.length));
		
	}
}


class ColonistTableModel extends AbstractTableModel {

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
		return 6;
	}

	@Override
	public String getColumnName(int columnIndex) {
		if (columnIndex == 0)
			return "Name";
		else if (columnIndex == 1)
			return "Hunger";
		else if (columnIndex == 2)
			return "Thirst";
		else if (columnIndex == 3)
			return "Fatigue";
		else if (columnIndex == 4)
			return "Task";
		else {
			return "Action";
		}
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
		data[rowIndex][columnIndex] = (String) aValue;
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

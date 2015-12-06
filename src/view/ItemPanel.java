package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.TableModelListener;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;

import items.*;
import model.Building;
import model.Item;

public class ItemPanel extends JPanel{

	private ArrayList<Item> itemArray;
	private String[] columnNames;
	private String[][] data;
	private static JTable table;
	
	
	public ItemPanel(ArrayList<Item> i) {
		itemArray = i;
		layoutGUI();
	}
	
	public void updateItemList(ArrayList<Item> in){
		itemArray = in;
	}
	
	private void layoutGUI() {
		columnNames = new String[]{"Name", "Owner"};
		data = new String[10][2];
		for(int i =0; i < itemArray.size(); i++){
			data[i][0] = itemArray.get(i).toString();
			data[i][1] = itemArray.get(i).getOwner();
		}
		for (int j = itemArray.size(); j < 10; j++) {
			data[j][0] = "";
			data[j][1] = "";
		}
		table = new JTable(new ItemTableModel(data,columnNames,10));
		table.setPreferredSize(new Dimension(400,1000));
		table.setFont(AresFrame.FONT);
		table.setForeground(AresFrame.F_COLOR);
		table.setBackground(Color.BLACK);
		table.setGridColor(Color.BLACK);
		table.setFocusable(false);
		JTableHeader header = table.getTableHeader();
	    header.setBackground(Color.black);
	    header.setForeground(Color.CYAN);
	    Font font = header.getFont();
	    Font boldFont = new Font(font.getFontName(), Font.BOLD, font.getSize() + 1);
	    header.setFont(boldFont);
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
		scrollPane.setPreferredSize(new Dimension((int) (Toolkit.getDefaultToolkit().getScreenSize().width * .333),(int) (Toolkit.getDefaultToolkit().getScreenSize().height * .13)));
		this.add(scrollPane);
	}

	private String[] toArrayInString(ArrayList<Item> i) {
		String temp[] = new String[i.size()];
		for (int j = 0; j < i.size(); j++) {
			temp[j] = i.get(j).toString();
		}
		return temp;
	}

	public JTable getItemTable() {
		return table;
	}
	
	public ArrayList<Item> getArrItems() {
		return itemArray;
	}

	public void update(ArrayList<Item> i) {
		for (int row = 0; row < i.size(); row++) {
			for (int col = 0; col < 2; col++) {
				if (col == 0)
					table.setValueAt(i.get(row).toString(), row, col);
				else 
					table.setValueAt(i.get(row).getOwner(), row, col);
			}
		}
		repaint();
	}
}

class ItemTableModel extends AbstractTableModel {

	private String[][] data;
	private String[] columnNames;
	private int row;
	
	public ItemTableModel(String[][] d, String[] c, int i) {
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
		return 2;
	}

	@Override
	public String getColumnName(int columnIndex) {
		if (columnIndex == 0)
			return "Name";
		else
			return "Owner";
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


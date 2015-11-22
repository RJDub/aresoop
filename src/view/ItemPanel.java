package view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.ScrollPaneConstants;

import items.*;
import model.Item;

public class ItemPanel extends JPanel{

	private ArrayList<Item> itemArray;
	private JList itemList;
	
	public ItemPanel(ArrayList<Item> i) {
		itemArray = i;
		layoutGUI();
	}
	
	private void layoutGUI() {
		itemList = new JList(toArrayInString(itemArray));
		itemList.setPreferredSize(new Dimension(400,1000));
		JScrollPane scrollPane = new JScrollPane(itemList);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
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
}

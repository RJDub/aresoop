package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.Building;
import model.Colonist;
import model.Item;

public class DisplayPanel extends JPanel{
	
	private JTextArea temp;
	private JButton play;
	private JButton optionsPopup;
	private JPanel buttonPanel;
	public DisplayPanel() {
		layoutGUI();
	}
	
	public JButton getPlay(){
		return play;
	}

	private void layoutGUI() {
		// Start Info
		temp = new JTextArea("\n\n\n\tSelect A Tile or A Colonist for More Information");
		temp.setEditable(false);
		//temp.setPreferredSize(new Dimension((int) (Toolkit.getDefaultToolkit().getScreenSize().width * .333),(int) (Toolkit.getDefaultToolkit().getScreenSize().height * .27)));
		setupButtonPanel();
		
		this.setLayout(new BorderLayout());
		this.add(temp, BorderLayout.CENTER);
		this.add(buttonPanel, BorderLayout.NORTH);
	}

	private void setupButtonPanel() {
		setupPlayButton();
		optionsPopup = new JButton("Temp Label:Options Popup?");
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1,4));
		buttonPanel.add(play);
		buttonPanel.add(optionsPopup);
		
	}

	private void setupPlayButton() {
		play = new JButton("Play/Pause");
	}

	// When colonist is selected
	public void colonistSelected(Colonist c) {
		String t = 
		"\n\n\tName:   " + c.getName()
		+ "\n\tHunger:   " + c.getHungerLevel()
		+ "\n\tThirst:   " + c.getThirstLevel()
		+ "\n\tFatigue:   " + c.getFatigueLevel()
		+ "\n\tTask:   " + c.getTask()
		+ "\n\tAction:   " + c.getAction()
		+ "\n\tResource Amount:   " + c.getResourceAmount();
		if (c.getItems()!=null){
			t = t+ "\n\tItem:   " + c.getItems().toString();
		}
		t = t+ "\n\tColumn:   " + c.getC()
		+ "\n\tRow:   " + c.getR();
		temp.setText(t);
	}

	private String getItems(ArrayList<Item> i) {
		String ref = "";
		if (i.isEmpty())
			ref = "None";
		for (Item thisItem: i) {
			ref += thisItem.toString();
		}
		return ref;
	}

	public void buildingSelected(Building b) {
		temp.setText("\n\n\tBuilding Type:   " + b.getType().toString()
		+ "\n\tRow:   " + b.getR() 
		+ "\n\tColumn:   " +b.getC()
		+ "\n\tHungerBonus:   " + b.getHungerBonus()
		+ "\n\tThirstBonus:   " + b.getThirstBonus()
		+ "\n\tFAtigueBonus:   " + b.getFatigueBonus()
		+ "\n\tThis building has (those facilities)");
	}
	
	public void displayTileInformation(int row, int col){
		temp.setText("You clicked the map");
	}
}

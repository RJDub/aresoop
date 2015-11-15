package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.Colonist;

public class DisplayPanel extends JPanel{
	
	private JTextArea temp;
	private JPanel colonistSelected;
	private JButton play;
	
	private ArrayList<Colonist> colonists;
	
	public DisplayPanel() {
		layoutGUI();
	}
	
	public JButton getPlay(){
		return play;
	}

	private void layoutGUI() {
		temp = new JTextArea("Game Start Info");
		temp.setEditable(false);
		temp.setPreferredSize(new Dimension(390,180));
		this.add(temp);
	}

	public void setInfo(Colonist c) {
//		temp.setText("Name:   " + c.getName()
//		+ "\nHunger:   " + c.getHunger().toString()
//		+ "\nThirst:   " + c.getThirst().toString()
//		+ "\nFatigue:   " + c.getFatigue().toString()
//		+ "\nX:   " + c.getXcoord()
//		+ "\nY:   " + c.getYcoord());
	}
}

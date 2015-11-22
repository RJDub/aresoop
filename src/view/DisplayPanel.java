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

import model.Colonist;

public class DisplayPanel extends JPanel{
	
	private JTextArea temp;
	private JButton play;
	
	public DisplayPanel() {
		layoutGUI();
	}
	
	public JButton getPlay(){
		return play;
	}

	private void layoutGUI() {
		temp = new JTextArea("\n\n\n\tSelect A Tile or A Colonist for More Information");
		temp.setEditable(false);
		temp.setPreferredSize(new Dimension((int) (Toolkit.getDefaultToolkit().getScreenSize().width * .333),(int) (Toolkit.getDefaultToolkit().getScreenSize().height * .27)));
		this.add(temp);
	}

	public void colonistSelected(Colonist c) {
		temp.setText("\n\n\n\n\tName:   " + c.getName()
		+ "\n\tHunger:   " + c.getHungerLevel()
		+ "\n\tThirst:   " + c.getThirstLevel()
		+ "\n\tFatigue:   " + c.getFatigueLevel()
		+ "\n\tTask:   " + c.getTask()
		+ "\n\tAction:   " + c.getAction()
		+ "\n\tX:   " + c.getC()
		+ "\n\tY:   " + c.getR());
	}
}

package view;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class HUD extends JPanel{
	
	private JLabel name;
	private JLabel hunger;
	private JLabel thirst;
	private JLabel fatigue;
	private JTextField nameField;
	private JTextField hungerField;
	private JTextField thirstField;
	private JTextField fatigueField;
	
	public HUD() {
		layoutGUI();
	}

	private void layoutGUI() {
		this.setLayout(new BorderLayout());
		
	}

	public void setInfo(String n, String h, String t, String f) {
		name = new JLabel("Name");
		hunger = new JLabel("Hunger");
		thirst = new JLabel("Thirst");
		
	}
}

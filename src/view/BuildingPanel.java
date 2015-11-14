package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BuildingPanel extends JPanel{
	
	private JButton addNewColonist;
	
	public BuildingPanel() {
		layoutGUI();
	}

	private void layoutGUI() {
		this.setLayout(new GridLayout(4,1));
		addNewColonist = new JButton("Add a new colonist");
		addNewColonist.addActionListener(new addNewColonistListener());
		this.add(addNewColonist);
	}
}

class addNewColonistListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		new AddNewColonistDialog();
	}
	
}

class AddNewColonistDialog extends JDialog{
	
	private JTextField nameField;
	private JTextField hungerField;
	private JTextField thirstField;
	private JTextField fatigueField;
	private JButton addNewColonist;
	private JButton clear;
	
	public AddNewColonistDialog() {
		this.setTitle("AddNewColonist");
		this.setSize(300, 250);
		this.setLocation(400, 200);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(new GridLayout(4,2));
		
		nameField = new JTextField("");
		hungerField = new JTextField("");
		thirstField = new JTextField("");
		fatigueField = new JTextField("");
		
		addNewColonist = new JButton("Add");
		addNewColonist.addActionListener(new AddToColonistList());
		clear = new JButton("clear");
		
		this.add(new JLabel("Name"));
		this.add(nameField);
		this.add(new JLabel("Hunger"));
		this.add(hungerField);
		this.add(new JLabel("Thirst"));
		this.add(thirstField);
		this.add(new JLabel("Fatigue"));
		this.add(fatigueField);
		this.add(addNewColonist);
		this.add(clear);
	}
	
	private class AddToColonistList implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
		    // do to the model
			// under construction
			
			
			//do to the list (temp) (delete after model communicate to gui(colonistPanel))
		}	
	}
	
	public void main(String[] args) {
		AddNewColonistDialog c = new AddNewColonistDialog();
	}
}

package view;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.*;

public class AresGUI extends JFrame{
	
	private Map board;

	
	
	private MotherBoard Logic;
	public static void main (String[] args){

		AresGUI view = new AresGUI();
		view.setVisible(true);
	}
	
	public AresGUI(){
		layoutGUI();
		setupModel();
	}
	
	public void layoutGUI(){
		this.setSize(1200, 1000);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new GridLayout(2,1));
		
		board = new Map();
		this.add(board);
		
		//informationPanel = new JPanel();
		//informationPanel.setLayout(new GridLayout(1,3));
		//informationPanel.add();
	}
	
	public void setupModel(){
		
	}
}

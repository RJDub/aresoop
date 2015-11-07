package view;

import javax.swing.JFrame;

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
		board = new Map();
		this.add(board);
	}
	
	public void setupModel(){
		Logic = new MotherBoard();
	}
}

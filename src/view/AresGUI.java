package view;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import management.*;

public class AresGUI extends JFrame{
	
	private Map board;
	private JPanel informationPanel;
	private JPanel leftPanel;
	private JScrollPane colonistPanel; // error
	private JPanel HUD;
	private Manager build_manager;
	private Manager col_manager;
	private Manager goal_manager;
	private Manager proj_manager;
	private Manager res_manager;
	
	public void main (String[] args){
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
		
		informationPanel = new JPanel();
		informationPanel.setLayout(new GridLayout(1,3));
		//informationPanel.add();
	}
	
	public void setupModel(){
		build_manager = new BuildingManager();
		col_manager = new ColonistManager();
		goal_manager = new ColonyGoalManager();
		proj_manager = new ColonyProjectManager();
		res_manager = new ResourceManager();
	}
}

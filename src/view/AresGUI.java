package view;

import javax.swing.JFrame;
import management.*;

public class AresGUI extends JFrame{
	
	private Map board;
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
		board = new Map();
		this.add(board);
	}
	
	public void setupModel(){
		build_manager = new BuildingManager();
		col_manager = new ColonistManager();
		goal_manager = new ColonyGoalManager();
		proj_manager = new ColonyProjectManager();
		res_manager = new ResourceManager();
	}
}

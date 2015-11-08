package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
<<<<<<< HEAD
import javax.swing.JScrollPane;

import model.*;
=======
>>>>>>> refs/remotes/origin/Mingcheng

public class AresGUI extends JFrame{
	
	private Map board;
<<<<<<< HEAD

=======
	private JPanel informationPanel;
	private JPanel leftPanel;
	private JPanel middlePanel;
	private JPanel rightPanel;
	
	private ColonistPanel colonist; // error
	private HUD hud;
	private BuildingPanel building;
	
	/*
	private Manager build_manager;
	private Manager col_manager;
	private Manager goal_manager;
	private Manager proj_manager;
	private Manager res_manager;
	*/
>>>>>>> refs/remotes/origin/Mingcheng
	
<<<<<<< HEAD
	
	private MotherBoard Logic;
	public static void main (String[] args){

=======
	public static void main (String[] args){
>>>>>>> refs/remotes/origin/Mingcheng
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
		this.setLayout(null);
		
		// place map on the top of the gui
		board = new Map();
		board.setSize(1200, 600);
		board.setLocation(0, 0);
		this.add(board);
		
<<<<<<< HEAD
		//informationPanel = new JPanel();
		//informationPanel.setLayout(new GridLayout(1,3));
		//informationPanel.add();
=======
		informationPanel = new JPanel();
		informationPanel.setLayout(new GridLayout(1,2));
		colonist = new ColonistPanel();
		hud = new HUD();
		building = new BuildingPanel();
		//informationPanel.add(colonist);
		//informationPanel.add(hud);
		//informationPanel.add(building);
		
		// place info panel on the bottom of the gui
		this.add(informationPanel);
		
		
>>>>>>> refs/remotes/origin/Mingcheng
	}
	
	public void setupModel(){
<<<<<<< HEAD
		
=======
		/*build_manager = new BuildingManager();
		col_manager = new ColonistManager();
		goal_manager = new ColonyGoalManager();
		proj_manager = new ColonyProjectManager();
		res_manager = new ResourceManager();
		*/
>>>>>>> refs/remotes/origin/Mingcheng
	}
}

package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Timer;

import model.*;

public class AresGUI extends JFrame{
	
	private Map board;

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
	
	private MotherBoard Logic;

	public static void main (String[] args){
		AresGUI view = new AresGUI();
		view.setVisible(true);

	}
	
	public AresGUI(){
		layoutGUI();
		setupModel();
		Timer timer = new Timer(1000, new UpdateGameStateActionListener());
		timer.start();
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
		board.drawBoard();
		

		informationPanel = new JPanel();
		informationPanel.setLayout(new GridLayout(1,2));
		colonist = new ColonistPanel();
		hud = new HUD();
		building = new BuildingPanel();
		informationPanel.add(colonist);
		informationPanel.add(hud);
		informationPanel.add(building);
		
		// place info panel on the bottom of the gui
		informationPanel.setSize(1200,400);
		informationPanel.setLocation(0, 600);
		this.add(informationPanel);
		

	}
	
	public void setupModel(){
		Logic = new MotherBoard();
		Logic.addObserver(board);
	}
	
	private class UpdateGameStateActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			Logic.update();
			
		}
	}
}

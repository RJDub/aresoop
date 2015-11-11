package view;

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
		this.setLayout(new GridLayout(2,1));
		
		board = new Map();
		this.add(board);
		board.drawBoard();
		
		//informationPanel = new JPanel();
		//informationPanel.setLayout(new GridLayout(1,3));
		//informationPanel.add();
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

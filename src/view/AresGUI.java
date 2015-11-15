package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.Timer;

import model.*;

public class AresGUI extends JFrame{
	
	private Map board;

	private JPanel informationPanel;
	private JPanel hudPanel;
	
	private ColonistPanel colonist;
	private DisplayPanel hud;
	private BuildingPanel building;
	
	private MotherBoard Logic;
	private LocalDateTime startTime;
	private LocalDateTime timeNow;
	private JTextField time;

	public static void main (String[] args){
		AresGUI view = new AresGUI();
		view.setVisible(true);

	}
	
	public AresGUI(){
		setupModel();
		layoutGUI();
		Timer timer = new Timer(1000, new UpdateGameStateActionListener());
		timer.start();
		startTime = LocalDateTime.now();
	}
	
	public void layoutGUI(){
		this.setSize(1200, 850);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
		
		// place map on the top of the gui
		//board = new Map();
		//board.setSize(1200, 600);
		//board.setLocation(0, 0);
		//this.add(board);
		//board.drawBoard();

		informationPanel = new JPanel();
		informationPanel.setLayout(new GridLayout(1,3));
		
		colonist = new ColonistPanel(Logic.getArrColonists());
		colonist.table.addMouseListener(new RowSelectListener());
		building = new BuildingPanel();
		
		hudPanel = new JPanel();
		hudPanel.setLayout(null);
		
		hud = new DisplayPanel();
		hud.setSize(400, 180);
		hud.setLocation(0, 0);
		hudPanel.add(hud);
		
		DisplayPanel hud2 = new DisplayPanel();
		hud2.setSize(400, 180);
		hud2.setLocation(0, 0);
		hud2.setVisible(false);
		hudPanel.add(hud2);
		
		time = new JTextField();
		time.setSize(400, 50);
		time.setLocation(0, 175);
		time.setEditable(false);
		time.setHorizontalAlignment(JTextField.CENTER);
		hudPanel.add(time);
		
		informationPanel.add(colonist);
		informationPanel.add(hudPanel);
		informationPanel.add(building);
		
		// place info panel on the bottom of the gui
		informationPanel.setSize(1200,400);
		informationPanel.setLocation(0, 600);
		this.add(informationPanel);
	}
	
	private class RowSelectListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			int rowSelected = colonist.table.getSelectedRow();
			if (rowSelected < 0) {
				
			}
			else{
				setInfo(colonist.data[rowSelected][0]);
			}
		}

		private void setInfo(String n) {
		JPanel colonistSelected;
		Colonist refColonist = null;
		for (Colonist thisColonist: colonist.colonists) {
			if (thisColonist.getName().equals(n))
				refColonist = thisColonist;
		}
		hud.setInfo(refColonist);
	}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public void setupModel(){
		Logic = new MotherBoard();
		//Logic.addObserver(board);
	}
	
	private class UpdateGameStateActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			Logic.update();
			calculateTime();
		}

		private void calculateTime() {
			timeNow = LocalDateTime.now();
			int day;
			int hour;
			if (timeNow.getMinute() >= startTime.getMinute()) {
				if (timeNow.getSecond() >= startTime.getSecond())
					day = timeNow.getMinute() - startTime.getMinute() + 1;
				else
					day = timeNow.getMinute() - startTime.getMinute();
			}
			else {
				if (timeNow.getSecond() >= startTime.getSecond())
					day = timeNow.getMinute() + 60 - startTime.getMinute() + 1;
				else
					day = timeNow.getMinute() + 60 - startTime.getMinute();
			}

			if(timeNow.getSecond() >= startTime.getSecond())
				hour = (timeNow.getSecond() - startTime.getSecond()) * 24 / 60;
			else 
				hour = (timeNow.getSecond() + 60 - startTime.getSecond()) * 24 / 60;
			time.setText("Day: " + day + "  Hour: " + hour);
			
		}
	}
}

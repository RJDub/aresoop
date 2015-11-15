package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.Timer;

import enums.TileType;
import model.*;

public class AresFrame extends JFrame {
	public static final int MINUTE = 1000000;
	
	private static MapPanel map;
	private static MotherBoard model;
	private Timer timer;

	public static void main(String[] args) {

		ArrayList<Colonist> colonists = new ArrayList<Colonist>();
		Tile[][] tiles = new Tile[10][10];
		model = new MotherBoard(colonists, Generator.generateMap(tiles));

		model.getArrColonists().add(new Colonist("Paul", 0, 0));
		model.getArrColonists().add(new Colonist("Mingcheng", 0, 0));
		map = new MapPanel();

		new AresFrame();
	}

	public AresFrame() {
		this.setVisible(true);
		this.setLayout(null);
		this.setLocation(100, 200);
		this.setSize(5000, 3000);
		this.setBackground(Color.RED);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		timer = new Timer(MINUTE, new OurTimerListener());
		model.addObserver(map);
		setupMapPanel();
		this.add(map);
		
		model.start();
		model.printModel();
		

	}
	
	private void setupMapPanel(){
		map.setVisible(true);
		map.setLocation(0, 0);
		map.setSize(5000, 2000);
		map.setBackground(Color.LIGHT_GRAY);
	}

	private class OurTimerListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			if (timer.isRunning()) {
				model.update();
			}
		}

	}
}

package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.ScrollPane;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.Timer;

import enums.Task;
import enums.TileType;
import model.*;

public class AresFrame extends JFrame {
	private static final int MINUTE = 1000000;
	private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	private JScrollPane mapPane;
	private MapPanel map;
	private ColonistPanel colonistPanel;
	private DisplayPanel hud;
	private SelectorPanel selector;
	private BuildingPanel buildings;
	private ItemPanel items;
	
	private static MotherBoard model;
	private JPanel view;
	private Timer timer;

	public static void main(String[] args) {

		ArrayList<Colonist> colonists = new ArrayList<Colonist>();
		Tile[][] tiles = new Tile[10][10];
		model = new MotherBoard(colonists, Generator.generateMap(tiles));

		model.getArrColonists().add(new Colonist("Paul", 0, 0));
		model.getArrColonists().add(new Colonist("Mingcheng", 0, 0));
		

		AresFrame window = new AresFrame();
		window.setVisible(true);
	}

	public AresFrame() {
		layoutGUI();
		setupModelAndTimer();
		registerListeners();
	}
	
	

	private void layoutGUI(){
		setupAresFrame();
		
		view = new JPanel();
		setupView();
		
		map = new MapPanel(model);
		setupMapPanel();
		mapPane = new JScrollPane(map);
		
		mapPane.setVisible(true);
		mapPane.setLocation(0, 0);
		mapPane.setSize(screenSize.width, (int) (screenSize.height * .666));
		mapPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		mapPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		colonistPanel = new ColonistPanel(model.getArrColonists());
		setupColonistPanel();
		
		hud = new DisplayPanel();
		setupHud();
		
		selector = new SelectorPanel();
		setupSelectorPanel();
		
		view.add(selector);
		view.add(hud);
		view.add(mapPane);
		view.add(colonistPanel);
		this.add(view);
	}
	
	private void setupModelAndTimer(){
		model.addObserver(map);
		model.assignTask(model.getArrColonists().get(0), Task.MiningIce);
		model.assignTask(model.getArrColonists().get(1), Task.MiningIronOre);
		
		timer = new Timer(1000, new OurTimerListener());
		timer.start();
//		model.start();
//		model.printModel();
	}
	
	private void registerListeners() {
		// TODO Auto-generated method stub
		
	}
	
	private void setupAresFrame(){
		this.setLayout(null);
		this.setFocusable(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(screenSize);
	}
	
	private void setupView(){
		view.setLayout(null);
		view.setSize(screenSize);
		view.setVisible(true);
		view.setLocation(0, 0);
		view.setBackground(Color.BLACK);
	}
	
	private void setupMapPanel(){
		map.setVisible(true);
		map.setLocation(0, 0);
		map.setPreferredSize(new Dimension(4000,4000));
		map.setBackground(Color.BLUE);
	}
	
	private void setupColonistPanel(){
		colonistPanel.setVisible(true);
		colonistPanel.setLocation(0, (int) (screenSize.height * .666));
		colonistPanel.setSize((int) (screenSize.width * .333), (int) (screenSize.height * .333));
		colonistPanel.setBackground(Color.RED);
	}
	
	private void setupHud(){
		hud.setVisible(true);
		hud.setLocation((int) (screenSize.width * .333), (int) (screenSize.height * .666));
		hud.setSize((int) (screenSize.width * .333), (int) (screenSize.height * .333));
		hud.setBackground(Color.DARK_GRAY);
	}
	
	private void setupSelectorPanel(){
		selector.setVisible(true);
		selector.setLocation((int) (screenSize.width * .666), (int) (screenSize.height * .666));
		selector.setSize((int) (screenSize.width * .333), (int) (screenSize.height * .333));
		selector.setBackground(Color.CYAN);
		
		buildings = new BuildingPanel();
		
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

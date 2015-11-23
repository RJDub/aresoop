package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.ScrollPane;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.Timer;

import buildings.Dormitory;
import buildings.Mess;
import buildings.StorageBuilding;
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
	private JPanel selector;
	private BuildingPanel buildings;
	private ItemPanel items;
	
	private static MotherBoard model;
	private JPanel view;
	private Timer timer;

	public static void main(String[] args) {

		ArrayList<Colonist> colonists = new ArrayList<Colonist>();
		Tile[][] tiles = new Tile[100][100];
//		model = new MotherBoard(colonists, Generator.generateMap(tiles));
		model = new MotherBoard(colonists, Generator.generateEasyMap(tiles));
		model.getArrColonists().add(new Colonist("Paul", 0, 0));
		model.getArrColonists().add(new Colonist("Mingcheng", 0, 0));
		
		model.addBuilding(new Dormitory(4,4));
		model.addBuilding(new Mess(4,5));
		model.addBuilding(new StorageBuilding(8, 1));
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
		
		selector = new JPanel();
		setupSelectorPanel();
		
		//TODO under construction (set up Layout and size)
		buildings = new BuildingPanel(model.getArrBuildings());
		setupBuildingPanel();
		
		// need Motherboard to return an ItemList
		//items = new ItemPanel();
		
		
		selector.add(buildings);
		//selector.add(items);
		
		
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
		
		timer = new Timer(500, new OurTimerListener());
		timer.start();
//		model.start();
//		model.printModel();
	}
	
	private void registerListeners() {
		// TODO Under construction 
		// add colonistPanelSelectedListener
		colonistPanel.getTable().addMouseListener(new ColonistRowSelectListener());
		buildings.getBuildingList().addMouseListener(new BuildingRowSelectListener());
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
	}
	
	private void setupBuildingPanel() {
		buildings.setVisible(true);
		buildings.setLocation(0, 0);
		buildings.setSize((int) (screenSize.width * .333), (int) (screenSize.height * .17));
	}
	
	

	private class OurTimerListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			if (timer.isRunning()) {
				model.update();
				
				//TODO Under construction(update panels)
				updateView();
			}
		}

		private void updateView() {
			colonistPanel.update(model.getArrColonists());
			updateHud();
		}

		private void updateHud() {
			int rowSelected = colonistPanel.getTable().getSelectedRow();
			if (rowSelected < 0) {
				// Do nothing 
			}
			else{
				Colonist refColonist = null;
				for (Colonist thisColonist: model.getArrColonists()) {
					if (thisColonist.getName().equals(colonistPanel.getData()[rowSelected][0]))
						refColonist = thisColonist;
				}
				hud.colonistSelected(refColonist);
			}
		}

	}
	
	private class ColonistRowSelectListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			int rowSelected = colonistPanel.getTable().getSelectedRow();
			if (rowSelected < 0) {
				// Do nothing 
			}
			else{
				Colonist refColonist = null;
				for (Colonist thisColonist: model.getArrColonists()) {
					if (thisColonist.getName().equals(colonistPanel.getData()[rowSelected][0]))
						refColonist = thisColonist;
				}
				hud.colonistSelected(refColonist);
			}
			
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
	
	private class BuildingRowSelectListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			int rowSelected = BuildingPanel.getBuildingList().getSelectedIndex();
			Building refBuilding = null;
			if (rowSelected < 0) {
				// Do nothing 
			}
			else{
				for (Building thisBuilding: model.getArrBuildings()) {
					if (thisBuilding.getType().equals(buildings.getArrBuildings().get(rowSelected)))
						refBuilding = thisBuilding;
				}
			}
			System.out.println();
				hud.buildingSelected(refBuilding);
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
	
}

package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.ScrollPane;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.Timer;

import buildings.*;
import enums.*;
import items.JackHammer;
import model.*;

public class AresFrame extends JFrame {
	private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	private static final int screen_height = (int) Math.round(screenSize.height);
	private static final int screen_width = (int) Math.round(screenSize.width);

	private JScrollPane mapPane;
	private MapPanel3D map;
	private ColonistPanel colonistPanel;
	private DisplayPanel hud;
	private JPanel selector;
	private BuildingPanel buildings;
	private ItemPanel items;

	private static MotherBoard model;
	private JPanel view;
	private Timer timer;

	public static void main(String[] args) {
		AresFrame window = new AresFrame();
		window.setVisible(true);
	}

	public AresFrame() {

		this.addWindowListener(new MyWindowListener());

		// Tile[][] tiles = new Tile[100][100];
		// model = new MotherBoard(colonists, Generator.generateMap(tiles));
		// Tile[][] tiles = new Tile[100][100];
		// model = new MotherBoard(colonists, Generator.generateMap(tiles));

		Tile[][] tiles = new Tile[30][50];
		ArrayList<Colonist> colonists = new ArrayList<Colonist>();
		boolean TESTINGMODE = false;
		if (TESTINGMODE) {

			//Tile[][] tiles = new Tile[30][50];
			model = Generator.generateStandardModel(100, 100);
			//model = Generator.generateTestMotherBoard(10, 10);

		} else {

			if (model == null) {
				int decision = JOptionPane.showConfirmDialog(AresFrame.this, "Do you want to load your saved game?");
				if (decision == JOptionPane.YES_OPTION) {
					ObjectInputStream objStr = null;
					try {
						FileInputStream stream = new FileInputStream("SavedModelState");
						objStr = new ObjectInputStream(stream);
						model = (MotherBoard) objStr.readObject();
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						System.err.println("Found something other than motherboard");
						e1.printStackTrace();
					} finally {
						try {
							if (objStr != null) {
								objStr.close();
							}
						} catch (IOException e1) {
							System.err.println("File did not close.");
							e1.printStackTrace();
						}
					}
				} else {

					model = Generator.generateStandardModel(50, 50);
//					model = new MotherBoard(colonists, Generator.generateEasyMap(tiles));		
//					model.getArrColonists().add(new Colonist("Paul", 0, 0));
//					model.getArrColonists().add(new Colonist("Mingcheng", 0, 0));
//					model.addBuilding(new Dormitory(4, 4));
//					model.addBuilding(new Mess(4, 5));
//					model.addBuilding(new StorageBuilding(8, 1));

					model.addItem(new JackHammer());

				}
			}
		}

		layoutGUI();
		setupModelAndTimer();
		registerListeners();

		timer.start();
	}

	private void layoutGUI() {
		setupAresFrame();

		view = new JPanel();
		setupView();

		map = new MapPanel3D(model);
		setupMapPanel();
		mapPane = new JScrollPane(map);

		mapPane.setVisible(true);
		mapPane.setLocation(0, 0);

		mapPane.setSize(screen_width, (int) (screen_height * .666));
		mapPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		mapPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

		colonistPanel = new ColonistPanel(model.getArrColonists());
		setupColonistPanel();

		hud = new DisplayPanel();
		setupHud();

		selector = new JPanel();
		setupSelectorPanel();

		// TODO under construction (set up Layout and size)
		buildings = new BuildingPanel(model.getArrBuildings());
		setupBuildingPanel();

		// need Motherboard to return an ItemList
		items = new ItemPanel(model.getArrItems());
		setupItemPanel();
		
		selector.add(buildings);
		selector.add(items);

		view.add(selector);
		view.add(hud);
		view.add(mapPane);
		view.add(colonistPanel);
		this.add(view);
	}

	private void setupModelAndTimer() {
		model.addObserver(map);
		model.addObserver(hud);
		model.assignTask(model.getArrColonists().get(0), Task.MiningIce);
		model.assignTask(model.getArrColonists().get(1), Task.MiningIronOre);

		timer = new Timer(500, new OurTimerListener());
		// timer.start();
		// model.start();
		// model.printModel();
	}

	private void registerListeners() {
		// TODO Under construction
		// add colonistPanelSelectedListener
		// colonistPanel.getTable().addMouseListener(new
		// ColonistRowSelectListener());
		hud.getPlay().addActionListener(new PlayPauseActionListener());
		hud.getAssignTask().addActionListener(new AssignTaskListener());
		hud.getConstruction().addActionListener(new BuildConstructListener());
		hud.getRecruitment().addActionListener(new RecruitmentListener());
		map.addMouseListener(new MapPanelClickedActionListener());

		// buildings.getBuildingList().addMouseListener(new
		// BuildingRowSelectListener());

	}

	private void setupAresFrame() {
		this.setLayout(null);
		this.setFocusable(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setPreferredSize(screenSize);
		this.setSize(screen_width, screen_height);

	}

	private void setupView() {
		view.setLayout(null);
		view.setSize(screen_width, screen_height);
		view.setVisible(true);
		view.setLocation(0, 0);
		view.setBackground(Color.BLACK);
	}

	private void setupMapPanel() {
		map.setVisible(true);
		map.setLocation(0, 0);
		map.setPreferredSize(new Dimension(screen_width, screen_height));
		map.setBackground(Color.BLUE);
	}

	private void setupColonistPanel() {
		colonistPanel.setVisible(true);
		colonistPanel.setLocation(0, (int) (screen_height * .666));
		colonistPanel.setSize((int) (screen_width * .333), (int) (screen_height * .333));
		colonistPanel.setBackground(Color.RED);
		colonistPanel.getTable().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				buildings.getBuildingList().clearSelection();
				if (e.getClickCount() == 1) {
					int index = colonistPanel.getTable().getSelectedRow();
					if (index >= 0 && index < model.getArrColonists().size())
						hud.setDisplayableObject(new DisplayableColonist(model.getArrColonists().get(index)));
				}
			}

		});
	}

	private void setupHud() {
		hud.setVisible(true);
		hud.setLocation((int) (screen_width * .333), (int) (screen_height * .666));
		hud.setSize((int) (screen_width * .333), (int) (screen_height * .333));
		hud.setBackground(Color.DARK_GRAY);

	}

	private void setupSelectorPanel() {
		selector.setVisible(true);
		selector.setLocation((int) (screen_width * .666), (int) (screen_height * .666));
		selector.setSize((int) (screen_width * .333), (int) (screen_height * .333));
		selector.setBackground(Color.CYAN);
	}

	private void setupBuildingPanel() {
		buildings.setVisible(true);
		buildings.setLocation(0, 0);
		buildings.setSize((int) (screen_width * .333), (int) (screen_height * .13));
		buildings.getBuildingList().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				colonistPanel.getTable().clearSelection();
				if (e.getClickCount() == 1) {
					int index = buildings.getBuildingList().locationToIndex(e.getPoint());
					Building b = buildings.getArrBuildings().get(index);
					if (b != null)
						hud.setDisplayableObject(new DisplayableBuilding(b));
				}
			}
		});
	}

	private void setupItemPanel() {
		items.setVisible(true);
		items.setLocation(0, (int) (screenSize.height * .17));
		items.setSize((int) (screen_width * .333), (int) (screen_height * .13));
		items.getItemList().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
					int index = items.getItemList().locationToIndex(e.getPoint());
					Item i = model.getArrItems().get(index);
					if (i != null)
						hud.setDisplayableObject(new DisplayableItem(i));
				}
				else if (e.getClickCount() == 2) {
					AssignItemToColonistDialog a = new AssignItemToColonistDialog(e);
				}
			}
		});
	}
	
	private void sendModelToPanels() {
		map.updateBoard(model);
		colonistPanel.updateColonistList(model.getArrColonists());
		buildings.updateBuildingList(model.getArrBuildings());
		items.updateItemList(model.getArrItems());
	}

	private void updateView() {
		colonistPanel.update(model.getArrColonists());
		// updateHud();
	}

	private void updateHud() {
		int rowSelected = colonistPanel.getTable().getSelectedRow();
		if (rowSelected < 0) {
			// Do nothing
		} else {
			Colonist refColonist = null;
			for (Colonist thisColonist : model.getArrColonists()) {
				if (thisColonist.getName().equals(colonistPanel.getData()[rowSelected][0]))
					refColonist = thisColonist;
			}
			hud.colonistSelected(refColonist);
		}
	}

	private class OurTimerListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (timer.isRunning()) {
				model.update();
				// System.out.println("called model.update");
				updateView();
			}
		}
	}

	private class MyWindowListener extends WindowAdapter {

		@Override
		public void windowClosing(WindowEvent e) {
			timer.stop();
			int decision = JOptionPane.showConfirmDialog(AresFrame.this, "Save game?");
			if (decision == JOptionPane.YES_OPTION) {
				ObjectOutputStream objStream = null;
				try {
					FileOutputStream stream = new FileOutputStream("SavedModelState");
					objStream = new ObjectOutputStream(stream);
					objStream.writeObject(model);
				} catch (IOException e1) {
					e1.printStackTrace();
				} finally {
					try {
						if (objStream != null) {
							objStream.close();
						}
					} catch (IOException e1) {
						System.err.println("File did not close.");
						e1.printStackTrace();
					}
				}
			}
		}
	}

	private class PlayPauseActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (timer.isRunning()) {
				timer.stop();
			} else
				timer.start();
		}
	}

	private class AssignTaskListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int rowSelected = colonistPanel.getTable().getSelectedRow();
			if (rowSelected < 0) {
				JOptionPane.showMessageDialog(null, "Select a colonist first!");
			} else {
				TaskDialog task = new TaskDialog();
			}
		}
	}

	private class BuildConstructListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int amount = model.getIronTotal();
			ArrayList<String> types = new ArrayList<String>();
			if (amount >= 10) {
				types.add("Storage");
			}
			if (amount >= 5) {
				types.add("Mess Hall");
				types.add("Dormitory");
				BuilderDialog builder = new BuilderDialog(types);
			} else {
				JOptionPane.showMessageDialog(null, "Gather more iron first!");
				colonistPanel.getTable().clearSelection();
				buildings.getBuildingList().clearSelection();
				items.getItemList().clearSelection();
				return;
			}
		}
	}

	private class RecruitmentListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (model.getIronTotal() >= 20) {
				RecruitDialog rec = new RecruitDialog();
				
			} else {
				JOptionPane.showMessageDialog(null, "Gather more iron first!");
				return;
			}
			//JOptionPane.showMessageDialog(null, builder, "Choose a building to be built", JOptionPane.INFORMATION_MESSAGE);
			colonistPanel.getTable().clearSelection();
			buildings.getBuildingList().clearSelection();
			items.getItemList().clearSelection();
		}
	}

	private class MapPanelClickedActionListener extends MouseAdapter {

		@Override
		public void mousePressed(MouseEvent arg0) {
			colonistPanel.getTable().clearSelection();
			buildings.getBuildingList().clearSelection();
			items.getItemList().clearSelection();
			
			if (arg0.getClickCount() == 2) {
				map.setSelectedRowColFromPixel(arg0.getX(), arg0.getY());
			}
			hud.displayTileInformation(arg0.getX(), arg0.getY());
		}
	}

	private class TaskDialog extends JDialog {

		private JButton select;
		private JList<String> list;
		private DefaultListModel<String> tasks;

		public TaskDialog() {
			this.setVisible(true);
			this.setLocation(400, 400);
			this.setSize(300, 100);
			list = new JList<String>();
			select = new JButton("Select");
			tasks = new DefaultListModel<String>();
			tasks.addElement("Collect Ice");
			tasks.addElement("Collect Iron Ore");
			tasks.addElement("Collect Unobtanium");
			tasks.addElement("Collect food");
			list.setModel(tasks);
			select.addActionListener(new TaskListListener());
			add(list, BorderLayout.CENTER);
			add(select, BorderLayout.SOUTH);
		}

		private class TaskListListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				String type = list.getSelectedValue();

				int rowSelected = colonistPanel.getTable().getSelectedRow();
				if (rowSelected < 0) {
					System.out.println(type + "   " + rowSelected);
				}
				else {
					Colonist refColonist = null;
					for (Colonist thisColonist : model.getArrColonists()) {
						System.out.println(type + "   " + rowSelected + "   " + thisColonist.getName());
						if (thisColonist.getName().equals(colonistPanel.getData()[rowSelected][0]))
							refColonist = thisColonist;
					}

					switch (type) {
					case "Collect Ice":
						refColonist.setTask(Task.MiningIce);
						break;
					case "Collect Iron Ore":
						refColonist.setTask(Task.MiningIronOre);
						break;
					case "Collect Unobtanium":
						refColonist.setTask(Task.MiningUnobtainium);
						break;
					case "Collect food":
						refColonist.setTask(Task.MiningMossyRock);
						break;
					default:
						break;
					}
				}
				colonistPanel.getTable().clearSelection();
				buildings.getBuildingList().clearSelection();
				items.getItemList().clearSelection();
				dispose();
			}
		}
	}

	private class BuilderDialog extends JDialog {
		private JList<String> list;
		private JButton select;
		private DefaultListModel<String> buildable;

		public BuilderDialog(ArrayList<String> strings) {
			this.setVisible(true);
			this.setSize(300, 100);
			this.setLocation(400, 400);
			list = new JList<String>();
			select = new JButton("Select");
			add(list, BorderLayout.CENTER);
			add(select, BorderLayout.SOUTH);
			buildable = new DefaultListModel<String>();
			for (String item : strings) {
				buildable.addElement(item);
			}
			list.setModel(buildable);
			select.addActionListener(new BuildListListener());
		}

		private class BuildListListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				String type = list.getSelectedValue();
				Random rand = new Random();
				switch (type) {
				case "Mess Hall":
					// TODO: figure out how to get location working better
					while(true){
						if (Generator.spawnBuilding(new Mess(10 + rand.nextInt(10), 10 + rand.nextInt(10)), model)){
							break;
						}
					}
					break;
				case "Dormitory":
					while(true){
						if (Generator.spawnBuilding(new Dormitory(10 + rand.nextInt(10), 10 + rand.nextInt(10)), model)){
							break;
						}
					}
					break;
				case "Storage":
					while(true){
						if (Generator.spawnBuilding(new StorageBuilding(10 + rand.nextInt(10), 10 + rand.nextInt(10)), model)){
							break;
						}
					}
					break;
				default:
					break;
				}
				colonistPanel.getTable().clearSelection();
				buildings.getBuildingList().clearSelection();
				items.getItemList().clearSelection();
				dispose();
			}
		}
	}

	private class RecruitDialog extends JDialog {

		private JTextField name;

		public RecruitDialog() {
			this.setVisible(true);
			this.setSize(300, 100);
			this.setLocation(400, 400);
			
			JLabel nameLabel = new JLabel("Input a name for your colonist:");
			name = new JTextField();
			JButton submit = new JButton("Submit");
			submit.addActionListener(new subButtonListener());
			
			this.add(nameLabel, BorderLayout.NORTH);
			this.add(name, BorderLayout.CENTER);
			this.add(submit, BorderLayout.SOUTH);
		}

		private class subButtonListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				String input = name.getText();
				if (input.compareTo("") != 0){
					colonistPanel.addANewRow();
					model.getArrColonists().add(new Colonist(input, 5, 5));
					colonistPanel.updateColonistList(model.getArrColonists());
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "ERROR! No name inputed by user. Enter a name!");
				}
			}
		}
	}
	
	private class AssignItemToColonistDialog extends JDialog {
		public AssignItemToColonistDialog(MouseEvent e){
			Colonist refColonist = null;
			Item refItem = null;
			int indexOfC = colonistPanel.getTable().getSelectedRow();
			if (indexOfC >= 0 && indexOfC < model.getArrColonists().size()) {
				refColonist = model.getArrColonists().get(indexOfC);
			}
			int indexOfI = items.getItemList().locationToIndex(e.getPoint());
			refItem = model.getArrItems().get(indexOfI);
			
			if (refColonist != null && refItem != null) {
				refColonist.addItem(refItem);
				JOptionPane.showMessageDialog(null, "Assign Item to Colonist successfully!");
				//TODO do somethings to the model
				model.getArrItems().remove(indexOfI);
			}
			else {
				JOptionPane.showMessageDialog(null, "Please Select a Colonist first!");
			}
			colonistPanel.getTable().clearSelection();
			buildings.getBuildingList().clearSelection();
			items.getItemList().clearSelection();
		}
	}
}

package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.ScrollPane;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
	private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	private static final int screen_height = (int) Math.round(screenSize.height);
	private static final int screen_width = (int) Math.round(screenSize.width);

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
		// Tile[][] tiles = new Tile[100][100];
		Tile[][] tiles = new Tile[30][50];

//		model = new MotherBoard(colonists, Generator.generateMap(tiles));
		model = new MotherBoard(colonists, Generator.generateEasyMap(tiles));
		model.getArrColonists().add(new Colonist("Paul", 0, 0));
		model.getArrColonists().add(new Colonist("Mingcheng", 0, 0));

		model.addBuilding(new Dormitory(4, 4));
		model.addBuilding(new Mess(4, 5));
		model.addBuilding(new StorageBuilding(8, 1));
		AresFrame window = new AresFrame();
		window.setVisible(true);
	}

	public AresFrame() {
		layoutGUI();
		setupModelAndTimer();
		registerListeners();
	}

	private void layoutGUI() {
		setupAresFrame();

		view = new JPanel();
		setupView();

		map = new MapPanel(model);
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
		map.addMouseListener(new MapPanelClickedActionListener());
//		buildings.getBuildingList().addMouseListener(new BuildingRowSelectListener());
		this.addWindowListener(new MyWindowListener());

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
				if (e.getClickCount() == 2) {
					int index = colonistPanel.getTable().getSelectedRow();
					if (index >= 0 && index < model.getArrColonists().size())
						hud.setDisplayableObject(new DisplayableColonist(model.getArrColonists().get(index)));
					System.out.println("Double clicked on Item " + index);
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
				if (e.getClickCount() == 2) {
					int index = buildings.getBuildingList().locationToIndex(e.getPoint());
					Building b = buildings.getArrBuildings().get(index);
					if (b != null)
						hud.setDisplayableObject(new DisplayableBuilding(b));

					System.out.println("Double clicked on Building Panel index " + index);
				}
			}
		});
	}

	private void setupItemPanel() {
		items.setVisible(true);
		items.setLocation(0, (int) (screenSize.height * .17));
		items.setSize((int) (screen_width * .333), (int) (screen_height * .13));
	}

	private class OurTimerListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			if (timer.isRunning()) {
				System.out.println("about to call update");
				model.update();
				System.out.println("called model.update");

				// TODO Under construction(update panels)
				updateView();
			}
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

	}

	/*
	 * private class ColonistRowSelectListener implements MouseListener {
	 * 
	 * @Override public void mouseClicked(MouseEvent e) { int rowSelected =
	 * colonistPanel.getTable().getSelectedRow(); if (rowSelected < 0) { // Do
	 * nothing } else { Colonist refColonist = null; for (Colonist thisColonist
	 * : model.getArrColonists()) { if
	 * (thisColonist.getName().equals(colonistPanel.getData()[rowSelected][0]))
	 * refColonist = thisColonist; } hud.setDisplayableObject(new
	 * DisplayableColonist(refColonist)); // hud.colonistSelected(refColonist);
	 * }
	 * 
	 * }
	 * 
	 * @Override public void mousePressed(MouseEvent e) { // TODO Auto-generated
	 * method stub
	 * 
	 * }
	 * 
	 * @Override public void mouseReleased(MouseEvent e) { // TODO
	 * Auto-generated method stub
	 * 
	 * }
	 * 
	 * @Override public void mouseEntered(MouseEvent e) { // TODO Auto-generated
	 * method stub
	 * 
	 * }
	 * 
	 * @Override public void mouseExited(MouseEvent e) { // TODO Auto-generated
	 * method stub
	 * 
	 * }
	 * 
	 * }
	 */
/*
	private class BuildingRowSelectListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			int rowSelected = BuildingPanel.getBuildingList().getSelectedIndex();
			Building refBuilding = null;
			if (rowSelected < 0) {
				// Do nothing
			} else {
				for (Building thisBuilding : model.getArrBuildings()) {
					if (thisBuilding.getType().equals(buildings.getArrBuildings().get(rowSelected).getType()))
						refBuilding = thisBuilding;
				}
			}
			System.out.println(refBuilding.toString());
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
*/
	private class MyWindowListener implements WindowListener {

		@Override
		public void windowOpened(WindowEvent e) {
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
			}
			timer.start();
		}

		public int[] getTileCoordinates(int pixel_x, int pixel_y) {
			int display_height = map.getHeight();
			int display_width = map.getWidth();
			return null;
		}

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

		@Override
		public void windowClosed(WindowEvent e) {
		}

		@Override
		public void windowIconified(WindowEvent e) {
		}

		@Override
		public void windowDeiconified(WindowEvent e) {
		}

		@Override
		public void windowActivated(WindowEvent e) {
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
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

	private class MapPanelClickedActionListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent arg0) {

			hud.displayTileInformation(arg0.getX(), arg0.getY());
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}
	}
}

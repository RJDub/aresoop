package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.ScrollPane;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.Timer;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

import Helpers.ResourceAmountHelper;
import buildings.*;
import enums.*;
import items.IceDrill;
import items.JackHammer;
import items.MossKing;
import items.TheKeyToWin;
import model.*;
import view.displayables.DisplayableBuilding;
import view.displayables.DisplayableColonist;
import view.displayables.DisplayableItem;
import view.displayables.DisplayableMapTile;

public class AresFrame extends JFrame {
	
	public static final Font BOLD_FONT = new Font(Font.MONOSPACED, Font.BOLD, 16);
	public static final Font FONT = new Font(Font.MONOSPACED, Font.BOLD, 12);
	public static final Color F_COLOR = Color.YELLOW;
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

			// Tile[][] tiles = new Tile[30][50];
			model = Generator.generateStandardModel(100, 100);
			// model = Generator.generateTestMotherBoard(10, 10);

		} else {
			SplashScreen splash = new SplashScreen();
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

		map = new MapPanel3D(model, screen_width, (int) (screen_height * .666));
		setupMapPanel();
		mapPane = new JScrollPane(map);

		mapPane.setVisible(true);
		mapPane.setLocation(0, 0);
		view.add(map);

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
//		model.addObserver(map);
		model.addObserver(hud);
//		model.addObserver(monitor);
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
		this.addKeyListener(new ArrowKeyListener());

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
	}

	private void setupMapPanel() {
		map.setVisible(true);
		map.setLocation(0, 0);
		map.setPreferredSize(new Dimension(screen_width, screen_height));
	}

	private void setupColonistPanel() {
		colonistPanel.setVisible(true);
		colonistPanel.setLocation(0, (int) (screen_height * .666));
		colonistPanel.setSize((int) (screen_width * .333), (int) (screen_height * .333));
		colonistPanel.setBackground(Color.RED);
		colonistPanel.getTable().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				buildings.getBuildingTable().clearSelection();
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
		buildings.getBuildingTable().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				colonistPanel.getTable().clearSelection();
				if (e.getClickCount() == 1) {
					int index = buildings.getBuildingTable().getSelectedRow();
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
		items.getItemTable().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
					int index = items.getItemTable().getSelectedRow();
					Item i = null;
					if (index < model.getArrItems().size())
						i = model.getArrItems().get(index);
					if (i != null) 
						hud.setDisplayableObject(new DisplayableItem(i));
				} else if (e.getClickCount() == 2) {
						AssignItemToColonistDialog a = new AssignItemToColonistDialog(e);
				}
			}
		});
	}
	
	private class ArrowKeyListener extends KeyAdapter{

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_UP){
				map.moveUp();
			} else if (e.getKeyCode() == KeyEvent.VK_DOWN){
				map.moveDown();
			} else if (e.getKeyCode() == KeyEvent.VK_LEFT){
				map.moveLeft();
			} else if (e.getKeyCode() == KeyEvent.VK_RIGHT){
				map.moveRight();
			}
		}
	}

	private void updateView() {
		colonistPanel.update(model.getArrColonists());
		items.update(model.getArrItems());
		buildings.update(model.getArrBuildings());
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
				if(isGameOver()){
					OverWindow over = new OverWindow(model);
				}
				// System.out.println("called model.update");
				updateView();
			}
			
		}
		
		private class OverWindow extends JDialog {
			private JLabel title;
			private JButton exit;
			private JPanel screen;
			private MotherBoard state;

			public OverWindow(MotherBoard in) {
				super((java.awt.Frame) null, true);
				this.setModal(true);
				this.setLayout(null);
				state = in;
				title = new JLabel("GAME OVER");
				exit = new JButton("Exit Game");
				screen = new JPanel() {
					@Override
					protected void paintComponent(Graphics g) {
						super.paintComponent(g);
						try {
							g.drawImage(ImageIO.read(new File("./images/gameover.jpg")), 0, 0, 600, 400, this);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				};
				screen.setVisible(true);
				screen.setLayout(null);
				screen.setSize(600, 400);
				screen.repaint();
				screen.add(title);
				screen.add(exit);

				title.setLocation(250, 10);
				// title.setFont(Font.MONOSPACED);
				title.setSize(300, 100);
				title.setVisible(true);
				exit.setLocation(240, 200);
				exit.setSize(100, 50);

				title.setForeground(Color.WHITE);

				this.add(screen);

				exit.addActionListener(new ExitListener());

				this.setLocation(550, 350);
				this.setSize(600, 400);
				this.setVisible(true);
			}
			
			private class ExitListener implements ActionListener {

				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
					System.exit(0);
				}
			}
		}

		private boolean isGameOver() {
			int totalColonists = model.getArrColonists().size();
			for(Colonist c: model.getArrColonists()) {
				if (!c.isAlive()) {
					totalColonists--;
				}
			}
			
			return (totalColonists <= 0);
		
		}
	}
	
	

	private class MyWindowListener extends WindowAdapter {

		@Override
		public void windowClosing(WindowEvent e) {
			timer.stop();
			CloseScreen close = new CloseScreen(model);
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
				buildings.getBuildingTable().clearSelection();
				items.getItemTable().clearSelection();
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
			// JOptionPane.showMessageDialog(null, builder, "Choose a building
			// to be built", JOptionPane.INFORMATION_MESSAGE);
			colonistPanel.getTable().clearSelection();
			buildings.getBuildingTable().clearSelection();
			items.getItemTable().clearSelection();
		}
	}

	private class MapPanelClickedActionListener extends MouseAdapter {

		@Override
		public void mousePressed(MouseEvent arg0) {
			colonistPanel.getTable().clearSelection();
			buildings.getBuildingTable().clearSelection();
			items.getItemTable().clearSelection();

			if (arg0.getClickCount() == 2) {
				map.setCenteredRowColFromPixel(arg0.getX(), arg0.getY());
				
				//hud.setDisplayableObject(new DisplayableMapTile(model,map.getSelectedRow(),map.getSelectedCol()));
			} else if (arg0.getClickCount() == 1){
				map.setHighlightedRowColFromPixel(arg0.getX(),arg0.getY());
				hud.setDisplayableObject(new DisplayableMapTile(model,map.getHighlightedRow(),map.getHighlightedCol()));
			}
			
			
			
			//hud.displayTileInformation(arg0.getX(), arg0.getY());
		}
	}

	private class TaskDialog extends JDialog {

		private JButton select;
		private JList<String> list;
		private DefaultListModel<String> tasks;

		public TaskDialog() {
			this.setVisible(true);
			this.setLocation(400, 400);
			this.setSize(300, 150);
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
				} else {
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
				buildings.getBuildingTable().clearSelection();
				items.getItemTable().clearSelection();
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
					model.getArrBuildings().add(new Mess(10 + rand.nextInt(10), 10 + rand.nextInt(10)));
					//model.withdrawIronTotal(5);
					ResourceAmountHelper.withdrawAmountUsingTileType(TileType.IronOre,model,5);
					break;
				case "Dormitory":
					model.getArrBuildings().add(new Dormitory(10 + rand.nextInt(10), 10 + rand.nextInt(10)));
					ResourceAmountHelper.withdrawAmountUsingTileType(TileType.IronOre,model,5);
					break;
				case "Storage":
					model.getArrBuildings().add(new StorageBuilding(10 + rand.nextInt(10), 10 + rand.nextInt(10)));
					ResourceAmountHelper.withdrawAmountUsingTileType(TileType.IronOre,model,10);
					break;
				default:
					break;
				}

				colonistPanel.getTable().clearSelection();
				buildings.getBuildingTable().clearSelection();
				items.getItemTable().clearSelection();
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
				if (input.compareTo("") != 0) {
					colonistPanel.addANewRow();
					model.getArrColonists().add(new Colonist(input, 5, 5));
					colonistPanel.updateColonistList(model.getArrColonists());
					model.withdrawIronTotal(20);
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "ERROR! No name inputed by user. Enter a name!");
				}
			}
		}
	}

	private class AssignItemToColonistDialog extends JDialog {
		public AssignItemToColonistDialog(MouseEvent e) {
			Colonist refColonist = null;
			Item refItem = null;
			int indexOfC = colonistPanel.getTable().getSelectedRow();
			if (indexOfC >= 0 && indexOfC < model.getArrColonists().size()) {
				refColonist = model.getArrColonists().get(indexOfC);
			}
			int indexOfI = items.getItemTable().getSelectedRow();
			if (indexOfI < model.getArrItems().size())
				refItem = model.getArrItems().get(indexOfI);

			if (refColonist != null && refItem != null) {
				refColonist.addItem(refItem);
				JOptionPane.showMessageDialog(null, "Assign Item to Colonist successfully!");
				// TODO do somethings to the model
				// model.getArrItems().remove(indexOfI);
				refItem.setOwner(refColonist);
			}
			else if (refColonist == null && refItem == null) {
				CreateNewItemDialog b = new CreateNewItemDialog();
			}
			else if (refItem != null && refItem.getOwner() != null) {
				refItem.reclaim(refItem);
				refItem.setOwner(null);
				JOptionPane.showMessageDialog(null, "Reclaim Item successfully");
			}
			else {
				JOptionPane.showMessageDialog(null, "Please Select a Colonist first!");
			}
			colonistPanel.getTable().clearSelection();
			buildings.getBuildingTable().clearSelection();
			items.getItemTable().clearSelection();
		}
	}
	
	private class CreateNewItemDialog extends JDialog {
		
		private JTable table;
		private String[] columnNames;
		private String[][] data;
		
		public CreateNewItemDialog() {
			this.setVisible(true);
			this.setSize(600, 200);
			this.setLocation(400, 400);
			columnNames = new String[]{"Name", "Cost", "Function"};
			data = new String[4][3];
			data[0][0] = "JackHammer";
			data[0][1] = "50 Unobtainium 50 Iron Ore";
			data[0][2] = "Mining Iron Ore Faster";
			data[1][0] = "MossKing";
			data[1][1] = "50 Unobtainium 50 Iron Ore";
			data[1][2] = "Gathering Food Faster";
			data[2][0] = "IceDrill";
			data[2][1] = "50 Unobtainium 50 Iron Ore";
			data[2][2] = "Mining Ice Faster";
			data[3][0] = "TheKeyToWin";
			data[3][1] = "50 Unobtainium 50 Iron Ore";
			data[3][2] = "Mining Unobtainium Faster";
			table = new JTable(new ItemCreateTableModel(data,columnNames,4));
			this.add(table, BorderLayout.CENTER);
			JButton select = new JButton("Select");
			select.addActionListener(new CreateItemListener());
			this.add(select, BorderLayout.SOUTH);
		}
		
		private class CreateItemListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				int i = table.getSelectedRow();
				switch (i) {
				case 0:
					if (model.withdrawIronTotal(50) && model.withdrawUnobtaniumTotal(50))
						model.getArrItems().add(new JackHammer());
					else
						JOptionPane.showMessageDialog(null, "You Need More Resources");
					break;
				case 1:
					if (model.withdrawIronTotal(50) && model.withdrawUnobtaniumTotal(50))
						model.getArrItems().add(new MossKing());
					else
						JOptionPane.showMessageDialog(null, "You Need More Resources");
					break;
				case 2:
					if (model.withdrawIronTotal(50) && model.withdrawUnobtaniumTotal(50))
						model.getArrItems().add(new IceDrill());
					else
						JOptionPane.showMessageDialog(null, "You Need More Resources");
					break;
				case 3:
					if (model.withdrawIronTotal(50) && model.withdrawUnobtaniumTotal(50))
						model.getArrItems().add(new TheKeyToWin());
					else
						JOptionPane.showMessageDialog(null, "You Need More Resources");
					break;
				default:
					break;
				}
				table.clearSelection();
				dispose();
			}
	}
		
	}
	class ItemCreateTableModel extends AbstractTableModel {

		private String[][] data;
		private String[] columnNames;
		private int row;
		
		public ItemCreateTableModel(String[][] d, String[] c, int i) {
			data = d;
			columnNames = c;
			row = i;
		}
		
		@Override
		public int getRowCount() {
			return row;
		}

		@Override
		public int getColumnCount() {
			return 3;
		}

		@Override
		public String getColumnName(int columnIndex) {
			if (columnIndex == 0)
				return "Name";
			else if (columnIndex == 1)
				return "Cost";
			else
				return "Function";
		}

		@Override
		public Class<?> getColumnClass(int columnIndex) {
			return String.class;
		}

		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			return data[rowIndex][columnIndex];
		}

		@Override
		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
			data[rowIndex][columnIndex] = (String) aValue;
			fireTableCellUpdated(rowIndex, columnIndex);
		}

		@Override
		public void addTableModelListener(TableModelListener l) {
			// TODO Auto-generated method stub

		}

		@Override
		public void removeTableModelListener(TableModelListener l) {
			// TODO Auto-generated method stub

		}

	}


	private class SplashScreen extends JDialog {

		private JLabel title;
		private JButton loadGame;
		private JButton newGame;
		private JPanel screen;

		public SplashScreen() {
			super((java.awt.Frame) null, true);
			this.setModal(true);
			this.setLayout(null);
			title = new JLabel("PROJECT A.R.E.S.");
			loadGame = new JButton("Load Game");
			newGame = new JButton("New Game");
			screen = new JPanel() {
				@Override
				protected void paintComponent(Graphics g) {
					super.paintComponent(g);
					try {
						g.drawImage(ImageIO.read(new File("./images/splash_screen.jpg")), 0, 0, 600, 400, this);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			};
			// title.setFont(Font.);
			screen.setVisible(true);
			screen.setLayout(null);
			screen.setSize(600, 400);
			screen.repaint();
			screen.add(title);
			screen.add(loadGame);
			screen.add(newGame);

			title.setLocation(250, 10);
			// title.setFont(Font.MONOSPACED);
			title.setSize(300, 100);
			title.setVisible(true);
			loadGame.setLocation(100, 300);
			loadGame.setSize(100, 50);
			newGame.setLocation(400, 300);
			newGame.setSize(100, 50);

			title.setForeground(Color.BLACK);

			this.add(screen);

			loadGame.addActionListener(new LoadListener());
			newGame.addActionListener(new NewGListener());

			this.setLocation(550, 350);
			this.setSize(600, 400);
			this.setVisible(true);
		}

		private class LoadListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
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
				dispose();
			}
		}

		private class NewGListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				model = Generator.generateStandardModel(50, 50);
				// model = new MotherBoard(colonists,
				// Generator.generateEasyMap(tiles));
				// model.getArrColonists().add(new Colonist("Paul", 0, 0));
				// model.getArrColonists().add(new Colonist("Mingcheng", 0, 0));
				// model.addBuilding(new Dormitory(4, 4));
				// model.addBuilding(new Mess(4, 5));
				// model.addBuilding(new StorageBuilding(8, 1));

				model.addItem(new JackHammer());
				dispose();
			}
		}
	}

	private class CloseScreen extends JDialog {
		private JLabel title;
		private JButton save;
		private JButton exit;
		private JPanel screen;
		private MotherBoard state;

		public CloseScreen(MotherBoard in) {
			super((java.awt.Frame) null, true);
			this.setModal(true);
			this.setLayout(null);
			state = in;
			title = new JLabel("EXIT MENU");
			save = new JButton("Save & Exit");
			exit = new JButton("Exit Game");
			screen = new JPanel() {
				@Override
				protected void paintComponent(Graphics g) {
					super.paintComponent(g);
					try {
						g.drawImage(ImageIO.read(new File("./images/closing_screen.jpg")), 0, 0, 600, 400, this);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			};
			screen.setVisible(true);
			screen.setLayout(null);
			screen.setSize(600, 400);
			screen.repaint();
			screen.add(title);
			screen.add(exit);
			screen.add(save);

			title.setLocation(250, 10);
			// title.setFont(Font.MONOSPACED);
			title.setSize(300, 100);
			title.setVisible(true);
			exit.setLocation(100, 300);
			exit.setSize(100, 50);
			save.setLocation(400, 300);
			save.setSize(100, 50);

			title.setForeground(Color.BLACK);

			this.add(screen);

			exit.addActionListener(new ExitListener());
			save.addActionListener(new SaveListener());

			this.setLocation(550, 350);
			this.setSize(600, 400);
			this.setVisible(true);
		}

		private class SaveListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				ObjectOutputStream objStream = null;
				try {
					FileOutputStream stream = new FileOutputStream("SavedModelState");
					objStream = new ObjectOutputStream(stream);
					objStream.writeObject(state);
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
				dispose();
			}
		}

		private class ExitListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				dispose();
			}
		}

	}
}


package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import enums.BuildingType;
import enums.TileType;
import model.*;

public class MapPanel extends JPanel implements Observer{
	private final int BOARD_ROW_SIZE = 10;
	private final int BOARD_COL_SIZE = 20;
	private final int INCREMENT = 50;
	private int MAX_ROW_COUNT;
	private int MAX_COL_COUNT;
	
	private MotherBoard mobo;
	private BufferedImage sheet, background;
	private int selected_row;
	private int selected_col;
	public static Random r = new Random();
	private JPanel buttons_panel;
	JButton button_north;
	JButton button_south;
	JButton button_east ;
	JButton button_west ;
	
	public MapPanel(MotherBoard boardIn) {
		mobo = boardIn;
		MAX_ROW_COUNT = mobo.getBoardHeight();
		MAX_COL_COUNT = mobo.getBoardWidth();
		try {
			sheet = ImageIO.read(new File("images" + File.separator
					+ "SpriteSheet.png"));

		} catch (IOException e) {
			System.out.println("Could not find 'SpriteSheet.png'");
		}
		drawBoard();
		buttons_panel= new JPanel();
		buttons_panel.setLocation(BOARD_COL_SIZE*INCREMENT,0);
		buttons_panel.setSize(200, 200);
		buttons_panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		button_north = new JButton("UP");
		button_south = new JButton("DOWN");
		button_east = new JButton("RIGHT");
		button_west = new JButton("LEFT");
		
		buttons_panel.add(button_north);
		buttons_panel.add(button_south);
		buttons_panel.add(button_east);
		buttons_panel.add(button_west);
		
		button_north.addActionListener(new DirectionButtonListener());
		button_south.addActionListener(new DirectionButtonListener());
		button_east.addActionListener(new DirectionButtonListener());
		button_west.addActionListener(new DirectionButtonListener());
		
		this.setLayout(null);
		this.add(buttons_panel);
		
		selected_row =0;
		selected_col =0;
		
	}

	// draws the board
	public void drawBoard() {
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		
		int offset_col = selected_col;
		int offset_row = selected_row;
		Graphics2D g2 = (Graphics2D) g;
		for (int row = 0; row < mobo.getBoardHeight(); row++){
			for (int col = 0; col < mobo.getBoardWidth(); col++){
				if ((row < BOARD_ROW_SIZE+offset_row) && (row > offset_row - BOARD_ROW_SIZE) && (col < BOARD_COL_SIZE + offset_col) && (col > offset_col - BOARD_COL_SIZE)){
					g2.drawImage(drawTile(row,col), (col-offset_col)*INCREMENT, (row-offset_row)*INCREMENT, null);
				}
			}
		}
//		drawArrBuildings(g2);

		for(Building b: mobo.getArrBuildings()){
			BuildingType bt = b.getType();
			int row = b.getR();
			int col = b.getC();
			if ((row < BOARD_ROW_SIZE+offset_row) && (row > offset_row - BOARD_ROW_SIZE) && (col < BOARD_COL_SIZE + offset_col) && (col > offset_col - BOARD_COL_SIZE))
					g2.drawImage(drawBuilding(bt), (col-offset_col)*INCREMENT, (row-offset_row)*INCREMENT, null);
		}
		for (Colonist c: mobo.getArrColonists()){
			int row = c.getR();
			int col = c.getC();
			if ((row < BOARD_ROW_SIZE+offset_row) && (row > offset_row - BOARD_ROW_SIZE) && (col < BOARD_COL_SIZE + offset_col) && (col > offset_col - BOARD_COL_SIZE))
				g2.drawImage(drawColonist(), (col-offset_col)*INCREMENT, (row-offset_row)*INCREMENT, null);
			 
		}
//		drawArrColonists(g2);
		
		
	}
	
	public void setSelectedRowCol(int x, int y){
		
	}
	private void drawArrBuildings(Graphics2D g2){
		for(Building b: mobo.getArrBuildings()){
			BuildingType bt = b.getType();
			g2.drawImage(drawBuilding(bt), b.getC()*INCREMENT, b.getR()*INCREMENT, null);
		}
	}
	private void drawArrColonists(Graphics2D g2){
		for (int x = 0; x < mobo.getArrColonists().size(); x++){
			g2.drawImage(drawColonist(), mobo.getArrColonists().get(x).getC()*INCREMENT, mobo.getArrColonists().get(x).getR()*INCREMENT, null);

		}
	}
	
	private BufferedImage drawBuilding(BuildingType b){
		switch (b){
		case Mess:
			return sheet.getSubimage(50	, 150, 50, 50);
		case Dormitory:
			return sheet.getSubimage(0	, 150, 50, 50);
		case Storage:
			return sheet.getSubimage(100, 100, 50, 50);
		default:
			return null;
		}
	}
	
	private BufferedImage drawTile(int x, int y){

		switch (mobo.getTileAtLocation(x, y).getType()){
		case Flat:
			return sheet.getSubimage(0, 0, 50, 50);
		case Ice:
			return sheet.getSubimage(100, 0, 50, 50);
		case IronOre:
			return sheet.getSubimage(50, 50, 50, 50);
		case Volcano:
			return sheet.getSubimage(100, 50, 50, 50);
		case Crater:
			return sheet.getSubimage(0, 100, 50, 50);
		case Mountain:
			return sheet.getSubimage(50, 100, 50, 50);
		default:
			return null;
		}
	}
	
	private BufferedImage drawColonist() {
		int width = 50;
		int height = 50;
		return sheet.getSubimage(0, 50, width, height);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		mobo = (MotherBoard) arg0;
		// do calculations
		// colonists = newMB.colonists;
		repaint();
		
	}
	
	private class DirectionButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (arg0.getSource()==button_north){
				if (selected_row >0){
					selected_row--;
					drawBoard();
				}
				
			} else if (arg0.getSource() == button_south){
				if (selected_row < MAX_ROW_COUNT){
					selected_row++;
					drawBoard();
				}
				
			} else if (arg0.getSource() == button_east){
				if (selected_col < MAX_COL_COUNT){
					selected_col++;
					drawBoard();
				}
				
			} else if (arg0.getSource()== button_west){
				if (selected_col > 0){
					selected_col--;
					drawBoard();
				}
			}
			
		}
		
	}
	
		
	
}

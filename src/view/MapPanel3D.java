
package view;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import enums.*;
import model.*;

import Helpers.Constants;

public class MapPanel3D extends JPanel {
//	private final int WINDOW_ROW_COUNT = 20;
//	private final int WINDOW_COL_COUNT = 15;
//
//	private final int X_INCREMENT = 50;
//	private final int Y_INCREMENT = 50;
//
//	private final int Y_OFFSET = 25;
//
	private int MAX_ROW_COUNT;
	private int MAX_COL_COUNT;

	private MotherBoard mobo;
	private BufferedImage sheet, background;
	private Board board;
	private int centered_row;
	private int centered_col;
	private Integer highlighted_row;
	private Integer highlighted_col;
	private int top_left_window_row;
	private int top_left_window_col;

	public static Random r = new Random();

	private JPanel[][] tiles;
	private JPanel infoPanel;

	public MapPanel3D(MotherBoard boardIn, int width, int height) {
		this.setSize(width, height);
		this.setLayout(null);
		mobo = boardIn;
		board = new Board(boardIn);
		//board.setLocation(0, 30);
		board.setSize(Constants.BOARD_X_SIZE, Constants.BOARD_Y_SIZE);
//		Integer xLoc = (Integer)((width/2) - (Constants.BOARD_X_SIZE/2));
//		Integer yLoc = (Integer)(15 + (height/2) - (Constants.BOARD_Y_SIZE/2));
		board.setLocation(0, 30);
		board.setVisible(true);
		board.setFocusable(false);
		board.setBackground(Color.BLACK);
		MAX_ROW_COUNT = mobo.getBoardHeight();
		MAX_COL_COUNT = mobo.getBoardWidth();
		try {
			sheet = ImageIO.read(new File("images" + File.separator + "SpriteSheet3D.png"));

		} catch (IOException e) {
			System.out.println("Could not find 'SpriteSheet.png'");
		}
		drawBoard();
		infoPanel = new ModelStatusMonitor(mobo, width);
		mobo.addObserver((Observer) infoPanel);
		mobo.addObserver(board);
		this.add(infoPanel);
		this.add(board);
		setTopLeftRowCol(0, 0);
		highlighted_row = null;
		highlighted_col = null;
	}

	public void setInfoPanelSize(int width, int height) {
		infoPanel.setSize(width, height);
	}

	private void setTopLeftRowCol(int r, int c) {
		if (r < 0)
			r = 0;
		if (c < 0)
			c = 0;
		if (r > (MAX_ROW_COUNT - Helpers.Constants.WINDOW_ROW_COUNT))
			r = (MAX_ROW_COUNT - Helpers.Constants.WINDOW_ROW_COUNT);
		if (c > (MAX_COL_COUNT - Helpers.Constants. WINDOW_COL_COUNT))
			c = (MAX_COL_COUNT - Helpers.Constants.WINDOW_COL_COUNT);
		top_left_window_row = r;
		top_left_window_col = c;
		setCenteredRowCol(top_left_window_row + Constants.WINDOW_ROW_COUNT / 2, top_left_window_col + Constants.WINDOW_COL_COUNT / 2);
	}

	// draws the board
	public void drawBoard() {
		repaint();
	}
	
	

	private class Board extends JPanel implements Observer {

		private MotherBoard mother;

		public Board(MotherBoard in) {
			mother = in;
		}

		@Override
		public void update(Observable o, Object arg) {
			mother = (MotherBoard) o;
			repaint();
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			int offset_col = centered_col;// -WINDOW_COL_COUNT/2;
			int offset_row = centered_row;// -WINDOW_ROW_COUNT/2;
			Graphics2D g2 = (Graphics2D) g;
			
			
			for (int row = 0; row < mobo.getBoardHeight(); row++) {
				for (int col = 0; col < mobo.getBoardWidth(); col++) {
					if (isInTheWindow(row, col)) {
						g2.drawImage(drawTile(row, col), (col - offset_col + Helpers.Constants.WINDOW_COL_COUNT / 2) * Helpers.Constants.X_INCREMENT,
								(row - offset_row + Helpers.Constants.WINDOW_ROW_COUNT / 2) * Helpers.Constants.Y_OFFSET, 50, 50, null);
						// add highlight;
						if (highlighted_row != null && highlighted_col != null){
							if (row == highlighted_row && col == highlighted_col) {
								g2.drawImage(drawHighlightBox(), (col - offset_col + Helpers.Constants.WINDOW_COL_COUNT / 2) * Helpers.Constants.X_INCREMENT,
										(row - offset_row + Helpers.Constants.WINDOW_ROW_COUNT / 2) * Helpers.Constants.Y_OFFSET, 50, 50, null);
							}
						}
						for (Building b : mobo.getArrBuildings()) {
							BuildingType bt = b.getType();
							if ((b.getR() == row) && (b.getC() == col)) {
								g2.drawImage(drawBuilding(bt), (col - offset_col + Helpers.Constants.WINDOW_COL_COUNT / 2) * Helpers.Constants.X_INCREMENT,
										(row - offset_row + Helpers.Constants.WINDOW_ROW_COUNT / 2) * Helpers.Constants.Y_OFFSET, 50, 50, null);
							}
						}
						for (Colonist c : mobo.getArrColonists()) {
							if ((c.getR() == row) && (c.getC() == col)) {
								g2.drawImage(drawColonist(c), (col - offset_col + Helpers.Constants.WINDOW_COL_COUNT / 2) * Helpers.Constants.X_INCREMENT,
										(row - offset_row + Helpers.Constants.WINDOW_ROW_COUNT / 2) * Helpers.Constants.Y_OFFSET, 50, 50, null);
							}
						}
					}
				}
			}
		}

	}

	private Image drawHighlightBox() {
		return sheet.getSubimage(50, 150, Helpers.Constants.X_INCREMENT, Helpers.Constants.Y_INCREMENT);

	}

	public void setCenteredRowCol(int r, int c) {
		centered_row = r;
		centered_col = c;
		/*
		 * if ((r > WINDOW_ROW_COUNT / 2)) { if (r <= (MAX_ROW_COUNT -
		 * (WINDOW_ROW_COUNT / 2))) centered_row = r; else centered_row =
		 * MAX_ROW_COUNT - (WINDOW_ROW_COUNT / 2); } else centered_row =
		 * (WINDOW_ROW_COUNT / 2)-1;
		 * 
		 * if (c > WINDOW_COL_COUNT / 2) { if (c <= (MAX_COL_COUNT -
		 * (WINDOW_COL_COUNT / 2))) centered_col = c; else centered_col =
		 * MAX_COL_COUNT - (WINDOW_COL_COUNT / 2); } else centered_col =
		 * (WINDOW_COL_COUNT / 2)-1;
		 */
		// System.out.println("Centered Row: " + centered_row);
		// System.out.println("Centered Col: " + centered_col);
	}

	private void drawArrBuildings(Graphics2D g2) {
		for (Building b : mobo.getArrBuildings()) {
			BuildingType bt = b.getType();
			g2.drawImage(drawBuilding(bt), b.getC() * Helpers.Constants.X_INCREMENT, b.getR() * Helpers.Constants.Y_INCREMENT, null);
		}
	}

	private void drawArrColonists(Graphics2D g2) {
		for (int x = 0; x < mobo.getArrColonists().size(); x++) {
			
			g2.drawImage(drawColonist(), mobo.getArrColonists().get(x).getC() * Helpers.Constants.X_INCREMENT,
					mobo.getArrColonists().get(x).getR() * Helpers.Constants.Y_INCREMENT, null);

		}
	}

	private BufferedImage drawBuilding(BuildingType b) {
		switch (b) {
		case Mess:
			return sheet.getSubimage(100, 100, Helpers.Constants.X_INCREMENT, Helpers.Constants.Y_INCREMENT);
		case Dormitory:
			return sheet.getSubimage(150, 100, Helpers.Constants.X_INCREMENT, Helpers.Constants.Y_INCREMENT);
		case Storage:
			return sheet.getSubimage(150, 150, Helpers.Constants.X_INCREMENT, Helpers.Constants.Y_INCREMENT);
		case LandingPad:
			return sheet.getSubimage(0, 250, Helpers.Constants.X_INCREMENT, Helpers.Constants.Y_INCREMENT);
		default:
			return null;
		}
	}

	private BufferedImage drawTile(int x, int y) {

		switch (mobo.getTileAtLocation(x, y).getType()) {
		case Flat:
			return sheet.getSubimage(0, 0, Helpers.Constants.X_INCREMENT, Helpers.Constants.Y_INCREMENT);
		case Ice:
			return sheet.getSubimage(0, 50, Helpers.Constants.X_INCREMENT, Helpers.Constants.Y_INCREMENT);
		case IronOre:
			return sheet.getSubimage(50, 50, Helpers.Constants.X_INCREMENT, Helpers.Constants.Y_INCREMENT);
		case Volcano:
			return sheet.getSubimage(50, 250, Helpers.Constants.X_INCREMENT, Helpers.Constants.Y_INCREMENT);
		case Crater:
//			return sheet.getSubimage(100, 250, Helpers.Constants.X_INCREMENT, Helpers.Constants.Y_INCREMENT);
			return drawCrater(x,y);			
//			return sheet.getSubimage(100, 250, Helpers.Constants.X_INCREMENT, Helpers.Constants.Y_INCREMENT);
		case Mountain:
			return sheet.getSubimage(150, 0, Helpers.Constants.X_INCREMENT, Helpers.Constants.Y_INCREMENT);
		case Unobtainium:
			return sheet.getSubimage(50, 0, Helpers.Constants.X_INCREMENT, Helpers.Constants.Y_INCREMENT);
		case MossyRock:
			return sheet.getSubimage(100, 0, Helpers.Constants.X_INCREMENT, Helpers.Constants.Y_INCREMENT);
		default:
			return null;
		}
	}

	private BufferedImage drawColonist() {
		int width = 50;
		int height = 50;
		return sheet.getSubimage(0, 100, Helpers.Constants.X_INCREMENT, Helpers.Constants.Y_INCREMENT);
	}

	private BufferedImage drawCrater(int row, int col){
		//return sheet.getSubimage(100, 250, Helpers.Constants.X_INCREMENT, Helpers.Constants.Y_INCREMENT);
		boolean right=false, left=false, up=false, down = false;
//		//check right
		if ((col+1) < mobo.getBoardWidth())
			right = (mobo.getTileAtLocation(row, col+1).getType() == TileType.Crater);
		
		if ((col-1) >= 0)
			left = (mobo.getTileAtLocation(row, col-1).getType() == TileType.Crater);
		
		if((row+1) < mobo.getBoardHeight())
			down = (mobo.getTileAtLocation(row+1, col).getType() == TileType.Crater);
		if((row-1) >= 0)
			up = (mobo.getTileAtLocation(row-1, col).getType() == TileType.Crater);
		if (right && left && up && down)
			return sheet.getSubimage(250, 50, Helpers.Constants.X_INCREMENT, Helpers.Constants.Y_INCREMENT);
		else if (right && left && up && ! down)
			return sheet.getSubimage(250, 100, Helpers.Constants.X_INCREMENT, Helpers.Constants.Y_INCREMENT);
		else if (right&&left&&!up&&!down)
			return sheet.getSubimage(250, 150, Helpers.Constants.X_INCREMENT, Helpers.Constants.Y_INCREMENT);
		else if (right&&!left&&!up&&!down)
			return sheet.getSubimage(200, 150, Helpers.Constants.X_INCREMENT, Helpers.Constants.Y_INCREMENT);
//		else if (!right&&!left&&!up&&!down)
//			return sheet.getSubimage(350, 0, Helpers.Constants.X_INCREMENT, Helpers.Constants.Y_INCREMENT);	
		else if (!right&&!left&&!up&&down)
			return sheet.getSubimage(200, 200, Helpers.Constants.X_INCREMENT, Helpers.Constants.Y_INCREMENT);
		
		else if (right&&!left&&!up&&down)
			return sheet.getSubimage(200, 0, Helpers.Constants.X_INCREMENT, Helpers.Constants.Y_INCREMENT);
		else if (right&&left&&!up&&down)
			return sheet.getSubimage(250, 0,Helpers.Constants.X_INCREMENT, Helpers.Constants.Y_INCREMENT);
		else if (right&&!left&&up&&!down)
			return sheet.getSubimage(200,100,Helpers.Constants.X_INCREMENT, Helpers.Constants.Y_INCREMENT);
		else if (!right&&left&&up&&down)
			return sheet.getSubimage(300,50,Helpers.Constants.X_INCREMENT, Helpers.Constants.Y_INCREMENT);
		else if (!right&&left&&!up&&down)
			return sheet.getSubimage(300,0,Helpers.Constants.X_INCREMENT, Helpers.Constants.Y_INCREMENT);
		else if (!right&&left&&!up&&!down)
			return sheet.getSubimage(300,150,Helpers.Constants.X_INCREMENT, Helpers.Constants.Y_INCREMENT);
		else if (!right&&!left&&up&&down)
			return sheet.getSubimage(250,200,Helpers.Constants.X_INCREMENT, Helpers.Constants.Y_INCREMENT);
		else if (!right&&!left&&up&&!down)
			return sheet.getSubimage(300,200,Helpers.Constants.X_INCREMENT, Helpers.Constants.Y_INCREMENT);
		else if (!right&&left&&up&&!down)
			return sheet.getSubimage(300,100,Helpers.Constants.X_INCREMENT, Helpers.Constants.Y_INCREMENT);
		else if (right&&!left&&up&&down)
			return sheet.getSubimage(200, 50,Helpers.Constants.X_INCREMENT, Helpers.Constants.Y_INCREMENT);
		else 
			return sheet.getSubimage(100, 250, Helpers.Constants.X_INCREMENT, Helpers.Constants.Y_INCREMENT);
	}
	
	private Image drawColonist(Colonist c) {
		int width = 50;
		int height = 50;
		if (c.isAlive())
			if (c.getTask() == Task.MiningIronOre)
				return sheet.getSubimage(0, 200, Helpers.Constants.X_INCREMENT, Helpers.Constants.Y_INCREMENT);
			else if (c . getTask() == Task.MiningIce)
				return sheet.getSubimage(50, 200, Helpers.Constants.X_INCREMENT, Helpers.Constants.Y_INCREMENT);
			else if (c . getTask() == Task.MiningMossyRock)
				return sheet.getSubimage(100, 200, Helpers.Constants.X_INCREMENT, Helpers.Constants.Y_INCREMENT);
			else if (c . getTask() == Task.MiningUnobtainium)
				return sheet.getSubimage(150, 200, Helpers.Constants.X_INCREMENT, Helpers.Constants.Y_INCREMENT);
			else
				return sheet.getSubimage(0, 100, Helpers.Constants.X_INCREMENT, Helpers.Constants.Y_INCREMENT);
		else
			return sheet.getSubimage(0, 150, Helpers.Constants.X_INCREMENT, Helpers.Constants.Y_INCREMENT);
	}
	public void moveUp() {
		int top_left_row = top_left_window_row;
		int top_left_col = top_left_window_col;
		setTopLeftRowCol(--top_left_row,top_left_col);
		drawBoard();
	}
	public void moveDown() {
		int top_left_row = top_left_window_row;
		int top_left_col = top_left_window_col;
		setTopLeftRowCol(++top_left_row,top_left_col);

		drawBoard();
	}

	public void moveLeft() {
		int top_left_row = top_left_window_row;
		int top_left_col = top_left_window_col;
		setTopLeftRowCol(top_left_row,--top_left_col);
		drawBoard();
	}

	public void moveRight() {
		int top_left_row = top_left_window_row;
		int top_left_col = top_left_window_col;
		setTopLeftRowCol(top_left_row,++top_left_col);
		drawBoard();
	}

	public void centerMap(int x, int y) {
		int clicked_row = (int) (x / Helpers.Constants.X_INCREMENT) - 1;
		int clicked_col = (int) (y / Helpers.Constants.Y_OFFSET) - 1;
	}

	public void setCenteredRowColFromPixel(int x, int y) {
		y -= 75;
		x -= 50;
		int col = (int) (x / Helpers.Constants.X_INCREMENT) - 1;
		int row = (int) (y / Helpers.Constants.Y_OFFSET) - 1;
		int delta_row = top_left_window_row + row - Helpers.Constants.WINDOW_ROW_COUNT / 2;
		int delta_col = top_left_window_col + col - Helpers.Constants.WINDOW_COL_COUNT / 2;
		setTopLeftRowCol(delta_row, delta_col);

	}

	private boolean isInTheWindow(int row, int col) {
		int row_offset = centered_row / 2;// - WINDOW_ROW_COUNT/2;
		int col_offset = centered_col / 2;// - WINDOW_COL_COUNT/2;

		boolean inRow = (row < centered_row + Helpers.Constants.WINDOW_ROW_COUNT / 2) && (row > centered_row - Helpers.Constants.WINDOW_ROW_COUNT / 2);
		boolean inCol = (col < centered_col + Helpers.Constants.WINDOW_COL_COUNT / 2) && (col > centered_col - Helpers.Constants.WINDOW_COL_COUNT / 2);
		return inRow && inCol;
	}

	public int getCenteredRow() {
		return centered_row;
	}

	public int getCenteredCol() {
		return centered_col;
	}

	public void setHighlightedRow(int r) {
		System.out.println("Highlilghted row: " + r);
		highlighted_row = r;
	}

	public void setHighlightedCol(int c) {
		highlighted_col = c;
	}

	public Integer getHighlightedRow() {
		return highlighted_row;
	}

	public Integer getHighlightedCol() {
		return highlighted_col;
	}

	public void setHighlightedRowColFromPixel(int x, int y) {
		int window_x_offset = 0;
		int window_y_offset = 0;
		y -= 50;
		x -= 0;
		int col = (int) (x / Helpers.Constants.X_INCREMENT);
		int row = (int) (y / Helpers.Constants.Y_OFFSET);
		int delta_row = row + centered_row - Helpers.Constants.WINDOW_ROW_COUNT / 2;
		int delta_col = col + centered_col - Helpers.Constants.WINDOW_COL_COUNT / 2;
		setHighlightedRow(delta_row);
		setHighlightedCol(delta_col);
	}

}

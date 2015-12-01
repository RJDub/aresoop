
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

public class MapPanel3D extends JPanel implements Observer{
	private final int WINDOW_ROW_COUNT = 20;
	private final int WINDOW_COL_COUNT = 20;
	
	private final int X_INCREMENT = 50;
	private final int Y_INCREMENT = 50;
	
	private final int Y_OFFSET = 25;
	
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
	
	public MapPanel3D(MotherBoard boardIn) {
		
		mobo = boardIn;
		MAX_ROW_COUNT = mobo.getBoardHeight();
		MAX_COL_COUNT = mobo.getBoardWidth();
		try {
			sheet = ImageIO.read(new File("images" + File.separator
					+ "SpriteSheet3D.png"));

		} catch (IOException e) {
			System.out.println("Could not find 'SpriteSheet.png'");
		}
		drawBoard();
		buttons_panel= new JPanel();
		buttons_panel.setLocation(WINDOW_COL_COUNT*X_INCREMENT,0);
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
		
		setSelectedRowCol(0,0);
		
		
	}
	


	// draws the board
	public void drawBoard() {
		repaint();
	}
	
	public void updateBoard(MotherBoard in){
		mobo = in;
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		
		int offset_col = selected_col;//-WINDOW_COL_COUNT/2;
		int offset_row = selected_row;//-WINDOW_ROW_COUNT/2;
		Graphics2D g2 = (Graphics2D) g;
		for (int row = 0; row < mobo.getBoardHeight(); row++){
			for (int col = 0; col < mobo.getBoardWidth(); col++){
				if (isInTheWindow(row,col)){
					g2.drawImage(drawTile(row,col), (col-offset_col+WINDOW_COL_COUNT/2)*X_INCREMENT, (row-offset_row+WINDOW_ROW_COUNT/2)*Y_OFFSET, null);
					for(Building b: mobo.getArrBuildings()){
						BuildingType bt = b.getType();
						if((b.getR()==row) && (b.getC()==col)){
								g2.drawImage(drawBuilding(bt), (col-offset_col+WINDOW_COL_COUNT/2)*X_INCREMENT, (row-offset_row+WINDOW_ROW_COUNT/2)*Y_OFFSET, null);
						}
					}
					for (Colonist c: mobo.getArrColonists()){
						if((c.getR()==row) && (c.getC()==col)){
								g2.drawImage(drawColonist(), (col-offset_col+WINDOW_COL_COUNT/2)*X_INCREMENT, (row-offset_row+WINDOW_ROW_COUNT/2)*Y_OFFSET, null);
						}
						 
					}
					
				}
			}
		}
//		drawArrBuildings(g2);

//		for(Building b: mobo.getArrBuildings()){
//			BuildingType bt = b.getType();
//			int row = b.getR();
//			int col = b.getC();
//			if ((row < WINDOW_ROW_COUNT+offset_row) && (row > offset_row - WINDOW_ROW_COUNT) && (col < WINDOW_COL_COUNT + offset_col) && (col > offset_col - WINDOW_COL_COUNT))
//					g2.drawImage(drawBuilding(bt), (col-offset_col)*X_INCREMENT, (row-offset_row)*Y_OFFSET, null);
//		}
//		for (Colonist c: mobo.getArrColonists()){
//			int row = c.getR();
//			int col = c.getC();
//			if ((row < WINDOW_ROW_COUNT+offset_row) && (row > offset_row - WINDOW_ROW_COUNT) && (col < WINDOW_COL_COUNT + offset_col) && (col > offset_col - WINDOW_COL_COUNT))
//				g2.drawImage(drawColonist(), (col-offset_col)*X_INCREMENT, (row-offset_row)*Y_OFFSET, null);
//			 
//		}
//		drawArrColonists(g2);
		
		
	}
//	public void setSelectedRowCol(int r, int c){
//		selected_row = r ;
//		selected_col = c ;
//	}
	
	public void setSelectedRowCol(int r, int c){
		if ((r > WINDOW_ROW_COUNT/2)){
			if (r < (MAX_ROW_COUNT-(WINDOW_ROW_COUNT/2)))
				selected_row = r;
			else
				selected_row = MAX_ROW_COUNT-(WINDOW_ROW_COUNT/2);
		} else 
			selected_row = WINDOW_ROW_COUNT/2;
		
		if (c > WINDOW_COL_COUNT/2){
				if( c < (MAX_COL_COUNT-(WINDOW_COL_COUNT/2)))
					selected_col = c;
				else
					selected_col = MAX_COL_COUNT-(WINDOW_COL_COUNT/2);
		} else
			selected_col = WINDOW_COL_COUNT/2;
			System.out.println("Selected Row: "+ selected_row);
			System.out.println("Selected Col: "+ selected_col);
	}
	private void drawArrBuildings(Graphics2D g2){
		for(Building b: mobo.getArrBuildings()){
			BuildingType bt = b.getType();
			g2.drawImage(drawBuilding(bt), b.getC()*X_INCREMENT, b.getR()*Y_INCREMENT, null);
		}
	}
	private void drawArrColonists(Graphics2D g2){
		for (int x = 0; x < mobo.getArrColonists().size(); x++){
			g2.drawImage(drawColonist(), mobo.getArrColonists().get(x).getC()*X_INCREMENT, mobo.getArrColonists().get(x).getR()*Y_INCREMENT, null);

		}
	}
	
	private BufferedImage drawBuilding(BuildingType b){
		switch (b){
		case Mess:
			return sheet.getSubimage(100, 100, X_INCREMENT, Y_INCREMENT);
		case Dormitory:
			return sheet.getSubimage(150, 100, X_INCREMENT, Y_INCREMENT);
		case Storage:
			return sheet.getSubimage(50, 100, X_INCREMENT, Y_INCREMENT);
		default:
			return null;
		}
	}
	
	private BufferedImage drawTile(int x, int y){

		switch (mobo.getTileAtLocation(x, y).getType()){
		case Flat:
			return sheet.getSubimage(0, 0, X_INCREMENT, Y_INCREMENT);
		case Ice:
			return sheet.getSubimage(0, 50,  X_INCREMENT, Y_INCREMENT);
		case IronOre:
			return sheet.getSubimage(50, 50,  X_INCREMENT, Y_INCREMENT);
		case Volcano:
			return sheet.getSubimage(150, 50,  X_INCREMENT, Y_INCREMENT);
		case Crater:
			return sheet.getSubimage(100, 50,  X_INCREMENT, Y_INCREMENT);
		case Mountain:
			return sheet.getSubimage(150, 0,  X_INCREMENT, Y_INCREMENT);
		default:
			return null;
		}
	}
	
	private BufferedImage drawColonist() {
		int width = 50;
		int height = 50;
		return sheet.getSubimage(0, 100, X_INCREMENT, Y_INCREMENT);
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

	public void setSelectedRowColFromPixel(int x, int y) {
		int window_x_offset = 0;
		int window_y_offset = 0;
		y-=25;
		System.out.println("Clicked: "+x+", " + y);
		int col = (int)(x)/X_INCREMENT-1;
		int row = (int) (y/Y_OFFSET)-1;
		System.out.println("Clicked row"+row+", col:" + col);
		System.out.println("selected_row: "+selected_row+", selected_col: "+selected_col);
		int delta_row = row-selected_row;
		int delta_col = col-selected_col;
		setSelectedRowCol(row+delta_row,col+delta_col);
		
				//setSelectedRowCol(row,col);
		
	}
	
	private boolean isInTheWindow(int row, int col){
		int row_offset = selected_row/2;// - WINDOW_ROW_COUNT/2;
		int col_offset = selected_col/2;// - WINDOW_COL_COUNT/2;
		
		boolean inRow = (row < selected_row + WINDOW_ROW_COUNT/2) && (row > selected_row - WINDOW_ROW_COUNT/2);
		boolean inCol = (col < selected_col + WINDOW_COL_COUNT/2) && (col > selected_col - WINDOW_COL_COUNT/2);
		return inRow && inCol;
	}
	
		
	
}

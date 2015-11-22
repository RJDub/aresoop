
package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import enums.BuildingType;
import enums.TileType;
import model.*;

public class MapPanel extends JPanel implements Observer{
	private final int BOARD_X_SIZE = 10;
	private final int BOARD_Y_SIZE = 10;
	private final int INCREMENT = 50;
	private MotherBoard mobo;
	private BufferedImage sheet, background;
	public static Random r = new Random();
	
	public MapPanel(MotherBoard boardIn) {
		mobo = boardIn;
		try {
			sheet = ImageIO.read(new File("images" + File.separator
					+ "SpriteSheet.png"));

		} catch (IOException e) {
			System.out.println("Could not find 'SpriteSheet.png'");
		}
		drawBoard();
	}

	// draws the board
	public void drawBoard() {
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		for (int y = 0; y < mobo.getBoardHeight(); y++){
			for (int x = 0; x < mobo.getBoardWidth(); x++){
				g2.drawImage(drawTile(x,y), x*INCREMENT, y*INCREMENT, null);
			}
		}
		drawArrBuildings(g2);
		drawArrColonists(g2);
		
		
		
		
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
	
}

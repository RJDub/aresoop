
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
		for (int x = 0; x < mobo.getArrColonists().size(); x++){
			g2.drawImage(drawColonist(), mobo.getArrColonists().get(x).getX()*INCREMENT, mobo.getArrColonists().get(x).getY()*INCREMENT, null);
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


package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import tiles.*;
import colonists.*;
import enums.Terrain;
import model.*;
import buildings.*;

public class Map extends JPanel implements Observer{
	private final int BOARD_X_SIZE = 10;
	private final int BOARD_Y_SIZE = 10;
	private final int INCREMENT = 50;

	private MotherBoard
	private ArrayList<Colonist> colonists;
	

	private BufferedImage sheet, background;
	public static Random r = new Random();
	
	public Map() {
		try {
			sheet = ImageIO.read(new File("images" + File.separator
					+ "SpriteSheet.gif"));

		} catch (IOException e) {
			System.out.println("Could not find 'SpriteSheet.gif'");
		}
		this.setVisible(true);
		this.setSize(1000, 1000);
		this.setBackground(Color.LIGHT_GRAY);

		BoardModel = new Tile[10][10];
		colonists = new ArrayList<Colonist>();
		buildings = new ArrayList<Building>();
		
		// temporary colonist construction
		colonists = new ArrayList<Colonist>();
		Colonist frank = new Farmer("Frank");
		Colonist susie = new Scientist("Susie");
		frank.setXcoord(2);
		frank.setYcoord(3);
		susie.setXcoord(2);
		susie.setYcoord(5);
		
		colonists.add(frank);
		colonists.add(susie);
		
		//temporary board construction
		for (int x = 0; x < BOARD_X_SIZE; x ++){
			for (int y = 0; y<BOARD_Y_SIZE; y++){
				BoardModel[x][y] = new GroundTile();
			}
		}
		BoardModel[5][4] = new IceSheetTile(new Point(3,4));
		//repaint();
	}

	// draws the board
	public void drawBoard() {
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		// Draw background image 100 times
		for (int x = 0; x < BoardModel[0].length; x++) {
			for (int y = 0; y < BoardModel.length; y ++) {
				Image image = getBufferedImage(BoardModel[x][y].terrainType);
				g2.drawImage(image, x*INCREMENT, y*INCREMENT, null);
			}
		}
		drawColonits(g2);
	}
	private void drawColonits(Graphics2D g2) {
		for (Colonist c: colonists){
			Image image = getBufferedImageColonist();
			g2.drawImage(image, INCREMENT*(int)c.getXcoord(),INCREMENT*(int)c.getYcoord(), null);
		}
		
	}

	private BufferedImage getBufferedImageColonist() {
		int width = 50;
		int height = 50;
		return sheet.getSubimage(0, 50, width, height);
	}

	private BufferedImage getBufferedImage(Terrain terrainType) {
		int width = 50;
		int height = 50;
		int xLocationOnSheet=0;
		int yLocationOnSheet=0;
		switch (terrainType){
		case Flat:
			xLocationOnSheet = 0;
			yLocationOnSheet= 0;
			break;
		case IceSheet:
			xLocationOnSheet =100;
			yLocationOnSheet= 0;
		}
		return sheet.getSubimage(xLocationOnSheet, yLocationOnSheet, width, height);
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		MotherBoard newMB = (MotherBoard) arg1;
		// do calculations
		// colonists = newMB.colonists;
		for(Colonist c : colonists){
			c.setXcoord(c.getXcoord()+1);
//			c.setYcoord(c.getYcoord()+1);
		}
		repaint();
		
	}
	
}

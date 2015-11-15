
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
import model.*;

public class MapPanel extends JPanel implements Observer{
	private final int BOARD_X_SIZE = 10;
	private final int BOARD_Y_SIZE = 10;
	private final int INCREMENT = 50;
	private MotherBoard mobo;
	private BufferedImage sheet, background;
	public static Random r = new Random();
	
	public MapPanel() {
		//mobo = boardIn;
		try {
			sheet = ImageIO.read(new File("images" + File.separator
					+ "SpriteSheet.gif"));

		} catch (IOException e) {
			System.out.println("Could not find 'SpriteSheet.gif'");
		}
	}

	// draws the board
	public void drawBoard() {
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
	}
	
	private BufferedImage getBufferedImageColonist() {
		int width = 50;
		int height = 50;
		return sheet.getSubimage(0, 50, width, height);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		mobo = (MotherBoard) arg0;
		// do calculations
		// colonists = newMB.colonists;
		for(Colonist c : mobo.getArrColonists()){
			c.setX(c.getX()+1);
//			c.setYcoord(c.getYcoord()+1);
		}
		repaint();
		
	}
	
}

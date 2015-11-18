
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

import enums.TileType;
import model.*;
import buildings.*;

public class GUIMap extends JPanel implements Observer{

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
//	private final int BOARD_X_SIZE = 10;
//	private final int BOARD_Y_SIZE = 10;
//	private final int INCREMENT = 50;
//
//	private MotherBoard model;
//	
//
//	private BufferedImage sheet, background;
//
//	
//	public GUIMap(MotherBoard m) {
//		model = m;
//		try {
//			sheet = ImageIO.read(new File("images" + File.separator
//					+ "SpriteSheet.png"));
//
//		} catch (IOException e) {
//			System.out.println("Could not find 'SpriteSheet.gif'");
//		}
//		this.setVisible(true);
//		this.setSize(1000, 1000);
//		this.setBackground(Color.LIGHT_GRAY);
//
//		//repaint();
//	}
//
//	// draws the board
//	public void drawBoard() {
//		repaint();
//	}
//	
//	public void paintComponent(Graphics g) {
//		super.paintComponent(g);
//		Graphics2D g2 = (Graphics2D) g;
//		// Draw background image 100 times
//		for (int x = 0; x < model.map.tiles[0].length; x++) {
//			for (int y = 0; y < model.map.tiles.length; y ++) {
//				Image image = getBufferedImage(model.map.tiles[x][y].type);
//				g2.drawImage(image, x*INCREMENT, y*INCREMENT, null);
//			}
//		}
//		drawColonits(g2);
//	}
//	private void drawColonists(Graphics2D g2) {
//		for (Colonist c: model.crew){
//			Image image = getBufferedImageColonist();
//			g2.drawImage(image, INCREMENT*(int)c.xLoc,INCREMENT*(int)c.yLoc, null);
//		}
//		
//	}
//
//	private BufferedImage getBufferedImageColonist() {
//		int width = 50;
//		int height = 50;
//		return sheet.getSubimage(0, 50, width, height);
//	}
//
//	private BufferedImage getBufferedImage(TileType type) {
//		int width = 50;
//		int height = 50;
//		int xLocationOnSheet=0;
//		int yLocationOnSheet=0;
//		switch (type){
//		case Flat:
//			xLocationOnSheet = 0;
//			yLocationOnSheet= 0;
//			break;
//		case Ice:
//			xLocationOnSheet =100;
//			yLocationOnSheet= 0;
//		}
//		return sheet.getSubimage(xLocationOnSheet, yLocationOnSheet, width, height);
//		
//	}
//
//	@Override
//	public void update(Observable arg0, Object arg1) {
//		model = (MotherBoard) arg1;
//		System.out.println("updated");
//		repaint();
//		
//	}
	
}

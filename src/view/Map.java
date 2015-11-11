package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import tiles.*;
import colonists.*;
import model.Building;
import model.Colonist;
import model.Tile;
import buildings.*;

public class Map extends JPanel {
	private final int BOARD_X_SIZE = 10;
	private final int BOARD_Y_SIZE = 10;

	private Tile[][] BoardModel;
	private ArrayList<Colonist> colonists;
	private ArrayList<Building> buildings;

	public Map() {
		this.setVisible(true);
		this.setSize(1000, 1000);
		this.setBackground(Color.LIGHT_GRAY);

		BoardModel = new Tile[10][10];
		colonists = new ArrayList<Colonist>();
		buildings = new ArrayList<Building>();
		
		//temporary board construction
		for (int x = 0; x < 10; x ++){
			for (int y = 0; y<10; y++){
				BoardModel[x][y] = new GroundTile();
			}
		}
	}

	// public void constructBoard(){
	// Random rando = new Random();
	// for (int r = 0; r < 100; r++){
	// for (int c = 0; c < 100; c++){
	// int chance = rando.nextInt(101);
	// if (chance < 60){
	// BoardModel[r][c] = new FarmingTile(new Point(r,c));
	// } else if (chance < 85){
	// BoardModel[r][c] = new ObstacleTile(new Point(r,c));
	// } else {
	// BoardModel[r][c] = new EnemyTile(new Point(r,c));
	// }
	// }
	// }
	//
	// }

	// draws the board
	public void drawBoard() {
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		Image tile = null;
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		// Draw background image 100 times
		for (int x = 0; x < BoardModel[0].length; x += 50) {
			for (int y = 0; y < BoardModel.length; y += 50) {
				try {
					tile = ImageIO.read(new File(BoardModel[x][y].getImagePath()));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				g2.drawImage(tile, x, y, null);
			}
		}
	}
}

package view;

import java.util.ArrayList;

import javax.swing.JFrame;

import enums.TileType;
import model.*;

public class AresFrame extends JFrame{

	private static MotherBoard model;
	
	public static void main(String[] args){
		
		ArrayList<Colonist> colonists = new ArrayList<Colonist>();
		Tile[][] tiles = new Tile[10][10];
		model = new MotherBoard(colonists, Generator.generateMap(tiles));
		
		model.getArrColonists().add(new Colonist("Paul", 0, 0));
		model.getArrColonists().add(new Colonist("Mingcheng", 0, 0));
		
		
		
		new AresFrame();
	}
	
	public AresFrame(){
		this.setVisible(true);
		
		model.start();
		model.printModel();
		
	}
}

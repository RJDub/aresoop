package view;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JPanel;
import tiles.*;
import colonists.*;
import buildings.*;

public class Map extends JPanel{

	private Tile[][] BoardModel;
	private ArrayList<Colonist> colonists;
	private ArrayList<Building> buildings;
	public Map(){
		this.setVisible(true);
		this.setSize(1000, 1000);
		this.setBackground(Color.LIGHT_GRAY);
		
		BoardModel = new Tile[100][100];
		colonists = new ArrayList<Colonist>();
		buildings = new ArrayList<Building>();
	}
}

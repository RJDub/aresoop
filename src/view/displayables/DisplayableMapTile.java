package view.displayables;

import model.MotherBoard;
import model.Tile;
import view.DisplayableObject;

public class DisplayableMapTile implements DisplayableObject {
	MotherBoard model;
	Tile tile;
	int row, col;
	public DisplayableMapTile(MotherBoard m, int r, int c){
		model = m;
		row =r;
		col = c;
		tile = model.getTileAtLocation(row, col);
	}
	@Override
	public String display() {
		String str = "";
		str += "Row: "+ row + " Col: "+ col+"\n";
		str += "Tile: "+tile.getType().toString();
		return str;
	}

}

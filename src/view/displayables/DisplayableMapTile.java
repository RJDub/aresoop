package view.displayables;

import enums.TileType;
import model.MotherBoard;
import model.Tile;

import view.displayables.*;;


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
		str += "Tile: "+tile.getType().toString()+"\n";
		if (tile.getType().isMineable()){
			str+=tile.getType().toString()+" remaining: "+tile.getResourceAmt()+"\n";
		}
		return str;
	}

}

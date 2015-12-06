package Helpers;

import java.util.ArrayList;
import java.util.Random;

import enums.TileType;
import model.MotherBoard;

import model.Tile;

public class MapGeneration {
	public static Tile[][] generateTiles(int rows, int cols, int nodeCount, int tileTypeCount){
		int[][]blueprint = generateMap(rows,cols,nodeCount,tileTypeCount);
		Tile[][] map = new Tile[rows][cols];
		TileType[] types = {TileType.Ice,
							TileType.Flat,
							TileType.Crater,
							TileType.IronOre,
							TileType.MossyRock,
							TileType.Mountain,
							TileType.Unobtainium,
							TileType.Volcano};
		
		for (int row = 0; row < rows; row++){
			for (int col = 0; col < cols; col++){
				int value = blueprint[row][col];
				map[row][col] = new Tile(types[value],row,col, types[value].getDefaultAmount());
			}
				
		}
		return map;
	}
	
	public static int[][] generateMap(int rows, int cols, int nodeCount, int tileTypeCount){
		int spawnNodes[][] = new int[nodeCount][3];
		int[][] tiles = new int[rows][cols];
		Random rand = new Random();
		for (int i = 0; i < nodeCount; i++){
			int c = rand.nextInt(cols);
			int r = rand.nextInt(rows);
			int t = rand.nextInt(tileTypeCount);
			spawnNodes[i][0] = r;
			spawnNodes[i][1] = c;
			spawnNodes[i][2] = t; 
		}
		
		for (int row = 0; row < rows; row++){
			for (int col = 0; col < cols; col++){
				//find closest node.
				//set to that node type.
				tiles[row][col]= findClosestNodeType(row,col, tiles, spawnNodes);
				
			}
		}
		return tiles;		
	}

	
	public static int findClosestNodeType(int start_row, int start_col, int[][] tiles, int[][]spawnNodes){
		//reset_visited();
		int typefound = isNode(start_row, start_col, spawnNodes);
		if (typefound>=0) return typefound;
	    ArrayList<Node> visited = new ArrayList<Node>();
		ArrayList<Node> queue = new ArrayList<Node>();
		Node current = new Node(start_row, start_col, null);
		queue.add(current);
		while (!queue.isEmpty()){
			current = queue.remove(0);
			ArrayList<Node> children = getChildren(current,tiles,visited);
			for (Node child: children){
				typefound = isNode(child.getRow(), child.getCol(), spawnNodes);
				if (typefound>=0){
					return typefound;
				} else {
					visited.add(child);
					queue.add(child);
				}
			}
		}
		//System.out.println("no destination found");
		return -1;
	}
	
	public static int isNode(int r, int c, int[][] spawnNodes ){
		for (int i = 0; i < spawnNodes.length; i ++){
			if ((r == spawnNodes[i][0])&&(c==spawnNodes[i][1]))
				return spawnNodes[i][2];
		}
		return -1;
	}
	private static ArrayList<Node> getChildren(Node current, int[][]tiles,ArrayList<Node> visited) {
		ArrayList<Node> children_to_add = new ArrayList<Node>();
		int row;
		int col;
		
		col = current.getCol() -1;
		row = current.getRow();
		if (is_in_bounds(row,col,tiles) && !has_visited(row,col,visited) ){
			children_to_add.add(new Node(row,col,current));
		}
		
		col = current.getCol() +1;
		row = current.getRow();
		if (is_in_bounds(row,col,tiles) && !has_visited(row,col,visited)){
			children_to_add.add(new Node(row,col,current));
		}
		
		col = current.getCol();
		row = current.getRow()+1;
		if (is_in_bounds(row,col,tiles) && !has_visited(row,col,visited)){
			children_to_add.add(new Node(row,col,current));
		}
		
		col = current.getCol();
		row = current.getRow()-1;
		if (is_in_bounds(row,col,tiles) && !has_visited(row,col,visited)){
			children_to_add.add(new Node(row,col,current));
		}
		return children_to_add;
	}
	private static boolean has_visited(int row, int col,ArrayList<Node> visited) {
		for (Node n: visited){
			if ((n.getCol() == col) && (n.getRow()==row))
				return true;
		}
		return false;
	}

	private static boolean is_in_bounds(int row, int col,int[][]tiles) {
		return ((row >= 0) && ( row < tiles.length) && (col >=0) && (col<tiles[0].length));
	}
}

class Node{
	private int row;
	private int col;
	private boolean visited;
	public Node parent;
	public Node(int r, int c, Node p) {
		row = r;
		col = c;
		this.parent = p;
	}
	
	public int getCol(){
		return col;
	}
	
	public int getRow(){
		return row;
	}
	
	@Override
	public String toString(){
		return "{"+row+ ","+col+"}\n";
	}
}

package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import enums.BuildingType;
import enums.TileType;

public class Map {
	public static int total_rows;
	public static int total_cols;
	public static Tile[][] tiles;
	public static ArrayList<Node> visited = new ArrayList<Node>();
	

	public static void printPath(ArrayList<int[]> path){
		for (int[] s: path){
			System.out.println("Row: "+s[0]+ " Col: "+s[1]);
		}
	}
	public static ArrayList<int[]> findPathToBuilding(int start_row, int start_col, BuildingType buildingType ,ArrayList<Building> buildings, Tile[][] t){
		//reset_visited();
	    visited = new ArrayList<Node>();
		ArrayList<Node> queue = new ArrayList<Node>();
		ArrayList<Node> visited2 = getVisited();
		tiles = t;
		Node current = new Node(start_row, start_col, null);
		//visited.add(current);
		queue.add(current);
		while (!queue.isEmpty()){
			current = queue.remove(0);
			ArrayList<Node> children = getChildren(current);
			for (Node child: children){
				for (Building b : buildings){
					if ((b.getType()== buildingType) && (b.getR()==child.getRow()) && (b.getC() == child.getCol())){
						// Path found to building type found
						ArrayList<int[]> path = get_reverse_path(child);
						return path;
					}
				}
				//looped through and building not found.
				visited.add(child);
				queue.add(child);
			}
		}
		System.out.println("no destination found");
		return null;
			
	}
	public static ArrayList<int[]> findPathToTileType(int start_row, int start_col, TileType type, Tile[][] t){
		//reset_visited();
		int [] start = {start_row,start_col};
		ArrayList<int[]>ret_array_list = new ArrayList<int[]>();
		ret_array_list.add(start);
		ArrayList<int[]> path = new ArrayList<int[]>();
	    visited = new ArrayList<Node>();
		ArrayList<Node> queue = new ArrayList<Node>();
		ArrayList<Node> visited2 = getVisited();
		tiles = t;
		
		Node current = new Node(start_row, start_col, null);
//		visited.add(current);
		if(t[start_row][start_col].getType() == type){
			return ret_array_list;
		}
		queue.add(current);
		while (!queue.isEmpty()){
			current = queue.remove(0);
			ArrayList<Node> children = getChildren(current);
			for (Node child: children){
				if (t[child.getRow()][child.getCol()].getType() == type){
					// Path found to tileType type
					path = get_reverse_path(child);
					return path;
				} else {
					visited.add(child);
					queue.add(child);
				}
			}
		}
		//System.out.println("no destination found");
		
		return ret_array_list;
			
	}
	public static int[] findNextStep(int start_row, int start_col, int end_row, int end_col, Tile[][] t){
		return null;
	}
	public static ArrayList<int[]> findFullPath(int start_row, int start_col, int end_row, int end_col, Tile[][] t){
		//reset_visited();
	    visited = new ArrayList<Node>();
		ArrayList<Node> queue = new ArrayList<Node>();
		ArrayList<Node> visited2 = getVisited();
		tiles = t;
		Node current = new Node(start_row, start_col, null);
		visited.add(current);
		queue.add(current);
		while (!queue.isEmpty()){
			current = queue.remove(0);
			ArrayList<Node> children = getChildren(current);
			for (Node child: children){
				if (child.getRow() == end_row && child.getCol()== end_col){
					// Destination found
					ArrayList<int[]> path = get_reverse_path(child);
					return path;
				} else {
					visited.add(child);
					queue.add(child);
				}
			}
		}
		System.out.println("no destination found");
		return null;
			
	}
	private static ArrayList<Node> getVisited() {
		
		return (ArrayList<Node>) visited;
	}
	private static void reset_visited() {
		visited  = new ArrayList<Node>();
		
	}
	private static ArrayList<int[]> get_reverse_path(Node child) {
		ArrayList<int[]> path = new ArrayList<int[]>();
		Node node = child;
		while (node.parent != null){
			int[] locs = {node.getRow(), node.getCol()};
			path.add(0,locs);
			node = node.parent;
		}
		return path;
	}

	private static ArrayList<Node> getChildren(Node current) {
		ArrayList<Node> children_to_add = new ArrayList<Node>();
		int row;
		int col;
		
		col = current.getCol() -1;
		row = current.getRow();
		if (is_in_bounds(row,col) && !has_visited(row,col) && tiles[row][col].getType().getWeight() < 999 ){
			children_to_add.add(new Node(row,col,current));
		}
		
		col = current.getCol() +1;
		row = current.getRow();
		if (is_in_bounds(row,col) && !has_visited(row,col)&& tiles[row][col].getType().getWeight() < 999){
			children_to_add.add(new Node(row,col,current));
		}
		
		col = current.getCol();
		row = current.getRow()+1;
		if (is_in_bounds(row,col) && !has_visited(row,col)&& tiles[row][col].getType().getWeight() < 999){
			children_to_add.add(new Node(row,col,current));
		}
		
		col = current.getCol();
		row = current.getRow()-1;
		if (is_in_bounds(row,col) && !has_visited(row,col)&& tiles[row][col].getType().getWeight() < 999){
			children_to_add.add(new Node(row,col,current));
		}
		return children_to_add;
	}

	private static boolean has_visited(int row, int col) {
		for (Node n: visited){
			if ((n.getCol() == col) && (n.getRow()==row))
				return true;
		}
		return false;
	}

	private static boolean is_in_bounds(int row, int col) {
		return ((row >= 0) && ( row < tiles[0].length) && (col >=0) && (col<tiles.length));
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

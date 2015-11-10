package model;

public abstract class Tile {
	
	
	private String imagePath;
	
	public Tile(String path){
		imagePath = path;
	}
	
	public String getImagePath(){
		return imagePath;
	}
}

package tests;

public class TestMapGeneration {
	public static void main(String[] args){
		int[][] map = Helpers.MapGeneration.generateMap(30, 20,44,10);
		for(int i = 0; i < map.length; i++){
			for (int j =0;j< map[0].length; j++){
				System.out.print(map[i][j]);
			}
			System.out.print('\n');
		}
	}
}

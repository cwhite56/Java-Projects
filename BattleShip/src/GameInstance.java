import java.util.ArrayList;

public class GameInstance {
	
	private int gridSize;
	private ArrayList<Battleship> shipList = new ArrayList<>();
	
	public GameInstance(int gridSize) {
		this.gridSize = gridSize;
		initShips(this.gridSize);
	}
	
	public void initShips(int gridSize) {
		// add checks for repeat
		Battleship s1 = new Battleship(3, gridSize);
		shipList.add(s1);
		
		Battleship s2 = new Battleship(3, gridSize);
		
		// Do while loop to check
		
		//Battleship s3 = new Battleship(3, gridSize);
	}
	
	public boolean collisionCheck(Battleship s1, Battleship s2) {
		return true;
	}
}
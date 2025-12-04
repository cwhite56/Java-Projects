import java.util.ArrayList;

public class GameInstance {
	
	private int gridSize;
	private ArrayList<Battleship> shipList = new ArrayList<>();
	
	public GameInstance(int gridSize) {
		this.gridSize = gridSize;
		initShips(this.gridSize);
	}
	
	public void initShips(int gridSize) {
		System.out.println("Ship 1: ");
		
		Battleship s1 = new Battleship(3, gridSize);
		shipList.add(s1);
		
		System.out.println("Ship 2: ");
		Battleship s2 = new Battleship(3, gridSize);
		
		while(collisionCheck(s2)) {
			System.out.println("Remake ship 2");
			s2 = new Battleship(3, gridSize);
		}
		shipList.add(s2);
		
		System.out.println("Ship 3: ");
		Battleship s3 = new Battleship(3, gridSize);
		
		while(collisionCheck(s3)) {
			s3 = new Battleship(3, gridSize);
		}
		shipList.add(s3); 
	}
	
	public boolean collisionCheck(Battleship other) {
		for (Battleship check : shipList) {
			
			for (int i = 0; i < check.getSize(); i++) {
				
				for (int j = 0; j < check.getSize(); j++) {
					
					if (check.get(i).getX() == other.get(j).getX() && check.get(i).getY() == other.get(j).getY()) {
						return true;
					}
				}
			}
		}
		return false;
	}
}
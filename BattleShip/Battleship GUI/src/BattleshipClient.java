import java.io.*;
import java.net.*;
/**
 * Class that represents the Battleship client that interacts with the server
 */
public class BattleshipClient {
	private PrintWriter out;
	private BufferedReader in;
	private Player player;
	private BattleshipGUI gui;

	public BattleshipClient() throws IOException{
		player = new Player();
		gui = new BattleshipGUI(player, this);
		setupNetworking();
	
	}

	public static void main(String[] args) throws IOException {
		BattleshipClient client = new BattleshipClient();
	}

	/**
	 * Method to initialize connection to server
	 * @throws IOException
	 */
	private void setupNetworking() throws IOException{
	Socket socket = new Socket("localhost", 5000);
	PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
	BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

	// need way to send ship list to server
	socket.close();
	}
	public boolean sendData() throws IOException{
		//out.println(player.getPlayerGuess());
		System.out.println(player.getPlayerGuess());

		//return Boolean.parseBoolean(in.readLine());
		return false;
	}
	
}
/*// write
	out.println("Client message");
	// read
	System.out.println(in.readLine());
 */
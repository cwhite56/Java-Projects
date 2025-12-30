import java.io.*;
import java.net.*;
/**
 * Class that represents the Battleship client that interacts with the server
 */
public class BattleshipClient {
	private PrintWriter out;
	private BufferedReader in;
	private Socket socket;
	private Player player;
	private BattleshipGUI gui;
	private static boolean gameFinished = false;

	public BattleshipClient() throws IOException{
		player = new Player();
		gui = new BattleshipGUI(player, this);
		
	
	}

	public static void main(String[] args) throws IOException {
		BattleshipClient client = new BattleshipClient();
		while (!gameFinished) {

		}
		client.closeSocket();
	}

	/**
	 * Method to initialize connection to server
	 * @throws IOException
	 */
	public void setupNetworking() throws IOException{
	socket = new Socket("localhost", 5000);
	out = new PrintWriter(socket.getOutputStream(), true);
	in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	OutputStream outputStream = socket.getOutputStream();
	ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream); 

	objectOutputStream.writeObject(player.getShipList());
	
	}
	public boolean sendData() throws IOException{

		out.println(player.getPlayerGuess());

		return Boolean.parseBoolean(in.readLine());
		
	}

	public void closeSocket() throws IOException {
		this.socket.close();
	}
	
}
/*// write
	out.println("Client message");
	// read
	System.out.println(in.readLine());
 */
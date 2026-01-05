import java.io.*;
import java.net.*;
/**
 * Class that represents the Battleship client that interacts with the server
 */
public class BattleshipClient {
	private PrintWriter out;
	private BufferedReader in;
	private Socket socket;
	private OutputStream outputStream;
	private Player player;
	private BattleshipGUI gui;
	private static volatile boolean gameFinished = false;

	public BattleshipClient() throws IOException{
		player = new Player();
		gui = new BattleshipGUI(player, this);
		
	}
// left to do: user error handling, threading, formatting / oop principles
	public static void main(String[] args) throws IOException {
		BattleshipClient client = new BattleshipClient();
		while (!gameFinished) {
		
		}
		client.closeStreams();
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
	public void finishGame() {
		gameFinished = true;
		out.println(BattleshipServer.STOP_STRING);
	}

	public void closeStreams() throws IOException {
		this.socket.close();
		System.out.println("Client closed successfully.");
	}
	
}

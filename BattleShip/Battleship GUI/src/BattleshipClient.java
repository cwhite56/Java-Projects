import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class BattleshipClient {
	PrintWriter out;
	BufferedReader in;
	ArrayList<Boolean> playerShipList = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		//BattleshipClient client = new BattleshipClient();
		//client.setupNetworking();
		BattleshipGUI gui = new BattleshipGUI();
	}

	private void setupNetworking() throws IOException{
	Socket socket = new Socket("localhost", 5000);
	PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
	BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

	// write
	out.println("Client message");
	// read
	System.out.println(in.readLine());

	socket.close();
	}

	public static void guess (int index) {

	}
}
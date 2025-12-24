import java.io.*;
import java.net.*;

public class BattleshipClient {
	PrintWriter out;
	BufferedReader in;
	public static void main(String[] args) throws IOException {
		BattleshipClient client = new BattleshipClient();
		client.setupNetworking();
		//GameInstance bs = new GameInstance(10);
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
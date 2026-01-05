import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class BattleshipServer {
    public static final String STOP_STRING = "##";
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;
    private ArrayList<Boolean> playerShipList;
    public static void main(String args[]) throws IOException, ClassNotFoundException {

        BattleshipServer server = new BattleshipServer();
        server.setupNetworkingServer();
        
    }
    /**
     * Method that initializes all necessary streams to communicate with the client
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void setupNetworkingServer()throws IOException, ClassNotFoundException{
        serverSocket = new ServerSocket(5000);
        System.out.println("Server is running and waiting for client connection...");

        clientSocket = serverSocket.accept();
        System.out.println("Client connected!");

        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new PrintWriter(clientSocket.getOutputStream(), true);

        ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());

        playerShipList = (ArrayList<Boolean>)objectInputStream.readObject();
        
        readMessages();
    }
    /**
     * Method that reads messages from the client
     * @throws IOException
     */
    private void readMessages() throws IOException{

        String message;

        while(!(message = in.readLine()).equals(STOP_STRING)) {
            
            if (playerShipList.get(Integer.parseInt(message))) {
                out.println("true");
            }
            else if (!playerShipList.get(Integer.parseInt(message))) {
                out.println("false");
            }
            
        }
        closeServerStreams();
    }
    /**
     * Method that closes all streams
     * @throws IOException
     */
    private void closeServerStreams() throws IOException {
        this.clientSocket.close();
        this.serverSocket.close();
        System.out.println("Server closed successfully.");
    }
}

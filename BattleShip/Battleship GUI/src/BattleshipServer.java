import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class BattleshipServer {
    public static final String STOP_STRING = "##";
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;
    private InputStream inputStream;
    private ArrayList<Boolean> playerShipList;
    public static void main(String args[]) throws IOException, ClassNotFoundException {
        // get input for arraylist
        // base output on whether incoming index is true or false
        // maybe need a while look for message
        BattleshipServer server = new BattleshipServer();
        server.setupNetworkingServer();
        
    }

    public void setupNetworkingServer()throws IOException, ClassNotFoundException {
        serverSocket = new ServerSocket(5000);
        System.out.println("Server is running and waiting for client connection...");

        clientSocket = serverSocket.accept();
        System.out.println("Client connected!");

        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new PrintWriter(clientSocket.getOutputStream(), true);

        inputStream = clientSocket.getInputStream();
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

        playerShipList = (ArrayList<Boolean>)objectInputStream.readObject();
        
        readMessages();
    }

    private void readMessages() throws IOException{

        String message = in.readLine();

        System.out.println("Client says: " + message);
        //gets index but only every other submission fix chain that sends boolean value based on index
        while((message = in.readLine()) != STOP_STRING) {

            if (message != null) {
                 System.out.println(in.readLine());
                out.println("Message received by the server.");
            }
        }


        closeServerStreams();
    }

    private void closeServerStreams() throws IOException {
        clientSocket.close();
        serverSocket.close();
        in.close();
        out.close();
        inputStream.close();
    }
}

import java.io.*;
import java.net.*;

public class BattleshipServer {
    public static final String STOP_STRING = "##";
    private ServerSocket serverSocket;
    private Socket clientSocket;
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

        while (true) {
        clientSocket = serverSocket.accept();
        BattleshipThread battleshipThread = new BattleshipThread(clientSocket, this);
        Thread thread = new Thread(battleshipThread);
        thread.start();
        }
    }

    public ServerSocket getServerSocket() {
        return this.serverSocket;
    }
    
}

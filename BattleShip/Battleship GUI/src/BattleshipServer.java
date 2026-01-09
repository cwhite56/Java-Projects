import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class BattleshipServer {
    public static final String STOP_STRING = "##";
    private static volatile int playerCount;
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private ArrayList<ArrayList<Boolean>> bothPlayerShipLists = new ArrayList<>();
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

    public int setPlayerNumber() {

        int temp = playerCount;

        if (temp == 0) {
            incrementPlayerCount();
            return temp;

        } else if (temp == 1) {
            return temp;
        
        } else {
            return -1;
        }
    }
    public void setPlayerShipList(int playerNumber, ArrayList<Boolean> playerShipList) {
        bothPlayerShipLists.add(playerNumber, playerShipList);
    }

    public synchronized ArrayList<Boolean> getPlayerShipList(int playerNumber) {
        while (playerCount < 2) {

        }
        if (playerNumber == 0) {
            return bothPlayerShipLists.get(1);

        } else if (playerNumber == 1) {
            return bothPlayerShipLists.get(0);
        }
        else {
            return null;
        }
    }


    private void incrementPlayerCount() {
        playerCount++;
    }
    
}

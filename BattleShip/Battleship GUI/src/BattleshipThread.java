import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class BattleshipThread implements Runnable{
    private BufferedReader in;
    private PrintWriter out;
    private Socket clientSocket;
    private ArrayList<Boolean> playerShipList;
    private BattleshipServer server;
    public static final String STOP_STRING = "##";

    public BattleshipThread(Socket clientSocket, BattleshipServer server) {
        this.clientSocket = clientSocket;
        this.server = server;
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);

            ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());

            playerShipList = (ArrayList<Boolean>)objectInputStream.readObject();

             readMessages();

        } catch (Exception e) {
            e.printStackTrace();
        }
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
        closeThreadStreams();
    }
    /**
     * Method that closes all streams
     * @throws IOException
     */
    private void closeThreadStreams() throws IOException {
        this.clientSocket.close();
        System.out.println("Thread closed successfully.");
    }
    
}

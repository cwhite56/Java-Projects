import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class BattleshipServer {
    public static void main(String args[]) throws IOException {

        ServerSocket serverSocket = new ServerSocket(5000);
        System.out.println("Server is running and waiting for client connection...");

        Socket clientSocket = serverSocket.accept();
        System.out.println("Client connected!");

        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

        String message = in.readLine();
        System.out.println("Client says: " + message);

        out.println("Message received by the server.");

        clientSocket.close();
        serverSocket.close();
    }
}

package server.main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(9000);
        } catch (IOException e) {
            e.printStackTrace();
        }
        boolean isStopped = false;
        while(!isStopped) {
            Socket clientSocket = null;
            try {
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

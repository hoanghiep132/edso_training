package edso.hiepnh.server.entites;

import edso.hiepnh.server.config.Config;
import edso.hiepnh.server.service.FileIO;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    private ServerSocket serverSocket;

    private int max_client;

    private static int index = 0;

    private ObjectInputStream ois;

    private ObjectOutputStream oos;

    public Server() {
        FileIO fileIO = new FileIO();
        Config config = fileIO.getConfig();
        max_client = config.getMaxClient();
        try {
            serverSocket = new ServerSocket(config.getPort());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void open(){
        while (true) {
            Socket socket = null;
            try {
                socket = serverSocket.accept();
                oos = new ObjectOutputStream(socket.getOutputStream());
                ois = new ObjectInputStream(socket.getInputStream());
                if(index < max_client){
                    Thread t = new ClientHandler(socket, ois, oos);
                    t.start();
                    index++;
                }
            }
            catch (Exception e){
                try {
                    socket.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.open();

    }
}

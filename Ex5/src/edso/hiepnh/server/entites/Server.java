package edso.hiepnh.server.entites;

import edso.hiepnh.client.enties.Client;
import edso.hiepnh.server.config.Config;
import edso.hiepnh.server.service.FileIO;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    private ServerSocket serverSocket;

    private List<ClientHandler> clientHandlers;

    private ObjectInputStream ois;

    private ObjectOutputStream oos;

    public static void main(String[] args) throws IOException {
        List<ClientHandler> clientHandlers = new ArrayList<>();
        FileIO fileIO = new FileIO();
        fileIO.readConfig();
        Config config = fileIO.getConfig();
        ServerSocket ss = new ServerSocket(config.getPort());

        while (true)
        {
            Socket s = null;
            try
            {
                s = ss.accept();
                System.out.println("A new client is connected : " + s.getInetAddress() + " / " + s.getPort());
                ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
                ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
                oos.writeObject("Hello client");
                oos.flush();
                Thread t = new ClientHandler(s, ois, oos);
                clientHandlers.add((ClientHandler) t);
                t.start();
            }
            catch (Exception e){
                s.close();
                e.printStackTrace();
            }
        }
    }
}

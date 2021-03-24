package edso.hiepnh.server.entites;

import edso.hiepnh.entities.Request;
import edso.hiepnh.entities.Response;
import edso.hiepnh.server.service.FileIO;
import edso.hiepnh.utils.ObjectUtils;

import java.io.*;
import java.net.Socket;

public class ClientHandler extends Thread{

    private Socket socketClient;

    private FileIO fileIO;

    private ObjectInputStream ois;

    private ObjectOutputStream oos;

    public ClientHandler(Socket socketClient, ObjectInputStream ois, ObjectOutputStream oos) {
        this.socketClient = socketClient;
        this.ois = ois;
        this.oos = oos;
    }

    @Override
    public void run(){
        try {
            oos.writeObject("Start Calling pair");
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true){
            Object requestObj = null;
            try {
                requestObj = ois.readObject();
                if(requestObj instanceof Request){
                    String fileName = ((Request) requestObj).getFileName();
                    if(fileIO.checkFileExist(fileName)){
                        oos.writeObject(Response.BAD_REQUEST);
                    }else {
                        oos.writeObject(Response.OK);
                    }
                    oos.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}

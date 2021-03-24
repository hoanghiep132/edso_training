package edso.hiepnh.client.enties;

import edso.hiepnh.client.config.Config;
import edso.hiepnh.client.service.FileIO;
import edso.hiepnh.entities.FileInfor;
import edso.hiepnh.entities.Request;
import edso.hiepnh.entities.Response;
import edso.hiepnh.utils.ObjectUtils;

import java.io.*;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public class Client implements Runnable{

    private FileIO fileIO;

    private int id;

    public Client(int id) {
        this.id = id;
    }

    public Client() {
        fileIO = new FileIO();
        fileIO.readConfig();
        Config config = fileIO.getConfig();
        try {
            socket = new Socket(config.getIp(),config.getPort());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Socket socket;

    private ObjectInputStream objectInputStream;

    private ObjectOutputStream objectOutputStream;

    private void sendFileRequest(String fileName){

    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        String currentFileName = null;
        try {
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true){
            try {
                String requestStr = scanner.nextLine();
                Request request = ObjectUtils.convertStringToRequest(requestStr);
                currentFileName = request.getFileName();
                objectOutputStream.writeObject(request);
                objectOutputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                String response = objectInputStream.readUTF();
                System.out.println(response);
                if(response.equals(Response.OK)){
                    FileInfor fileInfor = fileIO.readFile(currentFileName);
                    objectOutputStream.writeObject(fileInfor);
                    objectOutputStream.flush();
                }else {
                    currentFileName = null;
                    System.out.println(response);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {
        new Client().run();
    }

}

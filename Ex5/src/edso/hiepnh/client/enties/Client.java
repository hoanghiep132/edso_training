package edso.hiepnh.client.enties;

import edso.hiepnh.client.config.Config;
import edso.hiepnh.client.service.FileIO;
import edso.hiepnh.entities.FileInfor;
import edso.hiepnh.entities.Request;
import edso.hiepnh.entities.Response;
import edso.hiepnh.utils.ObjectUtils;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client implements Runnable{

    private FileIO fileIO;

    public Client() {
        fileIO = new FileIO();
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
        String currentMethod = null;
        try {
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true){
            try {
                String requestStr = scanner.nextLine();
                Request request = null;
                try {
                    request = ObjectUtils.convertStringToRequest(requestStr);
                    currentFileName = request.getFileName();
                    currentMethod = request.getMethod();
                }catch (Exception ex){
                    request = null;
                    System.err.println("Syntax Error");
                }
                if(request != null) {
                    objectOutputStream.writeObject(request);
                    objectOutputStream.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                Response response = null;
                try {
                    response = (Response) objectInputStream.readObject();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                if(response.getFileInfor() == null){
                    System.out.println(response.getMessage());
                    if(response.getMessage().equals(Response.OK)){
                        if(Request.POST_METHOD.equals(currentMethod)){
                            FileInfor fileInfor = fileIO.readFile(currentFileName);
                            objectOutputStream.writeObject(fileInfor);
                            objectOutputStream.flush();
                        }
                    }else {
                        currentFileName = null;
                        System.out.println(response.getMessage());
                    }
                }else{
                    System.out.println(response.getMessage());
                    String fileName = null;
                    if(fileIO.checkFileExist(currentFileName)){
                        String[] strings = currentFileName.split(".");
                        if(strings.length>1){
                            fileName = strings[0] + "_copy." + strings[1];
                        }else {
                            fileName = strings[0] + "_copy";
                        }
                    }else {
                        fileName = currentFileName;
                    }
                    fileIO.writeFile(response.getFileInfor().getDataBytes(),fileName);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}

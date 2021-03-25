package edso.hiepnh.server.entites;

import edso.hiepnh.entities.FileInfor;
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
        fileIO = new FileIO();
    }

    public Socket getSocketClient() {
        return socketClient;
    }

    @Override
    public void run(){
//        try {
//            oos.writeObject("Start Calling pair");
//            oos.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        String currentFileName = null;
        while (true){
            Object requestObj = null;
            try {
                requestObj = ois.readObject();
                // client gui request
                if(requestObj instanceof Request){
                    String fileName = ((Request) requestObj).getFileName();
                    // upload file
                    if(((Request) requestObj).getMethod().equals(Request.POST_METHOD)){
                        if(fileIO.checkFileExist(fileName)){
                            Response response = new Response(Response.BAD_REQUEST,null);
                            oos.writeObject(response);
                        }else {
                            currentFileName = fileName;
                            Response response = new Response(Response.OK,null);
                            oos.writeObject(response);
                        }
                        oos.flush();
                    // download file
                    } else if(((Request) requestObj).getMethod().equals(Request.GET_METHOD)){
                        if(fileIO.checkFileExist(fileName)){
                            FileInfor fileInfor = fileIO.readFile(fileName);
                            Response response = new Response(Response.OK,fileInfor);
                            oos.writeObject(response);
                            oos.flush();
                        }else {
                            oos.writeObject(Response.BAD_REQUEST);
                        }
                        oos.flush();
                    }else {
                        oos.writeObject(Response.BAD_REQUEST);
                    }
                //client gui file
                }else if(requestObj instanceof FileInfor){
                    fileIO.writeFile(((FileInfor) requestObj).getDataBytes(),currentFileName);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}

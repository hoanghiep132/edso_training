package edso.hiepnh.client.service;

import edso.hiepnh.client.config.Config;
import edso.hiepnh.entities.Request;
import edso.hiepnh.entities.Response;
import edso.hiepnh.utils.ObjectUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class FileService {

    public static Request sendRequest(String method,String fileName) {
        try (InputStream is = new FileInputStream(Config.client_dir + fileName)) {
            Request request = new Request();
            request.setMethod(method);
            request.setFileName(fileName);
            return request;
        } catch (Exception ex) {
            return null;
        }
    }

    public static String readResponse(Response response){
        if(response.getMessage().equals(Response.OK)){

        }

        return ObjectUtils.convertResponseToString(response);
    }



}

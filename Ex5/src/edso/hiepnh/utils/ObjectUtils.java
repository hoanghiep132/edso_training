package edso.hiepnh.utils;

import edso.hiepnh.entities.Request;

public class ObjectUtils {


    public static Request convertStringToRequest(String message){

        String[] strings = message.split(";");
        String method = strings[0].trim();
        if (method.equals(Request.POST_METHOD) || method.equals(Request.GET_METHOD)){
            String fileName = strings[1];
            Request request = new Request();
            request.setFileName(fileName);
            request.setMethod(method);
            return request;
        }else {
            return null;
        }

    }

}

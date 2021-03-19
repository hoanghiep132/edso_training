package edso.hiepnh.entities;

import java.io.InputStream;
import java.io.Serializable;

public class Request implements Serializable {

    public static final String GET_METHOD = "get";
    public static final String POST_METHOD = "post";

    private String method;

    private String fileName;


    public Request() {
    }

    public Request(String method, String fileName) {
        this.method = method;
        this.fileName = fileName;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}

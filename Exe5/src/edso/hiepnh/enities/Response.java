package edso.hiepnh.enities;

import java.io.Serializable;

public class Response implements Serializable {

    public static final String OK = "Ok";
    public static final String BAD_REQUEST = "Failed";

    private String message;

    private String fileName;


    public Response(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}

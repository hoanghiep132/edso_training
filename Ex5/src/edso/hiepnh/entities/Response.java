package edso.hiepnh.entities;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

public class Response implements Serializable{

    public static final String OK = "Ok";
    public static final String BAD_REQUEST = "Failed";

    private String message;

    private FileInfor fileInfor;

    public Response(String message, FileInfor fileInfor) {
        this.message = message;
        this.fileInfor = fileInfor;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public FileInfor getFileInfor() {
        return fileInfor;
    }

    public void setFileInfor(FileInfor fileInfor) {
        this.fileInfor = fileInfor;
    }
}

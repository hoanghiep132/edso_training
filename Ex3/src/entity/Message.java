package entity;

public class Message {

    private String message;

    public final static int PENDING = 0;
    public final static int SENDING = 1;
    public final static int SENT = 2;

    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

package edso.hiepnh.entity;

import java.util.ArrayList;
import java.util.List;

public class MessageQueue {

    private int length;

    private List<Message> messageList = new ArrayList<>();

    public MessageQueue() {
    }

    public MessageQueue(int length) {
        this.length = length;
    }


    private boolean isFully(){
        return messageList.size() >= length;
    }
}

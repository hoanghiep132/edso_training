package entity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MessageQueue implements MessageQueueStatement{

    private static MessageQueue messageQueue = null;

    public static int EMPTY = 0;
    public static int AVAIABLE = 1;
    public static int FULLY = 2;

    private int state = EMPTY;
    private int length;

    private Queue<Message> messageList = new LinkedList<>();

    public Queue<Message> getMessageList() {
        return messageList;
    }

    private MessageQueue(int length) {
        this.length = length;
    }

    public static MessageQueue getInstance(int length){
        if(messageQueue == null){
            messageQueue = new MessageQueue(length);
        }
        return messageQueue;
    }
    
    @Override
    public  Message sendMessageToConsumber() {
        if(state != EMPTY){
            Message msg = messageList.remove();
            if(messageList.size() == 0){
                state = EMPTY;
            }else if(state == FULLY){
                state = AVAIABLE;
            }
            return msg;
        }else{
            return null;
        }
    }

    @Override
    public int recivedMessageFromProducer(Message msg) {
        if(state != FULLY){
            messageList.add(msg);
            if(messageList.size() == length){
                state = FULLY;
            }else if(state == EMPTY){
                state = AVAIABLE;
            }
        }
        return state;
    }
}

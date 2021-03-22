package entity;


import java.util.LinkedList;
import java.util.Queue;

public class MessageQueue implements MessageQueueStatement{


    public static int EMPTY = 0;
    public static int AVAIABLE = 1;
    public static int FULLY = 2;

    private int state = EMPTY;
    private int length;

    private Queue<Message> messageList;


    public Queue<Message> getMessageList() {
        return messageList;
    }

    public boolean isFully(){
        return state == FULLY ? true : false;
    }

    public boolean isEmpty(){
        return state == EMPTY ? true : false;
    }

    public MessageQueue(int length) {
        this.length = length;
        messageList = new LinkedList<>();
    }


    public int getLength(){
        return this.getMessageList().size();
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
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
    public void recivedMessageFromProducer(Message msg) {
        if(state != FULLY){
            msg.setStatus(Message.PENDING);
            messageList.add(msg);
            if(messageList.size() == length){
                state = FULLY;
            }else if(state == EMPTY){
                state = AVAIABLE;
            }
        }
    }
}

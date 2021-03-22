package entity;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;

public class MessageQueue implements MessageQueueStatement{


    public static int EMPTY = 0;
    public static int AVAIABLE = 1;
    public static int FULLY = 2;

    private int state = EMPTY;
    private int length;

    private Producer producer;

    private Consumer consumer;

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


    @Override
    public synchronized Message sendMessageToConsumber() {
        while (isEmpty()){
            System.out.println("Consumer wait : " + new SimpleDateFormat("hh:mm:ss").format(new Date()));
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        notifyAll();
        Message msg = messageList.remove();
        if(messageList.size() == 0){
            state = EMPTY;
        }else if(state == FULLY){
            state = AVAIABLE;
        }
        return msg;
    }

    @Override
    public synchronized void recivedMessageFromProducer(Message msg) {
        while (isFully()){
            try {
                System.out.println("Producer wait : " + new SimpleDateFormat("hh:mm:ss").format(new Date()));
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        notifyAll();
        msg.setStatus(Message.PENDING);
        messageList.add(msg);
        if(messageList.size() == length){
            state = FULLY;
        }else if(state == EMPTY){
            state = AVAIABLE;
        }

    }
}

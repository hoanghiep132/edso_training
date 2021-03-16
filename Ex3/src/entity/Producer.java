package entity;

import javafx.collections.ObservableList;

import java.util.Date;

public class Producer extends Thread{

    private MessageQueue messageQueue;

    private long timeDelay;

    private ObservableList<String> producerMessages;

    public Producer() {
    }

    public Producer(MessageQueue messageQueue, long timeDelay, ObservableList<String> producerMessages) {
        this.messageQueue = messageQueue;
        this.timeDelay = timeDelay;
        this.producerMessages = producerMessages;
    }

    public void run(){
        int state = MessageQueue.EMPTY;
        while (true){
            if(state != MessageQueue.FULLY){
                Message msg = generateMsg();
                System.out.println("Producer : " + msg.getMessage());
                state = messageQueue.recivedMessageFromProducer(msg);
                producerMessages.add(msg.getMessage());
            }
            try {
                Thread.sleep(timeDelay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private Message generateMsg(){
        Message msg = new Message();
        Date date = new Date();
        String time = date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();
        String str = time + " Hello ";
        msg.setMessage(str);
        return msg;
    }

}

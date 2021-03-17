package entity;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Producer extends Thread{

    private MessageQueue messageQueue;

    private long timeDelay;

    private ObservableList<String> producerMessages;


    public Producer(MessageQueue messageQueue, long timeDelay) {
        this.messageQueue = messageQueue;
        this.timeDelay = timeDelay;
        this.producerMessages = FXCollections.observableArrayList();
    }

    public ObservableList<String> getProducerMessages() {
        return producerMessages;
    }

    public void run(){
        sendMsg();
    }

    private void sendMsg(){
        while (true){
            if(!MessageQueue.isFully()){
                Message msg = generateMsg();
                messageQueue.recivedMessageFromProducer(msg);
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
        String time = new SimpleDateFormat("hh:mm:ss").format(date);
        String str = time + " Hello ";
        msg.setMessage(str);
        return msg;
    }

}

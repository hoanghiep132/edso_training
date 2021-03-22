package entity;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

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
        int i = 1;
        while (true){
            Message msg = generateMsg(i++);
            messageQueue.recivedMessageFromProducer(msg);
            producerMessages.add(msg.getMessage());
            try {
                Thread.sleep(timeDelay * (new Random().nextInt(10)+1));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private Message generateMsg(int i){
        Message msg = new Message();
        Date date = new Date();
        String time = new SimpleDateFormat("hh:mm:ss").format(date);
        String str = time + " Hello " + i;
        msg.setMessage(str);
        msg.setStatus(Message.SENDING);
        return msg;
    }

}

package entity;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Random;

public class Consumer extends Thread {

    private MessageQueue messageQueue;


    private long timeDelay;


    private ObservableList<String> consumerMessages;

    public Consumer() {
    }

    public Consumer(MessageQueue messageQueue, long timeDelay) {
        this.messageQueue = messageQueue;
        this.timeDelay = timeDelay;
        this.consumerMessages = FXCollections.observableArrayList();
    }

    public ObservableList<String> getConsumerMessages() {
        return consumerMessages;
    }

    public void run() {
        while (true){
            Message msg = messageQueue.sendMessageToConsumber();
            if(msg != null){
                consumerMessages.add(msg.getMessage());
                msg.setStatus(Message.SENT);
            }
            try {
                Thread.sleep(timeDelay * (new Random().nextInt(10)+1));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
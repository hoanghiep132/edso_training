package entity;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Consumer extends Thread {

    private MessageQueue messageQueue;

    public static Message message;

    private long timeDelay;

    private boolean bus = false;

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
        long speed = timeDelay;
        while (true){
            if(bus){
                if(messageQueue.getLength() <= 3){
                    speed = timeDelay;
                }
            }else{
                if(messageQueue.isFully()){
                    bus = true;
                    speed /= 3;
                }
            }
            Message msg = messageQueue.sendMessageToConsumber();
            if(msg != null){
                consumerMessages.add(msg.getMessage());
                message = msg;
            }
            try {
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
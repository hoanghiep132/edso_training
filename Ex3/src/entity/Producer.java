package entity;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Producer extends Thread{

    private MessageQueue messageQueue;

    private long timeDelay;

    private ObservableList<String> producerMessages;

    public long getTimeDelay() {
        return timeDelay;
    }

    public Producer(MessageQueue messageQueue, long timeDelay) {
        this.messageQueue = messageQueue;
        this.timeDelay = timeDelay;
        this.producerMessages = FXCollections.observableArrayList();
    }

    public int getLength(){
        return messageQueue.getMessageList().size();
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
            if(!MessageQueue.isFully()){
                Message msg = generateMsg(i++);
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

    private Message generateMsg(int index){
        Message msg = new Message();
        Date date = new Date();
        String time = new SimpleDateFormat("hh:MM:ss").format(date);
        String str = time + " Hello " + index;
        msg.setMessage(str);
        return msg;
    }

}

package entity;

public class Consumer extends Thread {

    private MessageQueue messageQueue;

    public static Message message;

    private long timeDelay;

    public Consumer() {
    }

    public Consumer(MessageQueue messageQueue, long timeDelay) {
        this.messageQueue = messageQueue;
        this.timeDelay = timeDelay;
    }

    public void run() {
        while (true){
            Message msg = messageQueue.sendMessageToConsumber();
            if(msg == null){
                message = msg;
            }
            try {
                Thread.sleep(timeDelay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
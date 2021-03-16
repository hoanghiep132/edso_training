package edso.hiepnh.entity;

public interface MessageStatement {

    public void sendToMessageQueue();

    public void recivedByConsumer();
}

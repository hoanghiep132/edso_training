package entity;

public interface MessageQueueStatement {

    Message sendMessageToConsumber();

    void recivedMessageFromProducer(Message msg);
}

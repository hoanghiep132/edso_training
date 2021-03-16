package entity;

public interface MessageQueueStatement {

    Message sendMessageToConsumber();

    int recivedMessageFromProducer(Message msg);
}

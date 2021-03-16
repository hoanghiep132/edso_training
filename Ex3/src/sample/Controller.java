package sample;

import entity.Consumer;
import entity.Message;
import entity.MessageQueue;
import entity.Producer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable  {

    private final long TIME_DELAY_PRODUCER = 1000;
    private final long TIME_DELAY_CONSUMER = 1500;
    private final int LENGTH_QUEUE = 10;


    @FXML
    private ListView<String> listProducer;

    @FXML
    private ListView<Message> listMsgQueue;

    @FXML
    private ListView<String> listConsumer;

    private ObservableList<String> producerMessages ;

    private ObservableList<String> consumerMessages ;

    private ObservableList<Message> msgQueuerMessages;

    private  MessageQueue messageQueue;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        producerMessages = FXCollections.observableArrayList();
        consumerMessages = FXCollections.observableArrayList();
        messageQueue = MessageQueue.getInstance(LENGTH_QUEUE);
        Thread threadProducer = new Producer(messageQueue,TIME_DELAY_PRODUCER,producerMessages);
        Thread threadConsumer = new Consumer(messageQueue,TIME_DELAY_CONSUMER);

        msgQueuerMessages = FXCollections.observableArrayList(messageQueue.getMessageList());

        threadProducer.start();
        threadConsumer.start();


        listProducer.setItems(producerMessages);
        listMsgQueue.setItems(msgQueuerMessages);
        listConsumer.setItems(consumerMessages);

    }


}

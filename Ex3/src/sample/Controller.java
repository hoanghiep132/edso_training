package sample;

import entity.Consumer;
import entity.Message;
import entity.MessageQueue;
import entity.Producer;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable  {

    private final long TIME_DELAY_PRODUCER = 1500;
    private final long TIME_DELAY_CONSUMER = 1000;
    private final int LENGTH_QUEUE = 10;


    @FXML
    private ListView<String> listProducer;

    @FXML
    private ListView<String> listMsgQueue;

    @FXML
    private ListView<String> listConsumer;

    @FXML
    private Label queueSize;

    @FXML
    private Label producerSpeed;

    @FXML
    private Label consumerSpeed;


    private ObservableList<String> producerItems ;

    private ObservableList<String> consumerItems ;

    private ObservableList<String> messageItems ;

    private  MessageQueue messageQueue;

    private Producer threadProducer;

    private Consumer threadConsumer;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        producerItems = FXCollections.observableArrayList();
        consumerItems = FXCollections.observableArrayList();
        messageItems = FXCollections.observableArrayList();
        messageQueue = MessageQueue.getInstance(LENGTH_QUEUE);

        threadProducer = new Producer(messageQueue,TIME_DELAY_PRODUCER);
        threadConsumer = new Consumer(messageQueue,TIME_DELAY_CONSUMER);
        consumerSpeed.setText(String.valueOf(TIME_DELAY_CONSUMER));
        producerSpeed.setText(String.valueOf(TIME_DELAY_PRODUCER));
        getContent();
        threadProducer.start();
        threadConsumer.start();


    }


    private void getContent(){
        threadProducer.getProducerMessages().addListener((ListChangeListener) c ->{
            while (c.next()) {
                if(c.wasAdded()){
                    Platform.runLater(() -> {
                        producerItems.addAll(c.getFrom(),c.getAddedSubList());
                        int from = messageItems.size() < messageQueue.getLength() ? messageItems.size() : messageQueue.getLength();
                        messageItems.addAll(from,c.getAddedSubList());
                        queueSize.setText(String.valueOf(messageQueue.getLength()));
                        consumerSpeed.setText(String.valueOf(threadConsumer.getSpeed()));
                    });
                }
            }
        });
        threadConsumer.getConsumerMessages().addListener((ListChangeListener) c ->{
            while (c.next()) {
                if(c.wasAdded()){
                    Platform.runLater(() -> {
                        consumerItems.addAll(c.getFrom(),c.getAddedSubList());
                        messageItems.removeAll(c.getAddedSubList());
                        queueSize.setText(String.valueOf(messageQueue.getLength()));
                        consumerSpeed.setText(String.valueOf(threadConsumer.getSpeed()));
                    });
                }
            }
        });

        listProducer.setItems(producerItems);
        listConsumer.setItems(consumerItems);
        listMsgQueue.setItems(messageItems);
    }
}

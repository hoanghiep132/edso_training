package edso.hiepnh.main;

import edso.hiepnh.entities.ArrayHandler;
import edso.hiepnh.entities.ListResult;
import edso.hiepnh.entities.MyArray;
import edso.hiepnh.entities.Result;
import edso.hiepnh.entities.thread.*;
import edso.hiepnh.service.FileIO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {


    @FXML
    private Button selectBtn;

    @FXML
    private TextField searchField;

    @FXML
    private TextField fileInput;


    @FXML
    private TextArea resultView;

    private ObservableList<Result> results = FXCollections.observableArrayList();

    private File file;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        final FileChooser fileChooser = new FileChooser();
        selectBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                fileInput.clear();
                file = fileChooser.showOpenDialog(new Stage());
                if (file != null) {
                    fileInput.setText(file.getAbsolutePath());
                }
            }
        });
    }

    @FXML
    void start(ActionEvent event) {
//        if(fileInput.getText().equals("")){
        if(!fileInput.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Please choose file input!");
            alert.show();
//        }else if(searchField.getText().equals("")){
        }else if(!searchField.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Please type search field!");
            alert.show();
        }
        else{
//            int[] array = FileIO.readArrayToFile(file.getAbsolutePath());
            int[] array = FileIO.readArrayToFile("src/edso/hiepnh/file/input.txt");
            MyArray myArray = new MyArray(array,FileIO.lengthArray);
            ArrayHandler arrayHandler = new ArrayHandler(myArray);
//            ListResult listResult = new ListResult(results);
            ListResult listResult = new ListResult();
            myArray.setListResult(listResult);
            List<Integer> listSearch = Arrays.asList(23,32,5,7);
//            List<Integer> listSearch = new ArrayList<>();
//            String search = searchField.getText();
//            if(!search.equals("")){
//                String[] strings = search.split(" ");
//                for (String s : strings){
//                    try {
//                        listSearch.add(Integer.valueOf(s));
//                    }catch (Exception ex){
//                        System.err.println("Input sai");
//                    }
//                }
//            }
            SortingThread sorting = new SortingThread(arrayHandler);
            SearchingThread searching = new SearchingThread(arrayHandler, listSearch, listResult);
            DisplayThread display = new DisplayThread(listResult,resultView);

            ComplexThread sortThread = new ComplexThread("Sort", sorting);
            ComplexThread searchThread = new ComplexThread("Search" , searching);
            ComplexThread displayThread = new ComplexThread("Display",display);

            searchThread.start();
            sortThread.start();
            displayThread.start();

        }


    }
}

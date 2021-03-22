package edso.hiepnh.main;

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
        }else if(searchField.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Please type search field!");
            alert.show();
        }
        else{
//            int[] array = FileIO.readArrayToFile(file.getAbsolutePath());
            int[] array = FileIO.readArrayToFile("/home/hiepnguyen/InteliJ_Project/edso_training/Ex4/src/edso/hiepnh/file/input.txt");
            MyArray myArray = new MyArray(array,FileIO.lengthArray);
            List<Integer> listSearch = new ArrayList<>();
            String search = searchField.getText();
            if(!search.equals("")){
                String[] strings = search.split(" ");
                for (String s : strings){
                    try {
                        listSearch.add(Integer.valueOf(s));
                    }catch (Exception ex){
                        System.err.println("Input sai");
                    }
                }
            }
            ListResult listResult = new ListResult();
            Sorting sorting = new Sorting(myArray);
            Searching searching = new Searching(myArray,listSearch, listResult);
            DisplayResult displayResult = new DisplayResult(listResult);

            ComplexThread sortThread = new ComplexThread("Sort", sorting);
            ComplexThread searchThread = new ComplexThread("Search" , searching);
            ComplexThread displayThread = new ComplexThread("Display",displayResult);

            sortThread.start();
            searchThread.start();
            displayThread.start();
        }


    }
}

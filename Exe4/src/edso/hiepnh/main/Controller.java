package edso.hiepnh.main;

import edso.hiepnh.entites.DisplayThread;
import edso.hiepnh.entites.MyArray;
import edso.hiepnh.entites.SearchingThread;
import edso.hiepnh.entites.SortingThread;
import edso.hiepnh.service.FileIO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Controller implements Initializable {

    @FXML
    private TextField searchField;

    @FXML
    private TextField fileInput;

    @FXML
    private TextArea resultView;


    private SortingThread sortingThread;

    private SearchingThread searchingThread;

    private DisplayThread displayThread;

    private File fileArray;

    private File fileSearch;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        sortingThread = new SortingThread();
        searchingThread = new SearchingThread();
        displayThread = new DisplayThread();

        sortingThread.addSearchThread(searchingThread);
        searchingThread.addDisplayThread(displayThread);
        displayThread.setViewArea(resultView);

        sortingThread.start();
        searchingThread.start();
        displayThread.start();
    }


    @FXML
    void selectInput(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileInput.clear();
        fileArray = fileChooser.showOpenDialog(new Stage());
        if (fileArray != null) {
            fileInput.setText(fileArray.getAbsolutePath());
            int[] arr = FileIO.readArrayToFile(fileArray.getAbsolutePath());
            MyArray myArray = new MyArray(arr,arr.length);
            sortingThread.addInput(myArray);
        }
    }

    @FXML
    void selectSearch(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        searchField.clear();
        fileSearch = fileChooser.showOpenDialog(new Stage());
        if (fileSearch != null) {
            searchField.setText(fileSearch.getAbsolutePath());
            List<Integer> listSearch = FileIO.readSearchFile(fileSearch.getAbsolutePath());
            searchingThread.addSearchInput(listSearch);
        }
    }

}

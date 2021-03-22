package edso.hiepnh.entities;

import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MyArray {

    private int[] array;

    private int length;

    private ListResult listResult;

    private List<Integer> searchList = null;

    public int[] getArray() {
        return array;
    }

    public void setArray(int[] array) {
        this.array = array;
    }

    public  int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }



    public MyArray() {
    }


    public MyArray(int[] array, int length) {
        this.array = array;
        this.length = length;
        listResult = new ListResult();
    }

}

package edso.hiepnh.entities.thread;

import edso.hiepnh.entities.ListResult;
import edso.hiepnh.entities.MyArray;
import edso.hiepnh.entities.Result;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Searching implements IThreadArray{

    private MyArray myArray;

    private List<Integer> searchList;

    private ListResult listResult;

    public Searching(MyArray myArray, List<Integer> searchList, ListResult listResult) {
        this.myArray = myArray;
        this.searchList =  searchList;
        this.listResult = listResult;
    }

    @Override
    public synchronized void implement() {
//        try {
//            wait();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        search();
    }

    private void search(){
        for(int i = 0; i < myArray.getLength(); i++){
            if(searchList.contains(myArray.getArray()[i])){
                Result result = new Result(i,myArray.getArray()[i]);
                result.addListResult(listResult);
            }
        }
    }


}

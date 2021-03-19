package edso.hiepnh.entities.thread;

import edso.hiepnh.entities.MyArray;
import edso.hiepnh.entities.Result;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Searching implements IThreadArray{

    private MyArray myArray;

    private List<Integer> searchList;


    public Searching(MyArray myArray, List<Integer> searchList) {
        this.myArray = myArray;
//        this.searchList = searchList;
        myArray.setSearchList(searchList);
    }

    @Override
    public void implement() {
        search(searchList);
    }
    
    public void search(List<Integer> searchList){
         IntStream.range(0, myArray.getLength()-1).filter(i -> searchList.contains(myArray.getArray()[i]))
                .mapToObj(i -> new Result(i,myArray.getArray()[i]))
                .collect(Collectors.toList());
    }


}

package edso.hiepnh.entities.thread;

import edso.hiepnh.entities.MyArray;
import edso.hiepnh.entities.Result;

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
//        myArray.search(searchList);
        myArray.implement(2);
    }

}

package edso.hiepnh.entities.thread;

import edso.hiepnh.entities.MyArray;

import java.util.List;

public class Searching implements IThreadArray{

    private MyArray myArray;

    private List<Integer> searchList;

    public Searching(MyArray myArray, List<Integer> searchList) {
        this.myArray = myArray;
        this.searchList = searchList;
    }

    @Override
    public void implement() {
        synchronized (this) {
            myArray.search(searchList);
        }
    }
}

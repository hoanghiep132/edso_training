package edso.hiepnh.entities.thread;

import edso.hiepnh.entities.MyArray;
import edso.hiepnh.entities.Result;
import javafx.collections.ObservableList;

public class Printer implements IThreadArray{

    private MyArray myArray;

    public Printer(MyArray myArray) {
        this.myArray = myArray;
    }

    private ObservableList<Result> results;


    @Override
    public void implement() {
//        myArray.printMonitor();
    }

}

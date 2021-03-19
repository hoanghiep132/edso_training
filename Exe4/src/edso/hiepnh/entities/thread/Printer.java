package edso.hiepnh.entities.thread;

import edso.hiepnh.entities.MyArray;

public class Printer implements IThreadArray{

    private MyArray myArray;

    public Printer(MyArray myArray) {
        this.myArray = myArray;
    }

    @Override
    public void implement() {
//        myArray.printMonitor();
        myArray.implement(3);
    }

}

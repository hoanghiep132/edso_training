package edso.hiepnh.entities.thread;

import edso.hiepnh.entities.MyArray;

public class Sorting implements IThreadArray{

    private MyArray myArray;

    public Sorting(MyArray myArray) {
        this.myArray = myArray;
    }

    @Override
    public void implement() {
        myArray.implement(1);
//        myArray.sort();
    }

}

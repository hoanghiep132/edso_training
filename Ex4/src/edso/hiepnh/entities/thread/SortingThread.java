package edso.hiepnh.entities.thread;

import edso.hiepnh.entities.ArrayHandler;

public class SortingThread implements IThreadArray{

    private ArrayHandler arrayHandler;

    public SortingThread(ArrayHandler arrayHandler) {
        this.arrayHandler = arrayHandler;
    }

    @Override
    public void implement() {
        while(true) {
            synchronized (arrayHandler) {
                arrayHandler.sort();
            }
        }
    }

}


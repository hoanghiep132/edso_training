package edso.hiepnh.entities;

import javafx.collections.ObservableList;

public class Result {

    private int index;
    private int value;

    public Result() {
    }

    public Result(int index, int value) {
        this.index = index;
        this.value = value;
    }

    @Override
    public String toString() {
        return "{" +
                "index=" + index +
                ", value=" + value +
                '}';
    }

    public void addListResult(ListResult listResult){
        listResult.add(this);

        synchronized (listResult){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            listResult.notifyAll();
        }
    }
}

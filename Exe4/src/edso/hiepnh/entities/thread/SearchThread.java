package edso.hiepnh.entities.thread;

import edso.hiepnh.entities.MyArray;
import edso.hiepnh.entities.Result;

import java.util.List;

public class SearchThread extends Thread{

    private MyArray myArray;

    private List<Integer> searchList;

    private List<Result> resultList;


    public SearchThread(MyArray myArray, List<Integer> searchList) {
        this.myArray = myArray;
        this.searchList = searchList;
    }

    public List<Result> getResultList() {
        return resultList;
    }

    public void setResultList(List<Result> resultList) {
        this.resultList = resultList;
    }

    public void run(){
        synchronized (this){
            System.out.println("Thread search start : " + System.nanoTime());
            resultList = myArray.search(searchList);
            System.out.println("Thread search end : " + System.nanoTime());

        }
    }


}

package edso.hiepnh.entities.thread;

import edso.hiepnh.entities.Result;

import java.util.List;


public class PrintThread extends Thread {

    private List<Result> resultList;

    public PrintThread(List<Result> resultList) {
        this.resultList = resultList;
    }

    public void run() {
        System.out.println("Thread print start : " + System.nanoTime());
        printMonitor();
        System.out.println("Thread print end : " + System.nanoTime());
    }

    public synchronized void printMonitor() {
        System.out.println("Result : ");
        resultList.forEach(Result::toString);
    }
}

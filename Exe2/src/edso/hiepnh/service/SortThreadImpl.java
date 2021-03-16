package edso.hiepnh.service;

import edso.hiepnh.entities.MergeSort;
import edso.hiepnh.entities.MyArray;
import edso.hiepnh.entities.QuickSort;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SortThreadImpl {

    private final int THREAD_LIMIT = 100;

    private int threadCount = 1;

    private MyArray myArray;

    private List<Thread> threadList;

    public void setThreadCount(int threadCount) {
        if(threadCount > THREAD_LIMIT){
            this.threadCount = THREAD_LIMIT;
        }else if(threadCount < 0){
            this.threadCount = 1;
        }else{
            this.threadCount = threadCount;
        }
    }

    public MyArray getMyArray() {
        return myArray;
    }

    public void setMyArray(MyArray myArray) {
        this.myArray = myArray;
    }


    public void implement(){
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        long start = System.nanoTime();
        int range = myArray.getLength() / threadCount;
        threadList = new ArrayList<>();
        int i = 0;
        for(; i < threadCount-1; i++){
            int begin = i * range;
            int end = (i+1) * range - 1;
            Thread sortThread = new QuickSort(myArray.getArray(),begin,end);
            executorService.execute(sortThread);
        }
        Thread sortThread = new QuickSort(myArray.getArray(),i * range,myArray.getLength()-1);
        executorService.execute(sortThread);
//        for (Thread t : threadList){
//            t.start();
//            try {
//                t.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        long end = System.nanoTime();
        System.out.println("Sort time : " + (end-start) + " ns");
        executorService.shutdown();
        if(threadCount > 1){
            merge(threadCount);
        }
    }


    public void merge(int numberOfThread){
        int range = myArray.getLength() / threadCount * (threadCount / numberOfThread);
        int i = 0;
        for(; i < numberOfThread-2; i += 2){
            int begin = i*range;
            int medium = (i+1)*range-1;
            int end =(i+2)*range-1;
            mergeArray(myArray.getArray(),begin,medium,end);
        }
        mergeArray(myArray.getArray(),i*range,(i+1)*range-1,myArray.getLength()-1);
        if (numberOfThread > 2){
            merge(numberOfThread/2);
        }
    }

    void mergeArray(int[] arr, int l, int m, int r){
        int[] newArray = myArray.getArray().clone();
        int n = m+1;
        int index = l;
        while (l <= m && n <= r){
            try{
                if(arr[l] < arr[n]){
                    newArray[index++] = arr[l++];
                }else{
                    newArray[index++] = arr[n++];
                }
            }catch (Exception ex){
                System.err.println(r);
                break;
            }
        }
        try{
            while (l <= m){
                newArray[index++] = arr[l++];
            }
            while (n <= r){
                newArray[index++] = arr[n++];
            }
        }catch (Exception ex){
            System.err.println(r + "," + n);
        }
        myArray.setArray(newArray);
    }

    public void shutDown(){
        threadList.forEach(Thread::stop);
    }

}

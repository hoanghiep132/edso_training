package edso.hiepnh.entities;

import java.util.List;

public class ArrayHandler {

    private MyArray myArray;
    protected boolean bSorting = false;
    protected boolean bSorted = false;
    protected boolean bChanged = false;

    public synchronized  void setMyArray(MyArray myArray) {
        this.myArray = myArray;
        bSorting = false;
        bSorted = false;
        bChanged = true;
    }

    public synchronized  boolean isbSorting() {
        return bSorting;
    }

    public ArrayHandler(MyArray myArray) {
        this.myArray = myArray;
        bSorted = false;
        bChanged = true;
    }


    public void sort(){
        if (bSorted) {
            try {
                System.out.println("sort() sorted = true");
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return;
        }

        bSorting = true;
        quickSort(myArray.getArray(),0,myArray.getLength()-1);
        System.out.println("Done Sorting");
        synchronized (this) {
            this.notifyAll();

            bSorting = false;
            bSorted = true;
        }
    }

    public void searching(List<Integer> searchList, ListResult listResult){
        System.out.println("in searching");
        listResult.clearState();

        if (!bChanged){
            try {
                System.out.println("searching() bChanged = false");
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return;
        }

        synchronized (this) {
            if (bSorting && myArray.getLength() > 0 || bSorted == false) {
                try {
                    System.out.println("searching wait");

                    this.wait();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        bChanged = false;

        System.out.println("Start searching");
//        listResult.setWaiting(false);

        for (int i = 0; i < myArray.getLength(); i++) {
            if (searchList.contains(myArray.getArray()[i])) {
                Result result = new Result(i, myArray.getArray()[i]);
                result.addListResult(listResult);
                System.out.println("Index : " + i);
            }
        }

        listResult.setEndThread(true);

//        listResult.setEndThread(true);
//        synchronized (listResult) {
//
//
//            listResult.notifyAll();
//        }
    }

    private int partition(int[] arr, int begin, int end){
        int pivot = arr[end];
        int i = (begin-1);

        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i+1];
        arr[i+1] = arr[end];
        arr[end] = temp;
        return i+1;
    }

    private void quickSort(int arr[], int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex-1);
            quickSort(arr, partitionIndex+1, end);
        }
    }
}

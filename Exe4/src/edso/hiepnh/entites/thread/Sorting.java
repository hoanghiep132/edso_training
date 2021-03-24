package edso.hiepnh.entites.thread;

import edso.hiepnh.entites.MyArray;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Sorting implements IMyThread{


    private Queue<MyArray> listArrayInput;

    public Sorting() {
        listArrayInput = new LinkedList<>();
    }

    private void startSort(MyArray myArray){
        quickSort(myArray.getArray(),0,myArray.getLength()-1);
    }

    public void addInput(MyArray myArray){
        listArrayInput.add(myArray);
    }

    @Override
    public void implement() {
        while (true){
            if(!listArrayInput.isEmpty()){
                MyArray myArray = listArrayInput.remove();
                startSort(myArray);
            }else {
                synchronized (this){
                    try {
                        wait();
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
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

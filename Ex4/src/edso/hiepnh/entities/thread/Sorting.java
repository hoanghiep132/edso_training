package edso.hiepnh.entities.thread;

import edso.hiepnh.entities.MyArray;

public class Sorting implements IThreadArray{

    private MyArray myArray;

    public Sorting(MyArray myArray) {
        this.myArray = myArray;
    }

    @Override
    public synchronized void implement() {
        quickSort(myArray.getArray(),0, myArray.getLength()-1);
        notifyAll();
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

package edso.hiepnh.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MyArray {

    private int[] array;

    private int length;

    private List<Result> resultList;

    private List<Integer> searchList = null;

    public int[] getArray() {
        return array;
    }

    public void setArray(int[] array) {
        this.array = array;
    }

    public  int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }



    public List<Integer> getSearchList() {
        return searchList;
    }

    public void setSearchList(List<Integer> searchList) {
        this.searchList = searchList;
    }

    public MyArray() {
    }


    public MyArray(int[] array, int length) {
        this.array = array;
        this.length = length;
        resultList = new ArrayList<>();
    }

    public synchronized void sort(){
        quickSort(getArray(),0,getLength()-1);
    }


    public void printArray(){
        System.out.print(array[0] + " ");
        for (int i = 1; i < length;i++){
            if(i % 10 == 0){
                System.out.print(array[i] + "\n");
            }else {
                System.out.print(array[i] + " ");
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
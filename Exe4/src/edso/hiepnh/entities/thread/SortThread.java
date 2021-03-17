package edso.hiepnh.entities.thread;

public class SortThread extends Thread {

    private int[] array;
    private int begin;
    private int end;

    public SortThread(int[] array, int begin, int end) {
        this.array = array;
        this.begin = begin;
        this.end = end;
    }


    public void run(){
        System.out.println("Thread sort start : " + System.nanoTime());
        quickSort(array,begin,end);
        System.out.println("Thread sort start : " + System.nanoTime());
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

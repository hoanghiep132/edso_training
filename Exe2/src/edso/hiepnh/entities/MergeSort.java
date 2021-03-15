package edso.hiepnh.entities;

public class MergeSort extends Thread implements SortAlgorithm{

    private int[] array;
    private int length;
    private int begin;
    private int end;

    public MergeSort() {
    }

    public MergeSort(int[] array,int length, int begin, int end) {
        this.array = array;
        this.length = length;
        this.begin = begin;
        this.end = end;
    }

    public void run(){
        mergeArray(array,begin,end);
    }

    @Override
    public void sort() {
        mergeArray(array,begin,end);
//        sort(array,begin,end);
    }

    void merge(int arr[], int l, int m, int r)
    {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        /* Create temp arrays */
        int L[] = new int[n1];
        int R[] = new int[n2];

        /*Copy data to temp arrays*/
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        /* Merge the temp arrays */

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarry array
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            }
            else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        /* Copy remaining elements of L[] if any */
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    // merge()
    void sort(int arr[], int l, int r)
    {
        if (l < r) {
            // Find the middle point
            int m =l+ (r-l)/2;

            // Sort first and second halves
            sort(arr, l, m);
            sort(arr, m + 1, r);

            // Merge the sorted halves
            merge(arr, l, m, r);
        }
    }

    void mergeArray(int[] arr, int l, int r){
            int[] newArray = array;
            int m = (r-l)/2;
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
            this.array = newArray;
    }
}

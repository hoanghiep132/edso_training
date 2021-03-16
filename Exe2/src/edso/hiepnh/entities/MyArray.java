package edso.hiepnh.entities;

import java.util.Arrays;

public class MyArray {

    private int[] array;

    private int length;

    public int[] getArray() {
        return array;
    }

    public void setArray(int[] array) {
        this.array = array;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public MyArray() {
    }

    public MyArray(int[] array, int length) {
        this.array = array;
        this.length = length;
    }

    public void printArray(){
        System.out.print(array[0] + " ");
        for (int i = 1; i < length;i++){
            if(i%10==0){
                System.out.println();
            }
            System.out.print(array[i] + " ");
        }
    }

    public int checkOrdered(){
        for (int i = 0; i < length; i++){
            if(array[i] != i + 1){
                return i;
            }
        }
        return -1;
    }

    public int[] subArray(int begin, int end){
        if(begin < 0){
            begin = 0;
        }
        if(end > length){
            end = length;
        }
        return Arrays.copyOfRange(array,begin,end);
    }
}

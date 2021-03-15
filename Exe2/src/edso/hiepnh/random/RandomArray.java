package edso.hiepnh.random;

import edso.hiepnh.entities.MyArray;

import java.util.Random;

public class RandomArray {

    public MyArray randomArray(int n){
        MyArray rs = new MyArray();
        rs.setLength(n);
        int[] arr = new int[n];
        for (int i = 0; i < n; i++){
            arr[i] = i+1;
        }
        Random random = new Random();
        for (int i = 0; i < n; i++){
            int index = random.nextInt(n-i)+i;
            int temp = arr[i];
            arr[i] = arr[index];
            arr[index] = temp;
        }
        rs.setArray(arr);
        return rs;
    }

    public static void main(String[] args) {
        MyArray array = new RandomArray().randomArray(10000);
        for (int i = 0; i < 10000; i++){
            System.out.print(array.getArray()[i] + " ");
        }
    }
}

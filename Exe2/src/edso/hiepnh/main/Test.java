package edso.hiepnh.main;

import edso.hiepnh.entities.MyArray;

import edso.hiepnh.random.RandomArray;
import edso.hiepnh.service.FileIO;
import edso.hiepnh.service.SortThreadImpl;


public class Test {

    public static void main(String[] args) {

        FileIO.readConfigFile(FileIO.configFile);
        int num = FileIO.lengthArray;
        int[] array = new FileIO().readArrayToFile(FileIO.inputFile,num);

        MyArray arr1 = new MyArray();
        arr1.setArray(array.clone());
        arr1.setLength(num);
        SortThreadImpl sortThread1 = new SortThreadImpl();
        sortThread1.setMyArray(arr1);
        sortThread1.setThreadCount(1);
        long start1 = System.nanoTime();
        sortThread1.implement();
        long end1 = System.nanoTime();
        sortThread1.shutDown();

        SortThreadImpl sortThread2 = new SortThreadImpl();
        MyArray arr2 = new MyArray();
        arr2.setArray(array.clone());
        arr2.setLength(num);
        sortThread2.setMyArray(arr2);
        sortThread2.setThreadCount(2);
        long start2 = System.nanoTime();
        sortThread2.implement();
        long end2 = System.nanoTime();
        sortThread2.shutDown();

        MyArray arr4 = new MyArray();
        arr4.setArray(array.clone());
        arr4.setLength(num);
        SortThreadImpl sortThread4 = new SortThreadImpl();
        sortThread4.setMyArray(arr4);
        sortThread4.setThreadCount(4);
        long start4 = System.nanoTime();
        sortThread4.implement();
        long end4 = System.nanoTime();
//        FileIO.writeArrayToFile(FileIO.outputFile,arr4.getArray());
        arr4.printArray();
        System.out.println("\nTime 1 thread : " + (end1-start1)/1000000 + " ms");
        System.out.println("\nTime 2 threads : " + (end2-start2)/1000000 + " ms");
        System.out.println("\nTime 4 threads : " + (end4-start4)/1000000 + " ms");
    }
}

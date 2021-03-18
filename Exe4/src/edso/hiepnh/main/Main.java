package edso.hiepnh.main;

import edso.hiepnh.entities.*;
import edso.hiepnh.entities.thread.ComplexThread;
import edso.hiepnh.entities.thread.Printer;
import edso.hiepnh.entities.thread.Searching;
import edso.hiepnh.entities.thread.Sorting;
import edso.hiepnh.service.FileIO;

import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {

        int[] array = FileIO.readArrayToFile(FileIO.inputFile,FileIO.lengthArray);
        MyArray myArray = new MyArray(array,FileIO.lengthArray);
        List<Integer> list = FileIO.readSearchFile(FileIO.searchFile);

//        ExecutorService executor = Executors.newFixedThreadPool(3);

        Sorting sorting = new Sorting(myArray);
        Searching searching = new Searching(myArray,list);
        Printer printer = new Printer(myArray);

        ComplexThread sortThread = new ComplexThread("Sort", sorting);
        ComplexThread searchThread = new ComplexThread("Search" , searching);
        ComplexThread printThread = new ComplexThread("Print" , printer);

        sortThread.start();
        searchThread.start();
        printThread.start();
    }
}

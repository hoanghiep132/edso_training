package edso.hiepnh.main;

import edso.hiepnh.entities.*;
import edso.hiepnh.entities.thread.ComplexThread;
import edso.hiepnh.entities.thread.Printer;
import edso.hiepnh.entities.thread.Searching;
import edso.hiepnh.entities.thread.Sorting;
import edso.hiepnh.service.FileIO;

import java.util.List;

import static java.lang.Thread.NORM_PRIORITY;


public class Main {

    public static void main(String[] args) {

        int[] array = FileIO.readArrayToFile(FileIO.inputFile,FileIO.lengthArray);
        MyArray myArray = new MyArray(array,FileIO.lengthArray);
        List<Integer> list = FileIO.readSearchFile(FileIO.searchFile);

        Sorting sorting = new Sorting(myArray);
        Searching searching = new Searching(myArray,list);
        Printer printer = new Printer(myArray);

        ComplexThread sortThread = new ComplexThread("Sort", sorting);
        sortThread.setPriority(Thread.MAX_PRIORITY);

        ComplexThread searchThread = new ComplexThread("Search" , searching);
        searchThread.setPriority(Thread.NORM_PRIORITY);

        ComplexThread printThread = new ComplexThread("Print" , printer);
        printThread.setPriority(Thread.MIN_PRIORITY);

        sortThread.start();
        searchThread.start();
        printThread.start();

        try {
            sortThread.join();
            searchThread.join();
            printThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

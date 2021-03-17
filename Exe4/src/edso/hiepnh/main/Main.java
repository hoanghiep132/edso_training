package edso.hiepnh.main;

import edso.hiepnh.entities.MyArray;
import edso.hiepnh.entities.Result;
import edso.hiepnh.entities.thread.PrintThread;
import edso.hiepnh.entities.thread.SearchThread;
import edso.hiepnh.entities.thread.SortThread;
import edso.hiepnh.service.FileIO;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        int[] array = FileIO.readArrayToFile(FileIO.inputFile,FileIO.lengthArray);
        List<Integer> list = FileIO.readSearchFile(FileIO.searchFile);
        list.forEach(System.out::println);
        List<Result> resultList = new ArrayList<>();

        Thread sortThread = new SortThread(array,0,FileIO.lengthArray-1);
        sortThread.start();

        Thread searchThread = new SearchThread(new MyArray(array,FileIO.lengthArray),list);
        ((SearchThread) searchThread).setResultList(resultList);
        searchThread.start();

        Thread printThread = new PrintThread(((SearchThread) searchThread).getResultList());
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

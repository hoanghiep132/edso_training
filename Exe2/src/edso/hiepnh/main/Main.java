package edso.hiepnh.main;

import edso.hiepnh.entities.MyArray;
import edso.hiepnh.service.FileIO;
import edso.hiepnh.service.SortThreadImpl;

public class Main {

    public static void main(String[] args) {
        FileIO.readConfigFile(FileIO.configFile);
        int num = FileIO.lengthArray;
        int[] array = new FileIO().readArrayToFile(FileIO.inputFile,num);

        MyArray arr = new MyArray();
        arr.setArray(array);
        arr.setLength(num);
        SortThreadImpl sortThread = new SortThreadImpl();
        sortThread.setMyArray(arr);
        sortThread.setThreadCount(FileIO.threads);
        long start = System.nanoTime();
        sortThread.implement();
        long end = System.nanoTime();
        sortThread.shutDown();
        FileIO.writeArrayToFile(FileIO.outputFile,arr.getArray());
        String data;
        if(num >= 1000000){
            data = num/1000000 + "M";
        }else {
            data = num/1000 + "K";
        }
        String time;
        if((end-start)>1000000){
            time = (end-start)/1000000 + " ms";
        }else {
            time = (end-start)/1000 + " us";
        }
        System.out.println("\n"+ FileIO.threads + " Theads , " + data +" data  : " + time);
        System.out.println("Result : " + (arr.checkOrdered()==-1?" True" : "False"));
    }
}

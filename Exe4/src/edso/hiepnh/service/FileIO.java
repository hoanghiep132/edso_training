package edso.hiepnh.service;

import edso.hiepnh.entities.MyArray;
import edso.hiepnh.random.RandomArray;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileIO {

    public static String inputFile = "src/edso/hiepnh/file/input.txt";
    public static String searchFile = "src/edso/hiepnh/file/search.txt";
    public static Integer lengthArray = 10000;

    public static void writeArrayToFile(String fileName,int[] arr){
        File file = new File(fileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.append(String.valueOf(arr[0]));
            for(int i = 1; i < arr.length; i++){
                if(i % 10 == 0){
                    writer.append("\n"+arr[i]);
                }else{
                    writer.append(" " + arr[i]);
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public static List<Integer> readSearchFile(String fileName){
        List<Integer> list = new ArrayList<>();
        try(BufferedReader bf = new BufferedReader(new FileReader(fileName))){
            String line;
            while ((line = bf.readLine()) != null){
                String[] strings = line.split(" ");
                for(String str :  strings){
                    try{
                        list.add(Integer.valueOf(str));
                    }catch (Exception ex){
                        continue;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return list;
    }

    public static int[] readArrayToFile(String fileName, int n){
        int[] arr = new int[n];
        int i = 0;
        try(BufferedReader bf = new BufferedReader(new FileReader(fileName))){
            String line;
            while ((line = bf.readLine()) != null){
                String[] strings = line.split(" ");
                for(String str :  strings){
                    try{
                        arr[i++] = Integer.valueOf(str);
                    }catch (Exception ex){
                        i--;
                        continue;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return arr;
    }


    public static void main(String[] args) {
        MyArray myArray = new RandomArray().randomArray(lengthArray);
        writeArrayToFile(inputFile,myArray.getArray());
    }

}

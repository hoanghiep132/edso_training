package edso.hiepnh.service;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileIO {

    public static String inputFile = "src/edso/hiepnh/file/input.txt";
    public static String searchFile = "src/edso/hiepnh/file/search.txt";
    public static Integer lengthArray = 100000;

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

    public static int[] readArrayToFile(String fileName){
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
        int length = list.size();
        int[] arr = new int[length];
        for(int i = 0; i < length;i++){
            arr[i] = list.get(i);
        }
        return arr;
    }
}

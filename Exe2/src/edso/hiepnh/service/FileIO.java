package edso.hiepnh.service;

import edso.hiepnh.entities.MyArray;
import edso.hiepnh.random.RandomArray;

import java.io.*;

public class FileIO {

    public static String inputFile = "src/edso/hiepnh/file/input.txt";
    public static String outputFile = "src/edso/hiepnh/file/output.txt";
    public static String configFile = "src/edso/hiepnh/file/config.txt";
    public static Integer lengthArray = 10000000;
    public static Integer threads = 4;
    public static Boolean randomArray = false;

    public static void writeArrayToFile(String fileName,int[] arr){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.append(arr[0] + " ");
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

    public static void readConfigFile(String fileName){
        try(BufferedReader bf = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bf.readLine()) != null) {
                String[] strings = line.split("=");
                switch (strings[0]){
                    case "lengthArray":
                        if (strings.length>1){
                            try{
                                lengthArray = Integer.valueOf(strings[1]);
                            }catch (Exception ex){
                                System.err.println("Config sai");
                            }finally {
                                break;
                            }
                        }
                        break;
                    case "input":
                        if (strings.length>1){
                            inputFile = strings[1];
                        }
                        break;
                    case "output":
                        if (strings.length>1){
                            outputFile = strings[1];
                        }
                        break;
                    case "threads":
                        if (strings.length>1){
                            try{
                                threads = Integer.valueOf(strings[1]);
                            }catch (Exception ex){
                                System.err.println("Config sai");
                            }finally {
                                break;
                            }
                        }
                        break;
                    case "random":
                        if (strings.length>1){
                            if(strings[1].toLowerCase().equals("true")){
                                randomArray = true;
                            }else if(strings[1].toLowerCase().equals("false")){
                                randomArray = false;
                            }
                        }
                        randomArray =false;
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public static void main(String[] args) {

//        MyArray myArray = new RandomArray().randomArray(FileIO.lengthArray);
//        FileIO.writeArrayToFile(FileIO.inputFile,myArray.getArray());

        readConfigFile("src/edso/hiepnh/file/config.txt");
        System.out.println(1);
    }

}

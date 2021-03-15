package edso.hiepnh.service;

import java.io.*;

public class FileIO {


    public void writeArrayToFile(File file,int[] arr){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.append(arr[0] + " ");
            for(int i = 1; i < arr.length; i++){
                writer.append(String.valueOf(arr[i]));
                if(i%10 == 0){
                    writer.append("\n");
                }else{
                    writer.append(" ");
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public int[] readArrayToFile(File file, int n){
        int[] arr = new int[n];
        int i = 0;
        try(BufferedReader bf = new BufferedReader(new FileReader(file))){
            String line;
            while ((line = bf.readLine()) != null){
                String[] strings = line.split("");
                for(String str :  strings){
                    arr[i++] = Integer.valueOf(str);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return arr;
    }

}

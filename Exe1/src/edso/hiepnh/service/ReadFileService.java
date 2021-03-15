package edso.hiepnh.service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadFileService implements BaseReadFile {

    @Override
    public List<String> readFile(File file) {
        List<String> stringList = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)))){
            String line;
            while ((line = br.readLine()) != null){
                stringList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return stringList;
    }

    @Override
    public List<String> readFile(File file, int numberOfLine) {
        List<String> stringList = new ArrayList<>();
        try(RandomAccessFile raf = new RandomAccessFile(file,"r")){
            long len = raf.length() - numberOfLine;
            raf.seek(len);
            while (raf.getFilePointer() < raf.length()){
                stringList.add(raf.readLine());
            }
            return stringList;
        }catch (IOException ex){
            System.err.println(ex);
            return null;
        }
    }
}

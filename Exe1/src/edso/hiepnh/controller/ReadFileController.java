package edso.hiepnh.controller;

import edso.hiepnh.entities.BaseFile;
import edso.hiepnh.service.BaseReadFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

public class ReadFileController {

    private BaseReadFile baseReadFile;


    public ReadFileController(BaseReadFile baseReadFile) {
        this.baseReadFile = baseReadFile;
    }

    public List<String> readFile(BaseFile baseFile, int numberOfLine){
        File file = new File(baseFile.getFileDirectory());
        List<String> rs = baseReadFile.readFile(file);
        if(rs.size() < numberOfLine){
            return rs;
        }else{
            return rs.subList(rs.size() - numberOfLine, rs.size())
                    .stream().collect(Collectors.toList());
        }
    }

    public List<String> readFileAcsse(BaseFile baseFile, int numberOfLine) {
        File file = new File(baseFile.getFileDirectory());
        return baseReadFile.readFile(file,numberOfLine);
    }
}

package edso.hiepnh.main;

import edso.hiepnh.controller.ReadFileController;
import edso.hiepnh.entities.BaseFile;
import edso.hiepnh.service.BaseReadFile;
import edso.hiepnh.service.ReadFileService;

import java.util.List;

public class Main {


    public static void main(String[] args) {
        BaseFile baseFile = new BaseFile("src/edso/hiepnh/input/post-man.txt");
        BaseReadFile baseReadFile = new ReadFileService();
        List<String> rs = new ReadFileController(baseReadFile).readFile(baseFile,5);

        rs.forEach(e -> System.out.println(e.length() + " : " + e));
        System.out.println("\n\n");

        List<String> rs2 = new ReadFileController(baseReadFile).readFileByRandomAccess(baseFile,5);

        rs2.forEach(e -> System.out.println(e.length() + " : " + e));
    }
}

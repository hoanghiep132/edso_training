package edso.hiepnh.service;

import java.io.File;
import java.util.List;

public interface BaseReadFile {

    List<String> readFile(File file);


    List<String> readFileByRandomAccess(File file, int numberOfLine);
}

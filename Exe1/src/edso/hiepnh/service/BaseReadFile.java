package edso.hiepnh.service;

import java.io.File;
import java.io.InputStream;
import java.util.List;

public interface BaseReadFile {

    List<String> readFile(File file);


    List<String> readFile(File file, int numberOfLine);
}

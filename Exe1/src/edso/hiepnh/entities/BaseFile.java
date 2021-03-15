package edso.hiepnh.entities;

public class BaseFile {

    private String fileDirectory;

    public BaseFile() {
    }

    public BaseFile(String fileDirectory) {
        this.fileDirectory = fileDirectory;
    }

    public String getFileDirectory() {
        return fileDirectory;
    }

    public void setFileDirectory(String fileDirectory) {
        this.fileDirectory = fileDirectory;
    }
}

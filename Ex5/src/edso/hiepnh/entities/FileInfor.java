package edso.hiepnh.entities;

import java.io.Serializable;

public class FileInfor implements Serializable {

    private byte[] dataBytes;

    private long fileSize;

    public FileInfor(byte[] dataBytes, long fileSize) {
        this.dataBytes = dataBytes;
        this.fileSize = fileSize;
    }

    public byte[] getDataBytes() {
        return dataBytes;
    }

    public void setDataBytes(byte[] dataBytes) {
        this.dataBytes = dataBytes;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }
}

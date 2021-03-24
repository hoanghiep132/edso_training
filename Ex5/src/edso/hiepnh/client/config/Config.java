package edso.hiepnh.client.config;

public class Config {
    private String clientFileDir;

    private int port;

    private String ip;

    public String getClienFileDir() {
        return clientFileDir;
    }

    public void setClienFileDir(String serverFileDir) {
        this.clientFileDir = serverFileDir;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}

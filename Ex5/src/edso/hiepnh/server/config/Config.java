package edso.hiepnh.server.config;

public class Config {
    private String serverFileDir;

    private int port;

    private String ip;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getServerFileDir() {
        return serverFileDir;
    }

    public void setServerFileDir(String serverFileDir) {
        this.serverFileDir = serverFileDir;
    }
}

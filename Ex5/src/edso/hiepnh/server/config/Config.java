package edso.hiepnh.server.config;

public class Config {
    private String serverFileDir;

    private int port;

    private int maxClient;


    public int getMaxClient() {
        return maxClient;
    }

    public void setMaxClient(int maxClient) {
        this.maxClient = maxClient;
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

package edso.hiepnh.server.service;

import edso.hiepnh.entities.FileInfor;
import edso.hiepnh.server.config.Config;

import java.io.*;

public class FileIO {

    public FileIO() {
        config = new Config();
    }

    private Config config;

    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    public boolean checkFileExist(String fileName){
        File f = new File(config.getServerFileDir() + fileName);
        return f.exists();
    }

    public void readConfig(){
        String fileName = "src/edso/hiepnh/server/config/config.txt";
        try(BufferedReader bf = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bf.readLine()) != null) {
                String[] strings = line.split("=");
                switch (strings[0]){
                    case "server_file_dir":
                        if(strings.length>1){
                            config.setServerFileDir(strings[1]);
                        }
                        break;
                    case "port":
                        if (strings.length>1){
                            try {
                                int port = Integer.valueOf(strings[1]);
                                config.setPort(port);
                            }catch (Exception ex){

                            }finally {
                                break;
                            }
                        }
                    case "ip":
                        if(strings.length>1){
                            config.setIp(strings[1]);
                        }
                        break;
                }
            }
        } catch (FileNotFoundException e) {
        e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeFile(byte[] bytes, String fileName){
        try(FileOutputStream fos = new FileOutputStream(fileName)) {
            fos.write(bytes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FileInfor readFile(String fileName){
        File file = new File(fileName);
        long lengthSize = file.length();
        byte[] data = new byte[(int) lengthSize];
        try(FileInputStream fis = new FileInputStream(file)){
            fis.read(data);
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new FileInfor(data,lengthSize);
    }
}

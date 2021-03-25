package edso.hiepnh.client.service;

import edso.hiepnh.client.config.Config;
import edso.hiepnh.entities.FileInfor;

import java.io.*;


public class FileIO {

    private Config config;

    public FileIO() {
        config = new Config();
        readConfig();
    }

    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }
    public boolean checkFileExist(String fileName){
        File f = new File(config.getClienFileDir() + fileName);
        return f.exists();
    }

    private void readConfig(){
        String fileName = "src/edso/hiepnh/client/config/config.txt";
        try(BufferedReader bf = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bf.readLine()) != null) {
                String[] strings = line.split("=");
                switch (strings[0]){
                    case "client_file_dir":
                        if(strings.length>1){
                            config.setClienFileDir(strings[1]);
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
        File file = new File(config.getClienFileDir() + fileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try(FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(bytes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FileInfor readFile(String fileName){
        String path = config.getClienFileDir() + fileName;
        File file = new File(path);
        long lengthSize = file.length();
        byte[] data = new byte[(int) lengthSize];
        try(FileInputStream fis = new FileInputStream(file)){
            fis.read(data);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return new FileInfor(data,lengthSize);
    }



}

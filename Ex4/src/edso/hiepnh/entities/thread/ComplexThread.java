package edso.hiepnh.entities.thread;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ComplexThread extends Thread{

    private IThreadArray thread;

    public ComplexThread(String name, IThreadArray thread) {
        super(name);
        this.thread = thread;
    }

    public void run(){
        String format = "hh:mm:ss.SSS";
        System.out.println("Thread " + Thread.currentThread().getName() + " start : " + new SimpleDateFormat(format).format(new Date()));
        synchronized (this){
            thread.implement();
        }
    }
}

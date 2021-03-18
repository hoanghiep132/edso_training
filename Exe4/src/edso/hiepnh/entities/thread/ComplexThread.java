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
        synchronized (this) {
            System.out.println("Thread " + this.getName() + " start : " + new SimpleDateFormat(format).format(new Date()));
            thread.implement();
            System.out.println("Thread " + this.getName() + " end : " + new SimpleDateFormat(format).format(new Date()));
        }
    }
}

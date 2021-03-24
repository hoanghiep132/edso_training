package edso.hiepnh.entites;

import javafx.scene.control.TextArea;

import java.util.ArrayList;
import java.util.List;

public class DisplayThread extends Thread{

    private TextArea viewArea;

    private List<Result> resultList;

    private boolean addListener;

    private void init(){
        resultList = new ArrayList<>();
        addListener = false;
    }

    public void setViewArea(TextArea viewArea) {
        this.viewArea = viewArea;
    }

    public DisplayThread() {
        init();
    }

    public void addResult(Result rs){
        synchronized (this){
            notify();
        }
        resultList.add(rs);
        addListener = true;
    }

    private Result getLastItem(){
        return resultList.get(resultList.size()-1);
    }

    @Override
    public void run(){
        while (true){
            if(addListener){
                viewArea.appendText(getLastItem().toString() + "\n");
                this.addListener = false;
            }else {
                try {
                    synchronized (this){
                        wait();
                    }
//                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

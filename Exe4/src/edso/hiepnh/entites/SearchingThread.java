package edso.hiepnh.entites;

import java.util.ArrayList;
import java.util.List;

public class SearchingThread extends Thread{

    private List<List<Integer>> listSearchInput;

    private MyArray arraySorted;

    private DisplayThread displayThread;

    public SearchingThread() {
        listSearchInput = new ArrayList<>();
    }

    public void addDisplayThread(DisplayThread displayThread){
        this.displayThread = displayThread;
    }

    public synchronized void addSearchInput(List<Integer> searchInput){
        listSearchInput.add(searchInput);
        this.notify();
    }

    public synchronized void addSearch(MyArray arraySorted){
        this.arraySorted = arraySorted;
        this.notify();
    }

    private void startSearch(List<Integer> searchInput){
        for(int i = 0 ; i < arraySorted.getLength(); i++){
            if(searchInput.contains(arraySorted.get(i))){
                displayThread.addResult(new Result(i,arraySorted.get(i)));
            }
        }
        synchronized (this) {
            this.notify();
        }
    }

    @Override
    public void run() {
        while(true){
            if(!listSearchInput.isEmpty() && arraySorted != null){
                List<Integer> listInput = listSearchInput.remove(0);
                startSearch(listInput);
            }else {
                try {
                    synchronized (this){
                        wait();
                    }
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

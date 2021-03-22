package edso.hiepnh.entities.thread;

import edso.hiepnh.entities.ListResult;
import javafx.scene.control.TextArea;

public class DisplayThread implements IThreadArray{

    private ListResult listResult;

    private TextArea view;

    public DisplayThread(ListResult listResult, TextArea view) {
        this.listResult = listResult;
        this.view = view;
    }

    @Override
    public void implement() {
        System.out.println("Display is Running....");
        while (true) {
            if (!listResult.isEndThread()) {
                try {
                    System.out.println("display wait");
                    synchronized (listResult) {
                        listResult.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("Start display thread");

            if (listResult.isCheckAddListener()) {
                view.appendText(listResult.getLastItem().toString() + "\n");
                listResult.setCheckAddListener(false);
                System.out.println(listResult.getLastItem().toString());
            }
        }
    }
}

package edso.hiepnh.entities.thread;

import edso.hiepnh.entities.ListResult;

public class DisplayResult implements IThreadArray{

    private ListResult listResult;

    public DisplayResult(ListResult listResult) {
        this.listResult = listResult;
    }

    @Override
    public synchronized void implement() {
//        try {
//            wait();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        for(int i = 0; i < listResult.getLength(); i++){
            System.out.println(listResult.get(i).toString());
        }
    }
}

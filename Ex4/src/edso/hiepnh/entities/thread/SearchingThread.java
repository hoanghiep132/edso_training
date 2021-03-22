package edso.hiepnh.entities.thread;

import edso.hiepnh.entities.ArrayHandler;
import edso.hiepnh.entities.ListResult;

import java.util.List;

public class SearchingThread implements IThreadArray{

    private ArrayHandler arrayHandler;

    private ListResult listResult;

    private List<Integer> searchList;

    public SearchingThread(ArrayHandler arrayHandler, List<Integer> searchList, ListResult listResult) {
        this.arrayHandler = arrayHandler;
        this.listResult = listResult;
        this.searchList = searchList;
    }

    @Override
    public void implement() {
        while(true) {
            arrayHandler.searching(searchList, listResult);
        }
    }
}

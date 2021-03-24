package edso.hiepnh.entites.thread;

import java.util.List;

public class Searching implements IMyThread{

    private List<List<Integer>> listSearchInput;


    public void addSearchInput(List<Integer> searchInput){
        listSearchInput.add(searchInput);
    }

    private void startSearch(List<Integer> searchInput){

    }

    @Override
    public void implement() {

    }
}

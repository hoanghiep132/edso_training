package edso.hiepnh.entities;

import java.util.ArrayList;
import java.util.List;

public class ListResult {

    private List<Result> resultList;

    public ListResult() {
        resultList = new ArrayList<>();
    }


    public void add(Result result){
        resultList.add(result);
    }

    public Result get(int index){
        return resultList.get(index);
    }

    public int getLength(){
        return resultList.size();
    }
}

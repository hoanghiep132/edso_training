package edso.hiepnh.entities;

import java.util.List;
import java.util.stream.Collector;

public class Result {

    private int index;
    private int value;

    public Result() {
    }

    public Result(int index, int value) {
        this.index = index;
        this.value = value;
    }

    @Override
    public String toString() {
        return "{" +
                "index=" + index +
                ", value=" + value +
                '}';
    }

}

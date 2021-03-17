package edso.hiepnh.entities;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MyArray {

    private int[] array;

    private int length;

    public int[] getArray() {
        return array;
    }

    public void setArray(int[] array) {
        this.array = array;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public MyArray() {
    }

    public MyArray(int[] array, int length) {
        this.array = array;
        this.length = length;
    }

    public boolean checkOrdered(){
        for (int i = 0; i < length; i++){
            if(array[i] != i + 1){
                return false;
            }
        }
        return true;
    }

    public synchronized List<Result> search(List<Integer> list){
        return IntStream.range(0, array.length).filter(i -> list.contains(array[i]))
                .mapToObj(i -> new Result(i,array[i]))
                .collect(Collectors.toList());
    }
}

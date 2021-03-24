package edso.hiepnh.entities;


import java.util.List;


public class MyArray {

    private int[] array;

    private int length;

    private ListResult listResult;


    public int[] getArray() {
        return array;
    }

    public void setArray(int[] array) {
        this.array = array;
    }

    public  int getLength() {
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

    public ListResult getListResult() {
        return listResult;
    }

    public void setListResult(ListResult listResult) {
        this.listResult = listResult;
    }


    public synchronized void search(List<Integer> searchList){
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Start Searching");

    }

    public synchronized void displayResult(){
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for(int i = 0; i < listResult.getResults().size(); i++){
            System.out.println(listResult.get(i).toString());
        }
    }


}

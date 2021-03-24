package edso.hiepnh.entites;


import java.util.List;

public class MyArray {

    private int[] array;

    private int length;



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



    public int get(int index){
        return array[index];
    }


    public void print(){
        System.out.print(array[0]);
        for (int i = 1 ; i < length; i++){
            if(i%10 == 0){
                System.out.print("\n" + array[i]);
            }else {
                System.out.print(" " + array[i]);
            }
        }
    }

}

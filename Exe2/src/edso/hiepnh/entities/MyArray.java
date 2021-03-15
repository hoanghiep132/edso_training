package edso.hiepnh.entities;

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

    public MyArray(MyArray another) {
        this.array = another.getArray();
        this.length = another.getLength();
    }

    public void printArray(){
        System.out.print(array[0] + " ");
        for (int i = 1; i < length;i++){
            if(i%10==0){
                System.out.println();
            }
            System.out.print(array[i] + " ");
        }
    }
}

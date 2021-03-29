import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        SparseList list = new SparseList();
        list.set(2,3);
        list.set(70,1);
        list.set(3,4);
        list.set(4,6);
        list.remove(3);
        ArrayList<Integer> list1 =list.get(1);
        System.out.println(list1);
        System.out.println(list);
        list.sort_nonempty();
        System.out.println(list);
    }
}

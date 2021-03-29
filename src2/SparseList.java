import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class SparseList {
    LinkedList<Element> list;

    public SparseList() {
        this.list = new LinkedList<>();
    }
    public void set(int value,int index){
        if(value==0){
            throw new IllegalArgumentException("Can't save zero");
        }
        Element element=new Element(value);
        while(list.size()<=index){
            list.addLast(new Element(0));
        }
        list.set(index,element);
        countIndex();
    }
    private void countIndex(){
        int count=0;
        boolean begin = false;
        boolean start =true;
        ListIterator<Element> iterator = list.listIterator();
        Element element=null;
        while(iterator.hasNext()){
            count++;
            Element i=iterator.next();
            if(i.getValue()!=0 & start){
                start=false;
                i.setIndex(count);
            }
            else if(i.getValue()!=0 & !start & !begin){
                begin=true;
                element=i;
                count=0;
            } else if(i.getValue()!=0 & !start & begin){
                element.setIndex(count);
                element=i;
                count=0;
            }
        }
        if(begin){
            element.setIndex(count);
        }
    }
    public ArrayList<Integer> get(int index){
        ArrayList<Integer> array = new ArrayList<>();
        if(list.get(index).getValue()!=0){
            array.add(list.get(index).getValue());
            array.add(list.get(index).getIndex());
            return array;
        }
        else{
            throw new IndexOutOfBoundsException("Invalid Index");
        }
    }
    public void remove(int index){
        if(list.get(index).getValue()!=0){
            list.remove(index);
            countIndex();
        }
        else {
            throw new IndexOutOfBoundsException("Invalid Index");
        }
    }
    public void sort_nonempty(){
        ListIterator<Element> iterator1 = list.listIterator();
        while(iterator1.hasNext()){
            iterator1.next();
            Element i=null;
            ListIterator<Element> iterator2 = list.listIterator();
            while (iterator2.hasNext()){
                Element j = iterator2.next();
                if(i==null){
                    i=j;
                    continue;
                }
                if(i.getValue()> j.getValue()){
                    int temp = i.getValue();
                    i.setValue(j.getValue());
                    j.setValue(temp);
                } else if(i.getValue()<=j.getValue()){
                    i=j;
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        for(Element i: list){
            if(i.getValue()!=0){
                sb.append(i.getValue());
                sb.append(", ");
            }
        }
        sb.delete(sb.length()-2,sb.length());
        sb.append("]");
        return sb.toString();
    }
}

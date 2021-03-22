import java.util.ArrayList;

public class SparseList {
    ArrayList<Element> list;

    public SparseList() {
        list = new ArrayList<>();
    }

    public void set(int element, int index) {
        Element elem = new Element(element);
        while (list.size() <= index) {
            list.add(new Element(0));
        }
        boolean beggin = false;
        list.set(index, elem);
        for (int i = index; i >= 0; i--) {
            if (list.get(i).getValue() != 0 & i > 0) {
                list.get(i).setIndex(index - i);
            }
            if (i == 0) {
                elem.setIndex(index);
                beggin = true;
            }
        }
        boolean first = true;
        for (int i = index; i < list.size(); i++) {
            if (list.get(i).getValue() != 0 & !beggin) {
                elem.setIndex(i - index);
                break;
            }
            if (list.get(i).getValue() != 0 & beggin & first) {
                for (int p = i + 1; p < list.size(); p++) {
                    if (list.get(p).getValue() != 0) {
                        list.get(i).setIndex(p - i);
                        first = false;
                    }
                }
                if (first) {
                    list.get(i).setIndex(list.size() - 1 - i);
                    first = false;
                }
                break;
            }
            if (i == list.size() - 1 & !beggin) {
                elem.setIndex(i - index);
            }
        }

    }

    public int[] get(int index) {
        if (list.get(index).getValue() != 0) {
            int[] out = new int[2];
            out[0] = list.get(index).getValue();
            out[1] = list.get(index).getIndex();
            return out;
        }
        return new int[0];
    }

    public void remove(int index) {
        if (list.get(index).getValue() != 0) {
            list.get(index).setValue(0);
        } else {
            throw new IndexOutOfBoundsException("Немає такого елементу");
        }
    }

    public void sortNonEmpty() {
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).getValue() != 0) {
                Element curr = list.get(i);
                int ind = i;
                for (int j = i - 1; j >= 0; j--) {
                    if (list.get(j).getValue() > list.get(i).getValue() & list.get(j).getValue() != 0) {
                        list.get(j + 1).setValue(list.get(j).getValue());
                        ind = j;
                    }
                }
                list.get(ind).setValue(curr.getValue());
            }
        }
    }
}
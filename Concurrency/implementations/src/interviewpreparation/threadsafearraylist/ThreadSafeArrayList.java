package interviewpreparation.threadsafearraylist;

import java.util.ArrayList;

public class ThreadSafeArrayList<T> {

    ArrayList<T> arrayList = new ArrayList<>();

    Boolean add(T element){
        synchronized (arrayList){
           return arrayList.add(element);
        }
    }

    T get(int index){
        return arrayList.get(index);
    }

}

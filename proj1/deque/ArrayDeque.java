package deque;

import java.util.Iterator;

public class ArrayDeque <T> implements Deque<T>{
   public T[] array;
   public  int size;
   int prev;
   int next;
   public ArrayDeque(){
       array =(T[]) new Object[8]; // 向下转型
       prev = 8  / 2  ;
       next = prev +1;
   }
    public void addFirst(T item) {
        checkSize();
       size +=1;
       array[prev] = item;
        if(prev == 0){
            prev = array.length -1;
        }else{
            prev -=1;
        }
    }
    public void addLast(T item){
       checkSize();
       size +=1;
       array[next] = item;
       if (next == array.length -1 ){
           next = 0;
       }else{
           next +=1 ;
       }

    }
    public T removeFirst(){
       if (size == 0){
           return null;
       }
        int po = prev + 1 ;
       if (po >= array.length){
          po = 0;
       }
        T item = array[po];
       array[po] = null;
        prev = po  ;
        size -=1 ;

        return item;
    }
    public T removeLast(){
       if (size ==0){
           return null;
       }
       T item = array[next -1 ];
        next -=1;
        size -=1;
        return item;
    }

    @Override
    public T get(int index) {
        if (prev + 1 + index > (array.length -1) ){
            return array[(index - (array.length -1 - prev)) ];
        }      else{
           return array[prev + 1 + index];
        }
    }

    public void checkSize (){
       if (array.length == size){
           resize(array.length * 2 );
       }
    }
    public void resize(int s ){
       // copy
        T[] a =(T[]) new Object[s]; // 向下转型
        // des   :   size - prev
        //
        System.arraycopy(array, next, a, s- (size - next), size - next);
        System.arraycopy(array, 0 , a, 0,next);
        prev = next + (s - size) - 1;
        array = a;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
       if (size == 0){
           return true;
       }
       return false;
    }

    public void printDeque() {
       int p = prev;
        for (int i = 0 ; i < size ; i ++){
            System.out.print(array[p]);
            System.out.print(" ");
            prev +=1;
        }
    }

    private class ArraySetIterator implements Iterator<T> {
        private int wizPos;

        public ArraySetIterator() {
            wizPos = 0;
        }

        public boolean hasNext() {
            return wizPos < size;
        }

        public T next() {
            T item = get(wizPos);
            wizPos += 1;
            return item;
        }
    }
    public Iterator<T> iterator(){
        return new ArraySetIterator();
    }
    public boolean equals(Object other){
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (other.getClass() != this.getClass()) {
            return false;
        }
        ArrayDeque<T> o = (ArrayDeque<T>) other;
        if (o.size() != this.size()) {
            return false;
        }
        for (T item : this) {
            for (T item1 : o) {
                if (item1 != item){
                    return false;
                }
            }
        }
        return true;
    }
}

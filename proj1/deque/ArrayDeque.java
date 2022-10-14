package deque;

import java.util.Iterator;

public class ArrayDeque <T> implements Deque<T>, Iterable<T> {
   public T[] array;
   public  int size;
   public int first;
   public ArrayDeque(){
       array =(T[]) new Object[8]; // 向下转型
       first= 8  / 2  ;
   }
    public void addFirst(T item) {
        array[first] = item;
        first --;
        size +=1;
        if (first < 0){
            resize(array.length * 2 );
        }
    }
    public void addLast(T item){
        int lastIndex = first + size + 1 ;
        array[lastIndex] = item;

        size += 1 ;
        if (lastIndex == array.length - 1 ){
            resize(array.length * 2 );
        }

    }
    public T removeFirst(){
       if (size == 0){
           return null;
       }
        size -=1 ;
       first ++;
       T item = array[first] ;
       array[first] = null;
        if (size < array.length / 4 ){
            resize(array.length / 2 );
        }
       return item;

    }
    public T removeLast(){
       if (size ==0){
           return null;
       }
        T item = array[first + size  ] ;
        size -=1 ;
        if (size < array.length / 4 ){
            resize(array.length / 2 );
        }
        return item;
    }

    @Override
    public T get(int index) {
       return array[first + index + 1 ];
    }


    public void resize(int s ){
       // copy
        T[] a =(T[]) new Object[s]; // 向下转型
        // des   :   size - prev
        //
        int start = (s / 2 )  - (size() /  2 );
        System.arraycopy(array,first + 1,a,start, size());
        array = a;
        first = start - 1 ;


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
           for (int i = 0 ; i < size ; i ++){
               System.out.print(get(i));
           }

            System.out.println();
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
        for (int i = 0 ; i < this.size() ; i++){
            if (o.get(i) != this.get(i)){
                return false ;
            }
        }

        return true;
    }
}

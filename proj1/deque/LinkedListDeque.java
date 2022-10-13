package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>  {
    private IntNode sentinel;
    private int size;
    public class IntNode {
        public IntNode prev;
        public T item;
        public IntNode next;
        public IntNode(T item){
            this.item = item;
        }
        public IntNode(){
            item = null;
            next = null;
            prev = null;
        }
    }
    public LinkedListDeque(){
        IntNode node = new IntNode();
        sentinel = node;
         sentinel.prev = node;
        sentinel.next = node;
        size = 0;
    }
    public void addFirst(T item) {
        size +=1;
        IntNode node = new IntNode(item);
        sentinel.next.prev = node;
        node.next = sentinel.next;
        node.prev = sentinel;
        sentinel.next = node;

    }
    public void addLast(T item){
        size +=1;
        IntNode node = new IntNode(item);
        node.prev  = sentinel.prev;
        sentinel.prev.next = node;
        sentinel.prev = node;
        node.next = sentinel;

    }
    public boolean isEmpty(){
        if (size == 0){
            return true;
        }else{
            return false;
        }
    }
    public int size(){
        return size ;
    }
    public void printDeque(){
        IntNode node = sentinel.next;
       for (int i = 0 ; i < size ; i ++){
           System.out.print(node.item);
           System.out.print(" ");
           node = node.next;
       }
    }
    public T removeFirst(){
        if(size == 0){
            return null;
        }
        size -=1;
        IntNode node = sentinel.next;
        node.prev = null;
        sentinel.next = node.next;
        sentinel.next.prev = sentinel;

        return node.item;
    }
    public T removeLast(){
        if (size == 0){
            return  null;
        }
        IntNode last = sentinel.prev;
        last.prev.next = sentinel;
        sentinel.prev = last.prev;
        last.prev = null;
        size -=1;
        return last.item;
    }
    public T get(int index){
        IntNode node = sentinel.next;
        for(int i = 0 ; i < index;  i ++){
            node = node.next;
        }
        return  node.item;
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
        LinkedListDeque<T> o = (LinkedListDeque<T>) other;
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
    public T r (IntNode node , int index){

       if (index == 0) {
           return node.item;
       }
       return r(node.next,index-1);
    }
    public T getRecursive(int index){
        IntNode node = sentinel.next;

        return r(node,index) ;
    }
}

// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Benjamin Altermat, benaltermatt@vt.edu, 906347458
package prj5;

import java.util.Iterator;
import java.util.Comparator;

public class DoublyLinkedList<E> implements Iterable<E>{
    
    private Node<E> firstNode;
    private Node<E> lastNode;
    private int size;
    
    public DoublyLinkedList() {
        
    }
    
    public boolean isEmpty() {
        return false;
    }
    
    public int size() {
        return -1;
    }
    
    public void clear() {
        
    }
    
    public boolean contains(E element) {
        return false;
    }
    
    public E get(int index) {
        return null;
    }
    
    public void add(E newElement) {
        
    }
    
    public void add(E newElement, int index) {
        
    }
    
    public Node<E> getNodeAtIndex(int index) {
        return null;
    }
    
    public boolean remove(int index) {
        return false;
    }
    
    public void sort(Comparator<E> comparer) {
        
    }
    
    public E[] toArray() {
        return null;
    }
    
    public DLLIterator iterator() {
        return null;
    }
    
    public boolean equals(Object other) {
        return false;
    }

    private static class Node<E> {
        private E data;
        private Node<E> next;
        private Node<E> previous;
        
        public Node(E myData) {
            
        }
        
        public Node(E mayData, Node<E> starter, Node<E> end) {
            
        }
        
        public void setNextNode(Node<E> newNext) {
            
        }
        
        public void setPreviouNode(Node<E> newPrevious) {
            
        }
        
        public Node<E> getNext() {
            return null;
        }
        
        public Node<E> getPrevious() {
            return null;
        }
        
        public boolean equals(Object other) {
            return false;
        }
        
        
    }
    
    private class DLLIterator<A> implements Iterator<E> {
        private Node<E> prev;
        private boolean calledNext;
        
        public DLLIterator() {
            
        }
        
        public boolean hasNext() {
            return false;
        }
        
        public E next() {
            return null;
        }
        
        public void remove() {
            
        }
        
    }
}

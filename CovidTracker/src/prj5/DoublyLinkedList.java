// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Benjamin Altermat, benaltermatt@vt.edu, 906347458
package prj5;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Comparator;

public class DoublyLinkedList<E> implements Iterable<E>{
    
    private Node<E> firstNode;
    private Node<E> lastNode;
    private int size;
    
    public DoublyLinkedList() {
        firstNode = null;
        lastNode = null;
        size = 0;
    }
    
    public boolean isEmpty() {
        return (size == 0);
    }
    
    public int size() {
        return size;
    }
    
    public void clear() {
        firstNode = null;
        lastNode = null;
        size = 0;
    }
    
    public boolean contains(E element) {
        Node<E> current = firstNode;
        for (int i = 0; i < size; i ++) {
            if (current.getData() == element) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }
    
    /**
     * returns the data at index
     * @param index index of the node being searched for
     * @return the data at the index
     */
    public E get(int index) {
        if (size <= index) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> current = firstNode;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.getData();
    }
    
    public void add(E newElement) {
        Node<E> newNode = new Node<E>(newElement);
        if (size == 0) {
            firstNode = newNode;
            lastNode = newNode;
        }
        else {
            lastNode.setNextNode(newNode);
            newNode.setPreviousNode(lastNode);
            lastNode = newNode;
        }
        size++;
    }
    
    public void add(E newElement, int index) {
        Node<E> newNode = new Node<E>(newElement);
        Node<E> current = firstNode;
        if (index < 0 || size < index) {
            throw new IndexOutOfBoundsException();
        }
        if (size == 0) {
            firstNode = newNode;
            lastNode = newNode;
        }
        if (index == 0) {
            newNode.setNextNode(firstNode);
            firstNode.setPreviousNode(newNode);
            firstNode = newNode;
            size++;
        }
        else if (index == size) {
            newNode.setPreviousNode(lastNode);
            lastNode.setNextNode(newNode);
            lastNode = newNode;
            size++;
        }
        else {
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
            current.getPrevious().setNextNode(newNode);
            newNode.setPreviousNode(current.getPrevious());
            newNode.setNextNode(current);
            current.setPreviousNode(newNode);
            size++;
        }
    }
    
    private Node<E> getNodeAtIndex(int index) {
        if (index < 0 || size <= index) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> current = firstNode;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current;
    }
    
    public boolean remove(int index) {
        Node<E> current = firstNode;
        if (index < 0 || size < index) {
            throw new IndexOutOfBoundsException();
        }
        if (size == 0) {
            throw new IllegalArgumentException();
        }
        if (index == 0) {
            firstNode = firstNode.getNext();
            firstNode.setPreviousNode(null);
            size--;
            return true;
        }
        else if (index == size - 1) {
            lastNode = lastNode.getPrevious();
            lastNode.setNextNode(null);
            size--;
            return true;
        }
        else {
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
            current.getPrevious().setNextNode(current.getNext());
            current.getNext().setPreviousNode(current.getPrevious());
            size--;
            return true;
        }
    }
    
    public void sort(Comparator<E> comparer) {
        
    }
    
    public E[] toArray() {
        Node<E> current = firstNode;
        E[] array = (E[]) new Object[size];
        for (int i = 0; i < size; i++) {
            array[i] = current.getData();
            current = current.getNext();
        }
        return array;
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
            data = myData;
        }
        
        public Node(E myData, Node<E> starter, Node<E> end) {
            data = myData;
            previous = starter;
            next = end;
        }
        
        public void setNextNode(Node<E> newNext) {
            next = newNext;
        }
        
        public void setPreviousNode(Node<E> newPrevious) {
            previous = newPrevious;
        }
        
        public Node<E> getNext() {
            return next;
        }
        
        public Node<E> getPrevious() {
            return previous;
        }   
        
        public E getData() {
            return data;
        }
        
    }
    
    private class DLLIterator<A> implements Iterator<E> {
        private Node<E> next;
        private boolean calledNext;
        
        public DLLIterator() {
            next = firstNode;
            calledNext = false;
        }
        
        public boolean hasNext() {
            return (size > 0 && next.getNext() != null);
        }
        
        public E next() {
            if (this.hasNext()) {
                E data = next.getData();
                next = next.getNext();
                calledNext = true;
                return data;
            }
            throw new NoSuchElementException();
        }
        
        public void remove() {
            if (!calledNext) {
                throw new IllegalStateException("call next first");
            }
            Node <E> temp = firstNode;
            if (next.getPrevious().getPrevious() != null) {
                next.getPrevious().getPrevious().setNextNode(next);
                next.setPreviousNode(next.getPrevious().getPrevious());
                size--;
                calledNext = false;
            }
            else {
                next.setPreviousNode(null);
                firstNode = next;
                size--;
                calledNext = false;
            }
        }
        
    }
}

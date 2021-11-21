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

/**
 * This class will represent a doubly-linked-list
 * with sentinel nodes.
 * 
 * @author Benjamin Altermatt
 * @version 2021.11.20
 * @param <E>
 *            Type of all elements in list
 */
public class DoublyLinkedList<E> implements Iterable<E> {

    private Node<E> firstNode;
    private Node<E> lastNode;
    private int size;

    /**
     * Initializes an empty list
     */
    public DoublyLinkedList() {
        firstNode = new Node<E>(null);
        lastNode = new Node<E>(null, firstNode, null);
        firstNode.setNext(lastNode);

        size = 0;
    }


    /**
     * Checks whether this doubly linked list
     * is empty
     * 
     * @return
     *         Whether empty
     */
    public boolean isEmpty() {
        return size == 0;
    }


    /**
     * Checks the number of elements in the list
     * 
     * @return
     *         size
     */
    public int size() {
        return size;
    }


    /**
     * Empties the list
     */
    public void clear() {
        firstNode.setNext(lastNode);
        lastNode.setPrevious(firstNode);
        size = 0;
    }


    /**
     * Checks whethet the passed element is contained within
     * the list
     * 
     * @param element
     *            Element we are looking for
     * @return
     *         Element was found in list
     */
    public boolean contains(E element) {
        Node<E> currentNode = firstNode;
        while (currentNode.getNext() != lastNode) {
            currentNode = currentNode.getNext();
            if (currentNode.getData().equals(element)) {
                return true;
            }
        }

        return false;
    }


    /**
     * Retieves the element stored at the passed index
     * within the list.
     * 
     * @param index
     *            The index we are looking for the element of
     * @throws IndexOutOfBoundsException
     *             This index isnt within the bounds of the list
     * @return
     *         The element stored at passed index
     */
    public E get(int index) {
        // handle out of bounds
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node<E> currentNode = firstNode.getNext();
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.getNext();
        }

        return currentNode.getData();
    }


    /**
     * Adds the passed element to the list as
     * the last element.
     * 
     * @param newElement
     *            The element to be added
     */
    public void add(E newElement) {
        // handle null
        if (newElement == null) {
            return;
        }

        Node<E> newNode = new Node<E>(newElement, lastNode.getPrevious(),
            lastNode);

        newNode.getPrevious().setNext(newNode);
        newNode.getNext().setPrevious(newNode);
        size++;

    }


    /**
     * Adds the passed element to the list at
     * the specified index
     * 
     * @param newElement
     *            The element we wish to add to the list
     * @throws IndexOutOfBoundsException
     *             The specified index would not fit within the list
     * @param index
     *            The index at which we want to put the new element
     */
    public void add(E newElement, int index) {
        // handle null
        if (newElement == null) {
            return;
        }

        // handle out of bounds
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        Node<E> currentNode = firstNode;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.getNext();
        }

        Node<E> newNode = new Node<E>(newElement, currentNode, currentNode
            .getNext());
        currentNode.setNext(newNode);
        newNode.getNext().setPrevious(newNode);
        size++;

    }


    /**
     * Remove's the element at the specified index from
     * the list.
     * 
     * @param index
     *            The index of the element we wish to remove
     * @throws IndexOutOfBoundsException
     *             The specified index is not within the list
     * @return
     *         Whether removal was successful
     */
    public boolean remove(int index) {
        // handle illegal index
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node<E> currentNode = firstNode.getNext();
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.getNext();
        }

        currentNode.getPrevious().setNext(currentNode.getNext());
        currentNode.getNext().setPrevious(currentNode.getPrevious());
        size--;

        return true;
    }


    /**
     * Sorts the elements of the list according
     * to the specified comparator.
     * 
     * @param comparer
     *            The comparator used to make comparisons
     */
    public void sort(Comparator<E> comparer) {
        // handle short
        if (size < 2) {
            // effectively already sorted
            return;
        }

        Node<E> lastSorted = firstNode.getNext(); // this is the last node of
                                                  // the sorted half
        Node<E> firstUnsorted = lastSorted.getNext(); // this is the first node
                                                      // of the unsorted half

        // until there is nothing left to sort
        while (firstUnsorted != lastNode) {
            // until there we find the location to put the unsorted node
            Node<E> currentNode = lastSorted;

            // if(comparer.compare(firstUnsorted.getData(),
            // currentNode.getData()) >= 0)
            while (currentNode != firstNode && comparer.compare(firstUnsorted
                .getData(), currentNode.getData()) < 0) {
                currentNode = currentNode.getPrevious();
            }

            // lets save our position
            Node<E> nextFirstUnsorted = firstUnsorted.getNext();

            // lets rip out the node we want to move
            lastSorted.setNext(firstUnsorted.getNext());
            firstUnsorted.getNext().setPrevious(lastSorted);

            // lets insert between the current node and the node after it
            firstUnsorted.setNext(currentNode.getNext());
            firstUnsorted.setPrevious(currentNode);
            currentNode.setNext(firstUnsorted);
            firstUnsorted.getNext().setPrevious(firstUnsorted);

            // lets move to the next node
            firstUnsorted = nextFirstUnsorted;
            lastSorted = firstUnsorted.getPrevious();
        }

    }


    /**
     * An array representation of the list.
     * 
     * @return
     *         Array of list elements
     */
    public Object[] toArray() {
        // make the array
        Object[] array = new Object[size];

        // put all the references in the array
        int index = 0;
        Node<E> currentNode = firstNode.getNext();
        while (currentNode != lastNode) {
            array[index] = currentNode.getData();
            currentNode = currentNode.getNext();
            index++;
        }

        return array;
    }


    /**
     * An iterator that can be used to iterate
     * across and manipulate the list.
     * 
     * @return
     *         This lists iterator
     */
    @Override
    public DLLIterator<E> iterator() {
        return new DLLIterator<E>();
    }


    /**
     * Checks whether the passed object is equal in value to this
     * list
     * 
     * @return
     *         Equal or not
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object other) {
        // handle null
        if (other == null) {
            return false;
        }

        // handle the exact same object
        if (other == this) {
            return true;
        }

        // handle different type
        if (!(other instanceof DoublyLinkedList)) {
            return false;
        }

        DoublyLinkedList<E> otherList = (DoublyLinkedList<E>)other;

        // handle same type dif size
        if (size != otherList.size()) {
            return false;
        }

        // handle same type, size
        Object[] myArray = toArray();
        Object[] otherArray = otherList.toArray();
        for (int i = 0; i < myArray.length; i++) {
            if (!myArray[i].equals(otherArray[i])) {
                return false;
            }
        }

        return true;

    }

    /**
     * This is the class used to define the nodes
     * from which the list is built.
     * 
     * @author Benjamin Altermatt
     * @version 2021.11.20
     * @param <E>
     *            data type stored in node
     */
    private static class Node<E> {
        private E data;
        private Node<E> next;
        private Node<E> previous;

        /**
         * Creates a node with passed data
         * 
         * @param myData
         *            The data passed to be stored
         */
        public Node(E myData) {
            data = myData;
            next = null;
            previous = null;
        }


        /**
         * Creates a node with passed data and pointers
         * to previous and next.
         * 
         * @param myData
         *            Passed data
         * @param starter
         *            previousNode
         * @param end
         *            nextNode
         */
        public Node(E myData, Node<E> starter, Node<E> end) {
            data = myData;
            previous = starter;
            next = end;
        }


        /**
         * Sets the next node pointer to
         * the passed value
         * 
         * @param newNext
         *            The new next pointer
         */
        public void setNext(Node<E> newNext) {
            next = newNext;
        }


        /**
         * Sets the previous next node pointer
         * to the passed value
         * 
         * @param newPrevious
         *            The new previous node
         */
        public void setPrevious(Node<E> newPrevious) {
            previous = newPrevious;
        }


        /**
         * Accesses the data stored inside
         * 
         * @return
         *         data
         */
        public E getData() {
            return data;
        }


        /**
         * Sets the data to the passed new data
         * 
         * @param newData
         *            new data of the node
         */
        public void setData(E newData) {
            data = newData;
        }


        /**
         * Returns the reference to the next node
         * 
         * @return
         *         next
         */
        public Node<E> getNext() {
            return next;
        }


        /**
         * Returns the reference to the previous node
         * 
         * @return
         *         previous
         */
        public Node<E> getPrevious() {
            return previous;
        }

    }


    /**
     * This class exists to make the DoublyLinkedList
     * class iterable
     * 
     * @author Benjamin Altermatt
     * @version 2021.11.20
     * @param <A>
     *            Type of element the iterator deals with
     */
    private class DLLIterator<A> implements Iterator<E> {
        private Node<E> prev;
        private boolean calledNext;

        /**
         * Builds an iterator for the list
         */
        public DLLIterator() {
            prev = firstNode;
            calledNext = false;
        }


        /**
         * Checks whether there is another element unaccessed
         * by the iterator
         * 
         * @return
         *         More untouched elements exist
         */
        public boolean hasNext() {
            return prev.getNext() != lastNode;
        }


        /**
         * Returns the value stored in the next entry in the list
         * 
         * @throws NoSuchElementException
         *             next is called when hasNext is false
         * @return
         *         Next value
         */
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            calledNext = true;
            prev = prev.getNext();
            return prev.getData();
        }


        /**
         * Removes the last accessed value.
         * 
         * @throws IllegalStateException
         *             next hasnt been called yet (or since last removal)
         */
        public void remove() {
            if (!calledNext) {
                throw new IllegalStateException();
            }

            prev.getPrevious().setNext(prev.getNext());
            prev.getNext().setPrevious(prev.getPrevious());
            size--;
            calledNext = false;
        }

    }
}

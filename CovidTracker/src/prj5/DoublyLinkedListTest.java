// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Benjamin Altermat, benaltermatt@vt.edu, 906347458
package prj5;

import student.TestCase;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The entire purpose of this class is to
 * properly test the DoublyLinkedList class
 * 
 * @author Benjamin Altermatt
 * @version 2021.11.20
 */
public class DoublyLinkedListTest extends TestCase {

    private DoublyLinkedList<String> list;
    private DoublyLinkedList<String> empty;

    /**
     * Builds a precondition before each
     * test is run.
     */
    public void setUp() {
        list = new DoublyLinkedList<String>();
        for (int i = 0; i < 10; i++) {
            list.add("Monke" + i);
        }

        empty = new DoublyLinkedList<String>();
    }


    /**
     * Tests the isEmpty method of the
     * DoublyLinkedList class.
     */
    public void testIsEmpty() {
        // check for regular
        assertFalse(list.isEmpty());

        // check for empty
        assertTrue(empty.isEmpty());
    }


    /**
     * Tests the size method of the
     * DoublyLinkedList class.
     */
    public void testSize() {
        // check for regular
        assertEquals(list.size(), 10);

        // check for empty
        assertEquals(empty.size(), 0);
    }


    /**
     * Tests the clear method of the
     * DoublyLinkedList class.
     */
    public void testClear() {
        // check for regular
        list.clear();
        assertEquals(list.size(), 0);
        assertTrue(list.isEmpty());

        // check for not regular
        int size = empty.size();
        empty.clear();
        assertEquals(size, empty.size());
    }


    /**
     * Tests the contains method of the
     * DoublyLinkedList class.
     */
    public void testContains() {
        // check for element that doesnt exist
        assertFalse(list.contains("Monkey"));
        // check for element that exists
        assertTrue(list.contains("Monke3"));

    }


    /**
     * Tests the get method of the
     * DoublyLinkedList class.
     */
    public void testGet() {
        // check for index out of bounds
        Exception e = null;
        try {
            list.get(-1);
            fail("This should have thrown an IndexOutOfBoundsException");
        }
        catch (Exception exception) {
            e = exception;
        }

        assertTrue(e instanceof IndexOutOfBoundsException);

        e = null;
        try {
            assertNull(empty.get(0));
            fail("This should have thrown an IndexOutOfBoundsException");
        }
        catch (Exception exception) {
            e = exception;
        }

        assertTrue(e instanceof IndexOutOfBoundsException);

        // check for actually in bounds
        assertEquals(list.get(2), "Monke2");
    }


    /**
     * Tests the single-parameter
     * add method of the DoublyLinkedList
     * class
     */
    public void testAddByElement() {
        // check for adding null
        empty.add(null);
        assertTrue(empty.isEmpty());

        // check for actually adding something
        empty.add("Monke");
        assertEquals(empty.size(), 1);
        assertEquals(empty.get(0), "Monke");
    }


    /**
     * Tests the two-parameter add method
     * of the DoublyLinkedList class
     */
    public void testAddByIndex() {
        // check for adding null
        empty.add(null, 0);
        assertTrue(empty.isEmpty());

        // check for adding out of bounds
        Exception e = null;
        try {
            empty.add("Monke", -1);
            fail("This should have thrown an IndexOutOfBoundsException");
        }
        catch (Exception exception) {
            e = exception;
        }

        assertTrue(e instanceof IndexOutOfBoundsException);

        e = null;
        try {
            empty.add("Monke", 1);
            fail("This should have thrown an IndexOutOfBoundsException");
        }
        catch (Exception exception) {
            e = exception;
        }

        assertTrue(e instanceof IndexOutOfBoundsException);

        // test with the actual correct use
        int size = list.size();
        list.add("Monke", 1);
        assertEquals(list.get(1), "Monke");
        assertEquals(list.get(2), "Monke1");
        assertEquals(list.size(), size + 1);

    }


    /**
     * Tests the remove method of the
     * DoublyLinkedList class.
     */
    public void testRemove() {
        // test out of bounds
        Exception e = null;
        try {
            empty.remove(-1);
            fail("This should have thrown an IndexOutOfBoundsException");
        }
        catch (Exception exception) {
            e = exception;
        }

        assertTrue(e instanceof IndexOutOfBoundsException);

        e = null;
        try {
            empty.remove(0);
            fail("This should have thrown an IndexOutOfBoundsException");
        }
        catch (Exception exception) {
            e = exception;
        }

        assertTrue(e instanceof IndexOutOfBoundsException);

        // test actually works
        assertTrue(list.remove(2));
        assertEquals(list.get(2), "Monke3");
        assertEquals(list.size(), 9);

    }


    /**
     * Tests the sort method of the
     * DoublyLinkedList class.
     */
    public void testSort() {
        // test for something short so its already sorted
        empty.sort(new AlphaComparer()); // this will literally do nothing

        DoublyLinkedList<String> sortTester = new DoublyLinkedList<String>();
        sortTester.add("Coconut");
        sortTester.add("Banana");
        sortTester.add("Jabanero");
        sortTester.add("Diablo");
        sortTester.add("Hotel");
        sortTester.add("Batman");
        sortTester.add("Apple");

        sortTester.sort(new AlphaComparer());

        assertEquals(sortTester.get(0), "Apple");
        assertEquals(sortTester.get(1), "Banana");
        assertEquals(sortTester.get(2), "Batman");
        assertEquals(sortTester.get(3), "Coconut");
        assertEquals(sortTester.get(4), "Diablo");
        assertEquals(sortTester.get(5), "Hotel");
        assertEquals(sortTester.get(6), "Jabanero");
    }


    /**
     * Tests the toArray method of the
     * DoublyLinkedList class.
     */
    public void testToArray() {
        // not empty
        Object[] array = list.toArray();

        assertEquals(array.length, 10);
        for (int i = 0; i < array.length; i++) {
            assertEquals(array[i], "Monke" + i);
        }

        // empty
        assertEquals(empty.toArray().length, 0);

    }


    /**
     * Tests the iterator method of the
     * DoublyLinkedList class.
     */
    @SuppressWarnings("unchecked")
    public void testIterator() {
        // check that special for loop works
        int numElements = 0;
        for (String myMon : list) {
            assertEquals(myMon, "Monke" + numElements);
            numElements++;
        }

        assertEquals(numElements, 10);

        // test removing
        Iterator<String> iter = (Iterator<String>)list.iterator();

        // test that the NoSuchElementException works
        Exception e = null;
        try {
            iter.remove();
            fail("This should have thrown an IllegalStateException");
        }
        catch (Exception exception) {
            e = exception;
        }

        assertTrue(e instanceof IllegalStateException);

        // check that remove works
        assertEquals(iter.next(), "Monke0");
        iter.remove();
        assertEquals(list.get(0), "Monke1");

        // check that next throws exceptions when need be
        while (iter.hasNext()) {
            iter.next();
        }

        e = null;
        try {
            iter.next();
            fail("This should have thrown a NoSuchElementException");
        }
        catch (Exception exception) {
            e = exception;
        }

        assertTrue(e instanceof NoSuchElementException);
    }


    /**
     * Tests the equals method of the
     * DoublyLinkedList class.
     */
    public void testEquals() {
        // test for null
        assertFalse(list.equals(null));
        // test for exactly the same thing
        assertEquals(list, list);

        // test for different type of object
        assertFalse(list.equals(new Object()));

        // test for same type different length
        assertFalse(list.equals(empty));

        // test for same length different value
        DoublyLinkedList<String> sameLen = new DoublyLinkedList<String>();
        for (int i = 0; i < 10; i++) {
            sameLen.add("Monke");
        }
        assertFalse(list.equals(sameLen));

        // test for same value
        DoublyLinkedList<String> same = new DoublyLinkedList<String>();
        for (int i = 0; i < 10; i++) {
            same.add("Monke" + i);
        }
        assertEquals(list, same);
    }

    /**
     * This class exists to be used as the passed
     * Comparatore in the sort tests.
     * 
     * @author Benjamin Altermatt
     * @version 2021.11.20
     */
    private class AlphaComparer implements Comparator<String> {
        /**
         * Compares strings.
         * 
         * @return
         *         The compareTo value of the strings
         */
        public int compare(String l, String r) {
            return l.compareTo(r);
        }
    }
}

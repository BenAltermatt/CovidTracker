// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Benjamin Altermat, benaltermatt@vt.edu, 906347458
package prj5;
import student.TestCase;

/**
 * tests the implemented DoublyLinkedList
 * @author David Lim (david 98)
 * @version 11.19.2021
 *
 */
public class DoublyLinkedListTest extends TestCase {
    private DoublyLinkedList<String> list;
    
    /**
     * set up for test cases
     */
    public void setUp() {
        list = new DoublyLinkedList<String>();
    }
    
    /**
     * tests isEmpty method
     */
    public void testIsEmpty() {
        assertTrue(list.isEmpty());
        list.add("a");
        assertFalse(list.isEmpty());
    }
    
    
    /**
     * tests Size method
     */
    public void testSize() {
        assertEquals(0, list.size());
        list.add("a");
        assertEquals(1, list.size());
    }
    
    /**
     * tests add method
     */
    public void testAdd() {
        list.add("a", 0);
        assertEquals(1, list.size());
        Exception e = null;
        try {
            list.add("a", 3);
        }
        catch (Exception exception){
            e = exception;
        }
        assertTrue(e instanceof IndexOutOfBoundsException);
        assertEquals(1, list.size());
        list.add("b", 0);
        assertTrue("b".equals(list.get(0)));
    }
    /**
     * tests clear method
     */
    public void testClear() {
        list.add("a");
        list.add("b");
        list.clear();
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
    }
    
    /**
     * tests contains method
     */
    public void testContains() {
        list.add("a");
        list.add("b");
        assertTrue(list.contains("b"));
        assertFalse(list.contains("c"));
    }
    
    /**
     * tests get method
     */
    public void testGet() {
        list.add("a");
        list.add("b");
        list.add("c");
        assertTrue("c".equals(list.get(2)));
        assertFalse("b".equals(list.get(2)));
        
    }
    
    /**
     * tests remove method
     */
    public void testRemove() {
        list.add("a");
        list.add("b");
        list.add("c");
        list.remove(2);
        assertEquals(2, list.size());
        list.add("d");
        list.add("e");
        list.remove(0);
        assertTrue(list.get(0).equals("b"));
        assertEquals(3, list.size());
        list.add("f");
        list.add("g");
        list.remove(2);
        assertFalse(list.contains("e"));
    }
    
    /**
     * tests sort method
     */
    public void testSort() {
        list.add("a");
        list.add("c");
        list.add("b");
        list.sort(comparator);
        assertTrue("b".equals(list.get(1)));
        
    }
    
    /**
     * tests toArray method
     */
    public void testToArray() {
        
    }
}

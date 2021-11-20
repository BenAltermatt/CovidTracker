// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Benjamin Altermat, benaltermatt@vt.edu, 906347458
package prj5;

import student.TestCase;

/**
 * The sole purpose of this class is to test the
 * CompareByAlpha Comparator
 * @author Benjamin Altermatt
 * @version 2021.11.19
 */
public class CompareByAlphaTest extends TestCase{
    
    private CompareByAlpha comparer;
    private RaceGroup groupL;
    private RaceGroup groupR;
    private RaceGroup groupLEqual;
    
    /**
     * Creates a precondition that is used for each
     * test.
     */
    public void setUp() {
        groupL = new RaceGroup("DC", "Black", 23, 10);
        groupR = new RaceGroup("DC", "White", 0, 0);
        groupLEqual = new RaceGroup("DC", "White", 23, 10);
        
        comparer = new CompareByAlpha();
        
    }
    
    /**
     * Tests the compare method of the
     * CompareByAlpha class.
     */
    public void testCompare() {
        // test for left alphabetically earlier than right
        assertTrue(comparer.compare(groupL, groupR) < 0);
        
        // test for right alphabetically earlier than left
        assertTrue(comparer.compare(groupR, groupL) > 0);
        
        // test for same
        assertEquals(comparer.compare(groupL, groupLEqual), 0);
    }
}

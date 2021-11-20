// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Benjamin Altermat, benaltermatt@vt.edu, 906347458
package prj5;

import student.TestCase;

/**
 * This class solely exists to test the
 * CompareByCFR comparator
 * 
 * @author Benjamin Altermatt
 * @version 2021.11.19
 */
public class CompareByCFRTest extends TestCase {

    private RaceGroup group;
    private RaceGroup cfrCompare;
    private RaceGroup alphaCompare;
    private RaceGroup same;
    private CompareByCFR comparer;

    /**
     * Sets up a precondition that every test starts off
     * using.
     */
    public void setUp() {
        group = new RaceGroup("DC", "White", 10, 5);
        cfrCompare = new RaceGroup("DC", "White", 10, 4);
        alphaCompare = new RaceGroup("DC", "Black", 10, 5);
        same = new RaceGroup("MI", "White", 10, 5);

        comparer = new CompareByCFR();
    }


    /**
     * Tests the compare method of the CompareByCFR class
     */
    public void testCompare() {
        // left has a greater cfr than right
        assertTrue(comparer.compare(group, cfrCompare) < 0);

        // right has a greater cfr than left
        assertTrue(comparer.compare(cfrCompare, group) > 0);

        // left and right same cfr, left alphabetically before
        assertTrue(comparer.compare(alphaCompare, group) < 0);

        // left and right same cfr, right alphabetically before
        assertTrue(comparer.compare(group, alphaCompare) > 0);

        // left and right same name and cfr
        assertEquals(comparer.compare(group, same), 0);
    }
}

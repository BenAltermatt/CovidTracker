// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Benjamin Altermat, benaltermatt@vt.edu, 906347458
package prj5;

import student.TestCase;

/**
 * The entire purpose of this class is to
 * test the State class
 * 
 * @author Benjamin Altermatt
 * @version 2021.11.19
 */
public class StateTest extends TestCase {
    private State state;
    private State strangeState;

    /**
     * Sets up a precondition that is the
     * same for every single test.
     */
    public void setUp() {
        state = new State(
            "DC,70678,179563,97118,5407,91880,1924,13365,2269,254,NA".split(
                ","), ("State,Cases_White,Cases_Black,Cases_LatinX,"
                    + "Cases_Asian,Cases_Other,Deaths_White,Deaths_Black,"
                    + "Deaths_LatinX,Deaths_Asian,Deaths_Other").split(","));
    }


    /**
     * Tests the getName method of the
     * State class
     */
    public void testGetName() {

    }


    /**
     * Tests the sortByAlpha method of the
     * State class
     */
    public void testSortByAlpha() {

    }


    /**
     * Tests the soetByCFR method of the
     * State class
     */
    public void testSortByCFR() {

    }


    /**
     * Tests the getRaceGroup method of the
     * State class
     */
    public void testGetRaceGroups() {

    }


    /**
     * Tests the equals method of the
     * State class
     */
    public void testEquals() {

    }


    /**
     * Tests the compareTo method of the
     * State class
     */
    public void testCompareTo() {

    }

}

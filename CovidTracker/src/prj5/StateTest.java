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
        // relatively realistic values
        state = new State("DC,10,10,30,40,50,5,5,7,8,NA".split(","),
            ("State,Cases_White,Cases_Black,Cases_LatinX,"
                + "Cases_Asian,Cases_Other,Deaths_White,Deaths_Black,"
                + "Deaths_LatinX,Deaths_Asian,Deaths_Other").split(","));

        // abnormal input sequence
        strangeState = new State("NA,MI,NA,12,3".split(","),
            "Cases_White,State,Deaths_White,Cases_Asian,Deaths_Asian".split(
                ","));
    }


    /**
     * Tests the getName method of the
     * State class
     */
    public void testGetName() {
        // regular state
        assertEquals(state.getName(), "DC");

        // abnormal state
        assertEquals(strangeState.getName(), "MI");
    }


    /**
     * Tests the sortByAlpha method of the
     * State class
     */
    public void testSortByAlpha() {
        // sort and then compare the tostrings
        state.sortByAlpha();

        assertEquals(state.toString(), "DC\n" + "asian: 40 cases, 20.0% CFR\n"
            + "black: 10 cases, 50.0% CFR\n" + "latinx: 30 cases, 23.3% CFR\n"
            + "other: 50 cases, -1.0% CFR\n" + "white: 10 cases, 50.0% CFR");

        strangeState.sortByAlpha();
        assertEquals(strangeState.toString(), "MI\n"
            + "asian: 12 cases, 25.0% CFR\n" + "white: -1 cases, -1.0% CFR");
    }


    /**
     * Tests the soetByCFR method of the
     * State class
     */
    public void testSortByCFR() {
        // sort and then compare the tostrings

        state.sortByCFR();
        assertEquals(state.toString(), "DC\n" + "black: 10 cases, 50.0% CFR\n"
            + "white: 10 cases, 50.0% CFR" + "latinx: 30 cases, 23.3% CFR\n"
            + "asian: 40 cases, 20.0% CFR\n" + "other: 50 cases, -1.0% CFR\n");

        strangeState.sortByCFR();
        assertEquals(strangeState.toString(), "MI\n"
            + "asian: 12 cases, 25.0% CFR\n" + "white: -1 cases, -1.0% CFR");
    }


    /**
     * Tests the getRaceGroup method of the
     * State class
     */
    public void testGetRaceGroups() {
        // build the racegroup dll
        DoublyLinkedList<RaceGroup> races = new DoublyLinkedList<RaceGroup>();
        races.add(new RaceGroup("DC", "White", 10, 5));
        races.add(new RaceGroup("DC", "Black", 10, 5));
        races.add(new RaceGroup("DC", "LatinX", 30, 7));
        races.add(new RaceGroup("DC", "Asian", 40, 8));
        races.add(new RaceGroup("DC", "Other", 50, -1));

        assertEquals(races, state.getRaceGroups());

        races = new DoublyLinkedList<RaceGroup>();
        races.add(new RaceGroup("MI", "White", -1, -1));
        races.add(new RaceGroup("MI", "Asian", 12, 3));

        assertEquals(races, strangeState.getRaceGroups());
    }


    /**
     * Tests the equals method of the
     * State class
     */
    public void testEquals() {
        // test for null
        assertFalse(state.equals(null));

        // test for exact same object
        assertEquals(state, state);

        // test for different type of object
        assertFalse(state.equals(new Object()));

        // test for different state
        assertFalse(state.equals(strangeState));

        State sameState = new State("DC,10,10,30,40,50,5,5,7,8,NA".split(","),
            ("State,Cases_White,Cases_Black,Cases_LatinX,"
                + "Cases_Asian,Cases_Other,Deaths_White,Deaths_Black,"
                + "Deaths_LatinX,Deaths_Asian,Deaths_Other").split(","));

        // test for state of same value
        assertEquals(state, sameState);
    }


    /**
     * Tests the compareTo method of the
     * State class
     */
    public void testCompareTo() {
        // alphabetically earlier
        assertTrue(state.compareTo(strangeState) < 0);

        // alphabetically later
        assertTrue(strangeState.compareTo(state) > 0);

        // alphabetically the same
        State sameState = new State("DC,10,10,30,40,50,5,5,7,8,NA".split(","),
            ("State,Cases_White,Cases_Black,Cases_LatinX,"
                + "Cases_Asian,Cases_Other,Deaths_White,Deaths_Black,"
                + "Deaths_LatinX,Deaths_Asian,Deaths_Other").split(","));

        assertEquals(state.compareTo(sameState), 0);
    }


    /**
     * Tests the toString method of the State
     * class
     */
    public void testToString() {
        assertEquals(state.toString(), "DC\n" + "white: 10 cases, 50.0% CFR"
            + "black: 10 cases, 50.0% CFR\n" + "latinx: 30 cases, 23.3% CFR\n"
            + "asian: 40 cases, 20.0% CFR\n" + "other: 50 cases, -1.0% CFR\n");

        assertEquals(strangeState.toString(), "MI\n"
            + "white: -1 cases, -1.0% CFR" + "asian: 12 cases, 25.0% CFR\n");
    }

}

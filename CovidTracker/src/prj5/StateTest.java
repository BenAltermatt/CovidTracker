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
        state = new State("DC,10,10,30,40,50,5,5,7,8,NA".toLowerCase().split(
            ","), ("State,Cases_White,Cases_Black,Cases_LatinX,"
                + "Cases_Asian,Cases_Other,Deaths_White,Deaths_Black,"
                + "Deaths_LatinX,Deaths_Asian,Deaths_Other").toLowerCase()
                    .split(","));

        // abnormal input sequence
        strangeState = new State("NA,MI,NA,12,3".toLowerCase().split(","),
            "Cases_White,State,Deaths_White,Cases_Asian,Deaths_Asian"
                .toLowerCase().split(","));
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

        assertEquals(state.toString(), "DC\n" + "asian: 40 cases, 20% CFR\n"
            + "black: 10 cases, 50% CFR\n" + "latinx: 30 cases, 23.3% CFR\n"
            + "other: 50 cases, -1% CFR\n" + "white: 10 cases, 50% CFR\n");

        strangeState.sortByAlpha();
        assertEquals(strangeState.toString(), "MI\n"
            + "asian: 12 cases, 25% CFR\n" + "white: -1 cases, -1% CFR\n");
    }


    /**
     * Tests the soetByCFR method of the
     * State class
     */
    public void testSortByCFR() {
        // sort and then compare the tostrings

        state.sortByCFR();
        assertEquals(state.toString(), "DC\n" + "black: 10 cases, 50% CFR\n"
            + "white: 10 cases, 50% CFR\n" + "latinx: 30 cases, 23.3% CFR\n"
            + "asian: 40 cases, 20% CFR\n" + "other: 50 cases, -1% CFR\n");

        strangeState.sortByCFR();
        assertEquals(strangeState.toString(), "MI\n"
            + "asian: 12 cases, 25% CFR\n" + "white: -1 cases, -1% CFR\n");
    }


    /**
     * Tests the getRaceGroup method of the
     * State class
     */
    public void testGetRaceGroups() {
        // build the racegroup dll
        DoublyLinkedList<RaceGroup> races = new DoublyLinkedList<RaceGroup>();
        races.add(new RaceGroup("DC", "white", 10, 5));
        races.add(new RaceGroup("DC", "black", 10, 5));
        races.add(new RaceGroup("DC", "latinx", 30, 7));
        races.add(new RaceGroup("DC", "asian", 40, 8));
        races.add(new RaceGroup("DC", "other", 50, -1));

        assertEquals(races, state.getRaceGroups());

        races = new DoublyLinkedList<RaceGroup>();
        races.add(new RaceGroup("MI", "white", -1, -1));
        races.add(new RaceGroup("MI", "asian", 12, 3));

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

        // test for different states
        // different name
        State diffName = new State("HI,10,10,30,40,50,5,5,7,8,NA".toLowerCase()
            .split(","), ("State,Cases_White,Cases_Black,Cases_LatinX,"
                + "Cases_Asian,Cases_Other,Deaths_White,Deaths_Black,"
                + "Deaths_LatinX,Deaths_Asian,Deaths_Other").toLowerCase()
                    .split(","));
        assertFalse(state.equals(diffName));

        // different race data
        State diffRaces = new State("DC,10,11,30,40,50,5,5,7,8,NA".toLowerCase()
            .split(","), ("State,Cases_White,Cases_Black,Cases_LatinX,"
                + "Cases_Asian,Cases_Other,Deaths_White,Deaths_Black,"
                + "Deaths_LatinX,Deaths_Asian,Deaths_Other").toLowerCase()
                    .split(","));
        assertFalse(state.equals(diffRaces));

        State sameState = new State("DC,10,10,30,40,50,5,5,7,8,NA".toLowerCase()
            .split(","), ("State,Cases_White,Cases_Black,Cases_LatinX,"
                + "Cases_Asian,Cases_Other,Deaths_White,Deaths_Black,"
                + "Deaths_LatinX,Deaths_Asian,Deaths_Other").toLowerCase()
                    .split(","));

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
        State sameState = new State("DC,10,10,30,40,50,5,5,7,8,NA".toLowerCase()
            .split(","), ("State,Cases_White,Cases_Black,Cases_LatinX,"
                + "Cases_Asian,Cases_Other,Deaths_White,Deaths_Black,"
                + "Deaths_LatinX,Deaths_Asian,Deaths_Other").toLowerCase()
                    .split(","));

        assertEquals(state.compareTo(sameState), 0);
    }


    /**
     * Tests the toString method of the State
     * class
     */
    public void testToString() {
        assertEquals(state.toString(), "DC\n" + "white: 10 cases, 50% CFR\n"
            + "black: 10 cases, 50% CFR\n" + "latinx: 30 cases, 23.3% CFR\n"
            + "asian: 40 cases, 20% CFR\n" + "other: 50 cases, -1% CFR\n");

        assertEquals(strangeState.toString(), "MI\n"
            + "white: -1 cases, -1% CFR\n" + "asian: 12 cases, 25% CFR\n");
    }

}

// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Benjamin Altermat, benaltermatt@vt.edu, 906347458
package prj5;

import student.TestCase;

/**
 * The entire purpose of this class is to test
 * the RaceGroup class
 * 
 * @author Benjamin Altermatt
 * @version 2021.11.18
 */
public class RaceGroupTest extends TestCase {

    private RaceGroup group;
    private RaceGroup baseGroup;

    /**
     * Sets up a generalized starting state before
     * each test is run.
     */
    public void setUp() {
        group = new RaceGroup("Florida", "White", 500, 35);
        baseGroup = new RaceGroup(null, null, 0, 0);
    }


    /**
     * Tests the getState method of
     * the RaceGroup class.
     */
    public void testGetState() {
        // test for regular
        assertEquals(group.getState(), "Florida");

        // test for weird value
        assertEquals(baseGroup.getState(), "Invalid State");

    }


    /**
     * Tests the getRace method of
     * the RaceGroup class.
     */
    public void testGetRace() {
        // test for regular
        assertEquals(group.getRace(), "White");

        // test for weird value
        assertEquals(baseGroup.getRace(), "Invalid Race");
    }


    /**
     * Tests the getCases method of
     * the RaceGroup class.
     */
    public void testGetCases() {
        // test for regular
        assertEquals(group.getCases(), 500);

        // test for weird value
        assertEquals(baseGroup.getCases(), 0);
    }


    /**
     * Tests the getDeaths method of
     * the RaceGroup class.
     */
    public void testGetDeaths() {
        // test for regular
        assertEquals(group.getDeaths(), 35);

        // test for weird value
        assertEquals(baseGroup.getDeaths(), 0);
    }


    /**
     * Tests the getCFR method of
     * the RaceGroup class.
     */
    public void testGetCFR() {
        // test for regular
        assertEquals(group.getCFR(), (double)35 / 500, 0.01);

        // test for wierd value
        assertEquals(baseGroup.getCFR(), -1.0, 0.01);
    }


    /**
     * Tests the getBar method of
     * the RaceGroup class.
     */

    public void testGetBar() {
        // I guess this test will rely on a hopefully
        // professionally implemented equals method for
        // the shape class

        // test for regular
        assertEquals(group.getBar().getHeight(), (int)(group.getCFR() * 1000));

        // test for not regular
        assertEquals(baseGroup.getBar().getHeight(), 0);
    }


    /**
     * Tests the getLabel method of
     * the RaceGroup class.
     */
    public void testGetLabel() {
        // This will also rely on a hopefully professionally
        // implemented equals method for the
        // TextShape class

        // test for regular
        assertEquals(group.getLabel().getText(), group.getRace());

        // test for weird
        assertEquals(baseGroup.getLabel().getText(), "Invalid Race");
    }


    /**
     * Tests the getPercentage method of
     * the RaceGroup class.
     */
    public void testGetPercentage() {
        // This will also rely on a hopefully professionally
        // implemented equals method for the
        // TextShape class

        // test for regular
        assertEquals(group.getPercentage().getText(), "7%");

        // test for weird
        assertEquals(baseGroup.getPercentage().getText(), "na");
    }


    /**
     * Tests the equals method of the RaceGroup class.
     */
    public void testEquals() {
        // check null
        assertFalse(group.equals(null));

        // check exact same thing
        assertTrue(group.equals(group));

        // check different type
        assertFalse(group.equals(new Object()));

        // check different RaceGroups
        // dif state
        assertFalse(group.equals(new RaceGroup("Atlanta", "White", 500, 35)));
        // dif name
        assertFalse(group.equals(new RaceGroup("Florida", "Black", 500, 35)));
        // dif cases
        assertFalse(group.equals(new RaceGroup("Florida", "White", 400, 35)));
        // dif deaths
        assertFalse(group.equals(new RaceGroup("Florida", "White", 500, 40)));

        // check same value RaceGroup
        RaceGroup same = new RaceGroup("Florida", "White", 500, 35);
        assertTrue(group.equals(same));
    }


    /**
     * Tests the toString method of the RaceGroup class.
     */
    public void testToString() {
        // check regular
        assertEquals(group.toString(), "White: 500 cases, 7% CFR");

        // check weird
        assertEquals(baseGroup.toString(), "Invalid Race: 0 cases, -1% CFR");
    }
}

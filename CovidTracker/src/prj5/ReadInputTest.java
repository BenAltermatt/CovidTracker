// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Benjamin Altermat, benaltermatt@vt.edu, 906347458
package prj5;

import student.TestCase;
import java.io.FileNotFoundException;

/**
 * This class purely exists to test
 * the readInputTest class
 * 
 * @author Benjamin Altermatt
 * @version 2021.11.20
 */
public class ReadInputTest extends TestCase {

    private ReadInput input;
    private ReadInput nullFile;
    private ReadInput otherInput;

    /**
     * Initializes a precondition that
     * is the same across all tests before
     * they're run.
     */
    public void setUp() {
        input = new ReadInput(); // make a default one
        nullFile = new ReadInput(null);
        otherInput = new ReadInput(
            "Cases_and_Deaths_by_race_RANDOM_NUMBERS.csv");
    }


    /**
     * Tests the getFile method of the
     * ReadInput class.
     */
    public void testGetFile() {
        assertEquals(input.getFile(),
            "Cases_and_Deaths_by_race_CRDT_Sep2020.csv");
        assertEquals(nullFile.getFile(),
            "Cases_and_Deaths_by_race_CRDT_Sep2020.csv");
        assertEquals(otherInput.getFile(),
            "Cases_and_Deaths_by_race_RANDOM_NUMBERS.csv");
    }


    /**
     * Tests the equals method of the
     * ReadInput class.
     */
    public void testEquals() {
        // check null
        assertFalse(input.equals(null));

        // check exact same
        assertEquals(input, input);

        // check diff type
        assertFalse(input.equals(new Object()));

        // check same type diff
        assertFalse(input.equals(otherInput));

        // check same type same
        assertEquals(input, nullFile);
    }


    /**
     * Tests the readIn method of the
     * ReadInput class.
     */
    public void testReadIn() {
        // if the file doesnt exist
        ReadInput fakeFile = new ReadInput("asfhja.txt");
        Exception e = null;
        try {
            State[] states = fakeFile.readIn();
            fail("This should have thrown a FileNotFoundException");
        }
        catch (Exception exception) {
            e = exception;
        }

        assertTrue(e instanceof FileNotFoundException);

        String[] dataLabels = ("State,Cases_White,Cases_Black,"
            + "Cases_LatinX,Cases_Asian,Cases_Other,"
            + "Deaths_White,Deaths_Black,Deaths_LatinX,"
            + "Deaths_Asian,Deaths_Other").toLowerCase().split(",");

        String[] state1Info = ("DC,70678,179563,97118,5407,108784"
            + ",1924,13365,2269,254,170").toLowerCase().split(",");

        State state1 = new State(state1Info, dataLabels);

        try {
            assertEquals(input.readIn()[0], state1);
        }
        catch (FileNotFoundException except) {
            fail("This should have worked");
        }
    }

}

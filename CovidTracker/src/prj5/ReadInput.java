// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Benjamin Altermat, benaltermatt@vt.edu, 906347458
package prj5;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * This class is responsible for turning
 * the input file into actual, workable States
 * 
 * @author Benjamin Altermatt
 * @version 2021.11.20
 */
public class ReadInput {
    private String filename;

    /**
     * This is the string that would normally be used as a
     * default file if say, none were provided. It may not
     * actually be useful, in hindsight.
     */
    public static final String DEFAULT_FILE =
        "Cases_and_Deaths_by_race_CRDT_Sep2020.csv";

    /**
     * Creates a default ReadInput object
     * using the default object.
     */
    public ReadInput() {
        this(DEFAULT_FILE);
    }


    /**
     * Sets our filename according to what is input
     * 
     * @param file
     *            The file we will read in from
     */
    public ReadInput(String file) {
        // handle null
        if (file == null) {
            filename = DEFAULT_FILE;
        }
        else {
            filename = file;
        }

    }


    /**
     * Accesses the file that this reader
     * is using.
     * 
     * @return
     *         filename
     */
    public String getFile() {
        return filename;
    }


    /**
     * Compares this ReadInput to another
     * object and checks if they are equal in value
     * 
     * @param other
     *            Object being comapared against
     * @return
     *         Whether this and the passed object are
     *         logically equivalent
     */
    public boolean equals(Object other) {
        // handle null
        if (other == null) {
            return false;
        }

        // handle exact same ovject
        if (other == this) {
            return true;
        }

        // hanlde different type
        if (!(other instanceof ReadInput)) {
            return false;
        }

        ReadInput otherReader = (ReadInput)other;

        // handle same type
        return filename.equals(otherReader.getFile());
    }


    /**
     * Generates an array of state objects by reading in
     * the lines of the file and returns them.
     * 
     * @throws FileNotFoundException
     *             No file with given name found
     * @return
     *         Array of states from a file
     */
    public State[] readIn() throws FileNotFoundException {
        // we'll use this to collect the states
        DoublyLinkedList<State> states = new DoublyLinkedList<State>();
        Scanner reader = new Scanner(new File(filename)); // make the file

        String[] key = null;

        // get the first pattern line
        // normaally I would check if it exists but
        // that makes testing harder and I know it will exist
        key = reader.nextLine().toLowerCase().split(",");

        // read in the states
        while (reader.hasNextLine()) {
            states.add(new State(reader.nextLine().toLowerCase().split(","),
                key));
        }

        reader.close();

        // cast each object individually
        State[] retStates = new State[states.size()];
        Object[] uncasted = states.toArray();
        for (int i = 0; i < retStates.length; i++) {
            retStates[i] = (State)uncasted[i];
        }

        return retStates;
    }
}

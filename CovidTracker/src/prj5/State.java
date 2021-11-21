// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Benjamin Altermat, benaltermatt@vt.edu, 906347458
package prj5;

/**
 * This class will serve as a logical representation
 * of a state and its associated RaceGroups.
 * 
 * @author Benjamin Altermatt
 * @version 2021.11.20
 */
public class State {
    private String name;
    private DoublyLinkedList<RaceGroup> races;
    private CompareByAlpha alphaCompare;
    private CompareByCFR ratioCompare;

    /**
     * This is the value that will be passed to
     * a RaceGroup when data is NA.
     */
    public static final int NA_VAL = -1;

    /**
     * Takes a cut up string for the state. e.g., it takes
     * [DC, 70678, 179563, 97118, 5407, 91880, 1924, 13365, 2269, 254, NA]
     * for DC, and a cut up string for the data descriptors, e.g.
     * [State, Cases_White, Cases_Black, Cases_LatinX, Cases_Asian, Cases_Other
     * Deaths_White, Deaths_Black, Deaths_LatinX, Deaths_Asian, Deaths_Other]
     * for a regularly formatted file, and creates a state based on the
     * parameters.
     * 
     * @param parsedDescriptions
     *            The array that shows the information for this specific state
     * @param dataLabels
     *            The array that shows where the information for this specific
     *            state will be stored
     */
    public State(String[] parsedDescriptions, String[] dataLabels) {
        // find the name of the state
        for (int i = 0; i < dataLabels.length; i++) {
            // found the index the name is stored
            if (dataLabels[i].equals("state")) {
                name = parsedDescriptions[i].toUpperCase();
            }
        }

        // make the DLL of race groups
        races = new DoublyLinkedList<RaceGroup>();

        // fills it in
        buildRaceGroups(parsedDescriptions, dataLabels);

        // makes the comparators
        alphaCompare = new CompareByAlpha();
        ratioCompare = new CompareByCFR();
    }


    /**
     * The entire purpose of this class is to handle the gross process
     * of parsing the CSV lines properly to build the state accordingly.
     * It isn't efficient, it isn't pretty, but hopefully it maybe works.
     * It should take the lines and add all the race groups based
     * on the passed data.
     * 
     * @param parsedDescriptions
     *            The array that shows the information for this specific state
     * @param dataLabels
     *            The array that shows where data is stored in the arrays of
     *            info
     */
    private void buildRaceGroups(
        String[] parsedDescriptions,
        String[] dataLabels) {
        // now we'll build the info for every race
        for (int i = 0; i < dataLabels.length; i++) {
            // checks whether this is a cases data tag
            if (dataLabels[i].indexOf("cases") != -1) {
                // found the name of this race
                String raceName = dataLabels[i].substring(dataLabels[i].indexOf(
                    "_") + 1);
                for (int k = 0; k < dataLabels.length; k++) {
                    // check if this is the deaths for the corresponding race
                    if (dataLabels[k].indexOf(raceName) != -1 && dataLabels[k]
                        .indexOf("deaths") != -1) {
                        // parse the values
                        int cases = NA_VAL;
                        int deaths = NA_VAL;

                        // finds out what cases value will be passed
                        if (!parsedDescriptions[i].equals("na")) {
                            cases = Integer.parseInt(parsedDescriptions[i]);
                        }

                        // find out what deaths value will be passed
                        if (!parsedDescriptions[k].equals("na")) {
                            deaths = Integer.parseInt(parsedDescriptions[k]);
                        }

                        // finally build and add race group
                        races.add(new RaceGroup(name, raceName, cases, deaths));
                    }
                }
            }
        }
    }


    /**
     * Gets the name of the state
     * 
     * @return
     *         name
     */
    public String getName() {
        return name;
    }


    /**
     * Sorts the races within the state
     * alphabetically
     */
    public void sortByAlpha() {
        races.sort(alphaCompare);
    }


    /**
     * Sorts the races in order of
     * decreasing CFR
     */
    public void sortByCFR() {
        races.sort(ratioCompare);
    }


    /**
     * Accesses the List of the races
     * 
     * @return
     *         races
     */
    public DoublyLinkedList<RaceGroup> getRaceGroups() {
        return races;
    }


    /**
     * Checks whether the passed object is equivalent
     * in value to this state.
     * 
     * @param other
     *            The object we're checking against
     * @return
     *         Whether they're logically equivalent
     */
    @Override
    public boolean equals(Object other) {
        // deal with null
        if (other == null) {
            return false;
        }
        // deal with exact same object
        if (this == other) {
            return true;
        }
        // deal with object of different type
        if (!(other instanceof State)) {
            return false;
        }

        State otherState = (State)other;
        // deal with object of same type
        return name.equals(otherState.getName()) && races.equals(otherState
            .getRaceGroups());
    }


    /**
     * Compares states by their names returning
     * a lesser value if this is alphabetically
     * earlier, and a greater if this is
     * alphabetcially later
     * 
     * @param other
     *            The state being compared to
     * @return
     *         The comparison value
     */
    public int compareTo(State other) {
        return name.compareTo(other.getName());
    }


    /**
     * Returns a string representation of the state
     * 
     * @return
     *         A string representation
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(name + "\n");
        for (RaceGroup race : races) {
            builder.append(race.toString() + "\n");
        }

        return builder.toString();
    }
}

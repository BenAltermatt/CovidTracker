// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Benjamin Altermat, benaltermatt@vt.edu, 906347458
package prj5;

public class State {
    private String name;
    private DoublyLinkedList<RaceGroup> races;
    private CompareByAlpha alphaCompare;
    private CompareByCFR ratioCompare;

    /**
     * Takes a cut up string for the state. e.g., it takes
     * [DC, 70678, 179563, 97118, 5407, 91880, 1924, 13365, 2269, 254, NA]
     * for DC, and a cut up string for the data descriptors, e.g.
     * [State, Cases_White, Cases_Black, Cases_LatinX, Cases_Asian, Cases_Other,
     * Deaths_White, Deaths_Black, Deaths_LatinX, Deaths_Asian, Deaths_Other]
     * for a regularly formatted file, and creates a state based on the parameters.
     * @param parsedDescriptions
     * @param dataLabels
     */
    public State(String[] parsedDescriptions, String[] dataLabels) {
        
    }


    public String getName() {
        return null;
    }


    public void sortByAlpha() {

    }


    public void sortByCFR() {

    }


    public DoublyLinkedList<RaceGroup> getRaceGroups() {
        return null;
    }


    public boolean equals(Object other) {
        return false;
    }


    public int compareTo(State other) {
        return -1;
    }
    
    public String toString() {
        return null;
    }
}

// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Benjamin Altermat, benaltermatt@vt.edu, 906347458
package prj5;

import java.util.Comparator;

/**
 * This class exists to make it easier to sort
 * RaceGroups by race alphabetically.
 * 
 * @author Benjamin Altermatt
 * @version 2021.11.19
 */
public class CompareByAlpha implements Comparator<RaceGroup> {

    /**
     * Returns a value representative of the position the
     * first group should be in relation to the second
     * group depending on name. The earlier alphabetically
     * group1 is than group2, the more negative the number.
     * 
     * @param group1
     *            The first RaceGroup being compared
     * @param group2
     *            The second RaceGroup being compared
     * @return
     *         Comparison value
     */
    public int compare(RaceGroup group1, RaceGroup group2) {
        return group1.getRace().compareTo(group2.getRace());
    }

}

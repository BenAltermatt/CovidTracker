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
 * RaceGroups by CFR.
 * 
 * @author Benjamin Altermatt
 * @version 2021.11.19
 */
public class CompareByCFR implements Comparator<RaceGroup> {

    /**
     * Returns a value that is lesser if the first group
     * should be sorted earlier than the second, and greater
     * if the second should be sorted earlier than the first.
     * 0 if they have the same sort position.
     * 
     * @return
     *         Comparison value
     */
    public int compare(RaceGroup group1, RaceGroup group2) {
        double difCFR = group2.getCFR() - group1.getCFR();

        if (difCFR < 0) {
            return -1;
        }
        if (difCFR > 0) {
            return 1;
        }

        CompareByAlpha auxComparer = new CompareByAlpha();
        return auxComparer.compare(group1, group2);
    }
}

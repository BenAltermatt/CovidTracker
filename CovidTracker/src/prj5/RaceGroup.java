// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Benjamin Altermat, benaltermatt@vt.edu, 906347458
package prj5;

import cs2.Shape;
import cs2.TextShape;
import java.text.DecimalFormat;

/**
 * This class will represent a single data point
 * relevant to a specific race in a specific state.
 * It will also carry the means by which to
 * calculate its CFR and retrieve GUI elements
 * which can be manipulated and added to the GUI later
 * when needed.
 * 
 * @author Benjamin Altermatt
 * @version 2021.11.19
 */
public class RaceGroup {

    private String state;
    private String race;
    private int cases;
    private int deaths;
    private Shape bar;
    private TextShape label;
    private TextShape percentage;

    /**
     * Creates a race group with the passed
     * state, name, and case/death stats.
     * 
     * @param stateName
     *            Name of the state
     * @param raceName
     *            Name of the race
     * @param numCases
     *            Number of recorded covid cases
     * @param numDeaths
     *            Number of recorded deaths
     */
    public RaceGroup(
        String stateName,
        String raceName,
        int numCases,
        int numDeaths) {
        state = stateName;
        race = raceName;
        cases = numCases;
        deaths = numDeaths;

        // handle null state passed
        if (state == null) {
            state = "Invalid State";
        }

        // handle null name passed
        if (race == null) {
            race = "Invalid Race";
        }

        DecimalFormat formatter = new DecimalFormat("#0.#%");

        // handle NA values while creating GUI elements
        if (getCFR() == -1.0) { // either the
                                // cases or
                                // deaths are NA
            bar = new Shape(0, 0, 0, 0);
            percentage = new TextShape(0, 0, "na");
        }
        else {
            bar = new Shape(0, 0, (int)(getCFR() * 1000));
            percentage = new TextShape(0, 0, formatter.format(getCFR()));
        }

        label = new TextShape(0, 0, race);
    }


    /**
     * Accesses the name of the state
     * 
     * @return
     *         Name of the state the race data applies to
     */
    public String getState() {
        return state;
    }


    /**
     * Accesses the name of the race
     * 
     * @return
     *         The word used to identify this race
     */
    public String getRace() {
        return race;
    }


    /**
     * Accesses the number of cases this race
     * has documented.
     * 
     * @return
     *         Numnber of cases
     */
    public int getCases() {
        return cases;
    }


    /**
     * Accesses the number of deaths this race
     * has documented.
     * 
     * @return
     *         Number of deaths
     */
    public int getDeaths() {
        return deaths;
    }


    /**
     * Caculates and retuns the CFR for this
     * race group in this state. It will be -1.0
     * for missing data or undefined.
     * 
     * @return
     *         The Case Fatalaty Ratio for this race group
     */
    public double getCFR() {
        // handle edge cases
        if (cases == 0 || cases == State.NA_VAL || deaths == State.NA_VAL) {
            return -1.0;
        }

        // handle regular cases
        return (double)deaths / cases;
    }


    /**
     * Gets the visual bar representation
     * of the CFR
     * 
     * @return
     *         The Bar Shape
     */
    public Shape getBar() {
        return bar;
    }


    /**
     * Gets the visual label for the race group
     * 
     * @return
     *         The label
     */
    public TextShape getLabel() {
        return label;
    }


    /**
     * Gets the visual label for the CFR of the
     * race group.
     * 
     * @return
     *         The percentage
     */
    public TextShape getPercentage() {
        return percentage;
    }


    /**
     * Checks whether a passed object is
     * equivalent in value to this race group.
     * 
     * @return
     *         Whether they're equivalent
     */
    @Override
    public boolean equals(Object other) {
        // handle null
        if (other == null) {
            return false;
        }

        // handle exactly the same thing
        if (other == this) {
            return true;
        }

        // handle a different type
        if (!(other instanceof RaceGroup)) {
            return false;
        }

        RaceGroup otherGroup = (RaceGroup)other;

        // check if they have the same fields
        return otherGroup.getState().equals(state) && otherGroup.getRace()
            .equals(race) && otherGroup.getCases() == cases && otherGroup
                .getDeaths() == deaths;
    }


    /**
     * Builds and returns a string representation of a race group
     * 
     * @return
     *         String representation of the race group
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(race + ": ");
        builder.append(cases + " cases, ");

        // handle NA values
        if ((int)getCFR() != State.NA_VAL) {
            builder.append(percentage.getText() + " CFR");
        }
        else {
            builder.append("-1% CFR");
        }

        return builder.toString();
    }
}

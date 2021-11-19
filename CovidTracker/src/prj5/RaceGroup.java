// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Benjamin Altermat, benaltermatt@vt.edu, 906347458
package prj5;

import cs2.Shape;
import cs2.TextShape;

public class RaceGroup {
    
    private String state;
    private String race;
    private int cases;
    private int deaths;
    private Shape bar;
    private TextShape label;
    private TextShape percentage;
    
    public RaceGroup(String stateName, String raceName, int numCases, int numDeaths) {
        
    }
    
    public String getState() {
        return null;
    }
    
    public String getRace() {
        return null;
    }
    
    public int getCases() {
        return -1;
    }
    
    public int getDeaths() {
        return -1;
    }
    
    public double getCFR() {
        return -1.0;
    }
    
    public Shape getBar() {
        return null;
    }
    
    public TextShape getLabel() {
        return null;
    }
    
    public TextShape getPercentage() {
        return null;
    }
}

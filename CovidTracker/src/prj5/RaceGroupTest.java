// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Benjamin Altermat, benaltermatt@vt.edu, 906347458
package prj5;

import cs2.Shape;
import cs2.TextShape;
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
        assertNull(baseGroup.getState());
        
    }
    
    /**
     * Tests the getRace method of 
     * the RaceGroup class.
     */
    public void testGetRace() {
        // test for regular
        assertEquals(group.getRace(), "White");
        
        // test for weird value
        assertNull(baseGroup.getRace());
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
        assertEquals(group.getCFR(), (double) 500 / 35);
        
        // test for wierd value
        // should try and fail to divide by zero as stands
        Exception exception = null;
        try {
            baseGroup.getCFR();
            fail("This should have tried to divide by zero.");
        }
        catch(Exception e) {
            exception = e;
        }
        
        assertTrue(exception instanceof ArithmeticException);
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
        assertEquals(group.getBar(), new Shape(0, 0, 5, (int)(group.getCFR() * 1000)));
        
        // test for not regular
        assertEquals(baseGroup.getBar(), new Shape(0, 0, 0, 0));
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
        assertEquals(group.getLabel(), new TextShape(0, 0, group.getRace()));
        
        // test for weird
        assertEquals(baseGroup.getLabel(), new TextShape(0, 0, "Invalid Race"));
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
        assertEquals(group.getPercentage(), new TextShape(0, 0, "7%"));
        
        // test for weird
        assertEquals(baseGroup.getPercentage(), new TextShape(0, 0, "Illegal Data"));
    }

}

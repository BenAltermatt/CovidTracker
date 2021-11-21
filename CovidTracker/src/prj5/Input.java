// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Benjamin Altermat, benaltermatt@vt.edu, 906347458
package prj5;

import java.io.FileNotFoundException;

public class Input {
    private GUI display;

    public static void main(String[] args) throws FileNotFoundException {
        // read the input
        ReadInput input = new ReadInput(args[0]);

        State[] states = input.readIn();

        // print out everything
        for (State state : states) {
            state.sortByAlpha();
            System.out.print(state);
            System.out.println("=====");
            state.sortByCFR();
            System.out.print(state.toString().substring(3));
            System.out.println("=====");
        }

    }
}

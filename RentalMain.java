
/*
 * Main class for the Vehicle Rental System TCSS 305
 * 
 * TCSS 305 - Rentz
 */

package model;

import java.util.Scanner;

/**
 * RentalMain provides the main method for a simple VehicleRental application.
 * 
 * @author Athirai
 * @version Fall 2019
 */
public final class RentalMain {
    /**
     * Static constructor for all the classes.
     */
    private static final Scanner INFO = new Scanner(System.in);

    /**
     * A private constructor, to prevent external instantiation.
     */
    private RentalMain() {

    }
    public static Scanner getInfo() {
        return INFO;
    }

    /**
     * Main method for Rentz.
     * 
     * @param theArgs argument for main method.
     */
    public static void main(final String[] theArgs) {
        final Registration reg = new Registration();
        if (reg.printSignin()) {
            final RentalManager rm = new RentalManager(reg);
            rm.printOptions();
        }
        
    }

}

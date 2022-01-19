package model;
/**
 * This is the Bill class for Assignment 4.
 * this class will have all the required fields and methods for the billing system.
 * it will also have methods and operations that would help bring the desired output to the customer
 * when they choose they type of vehicle they want to rent 
 * @author mdawood
 * @version Winter21
 * Course: TCSS 305
 */
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import model.vehicles.Vehicles;

public class Bill {
    /**
     * number format.
     */
    protected static NumberFormat  nf = NumberFormat.getCurrencyInstance(Locale.US);
    /**
     * represent the Bill ID.
     */
    protected static int myBillID;
    /**
     * represent the user object.
     */
    protected User myPrimaryUser;
    /**
     * represent the vehicle object.
     */
    protected Vehicles myVehicle;
    /**
     * represent the number of days for rental.
     */
    protected int myNumDays;
    /**
     * represent the Bill amount.
     */
    protected BigDecimal myBillAmount;
    
    //parameterized constructor
    public Bill(final int theBill, final User theUser, final Vehicles theVehicle, final int theNum) {
        myBillID = theBill;
        myPrimaryUser = theUser;
        myVehicle = theVehicle;
        myNumDays = theNum;
        myBillAmount = new BigDecimal("0.00");
    }
    /**
     * getter method for myBillID.
     * @return myBillID
     */
    public static int getMyBillID() {
        return myBillID;
    }
    //prints rental summary, vehicle information, and cost information.
    public void computeAndPrintAmount() {
        System.out.println("**********************\n Rental Bill Summary \n**********************");
        System.out.print("User Name: " + this.myPrimaryUser.getMyName()  + "\n ");
        System.out.println("----Vehicle information----");
        System.out.println("Vehicle Name: " + this.myVehicle.getmyName());
        System.out.println("Vehicle Type: " + this.myVehicle.getmyVIN());
        System.out.println("VIN: " + this.myVehicle.getmyVIN());
        System.out.println("----Cost information----");
        myBillAmount = myVehicle.calculateRentalAmount();
        System.out.println("cost per day: " + nf.format(this.myVehicle.calculateRentalAmount()));
        System.out.println("N.of rental Days: " + myNumDays);
        
        // calculating total amount.
        this.myBillAmount = this.myBillAmount.multiply(new BigDecimal(this.myNumDays));
        System.out.println("Total amount: " + nf.format(this.myBillAmount));
        //calculating insurance.
        final BigDecimal insurance;
        insurance = myBillAmount.multiply(new BigDecimal("0.01"));
        myBillAmount = this.myBillAmount.add(insurance);

        System.out.println("insurance: " + nf.format(insurance));
        
        // checking if the customer is VIP.
        BigDecimal vipDiscount = new BigDecimal("0.00");
        if (myPrimaryUser.getMyVIPStatus()) {
            vipDiscount = insurance.multiply(new BigDecimal(-1));
            this.myBillAmount = this.myBillAmount.add(vipDiscount);
            System.out.println("VIP Discount: " + nf.format(vipDiscount));
        }
        //calculating tax
        final BigDecimal tax;
        tax = myBillAmount.multiply(new BigDecimal("0.1"));
        myBillAmount = this.myBillAmount.add(tax);
        
        System.out.println("tax: " + nf.format(tax));
        System.out.println("total rent: " + nf.format(myBillAmount));
        System.out.println("Rent Successful!");
        writeUserToFile(insurance, vipDiscount, tax, myBillAmount);
    }
    /**
     * Writes information to the file.
     * @param theInsurance The Insurance cost.
     * @param theVip VIP status.
     * @param theTax Tax from the total.
     * @param theBill The total rented.
     */
    public void writeUserToFile(final BigDecimal theInsurance, final BigDecimal theVip, final BigDecimal theTax, final BigDecimal theBill) {
    
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("./resources/Bills.txt"
                                                                   , true))) {
            final StringBuffer sb = new StringBuffer();
            sb.append("User Name: " + this.myPrimaryUser.getMyName());
            sb.append("\n" + "Vehicle name: " + this.myVehicle.getmyName());
            sb.append("\n" + "Vehicle type: " + this.myVehicle.getmyVIN());
            sb.append("\n" + "VIN: " + this.myVehicle.getmyVIN());
            sb.append("\n" + "----Cost Information----");
            sb.append("\nCost per day: " + nf.format(this.myVehicle.calculateRentalAmount()));
            sb.append("\nNo. of Rental Days: " + this.myNumDays);
            sb.append("\nTotal Amount: " + nf.format(this.myVehicle.calculateRentalAmount().doubleValue() * myNumDays));
            sb.append("\nInsurance:  " + nf.format(theInsurance));
            sb.append("\nVIP discount: " + nf.format(theVip));
            sb.append("\ntax: " + nf.format(theTax));
            sb.append("\ntotal rent " + nf.format(theBill));
            bw.write(sb.toString());
            bw.write("\n\n");
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }
}
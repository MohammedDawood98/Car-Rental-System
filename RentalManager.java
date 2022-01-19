package model;
/**
 * This is the RentalManager class for Assignment 4.
 * this class will have a HashMap for the vehicles ID along with refrence from the Registration class
 * it will also have methods and operations that would help bring the desired output to the customer when logged in 
 * @author modawood
 * @version Winter21
 */
import java.util.*;
import model.vehicles.*;

public class RentalManager {
    /**
     * Vehicle list.
     */
    protected Map<Integer, Vehicles> myVehicleList = new HashMap<Integer, Vehicles>();
    
    /**
     * Registration reference.
     */
    protected Registration myRegistration;
    /**
     * Bill System.
     */
    private final Map<Integer, Bill> myBills;
    
    /**
     * Rental manager's constructor.
     * @param theReg
     */
    public RentalManager(final Registration theReg) {
   
        if (theReg == null)  {
      
            throw new NullPointerException();
        }
        
        this.myRegistration = theReg;
        this.myVehicleList = generateInventory();
        this.myBills = new HashMap<Integer, Bill>();
    }
    
    /**
     * Check the inventory of what's available.
     * @return Map Retrieve the hash map of all the vehicles.
     */
    public Map<Integer, Vehicles> generateInventory() {
  
        final Map<Integer, Vehicles> vehicle = new HashMap<Integer, Vehicles>();
        Car car = new Car("Fiat", "V100", true, false, false, false);
        vehicle.put(car.getmyVehicleID(), car);
        
        car = new Car("Outback", "V101", true, true, true, false);
        vehicle.put(car.getmyVehicleID(), car);
        
        car = new Car("BMW", "V102", true, true, true, true);
        vehicle.put(car.getmyVehicleID(), car);
        
        MotorBike bike = new MotorBike("Bike1", "B100", true, false);
        vehicle.put(bike.getmyVehicleID(), bike);
        
        bike = new MotorBike("Bike2", "B101", true, true);
        vehicle.put(bike.getmyVehicleID(), bike);
        
        BiCycle cycle = new BiCycle("Roadies", "C100", true, "Road");
        vehicle.put(cycle.getmyVehicleID(), cycle);
        
        cycle = new BiCycle("Cruiser", "C101", true, "Cruiser");
        vehicle.put(cycle.getmyVehicleID(), cycle);
        
        cycle = new BiCycle("Mountain", "C102", true, "Mountain");
        vehicle.put(cycle.getmyVehicleID(), cycle);
        
        return vehicle;
    }
    
    /**
     * @return Map Retrieve the hash map of the vehicle list.
     */
    public Map<Integer, Vehicles> getMyVehicleList() {
    
        return this.myVehicleList;
    }
    
    /**
     * @param theVehicleID Vehicle's unique ID.
     * @return Boolean Retrieve if the vehicle part of the inventory.
     */
    public boolean isRentable(final int theVehicleID) {
    
        if (this.myVehicleList.containsKey(theVehicleID) && this.myVehicleList.get(theVehicleID).getcanRent()) {
        
            return true;
        }
        return false;
    }
    
    /**
     * @param theVehicleID Vehicle's unique ID.
     * @param theUserName Check the user is registered.
     * @param theNumDays Rental duration.
     * @param theBillID Bill unique ID.
     * @return Boolean Check if the rent went through or not.
     */
    public boolean rent(final int theVehicleID, final String theUserName, final int theNumDays, final int theBillID) {
    
        if (theUserName == null) {
        
            throw new NullPointerException(); 
         
        } else if (theUserName.isEmpty()) {
        
            throw new IllegalArgumentException();
        }
        
        if (isRentable(theVehicleID) && this.myRegistration.getMyUserList().containsKey(theUserName)) {
        
            this.myVehicleList.get(theVehicleID).setcanRent(false);
            
            final Bill bill = new Bill(theBillID, this.myRegistration.getMyUserList().get(theUserName), this.myVehicleList.get(theVehicleID), theNumDays);
            bill.computeAndPrintAmount();
            this.myBills.put(theBillID, bill);
            return true;
        }
        return false;
    }
    
    /**
     * @param theVehicleID Vehicle's unique ID.
     * @return Boolean Check if the vehicle ID is valid and not available to rent.
     */
    public boolean drop(final int theVehicleID) {
    
        if (myVehicleList.containsKey(theVehicleID)) {
        
            if (!isRentable(theVehicleID)) {
            
                this.myVehicleList.get(theVehicleID).setcanRent(true);
                System.out.println("Drop-off Successfull.");
                return true;
            
            } else {   
                System.out.println("Vehicle is not rented already.");
            }
        
        } else {
        
            System.out.println("Vehicle does not exists.");
        }
        return false;
    }
    
    /**
     * Print the vehicles that are available. 
     */
    public void printOptions() {
    
        final Scanner input = RentalMain.getInfo();
        boolean again = true;
        while (again) {
            System.out.print("Enter 1 or 2 or 3 (1. Rent  2. Drop-off 3. Exit  4.Show Vehicles): ");
            final int option = input.nextInt();
            switch (option) {
                case 1:
                    System.out.println("You entered option 1");
                    System.out.println("\n*************List of available vehicles:*************");
                    for (Map.Entry<Integer, Vehicles> entry : this.myVehicleList.entrySet()) {
                        if (entry.getValue().getcanRent()) {
                            System.out.println(entry.getValue().toString());
                        }
                    }
                    boolean allowed = false;
                    int id;
                    int days;
                    String name = "";
                    while (!allowed) {
                        System.out.println("**********************\n Enter Rental Details\n**********************");
                        System.out.print("Enter Vehicle ID: ");
                        id = input.nextInt();
                        System.out.print("Enter User Name: ");
                        name = input.next();
                        System.out.print("Enter Number of Days to Rent: ");
                        days = input.nextInt();
                        
                        if (!isRentable(id)) {
                            System.out.println("Vehicle not rentable.");
                            continue;
                        }
                        for (String key: this.myRegistration.getMyUserList().keySet()) {
                            if (name.equals(key)) {
                                allowed = true;
                                break;
                            }
                        }
                        if (!allowed) {
                            System.out.println("User does not exists, enter different user name: ");
                        } else {
                            if (myBills.get(1) == null) {
                                if (!rent(id, name, days, 1)) { 
                                    allowed = false;
                                }
                            } else if (!rent(id, name, days, myBills.get(1).getMyBillID() + 1)) { 
                            
                                allowed = false;
                            }
                        }
                    }
                    break;
                    
                case 2:
                    System.out.println("You entered option 2\n\n********************************************\n**********************\n Enter Drop-off Details\n**********************");
                    boolean allowed2 = false;
                    
                    while (!allowed2) {
                    
                        System.out.print("Enter Drop-off Vehicle ID: ");
                        id = input.nextInt();
                        
                        if (drop(id)) {
                        
                            allowed2 = true;
                        } 
                    }
                    break;
                case 3:
                    System.out.println("You entered option 3");
                    System.out.println("\n********************************************");
                    again = false;
                    break;
                case 4:
                    System.out.println("You entered option 4");
                    System.out.println("\n*************List of vehicles:*************");
                    this.myVehicleList.entrySet().stream().sorted((k1, k2) -> -k1.getValue().compareTo(k2.getValue())).forEach(k -> System.out.println(k.getValue() + " [Price: " + Bill.nf.format(k.getValue().calculateRentalAmount()) + "]"));
                    break;
                default:
                    break; 
            }
            
            if (again) {
            
                System.out.print("**********************\nDo you want to continue? ");
                
                if ("false".equals(input.next())) {
                
                    again = false;
                }
            }
        }
    }
    
    /**
     * Clears the lists.
     */
    public void clearLists() {
    
        this.myVehicleList.clear();
        this.myBills.clear();
    }
}

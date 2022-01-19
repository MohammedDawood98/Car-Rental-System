
/*
 * This file is the registration class for the Vehicle Rental System.
 * 
 * TCSS 305 - Rentz
 */

package model;
import java.util.Map;
import java.util.Scanner;
import  utility.FileLoader;
/**
 * This is the Registration class for assignment(1) 
 * Made by: Mohammed Dawood
 * Course: TCSS-305(W21)
 * Date: 01/26/2021
 */

/**
 * Represents User Sign-in Object.
 * 
 * Methods of this class throw NullPointerException if required parameters are null.
 * 
 * @author Athirai
 * @version Fall 2019
 */

public class Registration {
    /**
     * User Storage File.
     */
    public static final String USERFILE_NAME = "./resources/registeredusers.txt";

    /**
     * The registered user list for signin.
     */
    private final Map<String, User> myUserList;

    /**
     * Constructs a sigin/registration system.
     * 
     * 
     */
    public Registration() {
        myUserList = FileLoader.readItemsFromFile(USERFILE_NAME);
    }

    /**
     * getter for myUserList.
     * 
     * @return myUserList
     */
    public Map<String, User> getMyUserList() {
        return myUserList;
    }

    /**
     * display sign-in or registration options.
     * @return true
     */
    public Boolean printSignin() {
        // asking the user to enter an option
        final Scanner info = RentalMain.getInfo();
        System.out.println("Enter 1 or 2 (1. new Registeration 2. Login): ");
        final int i = info.nextInt();
        switch (i) {
         // new account
            case 1: {
                System.out.println("you entered option 1" + "\n ************** " + "\n Enter Details"
                            + "\n **************");
            //asking for username
                System.out.println(" enter Username: ");
                String username = "";
            
                boolean condition = false;
                while (!condition) {
                
                    username = info.next();
                    condition = true;
            
                    for (String key: myUserList.keySet()) {
                        if (username.equals(key)) {
                            System.out.println("User already exists, enter different user name: ");
                            condition = false;
                            break;
                        }
                    }
                }
                System.out.println(" enter Password: ");
                final String password = info.next();
                System.out.println("is VIP(true/False): ");
                final String vIP = info.next();
                final User user = new User(username, password, "true".equals(vIP.toString()));
            
                if (register(user)) {
                    System.out.println("Registration Successfull");
                
            
                } else {
                    System.out.println("Registration Failed");
                }
                break;
            }
     // Exsisting Account
            case 2: 
        
                System.out.println("you entered option 2" + "\n*************" + "\nEnter Details"
                            + "\n**************");
                boolean login = false;
                while (!login) {
                //asking for user info
                    System.out.print("User Name: ");
                    final String username = info.next();
                    System.out.print("Password: ");
                    final String password = info.next();
                    if (login(username, password)) {
                        login = true;
                    
                    } else {
                        System.out.println("Wrong Credentials");
                    }
                }
                System.out.println("Login Successfull");
                return true;
            
            default: throw new IllegalArgumentException();
        }      
        return false;
    }
    /**
     * Verify Sign-in procedure.
     * 
     * @param theUsername username for sign-in
     * @param thePassword password for signin
     * @return sign-in success
     */
    public boolean login(final String theUsername, final String thePassword) {
        
        if (theUsername == null || thePassword == null) {
            throw new NullPointerException();
        }    
        if (theUsername.isEmpty() || thePassword.isEmpty()) {
            throw new IllegalArgumentException();
        }
        for (String key: myUserList.keySet()) {
            if (theUsername.equals(myUserList.get(key).getMyName()) && myUserList.get(key).getMyPassword().equals(thePassword)) {
                return true;
            }
        }        
        return false;
    }

    /**
     * Adds a user to the registered user list.
     * 
     * @param theUser an order to add to this shopping cart
     * @return true/false returns if registration is successfull
     */
    public boolean register(final User theUser) {
        if (theUser == null) {
            throw new NullPointerException();
        }
        FileLoader.writeUserToFile(USERFILE_NAME, theUser);
        return true;
    }
    /**
     * Empties the user list.
     */
    public void clear() {
        this.myUserList.clear();
    }

    @Override
    /**
     * String representation of the object
     * 
     */
    public String toString() {
        String printOutput = "Registered UserList {";
        
        for (String key: myUserList.keySet())  {
            printOutput += key + " " + myUserList.get(key) + "}";
        }
        return printOutput;
    }
}


package model;
import java.util.Objects;
/**
 * This is the User class for assignment(1) 
 * Made by: Mohammed Dawood
 * Course: TCSS-305(W21)
 * Date: 01/26/2021
 */

/**
 * Represents a single user for registration or sign-in. User is an immutable object.
 * 
 * Constructors and methods of this class throw NullPointerException if required parameters are
 * null.
 * 
 * @author username
 * @version Fall 2019
 */
public final class User {
    /**
     * @param myName
     */
    private final String myName;
    /**
     * @param myPassword
     */
    private final String myPassword;
    /**
     * @param myVIPStatus
     */
    private final boolean myVIPStatus;
   
    public User(final String theName, final String thePassword) {         
        if (theName  == null || thePassword == null) {
            throw new NullPointerException("parameter Type cannot be null");
        }
        if (theName.isEmpty() || thePassword.isEmpty()) {
            throw new IllegalArgumentException("The username, or password cannot be empty");
        }
        this.myName = theName;
        this.myPassword = thePassword;
        myVIPStatus = false; 
    }
 
    public User(final String theName, final String thePassword, final boolean theVIPStatus) {
        if (theName  == null || thePassword == null) {
            throw new NullPointerException("Parameter Type cannot be null");
        }
        if (theName.isEmpty() || thePassword.isEmpty()) {
            throw new IllegalArgumentException("the username, or password cannot be empty");
        }
        myName = theName;
        myPassword = thePassword;
        myVIPStatus = theVIPStatus;
    }
    public String getMyName() {
        return myName;
    }
    public String getMyPassword() {
        return myPassword;
    }
    public boolean getMyVIPStatus() {
        return myVIPStatus;
    }
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" (" + myName + ", " + myPassword + ", " + myVIPStatus + ')');
        return sb.toString();
    }
    public boolean equals(final Object theOtherObject) {
        if (this == theOtherObject) {
            return true;
        }
        if (theOtherObject == null) {
            return false;
        }
        final User user = (User) theOtherObject;
        if (user.myName == this.myName && user.myPassword == this.myPassword
                         && user.myVIPStatus == this.myVIPStatus) {
            return true;
        }
        return false;
    }
    public int hashCode() {
        return Objects.hash(myName, myPassword, myVIPStatus);
    }
}


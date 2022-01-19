/**
 * 
 */
package tests;

import static org.junit.Assert.*;

import model.Registration;
import model.User;
import org.junit.Before;
import org.junit.Test;

/**
 * @author modawood
 * @version 1.0
 */
public class RegistrationTest {
    /**
     * @myRegistration
     */
    private Registration myRegistration;
    
    @Before
    public void setUp() {
        myRegistration = new Registration();
    }

    /**
     * Test method for {@link model.Registration#Registration()}.
     */
    @Test
    public void testRegistration() {
        assertNotNull(myRegistration);
    }

    /**
     * Test method for {@link model.Registration#getMyUserList()}.
     */
    @Test
    public void testGetMyUserList() {
        assertNotNull(myRegistration);
        assertNotEquals(myRegistration.getMyUserList(), null);
    }

    /**
     * Test method for {@link model.Registration#login(java.lang.String, java.lang.String)}.
     */
    @Test
    public void testLogin() {
        assertNotNull("variable is null", myRegistration);
        assertEquals("login method failed", myRegistration.login("adam", "2020"), true);
        assertNotEquals("login method failed", myRegistration.login("adam", "2022"), true);
    }
    @Test
        (expected = NullPointerException.class)
    public void testloginNull() {
        assertNotNull("the variable is null", myRegistration);
        myRegistration.login("hey", null);
    }
    
    /**
     * Test method for {@link model.Registration#register(model.User)}.
     */
    public void testRegister() {
        assertNotNull("the variable is null", myRegistration);
        assertEquals("register method failed", myRegistration.register
                     (new User("project", "proj")), true);
    }
    @Test(expected = NullPointerException.class)
    public void testRegistrationNull() {
        assertNotNull("the variable is null", myRegistration);
        myRegistration.register(null);
    }

    /**
     * Test method for {@link model.Registration#clear()}.
     */
    @Test
    public void testClear() {
        assertNotNull("the variable is null", myRegistration);
        final Registration reg2 = new Registration();
        myRegistration.clear();
        reg2.clear();
        assertEquals("clear method failed", myRegistration.getMyUserList(),
                     reg2.getMyUserList());
        assertNotEquals("clear method failed", myRegistration.getMyUserList(), null);
    }

    /**
     * Test method for {@link model.Registration#toString()}.
     */
    @Test
    public void testToString() {
        assertNotNull("the variable is null", myRegistration);
        assertNotEquals("tostring method failed", myRegistration.toString());
    }

}

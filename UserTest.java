/**
 * 
 */
package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Objects;
import model.User;
import org.junit.Before;
import org.junit.Test;

/**
 * @author modawood
 * @version 1.0
 */
public class UserTest {
    /**
     * @myUser
     */
    private User myUser;
    
    /**
     * @myUser2
     */
    private User myUser2;
    
    @Before
    public void setUp() {
        myUser = new User("mohammed", "12345");
        myUser2 = new User("adam", "2020", false);
    }
    /**
     * Test method for {@link model.User#User(java.lang.String, java.lang.String)}.
     */
    @Test
    public void testUserStringString() {
        assertNotNull("user is null", myUser);
        assertEquals("test method failed", "mohammed", myUser.getMyName());
        assertEquals("test method failed", "12345", myUser.getMyPassword());
    }
    @Test(expected = NullPointerException.class)
    public void testUserStringStringNull() {
        new User(null, null);
    }
    @Test(expected = NullPointerException.class)
    public void testUserStringStringNull2() {
        new User("1", null);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testUserIllegalArgument() {
        myUser = new User("", "");
    }

    /**
     * Test method for {@link model.User#User(java.lang.String, java.lang.String, boolean)}.
     */
    @Test
    public void testUserStringStringBoolean() {
        assertNotNull("user is null", myUser2);
        assertEquals("test method failed", "adam", myUser2.getMyName());
        assertEquals("test method failed", "2020", myUser2.getMyPassword());
        assertEquals("test method failed", false, myUser2.getMyVIPStatus());
    }

    @Test(expected = NullPointerException.class)
    public void testUserStringStringBooleanNull() {
        new User(null, null, false);
    }
    @Test(expected = NullPointerException.class)
    public void testUserStringStringBooleanNull2() {
        new User("1", null, false);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testUser2IllegalArgument() {
        myUser2 = new User("", "", false);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testUser2IllegalArgument2() {
        myUser2 = new User("10", "", false);
    }

    /**
     * Test method for {@link model.User#getMyName()}.
     */
    @Test
    public void testGetMyName() {
        assertEquals("getmyname test method failed", "adam", myUser2.getMyName());
        assertEquals("getmynametest method failed", "mohammed", myUser.getMyName());
    }

    /**
     * Test method for {@link model.User#getMyPassword()}.
     */
    @Test
    public void testGetMyPassword() {
        assertEquals("getmypassword test method failed", "12345", myUser.getMyPassword());
        assertEquals("getmypassword test method failed", "2020", myUser2.getMyPassword());
    }

    /**
     * Test method for {@link model.User#getMyVIPStatus()}.
     */
    @Test
    public void testGetMyVIPStatus() {
        assertEquals("getmyvipstatus test method failed", false, myUser2.getMyVIPStatus());
    }

    /**
     * Test method for {@link model.User#toString()}.
     */
    @Test
    public void testToString() {
        assertEquals("tostring test method failed", "User (mohammed, 12345, false)", myUser.toString());
        assertEquals("tostring test method failed", "User (adam, 2020, false)", myUser2.toString());
    }

    /**
     * Test method for {@link model.User#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObject() {
        final User user3 = new User("mohammed", "12345");
        assertEquals("equals test method failed", user3, myUser);
        assertEquals("equals test method failed", myUser, myUser);
        assertNotEquals("equals test method failed", myUser2, myUser);
        assertNotEquals("equals test method failed", myUser2, null);
    }

    /**
     * Test method for {@link model.User#hashCode()}.
     */
    @Test
    public void testHashCode() {
        assertEquals("hashcode test method failed", Objects.hash(myUser2.getMyName(), myUser2.getMyPassword()
                  , myUser2.getMyVIPStatus()), myUser2.hashCode());
                 
        final User user3 = myUser2;
        assertEquals("hashcode test method failed", myUser2.hashCode(), user3.hashCode());
    }

}

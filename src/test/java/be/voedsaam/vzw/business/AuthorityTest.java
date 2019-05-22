package be.voedsaam.vzw.business;

import be.voedsaam.vzw.business.impl.Employee;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AuthorityTest {

    private Authority classUnderTest;

    @Before
    public void setup() {
        classUnderTest = new Authority();
    }

    @Test
    public void testConstructor() {
        classUnderTest = new Authority("ADMIN");
        assertEquals("ROLE_ADMIN", classUnderTest.getAuthority());
    }

    @Test
    public void testAuthority() {
        classUnderTest.setAuthority("test authority");
        assertEquals("test authority", classUnderTest.getAuthority());
        classUnderTest.setAuthority("test2 authority");
        assertEquals("test2 authority", classUnderTest.getAuthority());
    }

    @Test
    public void testUser() {
        assertNull(classUnderTest.getUser());
    }

    @Test
    public void testEquals() {
        classUnderTest = new Authority("ADMIN");
        Authority authority = new Authority("ADMIN");

        assertEquals(authority,classUnderTest);
        authority.setUser(new Employee());

        assertEquals(authority,classUnderTest);
        assertEquals(authority.hashCode(),classUnderTest.hashCode());
        authority.setAuthority("ROLE_POO");

        assertNotEquals(authority,classUnderTest);
        assertNotEquals(authority.hashCode(),classUnderTest.hashCode());
        authority.setAuthority("ROLE_ADMIN");

        assertEquals(authority,classUnderTest);
        assertEquals(authority.hashCode(),classUnderTest.hashCode());

    }

}
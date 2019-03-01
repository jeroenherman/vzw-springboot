package be.voedsaam.vzw.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import be.voedsaam.vzw.business.Authority;

public class AuthorityTest {

private Authority classUnderTest;

@Before
public void setup() {
	classUnderTest = new Authority();
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
public void testToString() {
	fail("Not yet implemented");
}

}
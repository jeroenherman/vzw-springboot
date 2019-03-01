package be.voedsaam.vzw.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import be.voedsaam.vzw.business.Address;

public class AddressTest {

private Address classUnderTest;

@Before
public void setup() {
	classUnderTest = new Address();
}

@Test
public void testStreet() {
	classUnderTest.setStreet("test street");
	assertEquals("test street", classUnderTest.getStreet());
	classUnderTest.setStreet("test2 street");
	assertEquals("test2 street", classUnderTest.getStreet());
}

@Test
public void testNumber() {
	assertNull(classUnderTest.getNumber());
}

@Test
public void testPostalCode() {
	assertNull(classUnderTest.getPostalCode());
}

@Test
public void testCity() {
	classUnderTest.setCity("test City");
	assertEquals("test City", classUnderTest.getCity());
	classUnderTest.setCity("test2 City");
	assertEquals("test2 City", classUnderTest.getCity());
}

@Test
public void testEquals() {
	fail("Not yet implemented");
}

@Test
public void testHashCode() {
	fail("Not yet implemented");
}

}
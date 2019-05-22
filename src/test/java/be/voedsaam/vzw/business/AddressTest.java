package be.voedsaam.vzw.business;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

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
	classUnderTest.setStreet("testStreet");
	classUnderTest.setNumber(10);
	classUnderTest.setCity("stad");
	classUnderTest.setPostalCode(123);
	classUnderTest.setId(new Long(10));

	Address a = new Address();

	a.setStreet("testStreet");
	a.setNumber(10);
	a.setCity("stad");
	a.setPostalCode(123);
	a.setId(new Long(10));

	assertEquals(a, classUnderTest);
	assertEquals(a.hashCode(),classUnderTest.hashCode());

	a.setId(null);

	assertEquals(a, classUnderTest);
	assertEquals(a.hashCode(),classUnderTest.hashCode());

	a.setStreet(null);

	assertNotEquals(a, classUnderTest);
	assertNotEquals(a.hashCode(),classUnderTest.hashCode());


}


}
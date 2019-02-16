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
private Address a1;
private Address a2;
@Before
public void setup() {
	classUnderTest = new Address();
}


@Test
public void testId() {
	assertNull(classUnderTest.getId());
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
	a1 = new Address();
	a1.setId(Long.valueOf(1));
	a1.setCity("Sint-Niklaas");
	a1.setStreet("sportlaan");
	a1.setNumber(33);
	a1.setPostalCode(9170);

	a2 = new Address();
	a2.setId(Long.valueOf(1));
	a2.setCity("Sint-Niklaas");
	a2.setStreet("sportlaan");
	a2.setNumber(33);
	a2.setPostalCode(9170);

	assertEquals(a1,a2);
	assertEquals(a2,a1);

}

@Test
public void testHashCode() {
	testEquals();
	assertTrue(a1.hashCode()== a2.hashCode());
	assertTrue(a2.hashCode()== a1.hashCode());
}

}
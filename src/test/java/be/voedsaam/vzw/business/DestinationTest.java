package be.voedsaam.vzw.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import be.voedsaam.vzw.business.Destination;

import java.util.ArrayList;

public class DestinationTest {

private Destination classUnderTest;
private Destination d1;
private Destination d2;
@Before
public void setup() {
	classUnderTest = new Destination();
}

@Test
public void testId() {
	assertNull(classUnderTest.getId());
}

@Test
public void testAddress() {
	assertNull(classUnderTest.getAddress());
}

@Test
public void testAgreements() {
	assertNull(classUnderTest.getAgreements());
}

@Test
public void testTasks() {
	assertNull(classUnderTest.getTasks());
}

@Test
public void testContactInfo() {
	classUnderTest.setContactInfo("test contactInfo");
	assertEquals("test contactInfo", classUnderTest.getContactInfo());
	classUnderTest.setContactInfo("test2 contactInfo");
	assertEquals("test2 contactInfo", classUnderTest.getContactInfo());
}

@Test
public void testDestinationName() {
	classUnderTest.setDestinationName("test destinationName");
	assertEquals("test destinationName", classUnderTest.getDestinationName());
	classUnderTest.setDestinationName("test2 destinationName");
	assertEquals("test2 destinationName", classUnderTest.getDestinationName());
}

@Test
public void testEquals() {
	d1 = new Destination();
	d1.setId(Long.valueOf(1));
	d1.setContactInfo("blabla");
	d1.setDestinationName("thuis");
	d2 = new Destination();
	d2.setId(Long.valueOf(1));
	d2.setContactInfo("blabla");
	d2.setDestinationName("thuis");

	assertEquals(d1,d2);
	assertEquals(d2,d1);

}

@Test
public void testHashCode() {
	testEquals();
	assertTrue(d1.hashCode()==d2.hashCode());
	assertTrue(d2.hashCode()==d1.hashCode());

}

}
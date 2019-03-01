package be.voedsaam.vzw.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import be.voedsaam.vzw.business.Destination;

public class DestinationTest {

private Destination classUnderTest;

@Before
public void setup() {
	classUnderTest = new Destination();
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
public void testDrives() {
	assertNull(classUnderTest.getDrives());
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
	fail("Not yet implemented");
}

@Test
public void testHashCode() {
	fail("Not yet implemented");
}

@Test
public void testAddDrive() {
	fail("Not yet implemented");
}

@Test
public void testRemoveDrive() {
	fail("Not yet implemented");
}

@Test
public void testAddTask() {
	fail("Not yet implemented");
}

@Test
public void testRemoveTask() {
	fail("Not yet implemented");
}

@Test
public void testRemoveAgreement() {
	fail("Not yet implemented");
}

@Test
public void testAddAgreement() {
	fail("Not yet implemented");
}

}
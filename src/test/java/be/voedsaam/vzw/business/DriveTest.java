package be.voedsaam.vzw.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import be.voedsaam.vzw.business.Drive;

public class DriveTest {

private Drive classUnderTest;

@Before
public void setup() {
	classUnderTest = new Drive();
}

@Test
public void testDescription() {
	classUnderTest.setDescription("test description");
	assertEquals("test description", classUnderTest.getDescription());
	classUnderTest.setDescription("test2 description");
	assertEquals("test2 description", classUnderTest.getDescription());
}

@Test
public void testStartTime() {
	assertNull(classUnderTest.getStartTime());
}

@Test
public void testEndTime() {
	assertNull(classUnderTest.getEndTime());
}

@Test
public void testUsers() {
	assertNull(classUnderTest.getUsers());
}

@Test
public void testDestinations() {
	assertNull(classUnderTest.getDestinations());
}

@Test
public void testSchedule() {
	assertNull(classUnderTest.getSchedule());
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
public void testClear() {
	fail("Not yet implemented");
}

@Test
public void testRemoveDestination() {
	fail("Not yet implemented");
}

@Test
public void testAddUser() {
	fail("Not yet implemented");
}

@Test
public void testAddDestination() {
	fail("Not yet implemented");
}

@Test
public void testRemoveUser() {
	fail("Not yet implemented");
}

}
package be.voedsaam.vzw.service.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import be.voedsaam.vzw.service.dto.DriveDTO;

public class DriveDTOTest {

private DriveDTO classUnderTest;

@Before
public void setup() {
	classUnderTest = new DriveDTO();
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
public void testDriver() {
	classUnderTest.setDriver("test driver");
	assertEquals("test driver", classUnderTest.getDriver());
	classUnderTest.setDriver("test2 driver");
	assertEquals("test2 driver", classUnderTest.getDriver());
}

@Test
public void testAttendee() {
	classUnderTest.setAttendee("test attendee");
	assertEquals("test attendee", classUnderTest.getAttendee());
	classUnderTest.setAttendee("test2 attendee");
	assertEquals("test2 attendee", classUnderTest.getAttendee());
}

@Test
public void testDepotHelp() {
	classUnderTest.setDepotHelp("test depotHelp");
	assertEquals("test depotHelp", classUnderTest.getDepotHelp());
	classUnderTest.setDepotHelp("test2 depotHelp");
	assertEquals("test2 depotHelp", classUnderTest.getDepotHelp());
}

@Test
public void testId() {
	assertNull(classUnderTest.getId());
}

@Test
public void testEquals() {
	fail("Not yet implemented");
}

@Test
public void testToString() {
	fail("Not yet implemented");
}

@Test
public void testHashCode() {
	fail("Not yet implemented");
}

}
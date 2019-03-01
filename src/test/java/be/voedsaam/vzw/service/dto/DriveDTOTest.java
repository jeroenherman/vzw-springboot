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
public void testDrivers() {
	assertNull(classUnderTest.getDrivers());
}

@Test
public void testAttendees() {
	assertNull(classUnderTest.getAttendees());
}

@Test
public void testDepotHelps() {
	assertNull(classUnderTest.getDepotHelps());
}

@Test
public void testSchedule() {
	classUnderTest.setSchedule("test schedule");
	assertEquals("test schedule", classUnderTest.getSchedule());
	classUnderTest.setSchedule("test2 schedule");
	assertEquals("test2 schedule", classUnderTest.getSchedule());
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
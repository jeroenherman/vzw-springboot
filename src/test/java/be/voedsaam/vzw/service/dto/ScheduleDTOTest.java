package be.voedsaam.vzw.service.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import be.voedsaam.vzw.service.dto.ScheduleDTO;

public class ScheduleDTOTest {

private ScheduleDTO classUnderTest;

@Before
public void setup() {
	classUnderTest = new ScheduleDTO();
}

@Test
public void testId() {
	assertNull(classUnderTest.getId());
}

@Test
public void testName() {
	classUnderTest.setName("test name");
	assertEquals("test name", classUnderTest.getName());
	classUnderTest.setName("test2 name");
	assertEquals("test2 name", classUnderTest.getName());
}

@Test
public void testOwner() {
	classUnderTest.setOwner("test owner");
	assertEquals("test owner", classUnderTest.getOwner());
	classUnderTest.setOwner("test2 owner");
	assertEquals("test2 owner", classUnderTest.getOwner());
}

@Test
public void testViewers() {
	assertNull(classUnderTest.getViewers());
}

@Test
public void testDrives() {
	assertNull(classUnderTest.getDrives());
}

}
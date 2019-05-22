package be.voedsaam.vzw.service.dto;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EventDTOTest {

private EventDTO classUnderTest;

@Before
public void setup() {
	classUnderTest = new EventDTO();
}

@Test
public void testId() {
	assertNull(classUnderTest.getId());
}

@Test
public void testTitle() {
	classUnderTest.setTitle("test title");
	assertEquals("test title", classUnderTest.getTitle());
	classUnderTest.setTitle("test2 title");
	assertEquals("test2 title", classUnderTest.getTitle());
}

@Test
public void testStart() {
	classUnderTest.setStart("test start");
	assertEquals("test start", classUnderTest.getStart());
	classUnderTest.setStart("test2 start");
	assertEquals("test2 start", classUnderTest.getStart());
}

@Test
public void testEnd() {
	classUnderTest.setEnd("test end");
	assertEquals("test end", classUnderTest.getEnd());
	classUnderTest.setEnd("test2 end");
	assertEquals("test2 end", classUnderTest.getEnd());
}

@Test
public void testAllDay() {
	assertFalse(classUnderTest.isAllDay());
	classUnderTest.setAllDay(true);
	assertTrue(classUnderTest.isAllDay());
}

}
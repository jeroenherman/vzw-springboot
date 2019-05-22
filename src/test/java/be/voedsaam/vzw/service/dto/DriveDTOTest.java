package be.voedsaam.vzw.service.dto;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

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

		assertTrue(LocalDateTime.now().toLocalDate().equals(classUnderTest.getStartTime().toLocalDate()));
		assertTrue(classUnderTest.getStartTime().toLocalTime().equals(LocalTime.of(9, 00)));
	}

	@Test
	public void testEndTime() {
		assertTrue(LocalDateTime.now().toLocalDate().equals(classUnderTest.getEndTime().toLocalDate()));
		assertTrue(classUnderTest.getEndTime().toLocalTime().equals(LocalTime.of(10, 00)));
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
		classUnderTest.setDescription("test");
		classUnderTest.setSchedule("testschedule");

		DriveDTO dto = new DriveDTO();
		dto.setDescription("test");
		dto.setSchedule("testschedule");

		assertEquals(dto, classUnderTest);
		assertEquals(dto.hashCode(), classUnderTest.hashCode());

	}

	@Test
	public void setStartTime() {
		classUnderTest.setStartTime(LocalDateTime.of(2019,12,12,9,00,00,00));
		assertTrue(classUnderTest.getStartTime().equals(LocalDateTime.of(2019,12,12,9,00,00,00)));

	}

	@Test
	public void setEndTime() {
		classUnderTest.setEndTime(LocalDateTime.of(2019,12,12,10,00,00,00));
		assertTrue(classUnderTest.getEndTime().equals(LocalDateTime.of(2019,12,12,10,00,00,00)));

	}

	@Test(expected = ArithmeticException.class)
	public void testSetEndTimeException(){
		setStartTime();
		setEndTime();
		classUnderTest.setEndTime(LocalDateTime.of(2019,12,12,8,00,00,00));

	}



	@Test
	public void setDrivers() {
		List<String> strings = new ArrayList<>();
		strings.add("Jan");
		strings.add("Piet");
		classUnderTest.setDrivers(strings);
		assertEquals(2, classUnderTest.getDrivers().size());
		assertEquals("Jan",classUnderTest.getDrivers().get(0));
		assertEquals("Piet",classUnderTest.getDrivers().get(1));
	}



	@Test
	public void setAttendees() {
		List<String> strings = new ArrayList<>();
		strings.add("Jan");
		strings.add("Piet");
		classUnderTest.setAttendees(strings);
		assertEquals(2, classUnderTest.getAttendees().size());
		assertEquals("Jan",classUnderTest.getAttendees().get(0));
		assertEquals("Piet",classUnderTest.getAttendees().get(1));
	}


	@Test
	public void setDepotHelps() {
		List<String> strings = new ArrayList<>();
		strings.add("Jan");
		strings.add("Piet");
		classUnderTest.setDepotHelps(strings);
		assertEquals(2, classUnderTest.getDepotHelps().size());
		assertEquals("Jan",classUnderTest.getDepotHelps().get(0));
		assertEquals("Piet",classUnderTest.getDepotHelps().get(1));
	}

	@Test
	public void isUsers() {
		assertFalse(classUnderTest.isUsers());
	}

	@Test
	public void setUsers() {

		classUnderTest.setUsers(true);
		assertTrue(classUnderTest.isUsers());
	}
}
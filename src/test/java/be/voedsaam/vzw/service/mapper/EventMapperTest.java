package be.voedsaam.vzw.service.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import be.voedsaam.vzw.business.Drive;
import be.voedsaam.vzw.business.impl.Volunteer;
import be.voedsaam.vzw.business.repository.DriveRepository;
import be.voedsaam.vzw.enums.Role;
import be.voedsaam.vzw.service.dto.DriveDTO;
import be.voedsaam.vzw.service.dto.EventDTO;
import org.junit.Before;
import org.junit.Test;
import be.voedsaam.vzw.service.mapper.EventMapper;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class EventMapperTest {

private EventMapper classUnderTest;
private Drive b;
private EventDTO d;

@Mock
	DriveRepository mockRepository;

@Before
public void setup() {
	classUnderTest = new EventMapper();
	classUnderTest.setDriveRepository(mockRepository);
	b = new Drive();
	b.setDescription("beschrijving");
	b.setStartTime(LocalDateTime.of(2019, 12, 12, 9, 00, 00, 00));
	b.setEndTime(LocalDateTime.of(2019, 12, 12, 10, 00, 00, 00));
	b.setId(new Long(123));

	Volunteer driver = new Volunteer("driver 1");
	driver.setRole(Role.DRIVER);

	Volunteer attendee = new Volunteer("attendee 1");
	attendee.setRole(Role.ATTENDEE);

	Volunteer depotHelp = new Volunteer("depothelp 1");
	depotHelp.setRole(Role.DEPOTHELP);

	b.addUser(driver);
	b.addUser(attendee);
	b.addUser(depotHelp);
	d = new EventDTO();
}


	@Test
	public void testNullBusinessObject() {
		b = null;
		d = classUnderTest.mapToDTO(b);
		assertNull(d);
	}

	@Test
	public void testNullDTO() {
		d = null;
		b = classUnderTest.mapToObj(d);
		assertNull(b);
	}

	@Test
	public void testMapToDTO() {
		d = classUnderTest.mapToDTO(b);
		assertTrue(d.getTitle().equals(b.getDescription()));
		assertTrue(d.getStart().equals(b.getStartTime().format(DateTimeFormatter.ISO_DATE_TIME)));
		assertTrue(d.getEnd().equals(b.getEndTime().format(DateTimeFormatter.ISO_DATE_TIME)));
		assertEquals(d.getId(),b.getId());
	}

	@Test
	public void testMapToObjWithRepo() {
		Optional<Drive> o = Optional.of(b);
		d.setId(new Long(123));
		d.setTitle("beschrijving2");
		d.setStart(LocalDateTime.of(2019, 12, 12, 8, 00, 00, 00).format(DateTimeFormatter.ISO_DATE_TIME));
		d.setEnd(LocalDateTime.of(2019, 12, 12, 11, 00, 00, 00).format(DateTimeFormatter.ISO_DATE_TIME));
		Mockito.when(mockRepository.findById(new Long(123))).thenReturn(o);
		b = classUnderTest.mapToObj(d);
		assertTrue(d.getTitle().equals(b.getDescription()));
		assertTrue(d.getStart().equals(b.getStartTime().format(DateTimeFormatter.ISO_DATE_TIME)));
		assertTrue(d.getEnd().equals(b.getEndTime().format(DateTimeFormatter.ISO_DATE_TIME)));
		assertEquals(3, b.getUsers().size());
	}

	@Test
	public void testMapToObjWithoutRepo() {
		d.setTitle("beschrijving");
		d.setStart(LocalDateTime.of(2019, 12, 12, 9, 00, 00, 00).format(DateTimeFormatter.ISO_DATE_TIME));
		d.setEnd(LocalDateTime.of(2019, 12, 12, 10, 00, 00, 00).format(DateTimeFormatter.ISO_DATE_TIME));
		d.setId(new Long(10));
		b = classUnderTest.mapToObj(d);
		assertTrue(d.getTitle().equals(b.getDescription()));
		assertTrue(d.getStart().equals(b.getStartTime().format(DateTimeFormatter.ISO_DATE_TIME)));
		assertTrue(d.getEnd().equals(b.getEndTime().format(DateTimeFormatter.ISO_DATE_TIME)));
	}
}
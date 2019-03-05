package be.voedsaam.vzw.service.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import be.voedsaam.vzw.business.Address;
import be.voedsaam.vzw.business.Destination;
import be.voedsaam.vzw.business.Drive;
import be.voedsaam.vzw.business.Schedule;
import be.voedsaam.vzw.business.impl.Employee;
import be.voedsaam.vzw.business.repository.ScheduleRepository;
import be.voedsaam.vzw.business.repository.UserRepository;
import be.voedsaam.vzw.enums.Role;
import be.voedsaam.vzw.service.dto.ScheduleDTO;
import org.junit.Before;
import org.junit.Test;
import be.voedsaam.vzw.service.mapper.ScheduleMapper;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class ScheduleMapperTest {

private ScheduleMapper classUnderTest;
private Employee owner;
private Employee newowner;
private Employee viewer;
private Drive drive;
private Schedule b;
private ScheduleDTO d;
@Mock
ScheduleRepository scheduleRepository;


@Before
public void setup() {
	classUnderTest = new ScheduleMapper();
	classUnderTest.setScheduleRepository(scheduleRepository);

	owner = new Employee("jeroen","herman","jeroen.herman@outlook.be","123456");
	owner.setId(new Long(123));
	owner.setRole(Role.COORDINATOR);

	newowner = new Employee("xander","herman","xander.herman@outlook.be","123456");
	newowner.setId(new Long(125));
	newowner.setRole(Role.COORDINATOR);

	viewer = new Employee("xander","herman","jeroen.herman@outlook.be","123456");
	viewer.setId(new Long(124));
	viewer.setRole(Role.LOGISTICS);
	drive = new Drive();
	drive.setDescription("beschrijving");
	drive.setStartTime(LocalDateTime.of(2019, 12, 12, 9, 00, 00, 00));
	drive.setEndTime(LocalDateTime.of(2019, 12, 12, 10, 00, 00, 00));
	drive.setId(new Long(123));

	b = new Schedule();
	b.setId(new Long(123));
	b.setName("testSchedule");
	b.addDrive(drive);
	b.addUser(owner);
	b.addUser(viewer);

	d = new ScheduleDTO();
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
		d= null;
		d = classUnderTest.mapToDTO(b);

		assertEquals(d.getOwner(), owner.getFullName());
		assertEquals(1,d.getDrives().size());
		assertEquals(drive.getDescription(),d.getDrives().get(0));
		assertEquals(d.getName(),b.getName());
		assertEquals(2, d.getViewers().size());
		assertEquals(owner.getFullName(),d.getViewers().get(0));
		assertEquals(viewer.getFullName(),d.getViewers().get(1));

	}

	@Test
	public void testMapToObjWithRepo() {
		d.setName("nieuw schema");
		Optional<Schedule> o = Optional.of(b);
		Mockito.when(scheduleRepository.findById(new Long(123))).thenReturn(o);
		d.setId(new Long(123));
		b = classUnderTest.mapToObj(d);

		assertEquals(1,b.getDrives().size());
		assertEquals(d.getName(),b.getName());
		assertEquals(2, b.getUsers().size());
		assertEquals(d.getId(), b.getId());

	}

	@Test
	public void testMapToObjWithoutRepo() {

		d.setName("nieuw schema");
		d.setId(new Long (10));

		b = classUnderTest.mapToObj(d);
		assertEquals(d.getId(), b.getId());
		assertEquals(d.getName(), b.getName());

	}
}
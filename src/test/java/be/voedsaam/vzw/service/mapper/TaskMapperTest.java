package be.voedsaam.vzw.service.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import be.voedsaam.vzw.business.Destination;
import be.voedsaam.vzw.business.Task;
import be.voedsaam.vzw.business.repository.TaskRepository;
import be.voedsaam.vzw.enums.Role;
import be.voedsaam.vzw.service.dto.TaskDTO;
import org.junit.Before;
import org.junit.Test;
import be.voedsaam.vzw.service.mapper.TaskMapper;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class TaskMapperTest {

private TaskMapper classUnderTest;
private Task b;
private TaskDTO d;
private Destination destination;

@Mock
	TaskRepository mockRepository;

@Before
public void setup() {
	classUnderTest = new TaskMapper();
	classUnderTest.setTaskRepository(mockRepository);
	b = new Task();
	destination = new Destination();
	destination.setDestinationName("bestemming");
	b.setDestination(destination);
	b.setId(new Long(123));
	b.setRole(Role.DRIVER);
	b.setTitle("taak1");
	b.setDiscription("taak voor bestuurder");

	d = new TaskDTO();
	d.setRole(Role.ATTENDEE);
	d.setTitle("taak2");
	d.setDescription("taak voor bijrijder");
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

		assertEquals(d.getId(), b.getId());
		assertEquals(d.getTitle(), b.getTitle());
		assertEquals(d.getDescription(), b.getDiscription());
		assertEquals(d.getRole(),b.getRole());

	}

	@Test
	public void testMapToObjWithRepo() {
		Optional<Task> o = Optional.of(b);
		Mockito.when(mockRepository.findById(new Long(123))).thenReturn(o);
		d.setId(new Long(123));
		b = classUnderTest.mapToObj(d);
		assertEquals(d.getId(), b.getId());
		assertEquals(d.getTitle(), b.getTitle());
		assertEquals(d.getDescription(), b.getDiscription());
		assertEquals(d.getRole(),b.getRole());
		assertEquals(destination,b.getDestination());
	}

	@Test
	public void testMapToObjWithoutRepo() {

		b = classUnderTest.mapToObj(d);
		assertEquals(d.getId(), b.getId());
		assertEquals(d.getTitle(), b.getTitle());
		assertEquals(d.getDescription(), b.getDiscription());
		assertEquals(d.getRole(),b.getRole());
	}
}
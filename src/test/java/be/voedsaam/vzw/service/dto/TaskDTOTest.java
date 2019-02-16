package be.voedsaam.vzw.service.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import be.voedsaam.vzw.service.dto.TaskDTO;

public class TaskDTOTest {

private TaskDTO classUnderTest;

@Before
public void setup() {
	classUnderTest = new TaskDTO();
}

@Test
public void testDescription() {
	classUnderTest.setDescription("test description");
	assertEquals("test description", classUnderTest.getDescription());
	classUnderTest.setDescription("test2 description");
	assertEquals("test2 description", classUnderTest.getDescription());
}

@Test
public void testTitle() {
	classUnderTest.setTitle("test title");
	assertEquals("test title", classUnderTest.getTitle());
	classUnderTest.setTitle("test2 title");
	assertEquals("test2 title", classUnderTest.getTitle());
}

@Test
public void testRole() {
	assertNull(classUnderTest.getRole());
}

}
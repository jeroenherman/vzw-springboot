package be.voedsaam.vzw.service.dto;

import be.voedsaam.vzw.enums.Role;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TaskDTOTest {
    private TaskDTO classUnderTest;

    @Before
    public void setup() {
        classUnderTest = new TaskDTO();
    }

    @Test
    public void getDescription() throws Exception {
        assertNull(classUnderTest.getDescription());
    }

    @Test
    public void setDescription() throws Exception {
        classUnderTest.setDescription("test description");
        assertEquals("test description", classUnderTest.getDescription());
        classUnderTest.setDescription("test2 description");
        assertEquals("test2 description", classUnderTest.getDescription());
    }

    @Test
    public void getTitle() throws Exception {
        assertNull(classUnderTest.getTitle());
    }

    @Test
    public void setTitle() throws Exception {
        classUnderTest.setTitle("test description");
        assertEquals("test description", classUnderTest.getTitle());
        classUnderTest.setTitle("test2 description");
        assertEquals("test2 description", classUnderTest.getTitle());
    }

    @Test
    public void getRole() throws Exception {
        assertNull(classUnderTest.getRole());
    }

    @Test
    public void setRole() throws Exception {
        classUnderTest.setRole(Role.DRIVER);
        assertEquals(Role.DRIVER,classUnderTest.getRole());
        classUnderTest.setRole(Role.DEPOTHELP);
        assertEquals(Role.DEPOTHELP,classUnderTest.getRole());
        classUnderTest.setRole(Role.ATTENDEE);
        assertEquals(Role.ATTENDEE,classUnderTest.getRole());
        classUnderTest.setRole(Role.VOLUNTEER);
        assertEquals(Role.VOLUNTEER,classUnderTest.getRole());
    }

    @Test
    public void getId() throws Exception {
        assertNull(classUnderTest.getId());
    }

    @Test
    public void setId() throws Exception {
        classUnderTest.setId(new Long(10));
        assertEquals(new Long(10), classUnderTest.getId());
        classUnderTest.setId(new Long(20));
        assertEquals(new Long(20), classUnderTest.getId());
        classUnderTest.setId(new Long(-20));
        assertEquals(new Long(-20), classUnderTest.getId());

    }

}
package be.voedsaam.vzw.business;

import be.voedsaam.vzw.enums.Role;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TaskTest {
    private Task classToTest;
    private Role role;
    private Destination destination;



    @Before
    public void setUp() throws Exception {
        classToTest = new Task();
        destination = new Destination();
    }

    @Test
    public void getTitle() throws Exception {
        assertNull(classToTest.getTitle());

    }

    @Test
    public void setTitle() throws Exception {
        classToTest.setTitle("test");
        assertEquals("test", classToTest.getTitle());
        classToTest.setTitle("test2");
        assertEquals("test2", classToTest.getTitle());

    }

    @Test
    public void getDiscription() throws Exception {
        assertNull(classToTest.getDiscription());
    }

    @Test
    public void setDiscription() throws Exception {
        classToTest.setDiscription("test");
        assertEquals("test", classToTest.getDiscription());
        classToTest.setDiscription("test2");
        assertEquals("test2", classToTest.getDiscription());

    }

    @Test
    public void getRole() throws Exception {
        assertNull(classToTest.getRole());

    }

    @Test
    public void setRole() throws Exception {
        classToTest.setRole(Role.DRIVER);
        assertEquals(Role.DRIVER, classToTest.getRole());
        classToTest.setRole(Role.ATTENDEE);
        assertEquals(Role.ATTENDEE, classToTest.getRole());
    }

    @Test
    public void setDestination() throws Exception {
        classToTest.setDestination(destination);
        assertEquals(destination, classToTest.getDestination());
        destination.setDestinationName("testdestination");
        classToTest.setDestination(destination);
        assertEquals(destination, classToTest.getDestination());

    }

    @Test
    public void getDestination() throws Exception {
        assertNull(classToTest.getDestination());

    }

    @Test
    public void clear() throws Exception {
        setDestination();
        classToTest.clear();
        assertNull(classToTest.getDestination());
        assertNull(classToTest.getRole());
    }

    @Test
    public void equals() throws Exception {
        classToTest.setTitle("test");
        classToTest.setDiscription("testd");
        classToTest.setDestination(destination);
        classToTest.setRole(Role.DRIVER);
        classToTest.setId(new Long(10));

        Task task = new Task();

        task.setTitle("test");
        task.setDiscription("testd");
        task.setDestination(destination);
        task.setRole(Role.DRIVER);
        task.setId(new Long(10));

        assertEquals(task,classToTest);
        assertEquals(task.hashCode(), classToTest.hashCode());

        task.setId(null);

        assertEquals(task,classToTest);
        assertEquals(task.hashCode(), classToTest.hashCode());
        task.setDestination(null);
        assertEquals(task,classToTest);
        assertEquals(task.hashCode(), classToTest.hashCode());

    }

}
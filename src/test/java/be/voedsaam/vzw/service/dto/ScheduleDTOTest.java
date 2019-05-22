package be.voedsaam.vzw.service.dto;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

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
public void testViewers()
{
	assertEquals(0,classUnderTest.getViewers().size());
}

@Test
public void testDrives() {

	assertEquals(0,classUnderTest.getDrives().size());
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


    @Test
    public void setViewers() throws Exception {
        List<String> strings = new ArrayList<>();
        strings.add("Jan");
        strings.add("Piet");
        classUnderTest.setViewers(strings);
        assertEquals(2, classUnderTest.getViewers().size());
        assertEquals("Jan",classUnderTest.getViewers().get(0));
        assertEquals("Piet",classUnderTest.getViewers().get(1));
    }


    @Test
    public void setDrives() throws Exception {
        List<String> strings = new ArrayList<>();
        strings.add("rit1");
        strings.add("rit2");
        classUnderTest.setDrives(strings);
        assertEquals(2, classUnderTest.getDrives().size());
        assertEquals("rit1",classUnderTest.getDrives().get(0));
        assertEquals("rit2",classUnderTest.getDrives().get(1));
    }
}
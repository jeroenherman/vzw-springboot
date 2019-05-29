package be.voedsaam.vzw.business;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ContactTest {
    Contact classUnderTest;

    @Before
    public void setUp() throws Exception {
        classUnderTest = new Contact();
    }

    @Test
    public void getName() throws Exception {
        assertNull(classUnderTest.getName());
    }

    @Test
    public void setName() throws Exception {
        classUnderTest.setName("test1");
        assertEquals("test1", classUnderTest.getName());
        classUnderTest.setName("test2");
        assertEquals("test2", classUnderTest.getName());
    }

    @Test
    public void getEmail() throws Exception {
        assertNull(classUnderTest.getEmail());
    }

    @Test
    public void setEmail() throws Exception {
        classUnderTest.setEmail("test1");
        assertEquals("test1", classUnderTest.getEmail());
        classUnderTest.setEmail("test2");
        assertEquals("test2", classUnderTest.getEmail());
    }

    @Test
    public void getTel() throws Exception {
        assertNull(classUnderTest.getTel());
    }

    @Test
    public void setTel() throws Exception {
        classUnderTest.setTel("test1");
        assertEquals("test1", classUnderTest.getTel());
        classUnderTest.setTel("test2");
        assertEquals("test2", classUnderTest.getTel());
    }

    @Test
    public void getSubject() throws Exception {
        assertNull(classUnderTest.getSubject());
    }

    @Test
    public void setSubject() throws Exception {
        classUnderTest.setSubject("test1");
        assertEquals("test1", classUnderTest.getSubject());
        classUnderTest.setSubject("test2");
        assertEquals("test2", classUnderTest.getSubject());
    }

    @Test
    public void getMessage() throws Exception {
        assertNull(classUnderTest.getMessage());
    }

    @Test
    public void setMessage() throws Exception {
        classUnderTest.setMessage("test1");
        assertEquals("test1", classUnderTest.getMessage());
        classUnderTest.setMessage("test2");
        assertEquals("test2", classUnderTest.getMessage());
    }

}
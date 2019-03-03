package be.voedsaam.vzw.business.impl;

import be.voedsaam.vzw.business.Authority;
import be.voedsaam.vzw.business.Schedule;
import be.voedsaam.vzw.business.User;
import be.voedsaam.vzw.enums.Color;
import be.voedsaam.vzw.enums.Role;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EmployeeTest {

    private Employee classToTest;
    private Schedule schedule;


    @Before
    public void setUp() throws Exception {

        classToTest = new Employee();
        schedule = new Schedule();
        schedule.setName("testSchedule");


    }
    @Test
    public void testGetters(){
        assertNull(classToTest.getRole());
        assertNull(classToTest.getEmail());
        assertNull(classToTest.getFirstName());
        assertNull(classToTest.getLastName());
        assertNull(classToTest.getPassword());
        assertNull(classToTest.getAddress());
        assertNull(classToTest.getColor());
        assertNull(classToTest.getEncryptedPassword());
        assertNull(classToTest.getTel());
        assertEquals(0,classToTest.getAuthorities().size());
    }

    @Test
    public void testSetters(){
        classToTest.setFirstName("jeroen");
        assertEquals("jeroen", classToTest.getFirstName());
        classToTest.setLastName("herman");
        assertEquals("herman", classToTest.getLastName());
        assertEquals("jeroen herman", classToTest.getFullName());
        classToTest.setPassword("paswoord");
        assertEquals("paswoord", classToTest.getPassword());
        classToTest.setEncryptedPassword("encrypted");
        assertEquals("encrypted", classToTest.getEncryptedPassword());
        classToTest.setRole(Role.COORDINATOR);
        assertEquals(1, classToTest.getAuthorities().size());
        assertEquals(Role.COORDINATOR, classToTest.getRole());
        classToTest.setColor(Color.RED);
        assertEquals(Color.RED, classToTest.getColor());
        Authority admin = new Authority("ADMIN");
        classToTest.addAuthority(admin);
        assertEquals(2, classToTest.getAuthorities().size());
        assertTrue( classToTest.getAuthorities().contains(admin));
        classToTest.setTel("123456");
        assertEquals("123456", classToTest.getTel());
        classToTest.setId(new Long(10));
        assertEquals(new Long(10), classToTest.getId());

    }

    @Test
    public void addSchedule() throws Exception {
        testSetters();
        assertEquals(0,classToTest.getSchedules().size());
        classToTest.addSchedule(schedule);
        assertEquals(1,classToTest.getSchedules().size());


    }

    @Test
    public void removeSchedule() throws Exception {
        addSchedule();
        classToTest.removeSchedule(schedule);
        assertEquals(0,classToTest.getSchedules().size());

    }

    @Test(expected = UnsupportedOperationException.class)
    public void modifySchedules() throws Exception {
        addSchedule();
        classToTest.getSchedules().add(schedule);
    }

    @Test
    public void clear() throws Exception {
        addSchedule();
        classToTest.clear();
        assertEquals(0,classToTest.getSchedules().size());

    }

}
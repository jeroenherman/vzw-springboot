package be.voedsaam.vzw.business.impl;

import be.voedsaam.vzw.business.Authority;
import be.voedsaam.vzw.business.Drive;
import be.voedsaam.vzw.business.Schedule;
import be.voedsaam.vzw.enums.Color;
import be.voedsaam.vzw.enums.Role;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class VolunteerTest {
    private Volunteer classToTest;
    private Drive drive;
    private Schedule schedule;


    @Before
    public void setUp() throws Exception {
        classToTest = new Volunteer();
        schedule = new Schedule();
        schedule.setName("testSchedule");
        drive = new Drive();
        drive.setSchedule(schedule);
        drive.setDescription("test rit");
    }

    @Test
    public void testGetters(){
        assertEquals(Role.VOLUNTEER,classToTest.getRole());
        assertNull(classToTest.getEmail());
        assertNull(classToTest.getFirstName());
        assertNull(classToTest.getLastName());
        assertNull(classToTest.getPassword());
        assertNull(classToTest.getAddress());
        assertNull(classToTest.getColor());
        assertNull(classToTest.getEncryptedPassword());
        assertNull(classToTest.getTel());
        assertEquals(1,classToTest.getAuthorities().size());
        assertEquals("ROLE_VOLUNTEER",classToTest.getAuthorities().iterator().next().getAuthority());
    }

    @Test
    public void testSetters(){
        classToTest.setFirstName("kevin");
        assertEquals("kevin", classToTest.getFirstName());
        classToTest.setLastName("herman");
        assertEquals("herman", classToTest.getLastName());
        assertEquals("kevin herman", classToTest.getFullName());
        classToTest.setPassword("paswoord");
        assertEquals("paswoord", classToTest.getPassword());
        classToTest.setEncryptedPassword("encrypted");
        assertEquals("encrypted", classToTest.getEncryptedPassword());
        classToTest.setRole(Role.DRIVER);

        assertEquals(1, classToTest.getAuthorities().size());
        assertEquals(Role.DRIVER, classToTest.getRole());
        classToTest.setColor(Color.RED);
        assertEquals(Color.RED, classToTest.getColor());
        classToTest.setTel("123456");
        assertEquals("123456", classToTest.getTel());
        classToTest.setId(new Long(10));
        assertEquals(new Long(10), classToTest.getId());
    }

    @Test
    public void testModifyRole(){
        classToTest.setRole(Role.COORDINATOR);
        assertEquals(Role.VOLUNTEER,classToTest.getRole());
        assertEquals(1,classToTest.getAuthorities().size());
        classToTest.setRole(Role.LOGISTICS);
        assertEquals(1,classToTest.getAuthorities().size());
        assertEquals(Role.VOLUNTEER,classToTest.getRole());
        classToTest.setRole(Role.DRIVER);
        assertEquals(Role.DRIVER,classToTest.getRole());
        assertEquals(1,classToTest.getAuthorities().size());
        classToTest.setRole(Role.DEPOTHELP);
        assertEquals(Role.DEPOTHELP,classToTest.getRole());
        assertEquals(1,classToTest.getAuthorities().size());
        classToTest.setRole(Role.ATTENDEE);
        assertEquals(Role.ATTENDEE,classToTest.getRole());
        assertEquals(1,classToTest.getAuthorities().size());
        classToTest.setRole(Role.VOLUNTEER);
        assertEquals(Role.VOLUNTEER,classToTest.getRole());
        assertEquals(1,classToTest.getAuthorities().size());
    }

    @Test
    public void addDrive() throws Exception {
        testSetters();
        assertEquals(0,classToTest.getDrives().size());
        classToTest.addDrive(drive);
        assertEquals(1,classToTest.getDrives().size());

    }

    @Test
    public void removeDrive() throws Exception {
        addDrive();
        classToTest.removeDrive(drive);
        assertEquals(0,classToTest.getDrives().size());

    }


    @Test(expected = UnsupportedOperationException.class)
    public void modifyDrives() throws Exception {
        addDrive();
        classToTest.getDrives().add(drive);
    }

}
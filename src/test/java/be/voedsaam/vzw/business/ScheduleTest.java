package be.voedsaam.vzw.business;

import be.voedsaam.vzw.enums.Role;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class ScheduleTest {
    private Drive drive;
    private Employee e;
    private Volunteer driver;
    private Volunteer attendee;
    private Volunteer depothelp;
    private Destination start;
    private Destination end;
    private Address a1;
    private Address a2;

    private Schedule classUnderTest;
    @Before
    public void setUp() throws Exception {
        classUnderTest = new Schedule();
        e = new Employee();
        e.setRole(Role.COORDINATOR);
        driver = new Volunteer();
        driver.setFullName("driver 1");
        driver.setRole(Role.DRIVER);
        attendee = new Volunteer();
        attendee.setFullName("attendee 1");
        attendee.setRole(Role.ATTENDEE);
        depothelp = new Volunteer();
        depothelp.setFullName("depothelp 1");
        depothelp.setRole(Role.DEPOTHELP);
        start = new Destination();
        start.setDestinationName("start");
        a1 = new Address();
        a1.setStreet("straat1");
        start.setAddress(a1);
        end = new Destination();
        end.setDestinationName("end");
        a2 = new Address();
        a2.setStreet("straat2");
        end.setAddress(a2);
        drive = new Drive();
        drive.setStartTime(LocalDateTime.of(2019,02,02,9,00));
        drive.setEndTime(LocalDateTime.of(2019,02,02,10,00));
        drive.addDestination(start);
        drive.addDestination(end);
        drive.addUser(driver);
        drive.addUser(attendee);
        drive.addUser(depothelp);
    }

    @Test
    public void getName() throws Exception {
        assertNull(classUnderTest.getName());
    }

    @Test
    public void getUsers() throws Exception {
        assertEquals(0,classUnderTest.getUsers().size());
    }

    @Test
    public void getDrives() throws Exception {
        assertEquals(0,classUnderTest.getDrives().size());
    }

    @Test
    public void setName() throws Exception {
        classUnderTest.setName("testname");
        assertEquals("testname",classUnderTest.getName());
        classUnderTest.setName("testnam2");
        assertEquals("testnam2",classUnderTest.getName());
    }

    @Test
    public void addDrive() throws Exception {
        classUnderTest.addDrive(drive);
        assertEquals(1, classUnderTest.getDrives().size());
    }

    @Test
    public void removeDrive() throws Exception {
        addDrive();
        classUnderTest.removeDrive(drive);
        assertEquals(0, classUnderTest.getDrives().size());

    }

    @Test
    public void addUser() throws Exception {
        addDrive();
        Volunteer depotHelp = new Volunteer("volunteer new");
        drive.addUser(depotHelp);
        assertEquals(1, classUnderTest.getDrives().size());
    }

    @Test
    public void removeUser() throws Exception {
        addDrive();
        Volunteer depotHelp = new Volunteer("volunteer new");
        drive.removeUser(depotHelp);
        assertEquals(1, classUnderTest.getDrives().size());

    }

    @Test
    public void removeDrives() throws Exception {
        addDrive();
        addUser();
        classUnderTest.removeDrives();
        assertEquals(0, classUnderTest.getDrives().size());
    }

}
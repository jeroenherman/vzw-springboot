package be.voedsaam.vzw.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import be.voedsaam.vzw.enums.Role;
import org.junit.Before;
import org.junit.Test;
import be.voedsaam.vzw.business.Drive;

import java.time.LocalDateTime;

public class DriveTest {

private Drive classUnderTest;
	private Employee e;
	private Schedule s;
	private Volunteer driver;
	private Volunteer attendee;
	private Volunteer depothelp;
	private Destination start;
	private Destination end;
	private Address a1;
	private Address a2;

@Before
public void setup() {
	classUnderTest = new Drive();
	 e = new Employee();
	 e.setRole(Role.COORDINATOR);
	 s = new Schedule();
	s.addUser(e);
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
}

@Test
public void testDescription() {
	classUnderTest.setDescription("test description");
	assertEquals("test description", classUnderTest.getDescription());
	classUnderTest.setDescription("test2 description");
	assertEquals("test2 description", classUnderTest.getDescription());
}

@Test
public void testUsers() {
	assertEquals(0,classUnderTest.getUsers().size());
}

@Test
public void testDestinations() {
	assertEquals(0,classUnderTest.getDestinations().size());
}

@Test
public void testSchedule() {
	assertNull(classUnderTest.getSchedule());
}

@Test
public void testEquals() {
	classUnderTest.setDescription("testRit");
	classUnderTest.setStartTime(LocalDateTime.of(2019,02,02,9,00));
	classUnderTest.setEndTime(LocalDateTime.of(2019,02,02,10,00));
	Drive d = new Drive();
	d.setStartTime(LocalDateTime.of(2019,02,02,9,00));
	d.setEndTime(LocalDateTime.of(2019,02,02,10,00));
	d.setDescription("testRit");
	assertEquals(d,classUnderTest);
	assertEquals(d.hashCode(),classUnderTest.hashCode());
}


@Test
public void testClear() {
	testAddDestination();
	testAddUser();
	classUnderTest.clear();
	assertEquals(0,classUnderTest.getDestinations().size());
	assertEquals(0,classUnderTest.getUsers().size());
	assertNull(classUnderTest.getSchedule());
}

@Test
public void testRemoveDestination() {
	testAddDestination();
	classUnderTest.removeDestination(end);
	assertEquals(start, classUnderTest.getDestinations().get(0));
}

@Test
public void testAddUser() {
	classUnderTest.addUser(driver);
	classUnderTest.addUser(attendee);
	classUnderTest.addUser(depothelp);
	assertEquals(3, classUnderTest.getUsers().size());
}

@Test
public void testAddDestination() {
	testAddUser();
	classUnderTest.addDestination(start);
	classUnderTest.addDestination(end);
	assertEquals(2,classUnderTest.getDestinations().size());
	assertEquals(start,classUnderTest.getDestinations().get(0));
	assertEquals(end,classUnderTest.getDestinations().get(1));
}

@Test
public void testRemoveUser() {
	testAddUser();
	classUnderTest.removeUser(driver);
	assertEquals(2,classUnderTest.getUsers().size());
}

}
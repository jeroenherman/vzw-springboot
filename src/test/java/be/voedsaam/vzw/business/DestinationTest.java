package be.voedsaam.vzw.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import be.voedsaam.vzw.business.Destination;

import java.time.LocalDateTime;

public class DestinationTest {

private Destination classUnderTest;


@Before
public void setup() {
	classUnderTest = new Destination();
}


@Test
public void testAddress() {
	assertNull(classUnderTest.getAddress());
}

@Test
public void testAgreements() {
	assertEquals(0,classUnderTest.getAgreements().size());
}

@Test
public void testTasks() {
	assertEquals(0,classUnderTest.getTasks().size());
}

@Test
public void testDrives() {
	assertEquals(0,classUnderTest.getDrives().size());
}

@Test
public void testContactInfo() {
	classUnderTest.setContactInfo("test contactInfo");
	assertEquals("test contactInfo", classUnderTest.getContactInfo());
	classUnderTest.setContactInfo("test2 contactInfo");
	assertEquals("test2 contactInfo", classUnderTest.getContactInfo());
}

@Test
public void testDestinationName() {
	classUnderTest.setDestinationName("test destinationName");
	assertEquals("test destinationName", classUnderTest.getDestinationName());
	classUnderTest.setDestinationName("test2 destinationName");
	assertEquals("test2 destinationName", classUnderTest.getDestinationName());
}

@Test
public void testEquals() {
	testDestinationName();
	Destination d = new Destination();
	d.setDestinationName("test2 destinationName");
	assertEquals(d,classUnderTest);
	assertEquals(d.hashCode(),classUnderTest.hashCode());

}

@Test
public void testAddDrive() {
	Drive d = new Drive();
	d.setDescription("testRit");
	d.setStartTime(LocalDateTime.of(2019,02,02,9,00));
	d.setEndTime(LocalDateTime.of(2019,02,02,10,00));
	classUnderTest.addDrive(d);
	assertEquals(1,classUnderTest.getDrives().size());
	assertEquals(d,classUnderTest.getDrives().get(0));
}

@Test
public void testRemoveDrive() {
	testAddDrive();
	Drive d = new Drive();
	d.setDescription("testRit");
	d.setStartTime(LocalDateTime.of(2019,02,02,9,00));
	d.setEndTime(LocalDateTime.of(2019,02,02,10,00));
	assertEquals(d.getDescription(),classUnderTest.getDrives().get(0).getDescription());
	assertEquals(d,classUnderTest.getDrives().get(0));
	classUnderTest.removeDrive(d);
	assertEquals(0,classUnderTest.getDrives().size());

}

@Test
public void testAddTask() {

	Task t = new Task();
	t.setTitle("new task");
	classUnderTest.addTask(t);
	assertEquals(1,classUnderTest.getTasks().size());
	assertEquals(t,classUnderTest.getTasks().get(0));
}

@Test
public void testRemoveTask() {
	testAddTask();
	Task t = new Task();
	t.setTitle("new task");
	classUnderTest.removeTask(t);
	assertEquals(0,classUnderTest.getTasks().size());
}

@Test
public void testRemoveAgreement() {
	testAddAgreement();
	classUnderTest.removeAgreement("new agreement");
	assertEquals(0,classUnderTest.getAgreements().size());
}

@Test
public void testAddAgreement() {
	classUnderTest.addAgreement("new agreement");
	assertEquals("new agreement", classUnderTest.getAgreements().get(0));
}

}
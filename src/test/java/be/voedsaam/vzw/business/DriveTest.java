package be.voedsaam.vzw.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import be.voedsaam.vzw.business.Drive;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DriveTest {

private Drive classUnderTest;
Drive d1;
Drive d2;

@Before
public void setup() {
	classUnderTest = new Drive();
}



@Test
public void testId() {
	assertNull(classUnderTest.getId());
}

@Test
public void testStartTime() {
	assertNull(classUnderTest.getStartTime());
}

@Test
public void testEndTime() {
	assertNull(classUnderTest.getEndTime());
}

@Test
public void testDriver() {
	assertNull(classUnderTest.getDriver());
}

@Test
public void testAttendee() {
	assertNull(classUnderTest.getAttendee());
}

@Test
public void testDepotHelp() {
	assertNull(classUnderTest.getDepotHelp());
}

@Test
public void testDestinations() {
	assertNull(classUnderTest.getDestinations());
}

@Test
public void testEquals() {
	d1 = new Drive();
	d1.setId(Long.valueOf(12));
	d1.setStartTime(LocalDateTime.of(2019,12,1,12,50));
	d1.setEndTime(LocalDateTime.of(2019,12,2,12,50));
	d1.setDriver(new User("jeroen herman"));
	d1.setAttendee(new User("An Loquet"));
	d1.setDepotHelp(new User("Xander Herman"));
	d2 = new Drive();

	d2.setId(Long.valueOf(12));
	d2.setStartTime(LocalDateTime.of(2019,12,1,12,50));
	d2.setEndTime(LocalDateTime.of(2019,12,2,12,50));
	d2.setDriver(new User("jeroen herman"));
	d2.setAttendee(new User("An Loquet"));
	d2.setDepotHelp(new User("Xander Herman"));

	assertEquals(d1,d2);
	assertEquals(d2,d1);
}

@Test
public void testHashCode() {
	testEquals();
	assertTrue(d1.hashCode()==d2.hashCode());
	assertTrue(d2.hashCode()==d1.hashCode());
}

}
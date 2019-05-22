package be.voedsaam.vzw.business;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class AbstractDomainClassTest {

private AbstractDomainClass classUnderTest;

@Before
public void setup() {
	classUnderTest = new Drive();
}

@Test
public void testId() {
	assertNull(classUnderTest.getId());
}

@Test
public void testDateCreated() {
	assertNull(classUnderTest.getDateCreated());
}

@Test
public void testLastUpdated() {
	assertNull(classUnderTest.getLastUpdated());
}

@Test
public void testUpdateTimeStamps() {
	classUnderTest.updateTimeStamps();
	assertEquals(new Date(),classUnderTest.getDateCreated());
	assertEquals(new Date(),classUnderTest.getLastUpdated());
}

}
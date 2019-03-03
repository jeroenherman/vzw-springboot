package be.voedsaam.vzw.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import be.voedsaam.vzw.business.AbstractDomainClass;

import java.util.Date;

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
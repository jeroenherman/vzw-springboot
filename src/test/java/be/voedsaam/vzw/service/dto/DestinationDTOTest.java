package be.voedsaam.vzw.service.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import be.voedsaam.vzw.service.dto.DestinationDTO;

public class DestinationDTOTest {

private DestinationDTO classUnderTest;

@Before
public void setup() {
	classUnderTest = new DestinationDTO();
}

@Test
public void testId() {
	assertNull(classUnderTest.getId());
}

@Test
public void testStreet() {
	classUnderTest.setStreet("test street");
	assertEquals("test street", classUnderTest.getStreet());
	classUnderTest.setStreet("test2 street");
	assertEquals("test2 street", classUnderTest.getStreet());
}

@Test
public void testStreetNumber() {
	assertNull(classUnderTest.getStreetNumber());
}

@Test
public void testPostalCode() {
	assertNull(classUnderTest.getPostalCode());
}

@Test
public void testCity() {
	classUnderTest.setCity("test city");
	assertEquals("test city", classUnderTest.getCity());
	classUnderTest.setCity("test2 city");
	assertEquals("test2 city", classUnderTest.getCity());
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
public void testAgreements() {
	assertNull(classUnderTest.getAgreements());
}

}
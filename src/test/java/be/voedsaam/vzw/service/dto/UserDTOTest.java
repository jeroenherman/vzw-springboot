package be.voedsaam.vzw.service.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import be.voedsaam.vzw.service.dto.UserDTO;

public class UserDTOTest {

private UserDTO classUnderTest;

@Before
public void setup() {
	classUnderTest = new UserDTO();
}

@Test
public void testId() {
	assertNull(classUnderTest.getId());
}

@Test
public void testFirstName() {
	classUnderTest.setFirstName("test firstName");
	assertEquals("test firstName", classUnderTest.getFirstName());
	classUnderTest.setFirstName("test2 firstName");
	assertEquals("test2 firstName", classUnderTest.getFirstName());
}

@Test
public void testLastName() {
	classUnderTest.setLastName("test lastName");
	assertEquals("test lastName", classUnderTest.getLastName());
	classUnderTest.setLastName("test2 lastName");
	assertEquals("test2 lastName", classUnderTest.getLastName());
}

@Test
public void testEmail() {
	classUnderTest.setEmail("test email");
	assertEquals("test email", classUnderTest.getEmail());
	classUnderTest.setEmail("test2 email");
	assertEquals("test2 email", classUnderTest.getEmail());
}

@Test
public void testPassword() {
	classUnderTest.setPassword("test password");
	assertEquals("test password", classUnderTest.getPassword());
	classUnderTest.setPassword("test2 password");
	assertEquals("test2 password", classUnderTest.getPassword());
}

@Test
public void testRole() {
	assertNull(classUnderTest.getRole());
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
public void testTel() {
	classUnderTest.setTel("test tel");
	assertEquals("test tel", classUnderTest.getTel());
	classUnderTest.setTel("test2 tel");
	assertEquals("test2 tel", classUnderTest.getTel());
}

@Test
public void testColor() {
	assertNull(classUnderTest.getColor());
}

@Test
public void testEquals() {
	fail("Not yet implemented");
}

@Test
public void testToString() {
	fail("Not yet implemented");
}

@Test
public void testHashCode() {
	fail("Not yet implemented");
}

}
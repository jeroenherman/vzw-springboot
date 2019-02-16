package be.voedsaam.vzw.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import be.voedsaam.vzw.commons.Color;
import be.voedsaam.vzw.commons.Role;
import org.junit.Before;
import org.junit.Test;
import be.voedsaam.vzw.business.User;

public class UserTest {

private User classUnderTest;
private User u1,u2;
@Before
public void setup() {
	classUnderTest = new User();
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
public void testRole() {
	assertNull(classUnderTest.getRole());
}

@Test
public void testAddress() {
	assertNull(classUnderTest.getAddress());
}

@Test
public void testPassword() {
	classUnderTest.setPassword("test password");
	assertEquals("test password", classUnderTest.getPassword());
	classUnderTest.setPassword("test2 password");
	assertEquals("test2 password", classUnderTest.getPassword());
}

@Test
public void testEquals() {
	u1 = new User();
	u1.setFirstName("jeroen");
	u1.setLastName("herman");
	u1.setPassword("test123");
	u1.setRole(Role.COORDINATOR);
	u1.setColor( Color.RED);
	u1.setEmail("jeroen@example.com");
	u1.setTel("123456");
	u1.setId(Long.valueOf(152));

	u2 = new User("jeroen herman");
	u2.setPassword("test123");
	u2.setRole(Role.COORDINATOR);
	u2.setColor(Color.RED);
	u2.setEmail("jeroen@example.com");
	u2.setTel("123456");
	u2.setId(Long.valueOf(152));

	assertEquals(u1,u2);
	assertEquals(u2,u1);
}

@Test
public void testHashCode() {
	testEquals();
	assertTrue(u1.hashCode()==u2.hashCode());
	assertTrue(u2.hashCode()==u1.hashCode());

}

}
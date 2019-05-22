package be.voedsaam.vzw.service.dto;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LoginCommandTest {

private LoginCommand classUnderTest;

@Before
public void setup() {
	classUnderTest = new LoginCommand();
}

@Test
public void testUsername() {
	classUnderTest.setUsername("test username");
	assertEquals("test username", classUnderTest.getUsername());
	classUnderTest.setUsername("test2 username");
	assertEquals("test2 username", classUnderTest.getUsername());
}

@Test
public void testPassword() {
	classUnderTest.setPassword("test password");
	assertEquals("test password", classUnderTest.getPassword());
	classUnderTest.setPassword("test2 password");
	assertEquals("test2 password", classUnderTest.getPassword());
}

}
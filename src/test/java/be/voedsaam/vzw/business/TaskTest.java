package be.voedsaam.vzw.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import be.voedsaam.vzw.business.Task;

public class TaskTest {

private Task classUnderTest;

@Before
public void setup() {
	classUnderTest = new Task();
}


@Test
public void testId() {
	assertNull(classUnderTest.getId());
}

@Test
public void testTitle() {
	classUnderTest.setTitle("test title");
	assertEquals("test title", classUnderTest.getTitle());
	classUnderTest.setTitle("test2 title");
	assertEquals("test2 title", classUnderTest.getTitle());
}

@Test
public void testDiscription() {
	classUnderTest.setDiscription("test discription");
	assertEquals("test discription", classUnderTest.getDiscription());
	classUnderTest.setDiscription("test2 discription");
	assertEquals("test2 discription", classUnderTest.getDiscription());
}

@Test
public void testRole() {
	assertNull(classUnderTest.getRole());
}

}
package be.voedsaam.vzw.service.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import be.voedsaam.vzw.service.dto.StockDTO;

public class StockDTOTest {

private StockDTO classUnderTest;

@Before
public void setup() {
	classUnderTest = new StockDTO();
}

@Test
public void testId() {
	assertNull(classUnderTest.getId());
}

@Test
public void testName() {
	classUnderTest.setName("test name");
	assertEquals("test name", classUnderTest.getName());
	classUnderTest.setName("test2 name");
	assertEquals("test2 name", classUnderTest.getName());
}

@Test
public void testLocation() {
	classUnderTest.setLocation("test location");
	assertEquals("test location", classUnderTest.getLocation());
	classUnderTest.setLocation("test2 location");
	assertEquals("test2 location", classUnderTest.getLocation());
}

@Test
public void testUsers() {
	assertNull(classUnderTest.getUsers());
}

@Test
public void testEmptyStock() {
	assertFalse(classUnderTest.isEmptyStock());
	classUnderTest.setEmptyStock(true);
	assertTrue(classUnderTest.isEmptyStock());
}

@Test
public void testNoUsers() {
	assertFalse(classUnderTest.isNoUsers());
	classUnderTest.setNoUsers(true);
	assertTrue(classUnderTest.isNoUsers());
}

@Test
public void testNoOrders() {
	assertFalse(classUnderTest.isNoOrders());
	classUnderTest.setNoOrders(true);
	assertTrue(classUnderTest.isNoOrders());
}

}
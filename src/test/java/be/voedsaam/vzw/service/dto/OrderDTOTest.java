package be.voedsaam.vzw.service.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public class OrderDTOTest {

private OrderDTO classUnderTest;

@Before
public void setup() {
	classUnderTest = new OrderDTO();
}

@Test
public void testId() {
	assertNull(classUnderTest.getId());
}

@Test
public void testPickupDateTime() {
	classUnderTest.setPickupDateTime("test pickupDateTime");
	assertEquals("test pickupDateTime", classUnderTest.getPickupDateTime());
	classUnderTest.setPickupDateTime("test2 pickupDateTime");
	assertEquals("test2 pickupDateTime", classUnderTest.getPickupDateTime());
}

@Test
public void testPartner() {
	classUnderTest.setPartner("test partner");
	assertEquals("test partner", classUnderTest.getPartner());
	classUnderTest.setPartner("test2 partner");
	assertEquals("test2 partner", classUnderTest.getPartner());
}

@Test
public void testStock() {
	classUnderTest.setStock("test stock");
	assertEquals("test stock", classUnderTest.getStock());
	classUnderTest.setStock("test2 stock");
	assertEquals("test2 stock", classUnderTest.getStock());
}

@Test
public void testOrderStatus() {
	assertNull(classUnderTest.getOrderStatus());
}

@Test
public void testPickUp() {
	assertNull(classUnderTest.getPickUpDateTime());
}

}
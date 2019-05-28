package be.voedsaam.vzw.service.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import be.voedsaam.vzw.service.dto.BasketDTO;

public class BasketDTOTest {

private BasketDTO classUnderTest;

@Before
public void setup() {
	classUnderTest = new BasketDTO();
}

@Test
public void testIdStock() {
	assertNull(classUnderTest.getIdStock());
}

@Test
public void testIdOrder() {
	assertNull(classUnderTest.getIdOrder());
}

@Test
public void testProducts() {
	assertNull(classUnderTest.getProducts());
}

@Test
public void testPickupDateTime() {
	classUnderTest.setPickupDateTime("test pickupDateTime");
	assertEquals("test pickupDateTime", classUnderTest.getPickupDateTime());
	classUnderTest.setPickupDateTime("test2 pickupDateTime");
	assertEquals("test2 pickupDateTime", classUnderTest.getPickupDateTime());
}

}
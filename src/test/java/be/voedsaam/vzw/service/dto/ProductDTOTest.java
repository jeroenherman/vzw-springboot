package be.voedsaam.vzw.service.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import be.voedsaam.vzw.service.dto.ProductDTO;

public class ProductDTOTest {

private ProductDTO classUnderTest;

@Before
public void setup() {
	classUnderTest = new ProductDTO();
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
public void testDescription() {
	classUnderTest.setDescription("test description");
	assertEquals("test description", classUnderTest.getDescription());
	classUnderTest.setDescription("test2 description");
	assertEquals("test2 description", classUnderTest.getDescription());
}

@Test
public void testDeliveryNr() {
	classUnderTest.setDeliveryNr("test deliveryNr");
	assertEquals("test deliveryNr", classUnderTest.getDeliveryNr());
	classUnderTest.setDeliveryNr("test2 deliveryNr");
	assertEquals("test2 deliveryNr", classUnderTest.getDeliveryNr());
}

@Test
public void testShelfLife() {
	classUnderTest.setShelfLife("test shelfLife");
	assertEquals("test shelfLife", classUnderTest.getShelfLife());
	classUnderTest.setShelfLife("test2 shelfLife");
	assertEquals("test2 shelfLife", classUnderTest.getShelfLife());
}

@Test
public void testUnitOfMeasure() {
	assertNull(classUnderTest.getUnitOfMeasure());
}

@Test
public void testProductType() {
	assertNull(classUnderTest.getProductType());
}

@Test
public void testInStock() {
	assertFalse(classUnderTest.isInStock());
	classUnderTest.setInStock(true);
	assertTrue(classUnderTest.isInStock());
}

@Test
public void testQty() {
	assertNull(classUnderTest.getQty());
}

}
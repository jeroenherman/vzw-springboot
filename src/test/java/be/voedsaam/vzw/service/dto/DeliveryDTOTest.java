package be.voedsaam.vzw.service.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import be.voedsaam.vzw.service.dto.DeliveryDTO;

public class DeliveryDTOTest {

private DeliveryDTO classUnderTest;

@Before
public void setup() {
	classUnderTest = new DeliveryDTO();
}

@Test
public void testProducts() {
	assertNull(classUnderTest.getProducts());
}

}
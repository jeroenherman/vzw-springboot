package be.voedsaam.vzw.service.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import be.voedsaam.vzw.service.dto.TransferDTO;

public class TransferDTOTest {

private TransferDTO classUnderTest;

@Before
public void setup() {
	classUnderTest = new TransferDTO();
}

@Test
public void testIdFromStock() {
	assertNull(classUnderTest.getIdFromStock());
}

@Test
public void testIdToStock() {
	assertNull(classUnderTest.getIdToStock());
}

@Test
public void testProducts() {
	assertNull(classUnderTest.getProducts());
}

}
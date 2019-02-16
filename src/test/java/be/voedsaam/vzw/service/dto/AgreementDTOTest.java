package be.voedsaam.vzw.service.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import be.voedsaam.vzw.service.dto.AgreementDTO;

public class AgreementDTOTest {

private AgreementDTO classUnderTest;

@Before
public void setup() {
	classUnderTest = new AgreementDTO();
}

@Test
public void testId() {
	assertNull(classUnderTest.getId());
}

@Test
public void testAgreement() {
	classUnderTest.setAgreement("test agreement");
	assertEquals("test agreement", classUnderTest.getAgreement());
	classUnderTest.setAgreement("test2 agreement");
	assertEquals("test2 agreement", classUnderTest.getAgreement());
}

}
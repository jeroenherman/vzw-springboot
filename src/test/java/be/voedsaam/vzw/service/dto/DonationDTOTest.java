package be.voedsaam.vzw.service.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import be.voedsaam.vzw.service.dto.DonationDTO;

public class DonationDTOTest {

private DonationDTO classUnderTest;

@Before
public void setup() {
	classUnderTest = new DonationDTO();
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
public void testSubject() {
	classUnderTest.setSubject("test subject");
	assertEquals("test subject", classUnderTest.getSubject());
	classUnderTest.setSubject("test2 subject");
	assertEquals("test2 subject", classUnderTest.getSubject());
}

@Test
public void testMessage() {
	classUnderTest.setMessage("test message");
	assertEquals("test message", classUnderTest.getMessage());
	classUnderTest.setMessage("test2 message");
	assertEquals("test2 message", classUnderTest.getMessage());
}

@Test
public void testAmount() {
	classUnderTest.setAmount("test amount");
	assertEquals("test amount", classUnderTest.getAmount());
	classUnderTest.setAmount("test2 amount");
	assertEquals("test2 amount", classUnderTest.getAmount());
}

@Test
public void testReoccuringType() {
	classUnderTest.setReoccuringType("test reoccuringType");
	assertEquals("test reoccuringType", classUnderTest.getReoccuringType());
	classUnderTest.setReoccuringType("test2 reoccuringType");
	assertEquals("test2 reoccuringType", classUnderTest.getReoccuringType());
}

@Test
public void testPaymentType() {
	classUnderTest.setPaymentType("test paymentType");
	assertEquals("test paymentType", classUnderTest.getPaymentType());
	classUnderTest.setPaymentType("test2 paymentType");
	assertEquals("test2 paymentType", classUnderTest.getPaymentType());
}

}
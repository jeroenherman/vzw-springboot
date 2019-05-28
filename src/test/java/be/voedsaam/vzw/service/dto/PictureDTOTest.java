package be.voedsaam.vzw.service.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import be.voedsaam.vzw.service.dto.PictureDTO;

public class PictureDTOTest {

private PictureDTO classUnderTest;

@Before
public void setup() {
	classUnderTest = new PictureDTO();
}

@Test
public void testId() {
	assertNull(classUnderTest.getId());
}

@Test
public void testUrl() {
	classUnderTest.setUrl("test url");
	assertEquals("test url", classUnderTest.getUrl());
	classUnderTest.setUrl("test2 url");
	assertEquals("test2 url", classUnderTest.getUrl());
}

@Test
public void testAlternateText() {
	classUnderTest.setAlternateText("test alternateText");
	assertEquals("test alternateText", classUnderTest.getAlternateText());
	classUnderTest.setAlternateText("test2 alternateText");
	assertEquals("test2 alternateText", classUnderTest.getAlternateText());
}

}
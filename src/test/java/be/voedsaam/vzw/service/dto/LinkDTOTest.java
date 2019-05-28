package be.voedsaam.vzw.service.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import be.voedsaam.vzw.service.dto.LinkDTO;

public class LinkDTOTest {

private LinkDTO classUnderTest;

@Before
public void setup() {
	classUnderTest = new LinkDTO();
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
public void testUrl() {
	classUnderTest.setUrl("test url");
	assertEquals("test url", classUnderTest.getUrl());
	classUnderTest.setUrl("test2 url");
	assertEquals("test2 url", classUnderTest.getUrl());
}

}
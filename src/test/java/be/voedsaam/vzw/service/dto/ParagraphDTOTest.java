package be.voedsaam.vzw.service.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import be.voedsaam.vzw.service.dto.ParagraphDTO;

public class ParagraphDTOTest {

private ParagraphDTO classUnderTest;

@Before
public void setup() {
	classUnderTest = new ParagraphDTO();
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
public void testText() {
	classUnderTest.setText("test text");
	assertEquals("test text", classUnderTest.getText());
	classUnderTest.setText("test2 text");
	assertEquals("test2 text", classUnderTest.getText());
}

}
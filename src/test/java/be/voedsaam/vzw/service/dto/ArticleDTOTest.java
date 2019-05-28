package be.voedsaam.vzw.service.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import be.voedsaam.vzw.service.dto.ArticleDTO;

public class ArticleDTOTest {

private ArticleDTO classUnderTest;

@Before
public void setup() {
	classUnderTest = new ArticleDTO();
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
public void testPicture() {
	classUnderTest.setPicture("test picture");
	assertEquals("test picture", classUnderTest.getPicture());
	classUnderTest.setPicture("test2 picture");
	assertEquals("test2 picture", classUnderTest.getPicture());
}

@Test
public void testArticleType() {
	assertNull(classUnderTest.getArticleType());
}

@Test
public void testDate() {
	assertNull(classUnderTest.getDate());
}

}
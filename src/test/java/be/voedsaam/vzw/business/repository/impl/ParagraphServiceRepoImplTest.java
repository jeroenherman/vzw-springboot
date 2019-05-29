package be.voedsaam.vzw.business.repository.impl;

import be.voedsaam.vzw.business.Paragraph;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class ParagraphServiceRepoImplTest {
    @Autowired
    private ParagraphServiceRepoImpl classUnderTest;
    private Paragraph paragraph;

    @Before
    public void setUp() throws Exception {
        paragraph = new Paragraph();
        paragraph.setTitle("new title");
        paragraph.setText("blab bla bla");
    }

    @Test
    public void listAll() throws Exception {
        assertEquals(6,classUnderTest.listAll().size());
    }

    @Test
    public void getById() throws Exception {
        paragraph = classUnderTest.saveOrUpdate(paragraph);
        assertEquals( paragraph , classUnderTest.getById(paragraph.getId()));

    }

    @Test
    public void delete() throws Exception {
        paragraph = classUnderTest.saveOrUpdate(paragraph);
        Long id = paragraph.getId();
        classUnderTest.delete(null);
        classUnderTest.delete(paragraph.getId());
        assertNull( classUnderTest.getById(paragraph.getId()));
        assertNull( classUnderTest.getById(id));
    }

}
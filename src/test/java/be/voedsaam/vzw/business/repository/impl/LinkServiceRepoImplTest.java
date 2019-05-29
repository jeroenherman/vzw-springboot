package be.voedsaam.vzw.business.repository.impl;

import be.voedsaam.vzw.business.Link;
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
public class LinkServiceRepoImplTest {
    @Autowired
    private LinkServiceRepoImpl classUnderTest;
    private Link link;

    @Before
    public void setUp() throws Exception {
        link = new Link();
        link.setTitle("new link");
        link.setUrl("http://localhost");

    }

    @Test
    public void listAll() throws Exception {
        assertEquals(6,classUnderTest.listAll().size());
    }

    @Test
    public void getById() throws Exception {
        link = classUnderTest.saveOrUpdate(link);
        assertEquals( link, classUnderTest.getById(link.getId()));

    }

    @Test
    public void delete() throws Exception {
        link = classUnderTest.saveOrUpdate(link);
        Long id = link.getId();
        classUnderTest.delete(null);
        classUnderTest.delete(link.getId());
        assertNull( classUnderTest.getById(link.getId()));
        assertNull( classUnderTest.getById(id));
    }

}
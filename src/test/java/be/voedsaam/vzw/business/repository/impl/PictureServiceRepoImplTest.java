package be.voedsaam.vzw.business.repository.impl;

import be.voedsaam.vzw.business.Picture;
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
public class PictureServiceRepoImplTest {

    @Autowired
    private PictureServiceRepoImpl classUnderTest;
    private Picture picture;

    @Before
    public void setUp() throws Exception {
        picture = new Picture();
        picture.setTitle("nieuwe foto");
        picture.setUrl("foto.jpg");
        picture.setAlternateText("foto");
    }

    @Test
    public void listAll() throws Exception {
        assertEquals(5,classUnderTest.listAll().size());
    }

    @Test
    public void getById() throws Exception {
        picture = classUnderTest.saveOrUpdate(picture);
        assertEquals( picture , classUnderTest.getById(picture.getId()));

    }

    @Test
    public void delete() throws Exception {
        picture = classUnderTest.saveOrUpdate(picture);
        Long id = picture.getId();
        classUnderTest.delete(null);
        classUnderTest.delete(picture.getId());
        assertNull( classUnderTest.getById(picture.getId()));
        assertNull( classUnderTest.getById(id));

    }

}
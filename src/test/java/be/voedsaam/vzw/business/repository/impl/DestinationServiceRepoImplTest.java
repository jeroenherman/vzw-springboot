package be.voedsaam.vzw.business.repository.impl;

import be.voedsaam.vzw.business.Destination;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * Integration test uses test data brought in via bootstrap package
 */
@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class DestinationServiceRepoImplTest {

    @Autowired
    private DestinationServiceRepoImpl classUnderTest;
    private Destination destination;

    @Before
    public void setup(){
        destination = new Destination();
        destination.setDestinationName("nieuwe Bestemming");
        destination.setContactInfo("contact");
    }

    @Test
    public void listAll() throws Exception {
        assertEquals(3,classUnderTest.listAll().size());
    }

    @Test
    public void getById() throws Exception {
        destination = classUnderTest.saveOrUpdate(destination);
        assertEquals( destination , classUnderTest.getById(destination.getId()));
    }


    @Test
    public void delete() throws Exception {
        destination = classUnderTest.saveOrUpdate(destination);
        Long id = destination.getId();
        classUnderTest.delete(null);
        classUnderTest.delete(destination.getId());
        assertNull( classUnderTest.getById(destination.getId()));
        assertNull( classUnderTest.getById(id));
    }

}
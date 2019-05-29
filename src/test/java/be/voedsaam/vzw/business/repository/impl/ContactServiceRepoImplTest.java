package be.voedsaam.vzw.business.repository.impl;

import be.voedsaam.vzw.business.Contact;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class ContactServiceRepoImplTest {
    @Autowired
    private ContactServiceRepoImpl classUnderTest;
    private Contact contact;

    @Before
    public void setUp() throws Exception {
        contact = new Contact();
        contact.setMessage("Test Message");
        contact.setName("jeroen herman");
        contact.setTel("123456");
    }

    @Test
    public void listAll() throws Exception {
        assertEquals(0, classUnderTest.listAll().size());
    }

    @Test
    public void getById() throws Exception {
        contact = classUnderTest.saveOrUpdate(contact);
        assertEquals(contact, classUnderTest.getById(contact.getId()));
        assertEquals(1, classUnderTest.listAll().size());
    }

    @Test
    public void delete() throws Exception {
        contact = classUnderTest.saveOrUpdate(contact);
        Long id = contact.getId();
        classUnderTest.delete(null);
        classUnderTest.delete(contact.getId());
        assertNull(classUnderTest.getById(contact.getId()));
        assertNull(classUnderTest.getById(id));
    }

}
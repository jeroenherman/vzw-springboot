package be.voedsaam.vzw.service.mapper;

import be.voedsaam.vzw.business.Contact;
import be.voedsaam.vzw.business.repository.ContactRepository;
import be.voedsaam.vzw.service.dto.ContactDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.*;
@RunWith(MockitoJUnitRunner.class)
public class ContactMapperTest {
    private ContactMapper classUnderTest;
    private Contact b;
    private ContactDTO d;
    @Mock
    ContactRepository mockRepository;

    @Before
    public void setUp() throws Exception {
        b = new Contact();
        d= new ContactDTO();
        classUnderTest = new ContactMapper();
        classUnderTest.setContactRepository(mockRepository);

        b.setName("name");
        b.setTel("123456");
        b.setMessage("bla bla");
        b.setEmail("jp@bla.be");
        b.setSubject("morela");
        b.setId(123l);

    }

    @Test
    public void testNullBusinessObject() {
        b = null;
        d = classUnderTest.mapToDTO(b);
        assertNull(d);
    }

    @Test
    public void testNullDTO() {
        d = null;
        b = classUnderTest.mapToObj(d);
        assertNull(b);
    }

    @Test
    public void mapToDTO() throws Exception {
        d= null;
        d = classUnderTest.mapToDTO(b);
        assertEquals(d.getId(), b.getId());
        assertEquals(d.getName(), b.getName());
        assertEquals(d.getMessage(), b.getMessage());
        assertEquals(d.getEmail(), b.getEmail());
        assertEquals(d.getSubject(), b.getSubject());
        assertEquals(d.getTel(), b.getTel());

    }

    @Test
    public void mapToObj() throws Exception {
        d.setEmail("jp2@bla.be");
        d.setMessage("bla bla bla bla");
        d.setName("ikke");
        d.setSubject("onderwerp");
        d.setTel("56852");
        b = classUnderTest.mapToObj(d);
        assertEquals(d.getId(), b.getId());
        assertEquals(d.getName(), b.getName());
        assertEquals(d.getMessage(), b.getMessage());
        assertEquals(d.getEmail(), b.getEmail());
        assertEquals(d.getSubject(), b.getSubject());
        assertEquals(d.getTel(), b.getTel());
    }

    @Test
    public void mapToObjWithMock() throws Exception {
        Optional<Contact> o = Optional.of(b);
        Mockito.when(mockRepository.findById(new Long(123))).thenReturn(o);
        d.setId(new Long(123));
        b = classUnderTest.mapToObj(d);
        assertEquals(d.getId(), b.getId());
        assertEquals(d.getName(), b.getName());
        assertEquals(d.getMessage(), b.getMessage());
        assertEquals(d.getEmail(), b.getEmail());
        assertEquals(d.getSubject(), b.getSubject());
        assertEquals(d.getTel(), b.getTel());
    }
}
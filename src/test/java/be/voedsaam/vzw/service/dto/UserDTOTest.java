package be.voedsaam.vzw.service.dto;

import be.voedsaam.vzw.enums.Color;
import be.voedsaam.vzw.enums.Role;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class UserDTOTest {
    private UserDTO classUnderTest;

    @Before
    public void setup() {

        classUnderTest = new UserDTO();
    }


    @Test
    public void setFullName() throws Exception {
        classUnderTest.setFullName("jeroen herman");
        assertEquals("jeroen herman", classUnderTest.getFullName());
        assertEquals("jeroen", classUnderTest.getFirstName());
        assertEquals("herman", classUnderTest.getLastName());
        classUnderTest.setFullName("marie jose herman");
        assertEquals("marie jose herman", classUnderTest.getFullName());
        assertEquals("marie jose", classUnderTest.getFirstName());
        assertEquals("herman", classUnderTest.getLastName());
        classUnderTest.setFullName("marie-jose van de walle");
        assertEquals("marie-jose van de walle", classUnderTest.getFullName());
        assertEquals("marie-jose", classUnderTest.getFirstName());
        assertEquals("van de walle", classUnderTest.getLastName());
    }

    @Test
    public void testId() {
        assertNull(classUnderTest.getId());
    }

    @Test
    public void setId() throws Exception {
        classUnderTest.setId(new Long(10));
        assertEquals(new Long(10), classUnderTest.getId());
        classUnderTest.setId(new Long(20));
        assertEquals(new Long(20), classUnderTest.getId());
        classUnderTest.setId(new Long(-20));
        assertEquals(new Long(-20), classUnderTest.getId());

    }

    @Test
    public void getFirstName() throws Exception {
        assertNull(classUnderTest.getFirstName());
    }

    @Test
    public void setFirstName() throws Exception {
        classUnderTest.setFirstName("naam1");
        assertEquals("naam1", classUnderTest.getFirstName());
        classUnderTest.setFirstName("naam2");
        assertEquals("naam2", classUnderTest.getFirstName());
    }

    @Test
    public void getLastName() throws Exception {
        assertNull(classUnderTest.getFirstName());
    }

    @Test
    public void setLastName() throws Exception {
        classUnderTest.setLastName("naam1");
        assertEquals("naam1", classUnderTest.getLastName());
        classUnderTest.setLastName("naam2");
        assertEquals("naam2", classUnderTest.getLastName());
    }

    @Test
    public void getEmail() throws Exception {
        assertNull(classUnderTest.getEmail());
    }

    @Test
    public void setEmail() throws Exception {
        classUnderTest.setEmail("naam1");
        assertEquals("naam1", classUnderTest.getEmail());
        classUnderTest.setEmail("naam2");
        assertEquals("naam2", classUnderTest.getEmail());

    }

    @Test
    public void getRole() throws Exception {
        assertNull(classUnderTest.getRole());
    }

    @Test
    public void setRole() throws Exception {
        classUnderTest.setRole(Role.DRIVER);
        assertEquals(Role.DRIVER, classUnderTest.getRole());
        classUnderTest.setRole(Role.DEPOTHELP);
        assertEquals(Role.DEPOTHELP, classUnderTest.getRole());
        classUnderTest.setRole(Role.ATTENDEE);
        assertEquals(Role.ATTENDEE, classUnderTest.getRole());
        classUnderTest.setRole(Role.VOLUNTEER);
        assertEquals(Role.VOLUNTEER, classUnderTest.getRole());
    }

    @Test
    public void getStreet() throws Exception {
        assertNull(classUnderTest.getStreet());
    }

    @Test
    public void setStreet() throws Exception {
        classUnderTest.setStreet("naam1");
        assertEquals("naam1", classUnderTest.getStreet());
        classUnderTest.setStreet("naam2");
        assertEquals("naam2", classUnderTest.getStreet());
    }

    @Test
    public void getStreetNumber() throws Exception {
        assertNull(classUnderTest.getStreetNumber());
    }

    @Test
    public void setStreetNumber() throws Exception {
        classUnderTest.setStreetNumber(10);

        assertEquals(10, classUnderTest.getStreetNumber(), 0);
        classUnderTest.setStreetNumber(20);
        assertEquals(20, classUnderTest.getStreetNumber(), 0);
        classUnderTest.setStreetNumber(-20);
        assertEquals(-20, classUnderTest.getStreetNumber(), 0);
    }

    @Test
    public void getPostalCode() throws Exception {
        assertNull(classUnderTest.getPostalCode());
    }

    @Test
    public void setPostalCode() throws Exception {
        classUnderTest.setPostalCode(10);

        assertEquals(10, classUnderTest.getPostalCode(), 0);
        classUnderTest.setPostalCode(20);
        assertEquals(20, classUnderTest.getPostalCode(), 0);
        classUnderTest.setPostalCode(-20);
        assertEquals(-20, classUnderTest.getPostalCode(), 0);
    }


    @Test
    public void getCity() throws Exception {
        assertNull(classUnderTest.getCity());
    }

    @Test
    public void setCity() throws Exception {
        classUnderTest.setCity("naam1");
        assertEquals("naam1", classUnderTest.getCity());
        classUnderTest.setCity("naam2");
        assertEquals("naam2", classUnderTest.getCity());
    }

    @Test
    public void getTel() throws Exception {
        assertNull(classUnderTest.getTel());

    }

    @Test
    public void setTel() throws Exception {
        classUnderTest.setTel("naam1");
        assertEquals("naam1", classUnderTest.getTel());
        classUnderTest.setTel("naam2");
        assertEquals("naam2", classUnderTest.getTel());
    }

    @Test
    public void getColor() throws Exception {
        assertNull(classUnderTest.getColor());
    }

    @Test
    public void setColor() throws Exception {
        classUnderTest.setColor(Color.RED);
        assertEquals(Color.RED, classUnderTest.getColor());
        assertEquals("Rood", classUnderTest.getColor().getValue());
        classUnderTest.setColor(Color.BLACK);
        assertEquals(Color.BLACK, classUnderTest.getColor());
        assertEquals("Zwart", classUnderTest.getColor().getValue());
    }

    @Test
    public void getPassword() throws Exception {
        assertNull(classUnderTest.getPassword());
    }

    @Test
    public void setPassword() throws Exception {
        classUnderTest.setPassword("naam1");
        assertEquals("naam1", classUnderTest.getPassword());
        classUnderTest.setPassword("naam2");
        assertEquals("naam2", classUnderTest.getPassword());
    }

    @Test
    public void getSchedules() throws Exception {
        assertNull(classUnderTest.getSchedules());
        //assertEquals(0, classUnderTest.getSchedules().size());
    }

    @Test
    public void setSchedules() throws Exception {
        List<String> strings = new ArrayList<>();
        strings.add("Jan");
        strings.add("Piet");
        classUnderTest.setSchedules(strings);
        assertEquals(2, classUnderTest.getSchedules().size());
        assertEquals("Jan", classUnderTest.getSchedules().get(0));
    }

    @Test
    public void getDrives() throws Exception {
        assertNull(classUnderTest.getDrives());
    }

    @Test
    public void setDrivers() {
        List<String> strings = new ArrayList<>();
        strings.add("Jan");
        strings.add("Piet");
        classUnderTest.setDrives(strings);
        assertEquals(2, classUnderTest.getDrives().size());
        assertEquals("Jan", classUnderTest.getDrives().get(0));
        assertEquals("Piet", classUnderTest.getDrives().get(1));
    }

    @Test
    public void equalsAndHashCode(){
        classUnderTest.setFirstName("jeroen");
        classUnderTest.setLastName("herman");
        classUnderTest.setRole(Role.COORDINATOR);
        classUnderTest.setEmail("jeroen.herman@outlook.be");

        UserDTO dto = new UserDTO();
        dto.setFirstName("jeroen");
        dto.setLastName("herman");
        dto.setRole(Role.COORDINATOR);
        dto.setEmail("jeroen.herman@outlook.be");

        assertEquals(dto, classUnderTest);
        assertEquals(dto.hashCode(),classUnderTest.hashCode());

        dto = new UserDTO("jeroen herman");
        dto.setEmail("jeroen.herman@outlook.be");
        assertEquals(dto, classUnderTest);
        assertEquals(dto.hashCode(),classUnderTest.hashCode());
    }

}
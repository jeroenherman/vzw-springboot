package be.voedsaam.vzw.service.mapper;

import be.voedsaam.vzw.business.Address;
import be.voedsaam.vzw.business.Drive;
import be.voedsaam.vzw.business.Schedule;
import be.voedsaam.vzw.business.User;
import be.voedsaam.vzw.business.impl.Employee;
import be.voedsaam.vzw.business.impl.Volunteer;
import be.voedsaam.vzw.business.repository.UserRepository;
import be.voedsaam.vzw.enums.Role;
import be.voedsaam.vzw.service.dto.UserDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class VolunteerMapperTest {
    private VolunteerMapper classUnderTest;
    private Volunteer b;
    private UserDTO d;
    private Drive drive;

    @Mock
    UserRepository mockRepository;

    @Before
    public void setup(){
        classUnderTest = new VolunteerMapper();
        classUnderTest.setUserRepository(mockRepository);
        b = new Volunteer("jeroen","herman","jeroen.herman@outlook.be","123456");
        b.setId(new Long(123));
        Address address = new Address();
        address.setStreet("straat");
        address.setNumber(10);
        address.setCity("stad");
        address.setPostalCode(9100);
        b.setAddress(address);
        b.setRole(Role.DRIVER);

        drive = new Drive();
        drive.setDescription("beschrijving");
        drive.setStartTime(LocalDateTime.of(2019, 12, 12, 9, 00, 00, 00));
        drive.setEndTime(LocalDateTime.of(2019, 12, 12, 10, 00, 00, 00));
        drive.setId(new Long(123));

        b.addDrive(drive);

        d = new UserDTO();
        d.setRole(Role.ATTENDEE);


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
    public void testMapToDTO() {
        d= null;
        d = classUnderTest.mapToDTO(b);

        assertEquals(d.getId(), b.getId());
        assertEquals(d.getCity(), b.getAddress().getCity());
        assertEquals(d.getFullName(),b.getFullName());
        assertEquals(d.getRole(),b.getRole());
        assertEquals(1,d.getDrives().size());
        assertEquals(drive.getDescription(),d.getDrives().get(0));

    }

    @Test
    public void testMapToObjWithRepo() {
        Optional<User> o = Optional.of(b);
        Mockito.when(mockRepository.findById(new Long(123))).thenReturn(o);
        d.setId(new Long(123));
        b = classUnderTest.mapToObj(d);
        assertEquals(d.getRole(),b.getRole());
        assertEquals(1,b.getDrives().size());
        assertEquals(drive,b.getDrives().get(0));
    }

    @Test
    public void testMapToObjWithoutRepo() {

        d.setId(new Long (10));
        d.setStreet("straat2");
        d.setPostalCode(9140);
        b = classUnderTest.mapToObj(d);
        assertEquals(d.getId(), b.getId());
        assertEquals(d.getCity(), b.getAddress().getCity());
    }
}


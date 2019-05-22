package be.voedsaam.vzw.service.mapper;

import be.voedsaam.vzw.business.Address;
import be.voedsaam.vzw.business.Schedule;
import be.voedsaam.vzw.business.User;
import be.voedsaam.vzw.business.impl.Employee;
import be.voedsaam.vzw.business.repository.UserRepository;
import be.voedsaam.vzw.enums.Role;
import be.voedsaam.vzw.service.dto.UserDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
@RunWith(MockitoJUnitRunner.class)
public class EmployeeMapperTest {
    private EmployeeMapper classUnderTest;
    private Employee b;
    private UserDTO d;
    private Schedule schedule;

    @Mock
    UserRepository mockRepository;

    @Before
    public void setup(){
        classUnderTest = new EmployeeMapper();
        classUnderTest.setUserRepository(mockRepository);
        b = new Employee("jeroen","herman","jeroen.herman@outlook.be","123456");
        b.setId(new Long(123));
        Address address = new Address();
        address.setStreet("straat");
        address.setNumber(10);
        address.setCity("stad");
        address.setPostalCode(9100);
        b.setAddress(address);
        b.setRole(Role.COORDINATOR);
        schedule = new Schedule();
        schedule.setName("testschema");
        b.addSchedule(schedule);
        d = new UserDTO();
        d.setFullName("jeroen herman");
        d.setRole(Role.LOGISTICS);


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

    }

    @Test
    public void testMapToObjWithRepo() {
        Optional<User> o = Optional.of(b);
        Mockito.when(mockRepository.findById(new Long(123))).thenReturn(o);
        d.setId(new Long(123));
        b = classUnderTest.mapToObj(d);
        assertEquals(schedule,b.getSchedules().get(0));
        assertEquals(d.getRole(),b.getRole());
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


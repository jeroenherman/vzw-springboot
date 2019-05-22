package be.voedsaam.vzw.service.mapper;

import be.voedsaam.vzw.business.Drive;
import be.voedsaam.vzw.business.impl.Volunteer;
import be.voedsaam.vzw.business.repository.DriveRepository;
import be.voedsaam.vzw.enums.Role;
import be.voedsaam.vzw.service.dto.DriveDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class DriveMapperTest {

    private DriveMapper classUnderTest;
    private Drive b;
    private DriveDTO d;

    @Mock
    DriveRepository mockRepository;

    @Before
    public void setup() {
        classUnderTest = new DriveMapper();
        classUnderTest.setDriveRepository(mockRepository);
        b = new Drive();
        d = new DriveDTO();
        b.setDescription("beschrijving");
        b.setStartTime(LocalDateTime.of(2019, 12, 12, 9, 00, 00, 00));
        b.setEndTime(LocalDateTime.of(2019, 12, 12, 10, 00, 00, 00));
        b.setId(new Long(123));

        Volunteer driver = new Volunteer("driver 1");
        driver.setRole(Role.DRIVER);

        Volunteer attendee = new Volunteer("attendee 1");
        attendee.setRole(Role.ATTENDEE);

        Volunteer depotHelp = new Volunteer("depothelp 1");
        depotHelp.setRole(Role.DEPOTHELP);

        b.addUser(driver);
        b.addUser(attendee);
        b.addUser(depotHelp);

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
        d = classUnderTest.mapToDTO(b);
        assertTrue(d.getDescription().equals(b.getDescription()));
        assertTrue(d.getStartTime().equals(b.getStartTime()));
        assertTrue(d.getEndTime().equals(b.getEndTime()));
        assertEquals(1, d.getDrivers().size());
        assertEquals(1, d.getAttendees().size());
        assertEquals(1, d.getDepotHelps().size());
        assertTrue(d.isUsers());
    }

    @Test
    public void testMapToObjWithRepo() {
        Optional<Drive> o = Optional.of(b);
        d.setId(new Long(123));
        d.setDescription("beschrijving2");
        d.setStartTime(LocalDateTime.of(2019, 12, 12, 8, 00, 00, 00));
        d.setEndTime(LocalDateTime.of(2019, 12, 12, 11, 00, 00, 00));
        Mockito.when(mockRepository.findById(new Long(123))).thenReturn(o);
        b = classUnderTest.mapToObj(d);
        assertTrue(d.getDescription().equals(b.getDescription()));
        assertTrue(d.getStartTime().equals(b.getStartTime()));
        assertTrue(d.getEndTime().equals(b.getEndTime()));
        assertEquals(3, b.getUsers().size());
    }

    @Test
    public void testMapToObjWithoutRepo() {
        d = new DriveDTO();
        d.setDescription("beschrijving");
        d.setStartTime(LocalDateTime.of(2019, 12, 12, 9, 00, 00, 00));
        d.setEndTime(LocalDateTime.of(2019, 12, 12, 10, 00, 00, 00));
        d.setId(new Long(10));
        b = classUnderTest.mapToObj(d);
        assertTrue(d.getDescription().equals(b.getDescription()));
        assertTrue(d.getStartTime().equals(b.getStartTime()));
        assertTrue(d.getEndTime().equals(b.getEndTime()));
    }

}
package be.voedsaam.vzw.business.repository.impl;

import be.voedsaam.vzw.business.Drive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Integration test uses test data brought in via bootstrap package
 */
@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class DriveServiceRepoImplTest {
    @Autowired
    private DriveServiceRepoImpl classUnderTest;
    private Drive drive;
    @Before
    public void setup(){
        drive = new Drive();
        drive.setStartTime(LocalDateTime.now());
        drive.setEndTime(LocalDateTime.now());
        drive.setDescription("testRit");
    }


    @Test
    public void listAll() throws Exception {
        List<Drive> drives = (List<Drive>) classUnderTest.listAll();
        assertEquals(3,drives.size());

    }

    @Test
    public void getById() throws Exception {
        drive = classUnderTest.saveOrUpdate(drive);
        assertEquals(drive, classUnderTest.getById(drive.getId()));
    }


    @Test
    public void delete() throws Exception {
        drive = classUnderTest.saveOrUpdate(drive);
        Long id = drive.getId();
        classUnderTest.delete(null);
        classUnderTest.delete(drive.getId());
        assertNull(classUnderTest.getById(drive.getId()));
        assertNull(classUnderTest.getById(id));
    }

    @Test
    public void listAllByUser() throws Exception {
        List<Drive> drives =  classUnderTest.listAllByUser("kevin@voedsaam.be");
        assertEquals(3,drives.size());
    }

    @Test
    public void deleteAllDrivesWithoutSchedule() throws Exception {
        assertEquals(3,classUnderTest.listAll().size());
        drive = classUnderTest.saveOrUpdate(drive);
        assertEquals(4,classUnderTest.listAll().size());
        classUnderTest.deleteAllDrivesWithoutSchedule();
        assertEquals(3,classUnderTest.listAll().size());
    }

}
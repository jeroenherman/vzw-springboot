package be.voedsaam.vzw.business.repository.impl;

import be.voedsaam.vzw.business.Schedule;
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
public class ScheduleServiceRepoImplTest {

    @Autowired
    private ScheduleServiceRepoImpl classUnderTest;
    private Schedule schedule;


    @Before
    public void setup(){
        schedule = new Schedule();
        schedule.setName("testSchedule");

    }

    @Test
    public void listAll() throws Exception {
        assertEquals(2, classUnderTest.listAll().size());
    }

    @Test
    public void getById() throws Exception {
        schedule = classUnderTest.saveOrUpdate(schedule);
        schedule = classUnderTest.getById(schedule.getId());
    }


    @Test
    public void delete() throws Exception {
        schedule = classUnderTest.saveOrUpdate(schedule);
        Long id = schedule.getId();
        classUnderTest.delete(null);
        classUnderTest.delete(schedule.getId());
        classUnderTest.delete(id);
        assertNull(classUnderTest.getById(id));
    }

    @Test
    public void listAllByUserName() throws Exception {
        assertEquals(1,classUnderTest.listAllByUserName("jeroen.herman@voedsaam.be").size()); //owner
        assertEquals(1,classUnderTest.listAllByUserName("els.vandesteene@voedsaam.be").size()); //owner
        assertEquals(1,classUnderTest.listAllByUserName("cindy.depuydt@voedsaam.be").size()); // viewer
    }

    @Test
    public void removeDrives() throws Exception {
        schedule = classUnderTest.listAllByUserName("jeroen.herman@voedsaam.be").get(0);
        assertEquals(3,schedule.getDrives().size());
        classUnderTest.removeDrives(schedule.getId().intValue());
        schedule = classUnderTest.listAllByUserName("jeroen.herman@voedsaam.be").get(0);
        assertEquals(0,schedule.getDrives().size());
    }

}
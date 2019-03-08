package be.voedsaam.vzw.business.repository.impl;

import be.voedsaam.vzw.business.Task;
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
public class TaskServiceRepoImplTest {
    @Autowired
    private TaskServiceRepoImpl classUnderTest;
    private Task task;

    @Before
    public void setup(){
        task = new Task();
        task.setTitle("nieuwe Bestemming");
        task.setDiscription("contact");
    }

    @Test
    public void listAll() throws Exception {
        assertEquals(3,classUnderTest.listAll().size());
    }

    @Test
    public void getById() throws Exception {
        task = classUnderTest.saveOrUpdate(task);
        assertEquals(task, classUnderTest.getById(task.getId()));
    }


    @Test
    public void delete() throws Exception {
        task = classUnderTest.saveOrUpdate(task);
        Long id = task.getId();
        classUnderTest.delete(null);
        classUnderTest.delete(task.getId());
        assertNull( classUnderTest.getById(task.getId()));
        assertNull( classUnderTest.getById(id));
    }
}
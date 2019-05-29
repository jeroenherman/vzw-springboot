package be.voedsaam.vzw.business.repository.impl;

import be.voedsaam.vzw.business.Order;
import be.voedsaam.vzw.business.repository.OrderRepository;
import be.voedsaam.vzw.business.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class OrderServiceRepoImplTest {
    @Autowired
    private OrderServiceRepoImpl classUnderTest;
    private Order order;

    @Before
    public void setUp() throws Exception {
        order = new Order();
        order.setPickupDateTime(LocalDateTime.now());

    }

    @Test
    public void listAll() throws Exception {
        assertEquals(4,classUnderTest.listAll().size());

    }

    @Test
    public void getById() throws Exception {
        order = classUnderTest.saveOrUpdate(order);
        assertEquals( order , classUnderTest.getById(order.getId()));

    }



    @Test
    public void delete() throws Exception {
        order = classUnderTest.saveOrUpdate(order);
        Long id = order.getId();
        classUnderTest.delete(null);
        classUnderTest.delete(order.getId());
        assertNull( classUnderTest.getById(order.getId()));
        assertNull( classUnderTest.getById(id));
    }

    @Test
    public void listNewOrders() throws Exception {
        assertEquals(1,classUnderTest.listNewOrders().size());
    }

    @Test
    public void listCompletedOrders() throws Exception {
        assertEquals(1,classUnderTest.listCompletedOrders().size());
    }

    @Test
    public void listOrdersInProgress() throws Exception {
        assertEquals(0,classUnderTest.listOrdersInProgress().size());
    }

    @Test
    public void listCancelledOrders() throws Exception {
        assertEquals(1,classUnderTest.listCancelledOrders().size());
    }

    @Test
    public void listClosedOrders() throws Exception {
        assertEquals(1,classUnderTest.listClosedOrders().size());
    }

    @Test
    public void listAllByUser() throws Exception {
        assertEquals(4,classUnderTest.listAllByUser("kathy.blomme@gmail.com").size());
    }

}
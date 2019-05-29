package be.voedsaam.vzw.business.impl;

import be.voedsaam.vzw.business.Order;
import be.voedsaam.vzw.business.Stock;
import be.voedsaam.vzw.enums.Color;
import be.voedsaam.vzw.enums.Role;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PartnerTest {
    private Partner classToTest;
    private Stock stock = new Stock();
    private Order order = new Order();

    @Before
    public void setUp() throws Exception {
        classToTest = new Partner();
        order = new Order();
    }

    @Test
    public void testSetters() {
        classToTest.setFirstName("partner");
        assertEquals("partner", classToTest.getFirstName());
        classToTest.setLastName("herman");
        assertEquals("herman", classToTest.getLastName());
        assertEquals("partner herman", classToTest.getFullName());
        classToTest.setPassword("paswoord");
        assertEquals("paswoord", classToTest.getPassword());
        classToTest.setEncryptedPassword("encrypted");
        assertEquals("encrypted", classToTest.getEncryptedPassword());
        classToTest.setRole(Role.DRIVER);

        assertEquals(1, classToTest.getAuthorities().size());
        assertEquals(Role.PARTNER, classToTest.getRole());
        classToTest.setColor(Color.RED);
        assertEquals(Color.RED, classToTest.getColor());
        classToTest.setTel("123456");
        assertEquals("123456", classToTest.getTel());
        classToTest.setId(new Long(10));
        assertEquals(new Long(10), classToTest.getId());
    }

    @Test
    public void testModifyRole() {
        classToTest.setRole(Role.COORDINATOR);
        assertEquals(Role.PARTNER, classToTest.getRole());
        assertEquals(1, classToTest.getAuthorities().size());
        classToTest.setRole(Role.LOGISTICS);
        assertEquals(1, classToTest.getAuthorities().size());
        assertEquals(Role.PARTNER, classToTest.getRole());
        classToTest.setRole(Role.DRIVER);
        assertEquals(Role.PARTNER, classToTest.getRole());
        assertEquals(1, classToTest.getAuthorities().size());
        classToTest.setRole(Role.DEPOTHELP);
        assertEquals(Role.PARTNER, classToTest.getRole());
        assertEquals(1, classToTest.getAuthorities().size());
        classToTest.setRole(Role.ATTENDEE);
        assertEquals(Role.PARTNER, classToTest.getRole());
        assertEquals(1, classToTest.getAuthorities().size());
        classToTest.setRole(Role.VOLUNTEER);
        assertEquals(Role.PARTNER, classToTest.getRole());
        assertEquals(1, classToTest.getAuthorities().size());
    }

    @Test
    public void addStock() throws Exception {
        testSetters();
        assertEquals(0, classToTest.getStocks().size());
        classToTest.addStock(stock);
        assertEquals(1, classToTest.getStocks().size());


    }

    @Test
    public void removeStock() throws Exception {
        addStock();
        classToTest.removeStock(stock);
        assertEquals(0, classToTest.getStocks().size());

    }

    @Test
    public void addOrder() throws Exception {
        testSetters();
        assertEquals(0, classToTest.getOrders().size());
        classToTest.addOrder(order);
        assertEquals(1, classToTest.getOrders().size());

    }

    @Test
    public void removeOrder() throws Exception {
        addOrder();
        classToTest.removeOrder(order);
        assertEquals(0, classToTest.getStocks().size());
    }
}
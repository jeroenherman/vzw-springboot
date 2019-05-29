package be.voedsaam.vzw.business;

import be.voedsaam.vzw.business.impl.Partner;
import be.voedsaam.vzw.enums.OrderStatus;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class OrderTest {
    Order classUnderTest;
    Stock stock;
    Partner partner;
    Product product;

    @Before
    public void setUp() throws Exception {
        classUnderTest = new Order();
        stock = new Stock();
        partner = new Partner();
        product = new Product();
        product.setUnitOfMeasure(1.0);
    }

    @Test
    public void addProduct() throws Exception {
        assertEquals(0,classUnderTest.getProducts().size());
        classUnderTest.addProduct(product,10);
        assertEquals(1, classUnderTest.getProducts().size());
        assertEquals(10,classUnderTest.getProducts().get(product),0);
        classUnderTest.addProduct(product,10);
        assertEquals(1, classUnderTest.getProducts().size());
        assertEquals(20,classUnderTest.getProducts().get(product),0);
    }

    @Test
    public void removeProduct() throws Exception {
        addProduct();
        classUnderTest.removeProduct(product,10);
        assertEquals(1, classUnderTest.getProducts().size());
        assertEquals(10,classUnderTest.getProducts().get(product),0);
        classUnderTest.removeProduct(product,10);
        assertEquals(0,classUnderTest.getProducts().size());
    }


    @Test
    public void getPickupDateTime() throws Exception {
        assertNull(classUnderTest.getPickupDateTime());
    }

    @Test
    public void setPickupDateTime() throws Exception {
        classUnderTest.setPickupDateTime(LocalDateTime.of(2019,06,06,06,06));
        assertEquals(LocalDateTime.of(2019,06,06,06,06),classUnderTest.getPickupDateTime());
        classUnderTest.setPickupDateTime(LocalDateTime.of(2019,07,06,06,06));
        assertEquals(LocalDateTime.of(2019,07,06,06,06),classUnderTest.getPickupDateTime());

    }

    @Test
    public void getOrderStatus() throws Exception {
        assertNull(classUnderTest.getOrderStatus());
    }

    @Test
    public void setOrderStatus() throws Exception {
        classUnderTest.setOrderStatus(OrderStatus.NEW);
        assertEquals(OrderStatus.NEW, classUnderTest.getOrderStatus());
        classUnderTest.setOrderStatus(OrderStatus.IN_PROGRESS);
        assertEquals(OrderStatus.IN_PROGRESS, classUnderTest.getOrderStatus());

    }

    @Test
    public void getPartner() throws Exception {
        assertNull(classUnderTest.getPartner());
    }

    @Test
    public void setPartner() throws Exception {
        classUnderTest.setPartner(partner);
        assertEquals(partner, classUnderTest.getPartner());
    }

    @Test
    public void getStock() throws Exception {
        assertNull(classUnderTest.getStock());
    }

    @Test
    public void setStock() throws Exception {
        classUnderTest.setStock(stock);
        assertEquals(stock, classUnderTest.getStock());
    }

    @Test
    public void getTotalUnitOfMeasure() throws Exception {
        addProduct();
        assertEquals(20.0, classUnderTest.getTotalUnitOfMeasure(),0);


    }

}
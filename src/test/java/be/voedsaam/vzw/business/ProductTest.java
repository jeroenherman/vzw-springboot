package be.voedsaam.vzw.business;

import be.voedsaam.vzw.enums.ProductType;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class ProductTest {
    Product classUnderTest;
    Stock stock;
    @Before
    public void setUp() throws Exception {
        classUnderTest = new Product();
        stock = new Stock();

    }

    @Test
    public void getName() throws Exception {
        assertNull(classUnderTest.getName());
    }

    @Test
    public void setName() throws Exception {
        classUnderTest.setName("test1");
        assertEquals("test1", classUnderTest.getName());
        classUnderTest.setName("test2");
        assertEquals("test2", classUnderTest.getName());
    }

    @Test
    public void getDescription() throws Exception {
        assertNull(classUnderTest.getDescription());
    }

    @Test
    public void setDescription() throws Exception {
        classUnderTest.setDescription("test1");
        assertEquals("test1", classUnderTest.getDescription());
        classUnderTest.setDescription("test2");
        assertEquals("test2", classUnderTest.getDescription());
    }

    @Test
    public void getDeliveryNr() throws Exception {
        assertNull(classUnderTest.getDeliveryNr());
    }

    @Test
    public void setDeliveryNr() throws Exception {
        classUnderTest.setDeliveryNr("test1");
        assertEquals("test1", classUnderTest.getDeliveryNr());
        classUnderTest.setDeliveryNr("test2");
        assertEquals("test2", classUnderTest.getDeliveryNr());
    }

    @Test
    public void getUnitOfMeasure() throws Exception {
        assertNull(classUnderTest.getUnitOfMeasure());
    }

    @Test
    public void setUnitOfMeasure() throws Exception {
        classUnderTest.setUnitOfMeasure(10.0);
        assertEquals(10.0, classUnderTest.getUnitOfMeasure(),0);
        classUnderTest.setUnitOfMeasure(20.0);
        assertEquals(20.0, classUnderTest.getUnitOfMeasure(),0);
    }

    @Test
    public void getSchelfLife() throws Exception {
        assertNull(classUnderTest.getSchelfLife());
    }

    @Test
    public void setSchelfLife() throws Exception {
        classUnderTest.setSchelfLife(LocalDate.of(2019,06,06));
        assertEquals(LocalDate.of(2019,06,06),classUnderTest.getSchelfLife());
        classUnderTest.setSchelfLife(LocalDate.of(2019,07,06));
        assertEquals(LocalDate.of(2019,07,06),classUnderTest.getSchelfLife());
    }

    @Test
    public void getProductType() throws Exception {
        assertNull(classUnderTest.getProductType());
    }

    @Test
    public void setProductType() throws Exception {
        classUnderTest.setProductType(ProductType.FEAD);
        assertEquals(ProductType.FEAD, classUnderTest.getProductType());
        classUnderTest.setProductType(ProductType.FRUIT);
        assertEquals(ProductType.FRUIT, classUnderTest.getProductType());
    }

    @Test
    public void addStock() throws Exception {
        assertEquals(0,classUnderTest.getStocks().size());
        classUnderTest.addStock(stock);
        assertEquals(1,classUnderTest.getStocks().size());

    }

    @Test
    public void removeStock() throws Exception {
        addStock();
        classUnderTest.removeStock(stock);
        assertEquals(0,classUnderTest.getStocks().size());

    }


}
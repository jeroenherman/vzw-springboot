package be.voedsaam.vzw.service.mapper;

import be.voedsaam.vzw.business.Order;
import be.voedsaam.vzw.business.Stock;
import be.voedsaam.vzw.business.repository.OrderRepository;
import be.voedsaam.vzw.enums.OrderStatus;
import be.voedsaam.vzw.service.dto.OrderDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.junit.Assert.*;
@RunWith(MockitoJUnitRunner.class)
public class OrderMapperTest {

    private OrderMapper classUnderTest;
    private Order b;
    private OrderDTO d;

    @Mock
    OrderRepository mockRepository;

    @Before
    public void setUp() throws Exception {
        classUnderTest = new OrderMapper();
        classUnderTest.setOrderRepository(mockRepository);
        b = new Order();
        b.setOrderStatus(OrderStatus.NEW);
        b.setPickupDateTime(LocalDateTime.of(2019,06,06,06,06));
        Stock stock = new Stock();
        stock.setName("stocknaam");
        b.setStock(stock);


        d = new OrderDTO();
        d.setOrderStatus(OrderStatus.IN_PROGRESS);
        d.setPickupDateTime("2019-06-06T15:00:00");
        d.setPickUpDateTime(LocalDateTime.of(2019,06,06,15,0));
        d.setStock("stocknaam");

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
    public void mapToDTO() {
        d = null;
        d = classUnderTest.mapToDTO(b);
        assertEquals(d.getPickupDateTime(),b.getPickupDateTime().format(DateTimeFormatter.ISO_DATE_TIME));
        assertEquals(b.getPickupDateTime(),d.getPickUpDateTime());
        assertEquals(b.getPickupDateTime(),LocalDateTime.parse(d.getPickupDateTime(),DateTimeFormatter.ISO_DATE_TIME));
        assertEquals(d.getOrderStatus(),b.getOrderStatus());
    }

    @Test
    public void mapToObj() {
        b = null;
        b = classUnderTest.mapToObj(d);
        assertEquals(d.getPickupDateTime(),b.getPickupDateTime().format(DateTimeFormatter.ISO_DATE_TIME));
        assertEquals(b.getPickupDateTime(),d.getPickUpDateTime());
        assertEquals(b.getPickupDateTime(),LocalDateTime.parse(d.getPickupDateTime(),DateTimeFormatter.ISO_DATE_TIME));
        assertEquals(d.getOrderStatus(),b.getOrderStatus());

    }
    @Test
    public void mapToObjWithRepo() throws Exception {
        Optional<Order> o = Optional.of(b);
        Mockito.when(mockRepository.findById(new Long(123))).thenReturn(o);
        d.setId(new Long(123));
        b = classUnderTest.mapToObj(d);
        assertEquals(d.getId(), b.getId());
        assertEquals(d.getPickupDateTime(),b.getPickupDateTime().format(DateTimeFormatter.ISO_DATE_TIME));
        assertEquals(b.getPickupDateTime(),d.getPickUpDateTime());
        assertEquals(b.getPickupDateTime(),LocalDateTime.parse(d.getPickupDateTime(),DateTimeFormatter.ISO_DATE_TIME));
        assertEquals(d.getOrderStatus(),b.getOrderStatus());
        assertEquals(d.getStock(),b.getStock().getName());

    }

}
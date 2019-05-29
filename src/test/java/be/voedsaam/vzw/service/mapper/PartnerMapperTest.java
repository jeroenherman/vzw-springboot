package be.voedsaam.vzw.service.mapper;

import be.voedsaam.vzw.business.Address;
import be.voedsaam.vzw.business.Order;
import be.voedsaam.vzw.business.Stock;
import be.voedsaam.vzw.business.User;
import be.voedsaam.vzw.business.impl.Partner;
import be.voedsaam.vzw.business.impl.Volunteer;
import be.voedsaam.vzw.business.repository.UserRepository;
import be.voedsaam.vzw.enums.OrderStatus;
import be.voedsaam.vzw.enums.Role;
import be.voedsaam.vzw.service.dto.UserDTO;
import org.aspectj.weaver.ast.Or;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.*;
@RunWith(MockitoJUnitRunner.class)
public class PartnerMapperTest {
    private PartnerMapper classUnderTest;
    private Partner b;
    private UserDTO d;
    private Stock stock;
    private Order order;

    @Mock
    private UserRepository mockRepository;

    @Before
    public void setUp() throws Exception {
        classUnderTest = new PartnerMapper();
        classUnderTest.setUserRepository(mockRepository);
        b = new Partner("jeroen","herman","jeroen.herman@outlook.be","123456");
        b.setId(new Long(123));
        Address address = new Address();
        address.setStreet("straat");
        address.setNumber(10);
        address.setCity("stad");
        address.setPostalCode(9100);
        b.setAddress(address);
        b.setRole(Role.PARTNER);

        stock = new Stock();
        stock.setName("stocknaam");

        order = new Order();
        order.setStock(stock);
        order.setOrderStatus(OrderStatus.NEW);
        b.addStock(stock);
        b.addOrder(order);

        d = new UserDTO();
        d.setRole(Role.PARTNER);



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
        d= null;
        d = classUnderTest.mapToDTO(b);
        assertEquals(d.getRole(),b.getRole());
        assertEquals(d.getFirstName(),b.getFirstName());
        assertEquals(d.getLastName(),b.getLastName());
        assertEquals(d.getFullName(), b.getFullName());
        assertEquals(d.getCity(),b.getAddress().getCity());
        assertEquals(1,d.getStocks().size());

    }

    @Test
    public void mapToObj() {
        b = null;
        b = classUnderTest.mapToObj(d);
        assertEquals(d.getRole(),b.getRole());
        assertEquals(d.getFirstName(),b.getFirstName());
        assertEquals(d.getLastName(),b.getLastName());
        assertEquals(d.getFullName(), b.getFullName());
        assertEquals(d.getCity(),b.getAddress().getCity());
        assertEquals(0,b.getStocks().size());
    }

    @Test
    public void testMapToObjWithRepo() {
        Optional<User> o = Optional.of(b);
        Mockito.when(mockRepository.findById(new Long(123))).thenReturn(o);
        d.setId(new Long(123));
        b = classUnderTest.mapToObj(d);
        assertEquals(d.getRole(),b.getRole());
        assertEquals(d.getFirstName(),b.getFirstName());
        assertEquals(d.getLastName(),b.getLastName());
        assertEquals(d.getFullName(), b.getFullName());
        assertEquals(d.getCity(),b.getAddress().getCity());
        assertEquals(1,b.getStocks().size());

    }
}
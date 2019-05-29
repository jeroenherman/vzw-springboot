package be.voedsaam.vzw.service.mapper;

import be.voedsaam.vzw.business.Product;
import be.voedsaam.vzw.business.repository.ProductRepository;
import be.voedsaam.vzw.service.dto.ProductDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
@RunWith(MockitoJUnitRunner.class)
public class ProductMapperTest {
    private ProductMapper classUnderTest;
    private Product b;
    private ProductDTO d;

    @Mock
    private ProductRepository mockRepository;

    @Before
    public void setUp() throws Exception {
        classUnderTest = new ProductMapper();
        classUnderTest.setProductRepository(mockRepository);
    }

    @Test
    public void mapToDTO() {
    }

    @Test
    public void mapToObj() {
    }

    @Test
    public void mapToDTO1() {
    }

    @Test
    public void mapToObj1() {
    }

    @Test
    public void mapToDTO2() {
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
}
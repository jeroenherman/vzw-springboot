package be.voedsaam.vzw.service.mapper;

import be.voedsaam.vzw.business.Stock;
import be.voedsaam.vzw.business.repository.StockRepository;
import be.voedsaam.vzw.service.dto.StockDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
@RunWith(MockitoJUnitRunner.class)
public class StockMapperTest {
    private StockMapper classUnderTest;
    private Stock b;
    private StockDTO d;

    @Mock
    private StockRepository mockRepository;

    @Before
    public void setUp() throws Exception {
        classUnderTest = new StockMapper();
        classUnderTest.setStockRepository(mockRepository);
    }

    @Test
    public void mapToDTO() {
    }

    @Test
    public void mapToObj() {
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
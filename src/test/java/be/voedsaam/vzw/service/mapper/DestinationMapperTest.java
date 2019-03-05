package be.voedsaam.vzw.service.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import be.voedsaam.vzw.business.Address;
import be.voedsaam.vzw.business.Destination;
import be.voedsaam.vzw.business.repository.DestinationRepository;
import be.voedsaam.vzw.service.dto.DestinationDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class DestinationMapperTest {

	private DestinationMapper classUnderTest;
	private Destination b;
	private DestinationDTO d;
	@Mock
	DestinationRepository mockRepository;

	@Before
	public void setup() {
		classUnderTest = new DestinationMapper();
		classUnderTest.setDestinationRepository(mockRepository);
		b = new Destination();
		b.setDestinationName("bestemming");
		b.setContactInfo("contactinfo");
		List<String> strings = new ArrayList<>();
		strings.add("String1");
		strings.add("String2");
		b.setAgreements(strings);
		Address address = new Address();
		address.setStreet("straat");
		address.setNumber(10);
		address.setCity("stad");
		address.setPostalCode(9100);
		b.setAddress(address);
		b.setId(new Long(123));
		d = new DestinationDTO();

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
	public void testMapToDTO() {
		d= null;
		d = classUnderTest.mapToDTO(b);

		assertEquals(d.getId(), b.getId());
		assertEquals(d.getCity(), b.getAddress().getCity());
		assertEquals(d.getAgreements(), b.getAgreements());
		assertFalse(d.isDrives());

	}

	@Test
	public void testMapToObjWithRepo() {
		Optional<Destination> o = Optional.of(b);
		Mockito.when(mockRepository.findById(new Long(123))).thenReturn(o);
		d.setId(new Long(123));
		b = classUnderTest.mapToObj(d);
		assertEquals(d.getId(), b.getId());
		assertEquals(d.getCity(), b.getAddress().getCity());
		assertFalse(d.isDrives());
	}

	@Test
	public void testMapToObjWithoutRepo() {
		d.setDestinationName("bestemming2");
		d.setContactInfo("contactinfo2");
		d.setDrives(true);
		d.setId(new Long (10));
		d.setStreet("straat2");
		d.setPostalCode(9140);
		b = classUnderTest.mapToObj(d);
		assertEquals(d.getId(), b.getId());
		assertEquals(d.getCity(), b.getAddress().getCity());
		assertTrue(d.isDrives());
	}
}
package be.voedsaam.vzw.service.mapper;

import be.voedsaam.vzw.business.Address;
import be.voedsaam.vzw.business.Destination;
import be.voedsaam.vzw.commons.AbstractMapper;
import be.voedsaam.vzw.service.dto.DestinationDTO;
import org.springframework.stereotype.Component;

@Component
public class DestinationMapper extends AbstractMapper<Destination, DestinationDTO>{

	@Override
	public DestinationDTO mapToDTO(Destination b) {
		if (b==null)
		return null;
		DestinationDTO d = new DestinationDTO();
		d.setId(b.getId());
		d.setCity(b.getAddress().getCity());
		d.setStreet(b.getAddress().getStreet());
		d.setStreetNumber(b.getAddress().getNumber());
		d.setPostalCode(b.getAddress().getPostalCode());
		d.setContactInfo(b.getContactInfo());
		d.setDestinationName(b.getDestinationName());
		return d;
	}

	@Override
	public Destination mapToObj(DestinationDTO d) {
		if (d==null)
			return null;
			Destination b = new Destination();
			b.setId(d.getId());
			Address a = new Address();
			a.setCity(d.getCity());
			a.setStreet(d.getStreet());
			a.setNumber(d.getStreetNumber());
			a.setPostalCode(d.getPostalCode());
			b.setAddress(a);
			b.setContactInfo(d.getContactInfo());
			b.setDestinationName(d.getDestinationName());
			return b;
	}
	
	

}

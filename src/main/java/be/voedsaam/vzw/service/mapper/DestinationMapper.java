package be.voedsaam.vzw.service.mapper;

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
			b.getAddress().setCity(d.getCity());
			b.getAddress().setStreet(d.getStreet());
			b.getAddress().setNumber(d.getStreetNumber());
			b.getAddress().setPostalCode(d.getPostalCode());
			b.setContactInfo(d.getContactInfo());
			b.setDestinationName(d.getDestinationName());
			return b;
	}
	
	

}

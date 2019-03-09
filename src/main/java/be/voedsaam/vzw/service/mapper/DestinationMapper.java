package be.voedsaam.vzw.service.mapper;

import be.voedsaam.vzw.business.Address;
import be.voedsaam.vzw.business.Destination;
import be.voedsaam.vzw.business.repository.DestinationRepository;
import be.voedsaam.vzw.commons.AbstractMapper;
import be.voedsaam.vzw.service.dto.DestinationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DestinationMapper extends AbstractMapper<Destination, DestinationDTO> {

    private DestinationRepository destinationRepository;

    @Autowired
    public void setDestinationRepository(DestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;
    }

    @Override
    public DestinationDTO mapToDTO(Destination b) {
        if (b == null)
            return null;
        DestinationDTO d = new DestinationDTO();
        d.setId(b.getId());
        if (b.getAddress() != null) {
            d.setCity(b.getAddress().getCity());
            d.setStreet(b.getAddress().getStreet());
            d.setStreetNumber(b.getAddress().getNumber());
            d.setPostalCode(b.getAddress().getPostalCode());
        }
        d.setContactInfo(b.getContactInfo());
        d.setDestinationName(b.getDestinationName());
        d.setAgreements(b.getAgreements());
        if (b.getDrives().size() != 0)
            d.setDrives(true);
        return d;
    }

    @Override
    public Destination mapToObj(DestinationDTO d) {
        if (d == null)
            return null;
        Destination b = new Destination();
        Optional<Destination> o = Optional.empty();
        if (d.getId() != null)
            o = destinationRepository.findById(d.getId());

        if (o.isPresent())
            b = o.get();

        b.setId(d.getId());
        if (b.getAddress() == null) {
            b.setAddress(new Address());
        }
        b.getAddress().setCity(d.getCity());
        b.getAddress().setStreet(d.getStreet());
        b.getAddress().setNumber(d.getStreetNumber());
        b.getAddress().setPostalCode(d.getPostalCode());
        b.setContactInfo(d.getContactInfo());
        b.setDestinationName(d.getDestinationName());
        b.setAgreements(d.getAgreements());
        return b;
    }


}

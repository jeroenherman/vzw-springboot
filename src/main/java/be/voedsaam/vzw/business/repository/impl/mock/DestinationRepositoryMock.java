package be.voedsaam.vzw.business.repository.impl.mock;

import be.voedsaam.vzw.business.Destination;
import be.voedsaam.vzw.business.Drive;
import be.voedsaam.vzw.business.repository.DestinationRepository;
import be.voedsaam.vzw.business.repository.DriveRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("mock")
public class DestinationRepositoryMock extends AbstractMapService implements DestinationRepository {
	@Override
	public Destination getById(Long id) {
		return (Destination) super.getById(id) ;
	}

	@Override
	public Destination saveOrUpdate(Destination domainObject) {
		return (Destination) super.saveOrUpdate(domainObject);
	}
	@Override
	public void delete(Long id) {
		super.delete(id);
	}
}

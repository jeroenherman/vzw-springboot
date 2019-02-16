package be.voedsaam.vzw.business.repository.impl.mock;

import be.voedsaam.vzw.business.Destination;
import be.voedsaam.vzw.business.Drive;
import be.voedsaam.vzw.business.repository.DriveRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
@Profile("mock")
public class DriveRepositoryMock extends AbstractMapService implements DriveRepository {

	@Override
	public Drive getById(Long id) {
		return (Drive)super.getById(id) ;
	}

	@Override
	public Drive saveOrUpdate(Drive domainObject) {
		return (Drive) super.saveOrUpdate(domainObject);
	}

	@Override
	public void delete(Long id) {
		super.delete(id);
	}
}

package be.voedsaam.vzw.business.repository;

import be.voedsaam.vzw.business.Destination;
import be.voedsaam.vzw.business.Drive;
import be.voedsaam.vzw.commons.IRepository;

public interface DriveRepository extends IRepository<Drive> {

	Destination findDestinationById(Long id);

	Destination addDestination(Destination destination);
}

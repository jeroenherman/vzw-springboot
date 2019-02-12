package be.voedsaam.vzw.business.repository.impl.mock;

import be.voedsaam.vzw.business.Destination;
import be.voedsaam.vzw.business.Drive;
import be.voedsaam.vzw.business.repository.DriveRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
@Profile("mock")
public class DriveRepositoryMock implements DriveRepository {

	private Map<Long, Drive> drives;
	private Long id;

	public DriveRepositoryMock() {
		super();
		this.drives = new HashMap<Long, Drive>();
		this.id = new Long(1);
	}

	@Override
	public Drive find(Drive aggregate) {
		Drive found = null;
		for (Drive drive : drives.values()) {
			if (drive.equals(aggregate))
				found = drive;
		}
		return found;
	}

	@Override
	public Drive create(Drive aggregate) {
		aggregate.setId(id);
		drives.put(id, aggregate);
		id = id + 1;
		if (this.exists(aggregate))
			return aggregate;
		return null;
	}

	@Override
	public Drive update(Drive aggregate) {
		drives.replace(aggregate.getId(), aggregate);
		if (this.exists(aggregate))
			return aggregate;
		return null;
	}

	@Override
	public boolean delete(Drive aggregate) {

		for (Iterator<Drive> iterator = drives.values().iterator(); iterator.hasNext();) {
			Drive value = iterator.next();
			if (value.equals(aggregate)) {
				iterator.remove();
			}
		}

		return !this.exists(aggregate);
	}

	@Override
	public boolean createAll(Collection<Drive> aggregates) {
		for (Drive drive : aggregates) {
			this.create(drive);
		}
		return false;
	}

	@Override
	public List<Drive> getAll() {
		return new ArrayList<Drive>(drives.values());
	}

	@Override
	public boolean updateAll(Collection<Drive> aggregates) {
		for (Drive drive : aggregates) {
			drives.replace(drive.getId(), drive);
		}
		return true;
	}

	@Override
	public boolean deleteAll(Collection<Drive> aggregates) {
		drives.clear();
		return !drives.isEmpty();
	}

	@Override
	public Drive getByID(Long id) {
		return drives.get(id);
	}

	@Override
	public boolean exists(Drive aggregate) {
		return drives.containsValue(aggregate);
	}

	@Override
	public void close() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Destination findDestinationById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Destination addDestination(Destination destination) {
		// TODO Auto-generated method stub
		return null;
	}

}

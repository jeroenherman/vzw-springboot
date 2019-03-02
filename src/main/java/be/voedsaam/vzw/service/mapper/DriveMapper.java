package be.voedsaam.vzw.service.mapper;

import be.voedsaam.vzw.business.Drive;
import be.voedsaam.vzw.business.User;
import be.voedsaam.vzw.business.repository.DriveRepository;
import be.voedsaam.vzw.commons.AbstractMapper;
import be.voedsaam.vzw.enums.Role;
import be.voedsaam.vzw.service.dto.DriveDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DriveMapper extends AbstractMapper<Drive, DriveDTO> {

	private DriveRepository driveRepository;
	@Autowired
	public void setDriveRepository(DriveRepository driveRepository) {
		this.driveRepository = driveRepository;
	}

	@Override
	public DriveDTO mapToDTO(Drive b) {
		if (b==null)
			return null;
		DriveDTO d = new DriveDTO();
		d.setId(b.getId());
		d.setDescription(b.getDescription());
		d.setDrivers(b.getUsers().stream().filter(u -> u.getRole()
				.equals(Role.DRIVER))
				.map(User::getFullName)
				.collect(Collectors.toList()));
		d.setAttendees(b.getUsers().stream().filter(u -> u.getRole()
				.equals(Role.ATTENDEE))
				.map(User::getFullName).collect(Collectors.toList()));
		d.setDepotHelps(b.getUsers().stream().filter(u-> u.getRole()
				.equals(Role.DEPOTHELP))
				.map(User::getFullName).collect(Collectors.toList()));
		d.setStartTime(b.getStartTime());
		d.setEndTime(b.getEndTime());
		if (b.getSchedule()!=null)
		d.setSchedule(b.getSchedule().getName());
		if (b.getUsers().size()!=0)
			d.setUsers(true);
		return d;
	}

	@Override
	public Drive mapToObj(DriveDTO d) {
		if (d==null)
			return null;
		Drive b = new Drive();
		Optional<Drive> o = Optional.empty();
		if (d.getId()!=null)
			o = driveRepository.findById(d.getId());

		if(o.isPresent())
			b = o.get();
		b.setDescription(d.getDescription());
		b.setId(d.getId());
		b.setStartTime(d.getStartTime());
		b.setEndTime(d.getEndTime());
		return b;
	}
	

}

package be.voedsaam.vzw.service.mapper;

import be.voedsaam.vzw.business.Drive;
import be.voedsaam.vzw.business.User;
import be.voedsaam.vzw.commons.AbstractMapper;
import be.voedsaam.vzw.service.dto.DriveDTO;

public class DriveMapper extends AbstractMapper<Drive, DriveDTO> {

	@Override
	public DriveDTO mapToDTO(Drive b) {
		if (b==null)
			return null;
		DriveDTO d = new DriveDTO();
		d.setId(b.getId());
		if (b.getDriver()!=null)
		d.setDriver(b.getDriver().getFullName());
		if (b.getAttendee()!=null)
		d.setAttendee(b.getAttendee().getFullName());
		if (b.getDepotHelp()!=null)
		d.setDepotHelp(b.getDepotHelp().getFullName());
		d.setStartTime(b.getStartTime());
		d.setEndTime(b.getEndTime());
		return d;
	}

	@Override
	public Drive mapToObj(DriveDTO d) {
		if (d==null)
			return null;
		Drive b = new Drive();
		b.setId(d.getId());
		b.setDriver(new User(d.getDriver()));
		b.setAttendee(new User(d.getAttendee()));
		b.setDepotHelp(new User(d.getDepotHelp()));
		b.setStartTime(d.getStartTime());
		b.setEndTime(d.getEndTime());
		return b;
	}
	

}

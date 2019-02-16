package be.voedsaam.vzw.service.mapper;

import be.voedsaam.vzw.business.Drive;
import be.voedsaam.vzw.business.User;
import be.voedsaam.vzw.commons.AbstractMapper;
import be.voedsaam.vzw.service.dto.DriveDTO;
import org.springframework.stereotype.Component;

@Component
public class DriveMapper extends AbstractMapper<Drive, DriveDTO> {

	@Override
	public DriveDTO mapToDTO(Drive b) {
		if (b==null)
			return null;
		DriveDTO d = new DriveDTO();
		d.setId(b.getId());
		if (b.getDriver()!=null) {
			d.setDriverId(b.getDriver().getId());
			d.setDriver(b.getDriver().getFullName());
		}
		if (b.getAttendee()!=null) {
			d.setAttendeeId(b.getAttendee().getId());
			d.setAttendee(b.getAttendee().getFullName());
		}
		if (b.getDepotHelp()!=null) {
			{
				d.setDepotHelpId(b.getDepotHelp().getId());
				d.setDepotHelp(b.getDepotHelp().getFullName());
			}
		}
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
		if ((d.getDriverId()!=null)&&(d.getDriver()!=null)){
		User driver = new User(d.getDriver());
		driver.setId(d.getDriverId());
		b.setDriver(driver);
		}
		if ((d.getAttendeeId()!=null)&&(d.getAttendee()!=null)){
			User attendee = new User(d.getAttendee());
			attendee.setId(d.getAttendeeId());
			b.setAttendee(attendee);
		}
		if ((d.getDepotHelp()!=null)&&(d.getDepotHelp()!=null)){
			User depotHelp = new User(d.getDepotHelp());
			depotHelp.setId(d.getDepotHelpId());
			b.setDepotHelp(depotHelp);
		}
		b.setStartTime(d.getStartTime());
		b.setEndTime(d.getEndTime());
		return b;
	}
	

}

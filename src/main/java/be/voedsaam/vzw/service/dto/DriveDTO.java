package be.voedsaam.vzw.service.dto;

import java.time.LocalDateTime;
import java.util.List;

public class DriveDTO {
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private String driver;
	private Long driverId;
	private String attendee;
	private Long attendeeId;
	private String depotHelp;
	private Long depotHelpId;
	private Long id;
	public DriveDTO() {
		startTime = LocalDateTime.now();
		endTime = startTime.plusHours(1);

	}

	public DriveDTO(LocalDateTime startTime, LocalDateTime endTime) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) throws ArithmeticException {
		if (endTime.isBefore(startTime))
			throw new ArithmeticException("End time must be greater then start time");
		this.endTime = endTime;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getAttendee() {
		return attendee;
	}

	public void setAttendee(String attendee) {
		this.attendee = attendee;
	}

	public String getDepotHelp() {
		return depotHelp;
	}

	public void setDepotHelp(String depotHelp) {
		this.depotHelp = depotHelp;
	}

	public void setId(Long id) {
		this.id = id;

	}
	
	public Long getId() {
		return id;
	}

	public Long getDriverId() {
		return driverId;
	}

	public void setDriverId(Long driverId) {
		this.driverId = driverId;
	}

	public Long getAttendeeId() {
		return attendeeId;
	}

	public void setAttendeeId(Long attendeeId) {
		this.attendeeId = attendeeId;
	}

	public Long getDepotHelpId() {
		return depotHelpId;
	}

	public void setDepotHelpId(Long depotHelpId) {
		this.depotHelpId = depotHelpId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attendee == null) ? 0 : attendee.hashCode());
		result = prime * result + ((depotHelp == null) ? 0 : depotHelp.hashCode());
		result = prime * result + ((driver == null) ? 0 : driver.hashCode());
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DriveDTO other = (DriveDTO) obj;
		if (attendee == null) {
			if (other.attendee != null)
				return false;
		} else if (!attendee.equals(other.attendee))
			return false;
		if (depotHelp == null) {
			if (other.depotHelp != null)
				return false;
		} else if (!depotHelp.equals(other.depotHelp))
			return false;
		if (driver == null) {
			if (other.driver != null)
				return false;
		} else if (!driver.equals(other.driver))
			return false;
		if (endTime == null) {
			if (other.endTime != null)
				return false;
		} else if (!endTime.equals(other.endTime))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DriveDTO [startTime=" + startTime + ", endTime=" + endTime + ", driver=" + driver + ", attendee="
				+ attendee + ", depotHelp=" + depotHelp + "]";
	}

}

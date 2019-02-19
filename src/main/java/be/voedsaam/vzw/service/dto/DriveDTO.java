package be.voedsaam.vzw.service.dto;

import java.time.LocalDateTime;
import java.util.List;

public class DriveDTO {
	private String description;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private List<String> drivers;
	private List<String> attendees;
	private List<String> depotHelps;
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


	public void setId(Long id) {
		this.id = id;

	}
	
	public Long getId() {
		return id;
	}

	public List<String> getDrivers() {
		return drivers;
	}

	public void setDrivers(List<String> drivers) {
		this.drivers = drivers;
	}

	public List<String> getAttendees() {
		return attendees;
	}

	public void setAttendees(List<String> attendees) {
		this.attendees = attendees;
	}

	public List<String> getDepotHelps() {
		return depotHelps;
	}

	public void setDepotHelps(List<String> depotHelps) {
		this.depotHelps = depotHelps;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		DriveDTO driveDTO = (DriveDTO) o;

		if (!getDescription().equals(driveDTO.getDescription())) return false;
		if (!getStartTime().equals(driveDTO.getStartTime())) return false;
		if (!getEndTime().equals(driveDTO.getEndTime())) return false;
		return getId() != null ? getId().equals(driveDTO.getId()) : driveDTO.getId() == null;
	}

	@Override
	public int hashCode() {
		int result = getDescription().hashCode();
		result = 31 * result + getStartTime().hashCode();
		result = 31 * result + getEndTime().hashCode();
		result = 31 * result + (getId() != null ? getId().hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("DriveDTO{");
		sb.append("description='").append(description).append('\'');
		sb.append(", startTime=").append(startTime);
		sb.append(", endTime=").append(endTime);
		sb.append(", drivers=").append(drivers);
		sb.append(", attendees=").append(attendees);
		sb.append(", depotHelps=").append(depotHelps);
		sb.append('}');
		return sb.toString();
	}
}

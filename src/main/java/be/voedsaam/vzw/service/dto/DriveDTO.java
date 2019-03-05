package be.voedsaam.vzw.service.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class DriveDTO {
	private Long id;
	private String description;
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'hh:mm")
	private LocalDateTime startTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'hh:mm")
	private LocalDateTime endTime;
	private List<String> drivers;
	private List<String> attendees;
	private List<String> depotHelps;
	private String schedule;
	private boolean users;


	public DriveDTO() {
		LocalTime time = LocalTime.of(9,00);
		startTime = LocalDateTime.of(LocalDate.now(), time);
		endTime = startTime.plusHours(1);

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

	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	public boolean isUsers() {
		return users;
	}

	public void setUsers(boolean users) {
		this.users = users;
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

}

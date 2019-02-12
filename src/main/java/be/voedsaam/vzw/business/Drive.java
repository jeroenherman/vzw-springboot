package be.voedsaam.vzw.business;

import be.voedsaam.vzw.commons.Role;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Drive implements Serializable {


	private static final long serialVersionUID = -8732879233262787345L;
	@Id
	@GeneratedValue
	private Long id; 
	
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	@OneToOne(fetch = FetchType.EAGER)
	private User driver;
	@OneToOne( fetch = FetchType.EAGER)
	private User attendee;
	@OneToOne( fetch = FetchType.EAGER)
	private User depotHelp;
	@OneToMany(cascade = CascadeType.ALL)
	private Collection<Destination> destinations;
	
	
	public Drive() {
		super();
		this.startTime = LocalDateTime.now();
		this.endTime = LocalDateTime.now();
		this.driver = new User();
		driver.setRole(Role.DRIVER);
		this.attendee = new User();
		attendee.setRole(Role.ATTENDEE);
		this.depotHelp = new User();
		depotHelp.setRole(Role.DEPOTHELP);
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
	public User getDriver() {
		return driver;
	}
	public void setDriver(User driver) {
		this.driver = driver;
	}
	public User getAttendee() {
		return attendee;
	}
	public void setAttendee(User attendee) {
		this.attendee = attendee;
	}
	public User getDepotHelp() {
		return depotHelp;
	}
	public void setDepotHelp(User depotHelp) {
		this.depotHelp = depotHelp;
	}
	public Collection<Destination> getDestinations() {
		if (destinations==null)
			destinations = new ArrayList<Destination>();
		return destinations;
	}
	public void setDestinations(Collection<Destination> destinations) {
		this.destinations = destinations;
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((driver == null) ? 0 : driver.hashCode());
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
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
		Drive other = (Drive) obj;
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
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Drive [startTime=" + startTime + ", endTime=" + endTime + ", driver=" + driver + ", attendee="
				+ attendee + ", depotHelp=" + depotHelp + ", destinations=" + destinations + "]";
	}
	
	
	
	
}

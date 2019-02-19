package be.voedsaam.vzw.business;

import be.voedsaam.vzw.enums.Role;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Task extends AbstractDomainClass{

	private String title;
	private String discription;
	@ManyToOne()
	private Destination destination;
	@Enumerated(EnumType.STRING)
	private Role role;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDiscription() {
		return discription;
	}
	public void setDiscription(String discription) {
		this.discription = discription;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}


    public void setDestination(Destination destination) {
		this.destination = destination;
    }

	public Destination getDestination() {
		return destination;
	}
}

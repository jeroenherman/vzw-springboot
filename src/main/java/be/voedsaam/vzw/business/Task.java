package be.voedsaam.vzw.business;

import be.voedsaam.vzw.enums.Role;

import javax.persistence.*;

@Entity
public class Task extends AbstractDomainClass{

	private String title;
	@Lob
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

	@PreRemove
	public void clear(){
		setDestination(null);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Task task = (Task) o;

		if (!title.equals(task.title)) return false;
		return role == task.role;
	}

	@Override
	public int hashCode() {
		int result = title.hashCode();
		result = 31 * result + role.hashCode();
		return result;
	}
}

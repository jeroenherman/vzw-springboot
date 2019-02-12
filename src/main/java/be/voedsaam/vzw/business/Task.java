package be.voedsaam.vzw.business;

import be.voedsaam.vzw.commons.Role;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Task implements Serializable {


	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	
	private String title;
	private String discription;
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
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	
}

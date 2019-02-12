package be.voedsaam.vzw.service.dto;

import be.voedsaam.vzw.commons.Role;

public class TaskDTO {
	
	private  String description, title;
	private Role role;
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	

}

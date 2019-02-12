package be.voedsaam.vzw.service.manager;

import be.voedsaam.vzw.service.dto.UserDTO;

import java.util.List;

public interface VzwManagement {
	public UserDTO login(UserDTO user);
	public UserDTO addUser(UserDTO user);
	public boolean removeUser(Long id);
	public List<UserDTO> getAllUsers();
	public boolean logOut();
	public UserDTO getCurrentUser();
	public UserDTO getById(Long id);
}

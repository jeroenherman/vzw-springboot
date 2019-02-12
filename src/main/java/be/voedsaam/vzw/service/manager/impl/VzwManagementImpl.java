package be.voedsaam.vzw.service.manager.impl;

import be.voedsaam.vzw.business.User;
import be.voedsaam.vzw.business.repository.UserRepository;
import be.voedsaam.vzw.service.dto.UserDTO;
import be.voedsaam.vzw.service.manager.VzwManagement;
import be.voedsaam.vzw.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class VzwManagementImpl implements VzwManagement {
	private User currentUser;
	private UserMapper userMapper;
	private UserRepository userRepository;
	@Autowired
	public VzwManagementImpl(UserMapper userMapper, UserRepository userRepository) {
		super();
		this.userMapper = userMapper;
		this.userRepository = userRepository;
	}

	@Override
	public UserDTO login(UserDTO user) {
		if (userRepository.exists(userMapper.mapToObj(user))) {
			User found = userRepository.find(userMapper.mapToObj(user));
			
			if ((found.getEmail().equals(user.getEmail())&&(found.getPassword().equals(user.getPassword()))))
				currentUser = found;
				return userMapper.mapToDTO(currentUser);
		} else
			return null;
	}

	@Override
	public UserDTO addUser(UserDTO user) {
		User result = null;
		if(userRepository.exists(userMapper.mapToObj(user))) 
			result = userRepository.update(userMapper.mapToObj(user));
		else
			result = userRepository.create(userMapper.mapToObj(user));
		return userMapper.mapToDTO(result);
	}

	@Override
	public boolean removeUser(Long id) {
		return userRepository.delete(id);
	}

	@Override
	public boolean logOut() {
		currentUser = null;
		return true;
	}


	@Override
	public List<UserDTO> getAllUsers() {
		
		return userMapper.mapToDTO(userRepository.getAll());
	}

	@Override
	public UserDTO getCurrentUser() {
		
		return userMapper.mapToDTO(currentUser);
	}

	@Override
	public UserDTO getById(Long id) {
		 return userMapper.mapToDTO(userRepository.getByID(id));
	}
}

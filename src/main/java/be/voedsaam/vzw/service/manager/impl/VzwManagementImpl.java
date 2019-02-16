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
		//TODO spring security
		return null;
	}

	@Override
	public UserDTO addUser(UserDTO user) {
		return userMapper.mapToDTO(userRepository.saveOrUpdate(userMapper.mapToObj(user)));
	}

	@Override
	public boolean removeUser(Long id) {
		userRepository.delete(id);
		 if (userRepository.getById(id)==null)
		 	return true;
		 return false;
	}

	@Override
	public boolean logOut() {
		currentUser = null;
		return true;
	}


	@Override
	public List<UserDTO> getAllUsers() {
		
		return userMapper.mapToDTO((List<User>)userRepository.listAll());
	}

	@Override
	public UserDTO getCurrentUser() {
		
		return userMapper.mapToDTO(currentUser);
	}

	@Override
	public UserDTO getById(Long id) {
		 return userMapper.mapToDTO(userRepository.getById(id));
	}
}

package be.voedsaam.vzw.business.repository;

import be.voedsaam.vzw.business.User;
import be.voedsaam.vzw.commons.IRepository;

import java.util.Optional;

public interface UserRepository extends IRepository<User> {
		Optional<User> findByFullName(String fullName);
	
	
}

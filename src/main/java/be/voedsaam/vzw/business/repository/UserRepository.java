package be.voedsaam.vzw.business.repository;

import be.voedsaam.vzw.business.User;
import be.voedsaam.vzw.commons.IRepository;

public interface UserRepository extends IRepository<User> {
	public User findByName(User aggregate);
	
	
}

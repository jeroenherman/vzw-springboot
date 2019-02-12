package be.voedsaam.vzw.business.repository.impl.mock;

import be.voedsaam.vzw.business.User;
import be.voedsaam.vzw.business.repository.UserRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
@Profile("mock")
public class UserRepositoryMock implements UserRepository {
	
	private Map <Long,User> users;
	private Long id;
	public UserRepositoryMock() {
		users = new HashMap<Long, User>();
		id = new Long(1);
		
	}
	
	
	@Override
	public User find(User aggregate) {
		User found = null;
		for (User user : users.values()) {
			if (user.equals(aggregate))
				found = user;
		}
		return found;
	}

	@Override
	public User create(User aggregate) {
		aggregate.setId(id);
		users.put(id, aggregate);
		id = id + 1;
		if (this.exists(aggregate)) 
		return aggregate;
		return null;
	}

	@Override
	public User update(User aggregate) {
		users.replace(aggregate.getId(), aggregate);
		if (this.exists(aggregate)) 
			return aggregate;
			return null;
	}

	@Override
	public boolean delete(User aggregate) {
		for (User user : users.values()) {
			if (user.equals(aggregate))
				users.remove(user.getId());
		}
		return !this.exists(aggregate);
	}

	@Override
	public boolean delete(long id) {
		users.remove(id);
		return users.containsKey(id);
	}

	@Override
	public boolean createAll(Collection<User> aggregates) {
		for (User user : aggregates) {
			this.create(user);
		}
		return false;
	}

	@Override
	public List<User> getAll() {
		return new ArrayList<User>(users.values());
	}

	@Override
	public boolean updateAll(Collection<User> aggregates) {
		for (User user : aggregates) {
			users.replace(user.getId(), user);
		}
		return true;
	}

	@Override
	public boolean deleteAll(Collection<User> aggregates) {
		users.clear();
		return !users.isEmpty();
	}

	@Override
	public User getByID(Long id) {
		
		return users.get(id);
	}

	@Override
	public boolean exists(User aggregate) {
		return users.containsValue(aggregate);
	}


	@Override
	public void close() {
		throw new UnsupportedOperationException();	
	}


	@Override
	public User findByName(User aggregate) {
		User found = null;
		for (User user : users.values()) {
			if (user.getFullName().equals(aggregate.getFullName()))
				found = user;
		}
		return found;
	}

}

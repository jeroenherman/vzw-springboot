package be.voedsaam.vzw.business.repository.impl.mock;

import be.voedsaam.vzw.business.DomainObject;
import be.voedsaam.vzw.business.User;
import be.voedsaam.vzw.business.repository.UserRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
@Profile("mock")
public class UserRepositoryMock extends AbstractMapService implements UserRepository {

	@Override
	public Optional<User> findByFullName(String fullName) {
		Optional<User> o = Optional.empty();
		for ( DomainObject u: super.domainMap.values()) {
			if (((User)u).getFullName().equals(fullName));
				o = Optional.of((User)u);
		}
		return o;
	}

	@Override
	public User getById(Long id) {
		return (User)super.getById(id);
	}

	@Override
	public User saveOrUpdate(User domainObject) {
		return (User) super.saveOrUpdate(domainObject);
	}

	@Override
	public void delete(Long id) {
		super.delete(id);
	}

}

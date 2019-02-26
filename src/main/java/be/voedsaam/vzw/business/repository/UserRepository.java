package be.voedsaam.vzw.business.repository;

import be.voedsaam.vzw.business.User;
import be.voedsaam.vzw.enums.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmailIgnoreCase(String email);
    List<User> findAllByRole(Role role);
   // User findByEmailIgnoreCaseAndPassword(String email, String password);
}

package be.voedsaam.vzw.business.repository;

import be.voedsaam.vzw.business.User;
import be.voedsaam.vzw.enums.Role;
import org.springframework.data.repository.CrudRepository;



public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmailIgnoreCase(String email);
    User findByRole(Role role);
    User findByEmailIgnoreCaseAndPassword(String email, String password);
}

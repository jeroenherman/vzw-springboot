package be.voedsaam.vzw.service;


import be.voedsaam.vzw.business.User;
import be.voedsaam.vzw.commons.CRUDService;
import be.voedsaam.vzw.enums.Role;

public interface UserService extends CRUDService<User> {

    User findByEmail(String email);
    User findByRole(Role role);
    User Login(String email, String password);
}

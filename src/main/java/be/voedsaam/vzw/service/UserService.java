package be.voedsaam.vzw.service;


import be.voedsaam.vzw.business.User;
import be.voedsaam.vzw.commons.CRUDService;
import be.voedsaam.vzw.enums.Role;

import java.util.List;

public interface UserService extends CRUDService<User> {

    User findByEmail(String email);
    List<User> listByRole(Role role);
    User Login(String email, String password);
}

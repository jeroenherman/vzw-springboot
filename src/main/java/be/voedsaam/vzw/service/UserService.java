package be.voedsaam.vzw.service;


import be.voedsaam.vzw.business.Employee;
import be.voedsaam.vzw.business.User;
import be.voedsaam.vzw.business.Volunteer;
import be.voedsaam.vzw.commons.CRUDService;
import be.voedsaam.vzw.enums.Role;

import java.util.List;

public interface UserService extends CRUDService<User> {

    User findByEmail(String email);
    List<Volunteer> listVolunteerByRole(Role role);
    List<Employee> listEmployeeByRole(Role role);

    Volunteer getVolunteerById(Long id);
    Employee getEmployeeById(Long id);

    List<Employee> listAllEmployees();
    List<Volunteer> listAllVolunteers();
}

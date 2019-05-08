package be.voedsaam.vzw.business.repository.impl;


import be.voedsaam.vzw.business.User;
import be.voedsaam.vzw.business.impl.Employee;
import be.voedsaam.vzw.business.impl.Volunteer;
import be.voedsaam.vzw.business.repository.UserRepository;
import be.voedsaam.vzw.enums.Role;
import be.voedsaam.vzw.security.EncryptionService;
import be.voedsaam.vzw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Profile("springdatajpa")
public class UserServiceRepoImpl implements UserService {

    private UserRepository userRepository;
    private EncryptionService encryptionService;

    @Autowired
    public void setEncryptionService(EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public List<?> listAll() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add); //fun with Java 8
        return users;
    }

    @Override
    public User getById(Long id) {
      Optional<User> o= userRepository.findById(id);
        if (o.isPresent())
        return o.get();
        return null;

    }

    @Override
    public User saveOrUpdate(User domainObject) {
        if (domainObject.getPassword()!= null)
            domainObject.setEncryptedPassword(encryptionService.encryptString(domainObject.getPassword()));

        return userRepository.save(domainObject);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (id!=null) {
            Optional<User> o = userRepository.findById(id);
            if (o.isPresent())
                userRepository.delete(o.get());
        }
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmailIgnoreCase(email);
    }

    @Override
    public List<Volunteer> listVolunteerByRole(Role role) {
        if ((role.equals(Role.COORDINATOR)||role.equals(Role.LOGISTICS)))
            throw new UnsupportedOperationException("Volunteers cant have this role: " + role);
        List<Volunteer> volunteers = new ArrayList<>();
        userRepository.findAllByRole(role).forEach(user ->volunteers.add((Volunteer) user ));
        return volunteers;
    }

    @Override
    public List<Employee> listEmployeeByRole(Role role) {
        if (!((role.equals(Role.COORDINATOR)||role.equals(Role.LOGISTICS))))
            throw new UnsupportedOperationException("Employees cant have this role: " + role);
        List<Employee> employees = new ArrayList<>();
        userRepository.findAllByRole(role).forEach(user ->employees.add((Employee) user ));
        return employees;
    }

    @Override
    public Volunteer getVolunteerById(Long id) {
         User user =  getById(id);
        if (user!=null&&user.getClass().equals(Volunteer.class))
        return (Volunteer) getById(id);
        return null;
    }

    @Override
    public Employee getEmployeeById(Long id) {
        User user =  getById(id);
        if (user!=null&&user.getClass().equals(Employee.class))
            return (Employee) getById(id);
        return null;

    }

    @Override
    public List<Employee> listAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        listEmployeeByRole(Role.COORDINATOR).forEach(employees::add);
        listEmployeeByRole(Role.LOGISTICS).forEach(employees::add);
        return employees;
    }

    @Override
    public List<Volunteer> listAllVolunteers() {
        List<Volunteer> volunteers = new ArrayList<>();
        listVolunteerByRole(Role.DRIVER).forEach(volunteers::add);
        listVolunteerByRole(Role.VOLUNTEER).forEach(volunteers::add);
        listVolunteerByRole(Role.ATTENDEE).forEach(volunteers::add);
        listVolunteerByRole(Role.DEPOTHELP).forEach(volunteers::add);
        return volunteers;
}

}

package be.voedsaam.vzw.business.repository.impl;


import be.voedsaam.vzw.business.User;
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
        return userRepository.findById(id).get();
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
        Optional<User> o = userRepository.findById(id);
        if (o.isPresent())
        userRepository.delete(o.get());
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmailIgnoreCase(email);
    }

    @Override
    public List<User> listByRole(Role role) {
        return userRepository.findAllByRole(role);
    }

//    @Override
//    public User Login(String email, String password) {
//        return userRepository.findByEmailIgnoreCaseAndPassword(email,password);
//    }

}

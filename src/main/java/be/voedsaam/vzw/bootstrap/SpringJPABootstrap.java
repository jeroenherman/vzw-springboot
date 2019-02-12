package be.voedsaam.vzw.bootstrap;


import be.voedsaam.vzw.business.Address;
import be.voedsaam.vzw.business.User;
import be.voedsaam.vzw.business.repository.DriveRepository;
import be.voedsaam.vzw.business.repository.UserRepository;
import be.voedsaam.vzw.commons.Color;
import be.voedsaam.vzw.commons.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Created by jt on 12/9/15.
 */
@Component
public class SpringJPABootstrap implements ApplicationListener<ContextRefreshedEvent>{

    private DriveRepository driveRepository;
    private UserRepository userRepository;
    @Autowired
    public void setDriveRepository(DriveRepository driveRepository) {
        this.driveRepository = driveRepository;
    }
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        loadUsers();
        loadDrives();

    }

    private void loadDrives() {
    }

    private void loadUsers(){
        User jeroen;
        User coordinator;
        User logistics;
        User volunteer;
        User partner;
        Address a1;
        a1 = new Address("Sportlaan",33,9170,"Sint-Niklaas");
        jeroen = new User("jeroen", "herman","jeroen.herman@voedsaam.be", "037797243",a1, Role.COORDINATOR, Color.RED);
        logistics = new User("Cindy","DePuydt","Cindy.DePuydt@voedsaam.be", "03 /780.52.35");
        logistics.setRole(Role.LOGISTICS);
        coordinator  = new User("Els", "VandeSteene", "Els.VandeSteene@voedsaam.be","0492/250641");
        coordinator.setRole(Role.COORDINATOR);
        partner = new User("Kathy","blomme", "kathy.blomme@gmail.com", "unknown");
        partner.setRole(Role.PARTNER);
        volunteer = new User("leonard", "cleys", "cleysveedee@telenet.be", "unknown");
        volunteer.setRole(Role.VOLUNTEER);
        volunteer.setColor(Color.WHITE);
        jeroen.setPassword("Test123");
        logistics.setPassword("Test123");
        volunteer.setPassword("Test123");
        coordinator.setPassword("Test123");
    }
}


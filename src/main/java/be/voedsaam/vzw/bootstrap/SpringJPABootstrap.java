package be.voedsaam.vzw.bootstrap;


import be.voedsaam.vzw.business.*;
import be.voedsaam.vzw.enums.Color;
import be.voedsaam.vzw.enums.Role;
import be.voedsaam.vzw.service.DestinationService;
import be.voedsaam.vzw.service.DriveService;
import be.voedsaam.vzw.service.ScheduleService;
import be.voedsaam.vzw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by jt on 12/9/15.
 */
@Component
public class SpringJPABootstrap implements ApplicationListener<ContextRefreshedEvent>{

    private UserService userService;
    private DestinationService destinationService;
    private DriveService driveService;
    private ScheduleService scheduleService;

    @Autowired
    public SpringJPABootstrap(UserService userService, DestinationService destinationService, DriveService driveService, ScheduleService scheduleService) {
        this.userService = userService;
        this.destinationService = destinationService;
        this.driveService = driveService;
        this.scheduleService = scheduleService;
    }

    @Override
    // to avoid detached entities use @Transactional !!!!
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        loadUsers();
        loadDestinations();
        loadDrives();
        addDrivesToSchedule();

    }

    private void addDrivesToSchedule() {
        Schedule schedule = new Schedule();
        schedule.setName("Test Schedule");
        scheduleService.saveOrUpdate(schedule);
        schedule.addUser(userService.findByEmail("jeroen.herman@voedsaam.be"));
        List<Drive> drives = (List<Drive>)driveService.listAll();
        drives.forEach(schedule::addDrive);
        schedule = new Schedule();
        schedule.setName("Ritten Els");
        scheduleService.saveOrUpdate(schedule);
        schedule.addUser(userService.findByEmail("els.vandeSteene@voedsaam.be"));
        schedule.addUser(userService.findByEmail("cindy.depuydt@voedsaam.be"));
    }

    private void loadDestinations() {
        Destination destination1 = new Destination();
        Destination destination2 = new Destination();
        Destination destination3 = new Destination();

        Address a1  = new Address();
        a1.setCity("Sint-Niklaas");
        a1.setPostalCode(9100);
        a1.setStreet("LamStraat");
        a1.setNumber(113);
        destination1.setContactInfo("Els van de Steene:  03 780 52 35");
        destination1.setDestinationName("Vzw Voedsaam");
        destination1.setAddress(a1);

        Address a2  = new Address();
        a2.setCity("Temse");
        a2.setPostalCode(9100);
        a2.setStreet("Krijgsbaan");
        a2.setNumber(247);
        destination2.setContactInfo("Els van de Steene:  03 780 52 35");
        destination2.setDestinationName("Voedsel Depot: Den azalee");
        destination2.setAddress(a2);

        Address a3  = new Address();
        a3.setCity("Sint-Katelijne-Waver");
        a3.setPostalCode(2860);
        a3.setStreet("Mechelsesteenweg");
        a3.setNumber(120);
        destination3.setContactInfo("015 55 11 11");
        destination3.setDestinationName("Belorta");
        destination3.setAddress(a3);
        destination3.addAgreement("afspraak om 6u aan het depot, Krijgsbaan 247 9140 Temse");
        destination3.addAgreement("Ophaal sleutel camionette + hek: in de sleutelkluis op de afgesproken plaats, code krijg je per sms, de avond voor de rit");
        Task task1 = new Task();
        task1.setRole(Role.DRIVER);
        task1.setTitle("Taak 1");
        task1.setDiscription("Ophaal groenten op veiling");
        destination3.addTask(task1);
        Task task2 = new Task();
        task2.setRole(Role.ATTENDEE);
        task2.setTitle("Taak 2");
        task2.setDiscription("Toegangspasjes en ritplanning voor veiling: rood kaft in camionette.");
        destination3.addTask(task2);
        Task task3 = new Task();
        task3.setRole(Role.DEPOTHELP);
        task3.setTitle("Taak 3");
        task3.setDiscription("Klaarzetten voor ophaling, 20 grijze bakken moeten worden voorzien.");
        destination3.addTask(task3);
        destinationService.saveOrUpdate(destination1);
        destinationService.saveOrUpdate(destination2);
        destinationService.saveOrUpdate(destination3);
    }

    private void loadDrives() {
        List<Destination> destinations = (List<Destination>)destinationService.listAll();
        List<User> users = (List<User>) userService.listAll();

        Drive drive1 = new Drive();
        drive1.setDescription("Rit Voedsaam -> Depot");
        drive1.addUser(users.get(5));
        drive1.addUser(users.get(6));
        drive1.addUser(users.get(7));
        drive1.setStartTime(LocalDateTime.of(2019, 01, 01, 9, 30));
        drive1.setEndTime(LocalDateTime.of(2019, 01, 01, 10, 30));
        drive1.addDestination(destinations.get(0));
        drive1.addDestination(destinations.get(1));


        Drive drive2 = new Drive();
        drive2.setDescription("Rit Depot -> Belorta");
        drive2.addUser(users.get(5));
        drive2.addUser(users.get(6));
        drive2.setStartTime(LocalDateTime.of(2019, 02, 01, 9, 30));
        drive2.setEndTime(LocalDateTime.of(2019, 02, 01, 10, 30));
        drive2.addDestination(destinations.get(0));
        drive2.addDestination(destinations.get(2));

        Drive drive3 = new Drive();
        drive3.setDescription("Rit Voedsaam -> Depot -> Belorta");
        drive3.addUser(users.get(5));
        drive3.setStartTime(LocalDateTime.of(2019, 03, 01, 9, 30));
        drive3.setEndTime(LocalDateTime.of(2019, 03, 01, 10, 30));
        drive3.addDestination(destinations.get(0));
        drive3.addDestination(destinations.get(1));
        drive3.addDestination(destinations.get(2));

        driveService.saveOrUpdate(drive1);
        driveService.saveOrUpdate(drive2);
        driveService.saveOrUpdate(drive3);

    }

    private void loadUsers(){
        User admin = new User();
        admin.setEmail("admin@voedsaam.be");
        admin.setPassword("test123");
        admin.setRole(Role.COORDINATOR);
        Authority authority1 = new Authority("ADMIN");
        Authority authority2 = new Authority("USER");
        admin.addAuthority(authority1);
        admin.addAuthority(authority2);
        userService.saveOrUpdate(admin);

        User jeroen;
        User coordinator;
        User logistics;
        User volunteer;
        User partner;
        Address a1;
        a1 = new Address("Sportlaan",33,9170,"Sint-Niklaas");
        jeroen = new User("jeroen", "herman","jeroen.herman@voedsaam.be", "037797243",a1, Role.COORDINATOR, Color.RED);
        logistics = new User("Cindy","DePuydt","cindy.depuydt@voedsaam.be", "03 /780.52.35");
        logistics.setRole(Role.LOGISTICS);
        coordinator  = new User("Els", "VandeSteene", "els.vandesteene@voedsaam.be","0492/250641");
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
        userService.saveOrUpdate(coordinator);
        userService.saveOrUpdate(jeroen);
        userService.saveOrUpdate(logistics);
        userService.saveOrUpdate(partner);
        userService.saveOrUpdate(volunteer);
        User driver;
        User attendee;
        User depotHelp;
        driver = new User("Kevin Van Leugenhaege");
        driver.setTel("0472 40 07 94");
        driver.setRole(Role.DRIVER);

        attendee = new User("Veerle Van Overtvelt");
        attendee.setTel("0497 16 36 26");
        attendee.setRole(Role.ATTENDEE);


        depotHelp = new User("Marie-Noëlle Delarbre");
        depotHelp.setTel("0474 84 75 91");
        depotHelp.setRole(Role.DEPOTHELP);

        userService.saveOrUpdate(driver);
        userService.saveOrUpdate(attendee);
        userService.saveOrUpdate(depotHelp);

    }
}


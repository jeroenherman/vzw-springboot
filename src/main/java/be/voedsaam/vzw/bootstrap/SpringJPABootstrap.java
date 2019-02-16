package be.voedsaam.vzw.bootstrap;


import be.voedsaam.vzw.business.Address;
import be.voedsaam.vzw.business.Drive;
import be.voedsaam.vzw.business.User;
import be.voedsaam.vzw.business.repository.DestinationRepository;
import be.voedsaam.vzw.business.repository.DriveRepository;
import be.voedsaam.vzw.business.repository.UserRepository;
import be.voedsaam.vzw.commons.Color;
import be.voedsaam.vzw.commons.Role;
import be.voedsaam.vzw.service.dto.DestinationDTO;
import be.voedsaam.vzw.service.dto.DriveDTO;
import be.voedsaam.vzw.service.dto.UserDTO;
import be.voedsaam.vzw.service.mapper.DestinationMapper;
import be.voedsaam.vzw.service.mapper.DriveMapper;
import be.voedsaam.vzw.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by jt on 12/9/15.
 */
@Component
public class SpringJPABootstrap implements ApplicationListener<ContextRefreshedEvent>{

    private DriveRepository driveRepository;
    private UserRepository userRepository;
    private DestinationMapper destinationMapper;
    private DriveMapper driveMapper;
    private UserMapper userMapper;
    private DestinationRepository destinationRepository;

    @Autowired
    public void setDestinationRepository(DestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;
    }
    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Autowired
    public void setDriveMapper(DriveMapper driveMapper) {
        this.driveMapper = driveMapper;
    }
    @Autowired
    public void setDestinationMapper(DestinationMapper destinationMapper) {
        this.destinationMapper = destinationMapper;
    }
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
        loadDestinations();
        loadDrives();

    }

    private void loadDrives() {
        DriveDTO drive1 = new DriveDTO();
        DriveDTO drive2= new DriveDTO();
        DriveDTO drive3= new DriveDTO();
        //
        UserDTO driver = new UserDTO();
        UserDTO attendee = new UserDTO();
        UserDTO depotHelp = new UserDTO();
        //
        driver = new UserDTO("Kevin Van Leugenhaege");
        driver.setTel("0472 40 07 94");
        driver.setRole(Role.DRIVER);

        attendee = new UserDTO("Veerle Van Overtvelt");
        attendee.setTel("0497 16 36 26");
        attendee.setRole(Role.ATTENDEE);


        depotHelp = new UserDTO("Marie-No�lle Delarbre");
        depotHelp.setTel("0474 84 75 91");
        depotHelp.setRole(Role.DEPOTHELP);

        drive1.setStartTime(LocalDateTime.of(2019, 01, 01, 9, 30));
        drive1.setEndTime(LocalDateTime.of(2019, 01, 01, 10, 30));

        drive2 =new DriveDTO();
        drive2.setStartTime(LocalDateTime.of(2019, 02, 01, 9, 30));
        drive2.setEndTime(LocalDateTime.of(2019, 02, 01, 10, 30));

        drive3 =new DriveDTO();
        drive3.setStartTime(LocalDateTime.of(2019, 03, 01, 9, 30));
        drive3.setEndTime(LocalDateTime.of(2019, 03, 01, 10, 30));

        Drive drive = driveMapper.mapToObj(drive1);
        drive.setDriver(userRepository.saveOrUpdate(userMapper.mapToObj(driver)));

        driveRepository.saveOrUpdate(drive);

        drive = driveMapper.mapToObj(drive2);
        drive.setDriver(userRepository.saveOrUpdate(userMapper.mapToObj(driver)));
        drive.setAttendee(userRepository.saveOrUpdate(userMapper.mapToObj(attendee)));
        driveRepository.saveOrUpdate(drive);

        drive = driveMapper.mapToObj(drive3);
        drive.setDriver(userRepository.saveOrUpdate(userMapper.mapToObj(driver)));
        drive.setAttendee(userRepository.saveOrUpdate(userMapper.mapToObj(attendee)));
        drive.setDepotHelp(userRepository.saveOrUpdate(userMapper.mapToObj(depotHelp)));
        driveRepository.saveOrUpdate(drive);

    }

    private void loadDestinations(){

        DestinationDTO destination1 = new DestinationDTO();
        DestinationDTO destination2= new DestinationDTO();
        DestinationDTO destination3= new DestinationDTO();


        destination1.setCity("Sint-Niklaas");
        destination1.setPostalCode(9100);
        destination1.setStreet("LamStraat");
        destination1.setStreetNumber(113);
        destination1.setContactInfo("Els van de Steene:  03 780 52 35");
        destination1.setDestinationName("Vzw Voedsaam");

        destination2.setCity("Temse");
        destination2.setPostalCode(9140);
        destination2.setStreet("Krijgsbaan");
        destination2.setStreetNumber(247);
        destination2.setContactInfo("Els van de Steene:  03 780 52 35");
        destination2.setDestinationName("Voedsel Depot: Den azalee");


        destination3.setCity("Sint-Katelijne-Waver");
        destination3.setPostalCode(2860 );
        destination3.setStreet("Mechelsesteenweg");
        destination3.setStreetNumber(120);
        destination3.setContactInfo("015 55 11 11");
        destination3.setDestinationName("Belorta");

        destinationRepository.saveOrUpdate(destinationMapper.mapToObj(destination1));
        destinationRepository.saveOrUpdate(destinationMapper.mapToObj(destination2));
        destinationRepository.saveOrUpdate(destinationMapper.mapToObj(destination3));

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
        userRepository.saveOrUpdate(jeroen);
        userRepository.saveOrUpdate(logistics);
        userRepository.saveOrUpdate(partner);
        userRepository.saveOrUpdate(volunteer);



    }
}


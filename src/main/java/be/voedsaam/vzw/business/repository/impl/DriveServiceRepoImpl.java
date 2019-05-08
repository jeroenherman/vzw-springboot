package be.voedsaam.vzw.business.repository.impl;

import be.voedsaam.vzw.business.Drive;
import be.voedsaam.vzw.business.repository.DestinationRepository;
import be.voedsaam.vzw.business.repository.DriveRepository;
import be.voedsaam.vzw.business.repository.UserRepository;
import be.voedsaam.vzw.service.DriveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@Profile("springdatajpa")
public class DriveServiceRepoImpl implements DriveService {

    private UserRepository userRepository;
    private DestinationRepository destinationRepository;
    private DriveRepository driveRepository;

    @Autowired
    public void setDriveRepository(DriveRepository driveRepository) {
        this.driveRepository = driveRepository;
    }
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Autowired
    public void setDestinationRepository(DestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;
    }

    @Override
    public List<?> listAll() {
        List<Drive> drives = new ArrayList<>();
        driveRepository.findAll().forEach(drives::add); //fun with Java 8
        return drives;
    }

    @Override
    public Drive getById(Long id) {
       Optional<Drive> drive = driveRepository.findById(id);
       if (drive.isPresent())
       return drive.get();
        else return null;
    }

    @Override
    public Drive saveOrUpdate(Drive domainObject) {
        return driveRepository.save(domainObject);
    }

    @Override
    public void delete(Long id) {
        if (id!=null) {
        Optional<Drive> o = driveRepository.findById(id);
        if (o.isPresent())
            driveRepository.delete(o.get());
        }
    }
    @Override
    public List<Drive> listAllByUser(String name) {
        return driveRepository.findAllByUsersContaining(userRepository.findByEmailIgnoreCase(name));
    }

    @Override
    public void deleteAllDrivesWithoutSchedule() {
        List<Drive> toDelete = driveRepository.findAllByScheduleEquals(null);
        toDelete.forEach(drive -> delete(drive.getId()));
    }
}

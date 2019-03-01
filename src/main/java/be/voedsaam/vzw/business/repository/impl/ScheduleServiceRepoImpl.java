package be.voedsaam.vzw.business.repository.impl;

import be.voedsaam.vzw.business.Schedule;
import be.voedsaam.vzw.business.User;
import be.voedsaam.vzw.business.repository.ScheduleRepository;
import be.voedsaam.vzw.business.repository.UserRepository;
import be.voedsaam.vzw.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Profile("springdatajpa")
public class ScheduleServiceRepoImpl implements ScheduleService {

    private ScheduleRepository scheduleRepository;
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setScheduleRepository(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public List<?> listAll() {
        List<Schedule> schedules = new ArrayList<>();
        scheduleRepository.findAll().forEach(schedules::add);
        return schedules;
    }

    @Override
    public Schedule getById(Long id) {
        return scheduleRepository.findById(id).get();
    }

    @Override
    public Schedule saveOrUpdate(Schedule domainObject) {
        return scheduleRepository.save(domainObject);
    }

    @Override
    public void delete(Long id) throws UnsupportedOperationException{
        Schedule schedule = getById(id);
        if (schedule.getUsers().size()!=0)
            throw new UnsupportedOperationException("Schedule still has users please delete users from Schedule");
        if (schedule.getDrives().size()!=0)
            throw new UnsupportedOperationException("Schedule still has drives please delete users from Schedule");
        scheduleRepository.deleteById(id);
    }

    @Override
    public List<Schedule> listAllByUserName(String userName) {
        List<Schedule> schedules = (List<Schedule>) listAll();
        return schedules.stream()
                .filter(schedule -> schedule.getUsers()
                        .contains(userRepository.findByEmailIgnoreCase(userName)))
                .collect(Collectors.toList());
    }
}

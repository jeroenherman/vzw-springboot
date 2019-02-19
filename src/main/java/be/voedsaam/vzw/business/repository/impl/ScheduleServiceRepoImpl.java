package be.voedsaam.vzw.business.repository.impl;

import be.voedsaam.vzw.business.Schedule;
import be.voedsaam.vzw.business.repository.ScheduleRepository;
import be.voedsaam.vzw.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@Profile("springdatajpa")
public class ScheduleServiceRepoImpl implements ScheduleService {

    private ScheduleRepository scheduleRepository;
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
    public void delete(Long id) {
        scheduleRepository.deleteById(id);
    }
}

package be.voedsaam.vzw.service.mapper;

import be.voedsaam.vzw.business.impl.Employee;
import be.voedsaam.vzw.business.Schedule;
import be.voedsaam.vzw.business.repository.ScheduleRepository;
import be.voedsaam.vzw.business.repository.UserRepository;
import be.voedsaam.vzw.commons.AbstractMapper;
import be.voedsaam.vzw.enums.Role;
import be.voedsaam.vzw.service.dto.ScheduleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ScheduleMapper extends AbstractMapper<Schedule, ScheduleDTO> {

    private ScheduleRepository scheduleRepository;


    @Autowired
    public void setScheduleRepository(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public ScheduleDTO mapToDTO(Schedule b) {
        if (b == null)
            return null;
        ScheduleDTO d = new ScheduleDTO();
        d.setId(b.getId());
        d.setName(b.getName());
        Optional<Employee> owner = b.getUsers().stream().filter(user -> user.getRole().equals(Role.COORDINATOR)).findAny();
        if (owner.isPresent())
        d.setOwner(owner.get().getFullName());
        b.getDrives().stream().map(drive -> drive.getDescription()).forEach(d.getDrives()::add);
        b.getUsers().stream().map(user -> user.getFullName()).forEach(d.getViewers()::add);
        return d;
    }

    @Override
    public Schedule mapToObj(ScheduleDTO d) {
        if (d == null)
            return null;
        Schedule b = new Schedule();
        Optional<Schedule> o = Optional.empty();
        if (d.getId() != null)
            o = scheduleRepository.findById(d.getId());
        if (o.isPresent())
            b = o.get();
        b.setName(d.getName());
        b.setId(d.getId());
        return b;
    }
}


package be.voedsaam.vzw.service.mapper;

import be.voedsaam.vzw.business.Schedule;
import be.voedsaam.vzw.commons.AbstractMapper;
import be.voedsaam.vzw.enums.Role;
import be.voedsaam.vzw.service.dto.ScheduleDTO;
import org.springframework.stereotype.Component;

@Component
public class ScheduleMapper extends AbstractMapper<Schedule,ScheduleDTO> {

    @Override
    public ScheduleDTO mapToDTO(Schedule b) {
      if (b==null)
        return null;
      ScheduleDTO d = new ScheduleDTO();
      d.setId(b.getId());
      d.setName(b.getName());
      d.setOwner(b.getUsers().stream()
              .filter(user -> user.getRole()
              .equals(Role.COORDINATOR))
              .findFirst().get()
              .getFullName());
      b.getDrives().stream().map(drive -> drive.getDescription()).forEach(d.getDrives()::add);
      b.getUsers().stream().map(user -> user.getFullName()).forEach(d.getViewers()::add);
      return d;
    }

    @Override
    public Schedule mapToObj(ScheduleDTO d) {
        if(d==null)
        return null;
        Schedule b = new Schedule();
        b.setName(d.getName());
        b.setId(d.getId());
        return b;
    }
}


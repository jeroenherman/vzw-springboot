package be.voedsaam.vzw.service.mapper;

import be.voedsaam.vzw.business.Drive;
import be.voedsaam.vzw.business.impl.Volunteer;
import be.voedsaam.vzw.business.repository.DriveRepository;
import be.voedsaam.vzw.commons.AbstractMapper;
import be.voedsaam.vzw.enums.Role;
import be.voedsaam.vzw.service.dto.EventDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Component
public class EventMapper extends AbstractMapper<Drive, EventDTO> {
    private DriveRepository driveRepository;

    @Autowired
    public void setDriveRepository(DriveRepository driveRepository) {
        this.driveRepository = driveRepository;
    }


    @Override
    public EventDTO mapToDTO(Drive b) {
        if (b == null)
            return null;
        EventDTO d = new EventDTO();
        d.setId(b.getId());
        d.setStart(b.getStartTime().format(DateTimeFormatter.ISO_DATE_TIME));
        d.setEnd(b.getEndTime().format(DateTimeFormatter.ISO_DATE_TIME));
        d.setAllDay(b.isAllDay());
        d.setTitle(b.getDescription());
        Optional<Drive> drive = driveRepository.findById(b.getId());
        if (drive.isPresent()){
        List<Volunteer> volunteers = drive.get().getUsers();
        Optional<Volunteer> driver = volunteers.stream().filter(v -> v.getRole().equals(Role.DRIVER)).findFirst();
        if (driver.isPresent())
            if (driver.get().getColor()!= null)
                d.setColor(driver.get().getColor().getHex());
        }
        return d;
    }

    @Override
    public Drive mapToObj(EventDTO d) {
        if (d == null)
            return null;
        Drive b = new Drive();
        Optional<Drive> o = Optional.empty();
        if (d.getId() != null)
            o = driveRepository.findById(d.getId());

        if (o.isPresent())
            b = o.get();
        b.setDescription(d.getTitle());
        b.setId(d.getId());
        if (d.getStart().length()>15) {
            b.setStartTime(LocalDateTime.parse(d.getStart(), DateTimeFormatter.ISO_DATE_TIME));
            b.setEndTime(LocalDateTime.parse(d.getEnd(), DateTimeFormatter.ISO_DATE_TIME));
            b.setAllDay(d.isAllDay());
        }
        if (d.getStart().length()==10) {
            LocalDate start = LocalDate.parse(d.getStart(),DateTimeFormatter.ISO_LOCAL_DATE);
            LocalDate end = LocalDate.parse(d.getEnd(),DateTimeFormatter.ISO_LOCAL_DATE);
            b.setStartTime(LocalDateTime.of(start, LocalTime.of(0,00)));
            b.setEndTime(LocalDateTime.of(end,LocalTime.of(0,00)));
            b.setAllDay(true);
        }


        return b;
    }
}

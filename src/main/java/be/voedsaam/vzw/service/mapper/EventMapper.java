package be.voedsaam.vzw.service.mapper;

import be.voedsaam.vzw.business.Drive;
import be.voedsaam.vzw.business.repository.DriveRepository;
import be.voedsaam.vzw.commons.AbstractMapper;
import be.voedsaam.vzw.service.dto.EventDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
@Component
public class EventMapper extends AbstractMapper<Drive,EventDTO> {
    private DriveRepository driveRepository;
    @Autowired
    public void setDriveRepository(DriveRepository driveRepository) {
        this.driveRepository = driveRepository;
    }


    @Override
    public EventDTO mapToDTO(Drive b) {
        if (b==null)
        return null;
        EventDTO d = new EventDTO();
        d.setId(b.getId());
        d.setStart(b.getStartTime().format(DateTimeFormatter.ISO_DATE_TIME));
        d.setEnd(b.getEndTime().format(DateTimeFormatter.ISO_DATE_TIME));
        d.setTitle(b.getDescription());
        return d;
    }

    @Override
    public Drive mapToObj(EventDTO d) {
        if (d ==null)
        return null;
        Drive b = new Drive();
        Optional<Drive> o = Optional.empty();
        if (d.getId()!=null)
            o = driveRepository.findById(d.getId());

        if(o.isPresent())
            b = o.get();
        b.setDescription(d.getTitle());
        b.setId(d.getId());
        b.setStartTime(LocalDateTime.parse(d.getStart(),DateTimeFormatter.ISO_DATE_TIME));
        b.setEndTime(LocalDateTime.parse(d.getEnd(),DateTimeFormatter.ISO_DATE_TIME));

        return b;
    }
}

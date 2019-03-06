package be.voedsaam.vzw.controller;

import be.voedsaam.vzw.business.Drive;
import be.voedsaam.vzw.business.Schedule;
import be.voedsaam.vzw.service.DriveService;
import be.voedsaam.vzw.service.dto.EventDTO;
import be.voedsaam.vzw.service.mapper.DriveMapper;
import be.voedsaam.vzw.service.mapper.EventMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CalendarEventsController {

    private DriveService driveService;
    private EventMapper eventMapper;

    @Autowired
    public void setDriveService(DriveService driveService) {
        this.driveService = driveService;
    }
    @Autowired
    public void setEventMapper(EventMapper eventMapper) {
        this.eventMapper = eventMapper;
    }

    @GetMapping(value = "/events", produces = "application/json")
    public List<EventDTO> getEvents(
            @RequestParam(value="start", required=false) String start,
            @RequestParam(value="end", required=false) String end) {

        List<EventDTO> events = new ArrayList<>();

        events.addAll(eventMapper.mapToDTO((List<Drive>)driveService.listAll()));

        return events;
    }



}
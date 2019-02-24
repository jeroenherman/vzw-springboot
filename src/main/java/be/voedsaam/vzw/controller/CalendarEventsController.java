package be.voedsaam.vzw.controller;

import be.voedsaam.vzw.service.dto.EventDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CalendarEventsController {

    @GetMapping(value = "/events", produces = "application/json")
    public List<EventDTO> getEvents(
            @RequestParam(value="start", required=false) String start,
            @RequestParam(value="end", required=false) String end) {


        List<EventDTO> events = new ArrayList<>();

        EventDTO e1 = new EventDTO();
        e1.setTitle("Front-End Conference");
        e1.setStart("2018-11-16");
        e1.setEnd("2018-11-18");

        EventDTO e2 = new EventDTO();
        e2.setTitle("Hair stylist with Mike");
        e2.setStart("2018-11-20");
        e2.setAllDay(true);

        events.add(e1);
        events.add(e2);

        return events;
    }


}
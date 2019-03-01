package be.voedsaam.vzw.controller;

import be.voedsaam.vzw.business.Destination;
import be.voedsaam.vzw.service.DestinationService;
import be.voedsaam.vzw.service.mapper.DestinationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/destination")
public class DestinationController {

    private DestinationService destinationService;
    private DestinationMapper destinationMapper;

    @Autowired
    public void setDestinationMapper(DestinationMapper destinationMapper) {
        this.destinationMapper = destinationMapper;
    }

    @Autowired
    public void setDestinationService(DestinationService destinationService) {
        this.destinationService = destinationService;
    }

    @RequestMapping({"/", "/list"})
    public String getDestinations(Model model) {
        model.addAttribute("destinations", destinationMapper.mapToDTO((List<Destination>) destinationService.listAll()));
        return "destination/list";
    }

    @RequestMapping("/show/{id}")
    public String getSchedule(@PathVariable Integer id, Model model) {
        Destination selectedDestination = destinationService.getById(id.longValue());
        model.addAttribute("destination", destinationMapper.mapToDTO(selectedDestination));
        model.addAttribute("tasks" , selectedDestination.getTasks());
        return "destination/show";
    }

}

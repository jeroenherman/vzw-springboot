package be.voedsaam.vzw.controller;

import be.voedsaam.vzw.business.Destination;
import be.voedsaam.vzw.service.DestinationService;
import be.voedsaam.vzw.service.dto.DestinationDTO;
import be.voedsaam.vzw.service.mapper.DestinationMapper;
import be.voedsaam.vzw.service.mapper.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/destination")
public class DestinationController {

    private DestinationService destinationService;
    private DestinationMapper destinationMapper;
    private TaskMapper taskMapper;

    @Autowired
    public void setDestinationMapper(DestinationMapper destinationMapper) {
        this.destinationMapper = destinationMapper;
    }

    @Autowired
    public void setDestinationService(DestinationService destinationService) {
        this.destinationService = destinationService;
    }
    @Autowired
    public void setTaskMapper(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    @RequestMapping({"/", "/list"})
    public String getDestinations(Model model) {
        model.addAttribute("destinations", destinationMapper.mapToDTO((List<Destination>) destinationService.listAll()));
        return "destination/list";
    }

    @RequestMapping("/show/{id}")
    public String showDestination(@PathVariable Integer id, Model model) {
        Destination selectedDestination = destinationService.getById(id.longValue());
        model.addAttribute("destination", destinationMapper.mapToDTO(selectedDestination));
        model.addAttribute("tasks" , taskMapper.mapToDTO(selectedDestination.getTasks()));
        return "destination/show";
    }

    @RequestMapping("/edit/{id}")
    public String editDestination(@PathVariable Integer id, Model model) {
        Destination selectedDestination = destinationService.getById(id.longValue());
        model.addAttribute("destination", destinationMapper.mapToDTO(selectedDestination));
        model.addAttribute("tasks" , taskMapper.mapToDTO(selectedDestination.getTasks()));
        return "destination/form";
    }
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String saveOrUpdate(DestinationDTO dto){
        Destination destination = destinationMapper.mapToObj(dto);
         destination = destinationService.saveOrUpdate(destination);
        return "redirect:/destination/show/" + destination.getId();
    }
}

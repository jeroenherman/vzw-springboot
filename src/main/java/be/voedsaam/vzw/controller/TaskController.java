package be.voedsaam.vzw.controller;

import be.voedsaam.vzw.business.Destination;
import be.voedsaam.vzw.business.Task;
import be.voedsaam.vzw.enums.Role;
import be.voedsaam.vzw.service.DestinationService;
import be.voedsaam.vzw.service.TaskService;
import be.voedsaam.vzw.service.dto.DestinationDTO;
import be.voedsaam.vzw.service.dto.TaskDTO;
import be.voedsaam.vzw.service.mapper.DestinationMapper;
import be.voedsaam.vzw.service.mapper.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/task")
public class TaskController {

    private TaskService taskService;
    private DestinationService destinationService;
    private TaskMapper taskMapper;
    private DestinationMapper destinationMapper;
    @Autowired
    public void setDestinationService(DestinationService destinationService) {
        this.destinationService = destinationService;
    }

    @Autowired
    public void setDestinationMapper(DestinationMapper destinationMapper) {
        this.destinationMapper = destinationMapper;
    }

    @Autowired
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }
    @Autowired
    public void setTaskMapper(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    @RequestMapping("/edit/{id}")
    public String editTask(@PathVariable Integer id, Model model){
        Task task = taskService.getById(id.longValue());
        model.addAttribute("task", taskMapper.mapToDTO(task));
        model.addAttribute("destination", destinationMapper.mapToDTO(task.getDestination()));
        List<Role> roles = new ArrayList<>();
        roles.add(Role.DRIVER); roles.add(Role.ATTENDEE); roles.add(Role.DEPOTHELP); roles.add(Role.VOLUNTEER);
        model.addAttribute("roles",roles);
        return "task/form";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String saveOrUpdate(TaskDTO dto){
        Task task = taskMapper.mapToObj(dto);
        task = taskService.saveOrUpdate(task);
        return "redirect:/destination/edit/" + task.getDestination().getId();
    }

    @Secured({"ROLE_COORDINATOR","ROLE_LOGISTICS"})
    @RequestMapping("/new/{id}")
    public String newTask(@PathVariable Integer id) throws UnsupportedOperationException{
        Task task = new Task();
        task.setTitle("Nieuwe Taak");
        Destination destination = destinationService.getById(id.longValue());
        task.setDestination(destination);
        task = taskService.saveOrUpdate(task);
        return "redirect:/task/edit/" + task.getId();
    }

    @RequestMapping("/delete/{id}")
    @Transactional
    public String delete(@PathVariable Integer id){
        Destination destination = taskService.getById(id.longValue()).getDestination();
        taskService.delete(id.longValue());
        return "redirect:/destination/edit/" + destination.getId();
    }
}

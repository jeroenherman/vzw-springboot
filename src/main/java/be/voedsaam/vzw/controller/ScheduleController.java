package be.voedsaam.vzw.controller;

import be.voedsaam.vzw.business.Drive;
import be.voedsaam.vzw.business.Schedule;
import be.voedsaam.vzw.business.User;
import be.voedsaam.vzw.enums.Role;
import be.voedsaam.vzw.service.DriveService;
import be.voedsaam.vzw.service.ScheduleService;
import be.voedsaam.vzw.service.UserService;
import be.voedsaam.vzw.service.dto.EventDTO;
import be.voedsaam.vzw.service.dto.ScheduleDTO;
import be.voedsaam.vzw.service.mapper.EventMapper;
import be.voedsaam.vzw.service.mapper.ScheduleMapper;
import be.voedsaam.vzw.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;



@Controller
@RequestMapping("/schedule")
public class ScheduleController {

    private ScheduleService scheduleService;
    private UserService userService;
    private ScheduleMapper scheduleMapper;
    private UserMapper userMapper;
    private Schedule selectedSchedule;
    private DriveService driveService;
    private  EventMapper eventMapper;
    @Autowired
    public void setSelectedSchedule(Schedule selectedSchedule) {
        this.selectedSchedule = selectedSchedule;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
    @Autowired
    public void setScheduleService(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }
    @Autowired
    public void setScheduleMapper(ScheduleMapper scheduleMapper) {
        this.scheduleMapper = scheduleMapper;
    }
    @Autowired
    public void setDriveService(DriveService driveService) {
        this.driveService = driveService;
    }
    @Autowired
    public void setEventMapper(EventMapper eventMapper) {
        this.eventMapper = eventMapper;
    }

    @RequestMapping({"list" ,"/"})
    public String listSchedules(Model model, Principal user){
        model.addAttribute("schedules" ,scheduleMapper.mapToDTO(scheduleService.listAllByUserName(user.getName())));
        return "schedule/list";
    }
    @Secured("ROLE_COORDINATOR")
    @RequestMapping("/clear/{id}")
    public String clearSchedule(@PathVariable Integer id) {
        selectedSchedule = scheduleService.getById(id.longValue());
        selectedSchedule.getDrives().forEach(selectedSchedule::removeDrive);
        return "/show/"+ id;
    }
    @RequestMapping("/show/{id}")
    public String getSchedule(@PathVariable Integer id, Model model){
        selectedSchedule = scheduleService.getById(id.longValue());
        model.addAttribute("schedule", scheduleMapper.mapToDTO(selectedSchedule));
        return "schedule/show";
    }

    @ResponseBody
    @GetMapping(value = "/events", produces = "application/json")
    public List<EventDTO> getEvents(
            @RequestParam(value="start", required=false) String start,
            @RequestParam(value="end", required=false) String end) {
        List<EventDTO> events = new ArrayList<>();
         events.addAll(eventMapper.mapToDTO(selectedSchedule.getDrives()));

        return events;
    }
    @Secured({"ROLE_COORDINATOR"})
    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        selectedSchedule = scheduleService.getById(id.longValue());
        model.addAttribute("schedule", scheduleMapper.mapToDTO(selectedSchedule));
        System.out.println(scheduleMapper.mapToDTO(scheduleService.getById(id.longValue())));
        List<User> currentUsers = scheduleService.getById(id.longValue()).getUsers()
                .stream()
                // .filter(u -> u.getRole()
                //        .equals(Role.LOGISTICS))
                .collect(Collectors.toList());
        List<User> possibleUsers = userService.listByRole(Role.LOGISTICS);
        possibleUsers.removeAll(currentUsers);
        model.addAttribute("currentUsers",userMapper.mapToDTO(currentUsers));
        model.addAttribute("possibleUsers",userMapper.mapToDTO(possibleUsers));
        return "schedule/form";
    }
    @Secured("ROLE_COORDINATOR")
    @RequestMapping("/new")
    public String newUser(Model model){
        model.addAttribute("schedule", new ScheduleDTO());
        model.addAttribute("coordinators", userMapper.mapToDTO(userService.listByRole(Role.COORDINATOR)));
        return "schedule/newscheduleform";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String saveOrUpdate(ScheduleDTO dto){
        Schedule schedule = scheduleMapper.mapToObj(dto);
        scheduleService.saveOrUpdate(schedule);
        return "redirect:/schedule/show/" + schedule.getId();
    }
    @Secured("ROLE_COORDINATOR")
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, Model model){
        try {
            scheduleService.delete(id.longValue());
        }
        catch (UnsupportedOperationException e){
            model.addAttribute("error", e.getMessage());
            return "redirect:error";
        }
        return "redirect:/schedule/list";
    }
    @Transactional
    @RequestMapping("{idSchedule}/deleteuser/{idUser}")
    public String deletUserFromSchedule(@PathVariable Integer idSchedule ,@PathVariable Integer idUser){
        Schedule schedule = scheduleService.getById(idSchedule.longValue());
        User user = userService.getById(idUser.longValue());
        try {
            schedule.removeUser(user);
        }
        catch (UnsupportedOperationException e){
            return "redirect:error";
        }
        scheduleService.saveOrUpdate(schedule);
        return "redirect:/schedule/edit/" + schedule.getId();
    }
    @Transactional
    @RequestMapping("{idSchedule}/adduser/{idUser}")
    public String addUserToSchedule(@PathVariable Integer idSchedule ,@PathVariable Integer idUser){
        Schedule schedule = scheduleService.getById(idSchedule.longValue());
        User user = userService.getById(idUser.longValue());
        try {
            schedule.addUser(user);
        }
        catch (UnsupportedOperationException e){
            return "redirect:error";
        }
        scheduleService.saveOrUpdate(schedule);
        return "redirect:/schedule/edit/" + schedule.getId();
    }



}

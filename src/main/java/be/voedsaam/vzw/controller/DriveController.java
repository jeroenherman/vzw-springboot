package be.voedsaam.vzw.controller;

import be.voedsaam.vzw.business.Destination;
import be.voedsaam.vzw.business.Drive;
import be.voedsaam.vzw.business.Schedule;
import be.voedsaam.vzw.business.User;
import be.voedsaam.vzw.enums.Role;
import be.voedsaam.vzw.security.UserSecurity;
import be.voedsaam.vzw.service.DestinationService;
import be.voedsaam.vzw.service.DriveService;
import be.voedsaam.vzw.service.ScheduleService;
import be.voedsaam.vzw.service.UserService;
import be.voedsaam.vzw.service.dto.EventDTO;
import be.voedsaam.vzw.service.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/drive")
public class DriveController {

    private ScheduleService scheduleService;
    private UserService userService;
    private ScheduleMapper scheduleMapper;
    private UserMapper userMapper;
    private DriveService driveService;
    private DriveMapper driveMapper;
    private EventMapper eventMapper;
    private DestinationMapper destinationMapper;
    private DestinationService destinationService;
    @Autowired
    public void setDestinationMapper(DestinationMapper destinationMapper) {
        this.destinationMapper = destinationMapper;
    }
    @Autowired
    public void setDestinationService(DestinationService destinationService) {
        this.destinationService = destinationService;
    }

    @Autowired
    public void setEventMapper(EventMapper eventMapper) {
        this.eventMapper = eventMapper;
    }

    @Autowired
    public void setDriveMapper(DriveMapper driveMapper) {
        this.driveMapper = driveMapper;
    }

    @Autowired
    public void setDriveService(DriveService driveService) {
        this.driveService = driveService;
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

    @RequestMapping({"list", "/"})
    public String listDrives(Model model) {
        model.addAttribute("drives", driveMapper.mapToDTO((List<Drive>) driveService.listAll()));
        model.addAttribute("events", eventMapper.mapToDTO((List<Drive>) driveService.listAll()));
        return "drive/list";
    }

    @RequestMapping({"listbyuser", })
    public String listDrives(Model model, Principal principal) {
        model.addAttribute("drives", driveMapper.mapToDTO( driveService.findbyPerson(principal.getName())));
        model.addAttribute("events", eventMapper.mapToDTO( driveService.findbyPerson(principal.getName())));
        return "drive/list";
    }


    @RequestMapping("/show/{id}")
    public String getSchedule(@PathVariable Integer id, Model model) {
        model.addAttribute("drive", driveMapper.mapToDTO(driveService.getById(id.longValue())));
        return "drive/show";
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Drive drive = driveService.getById(id.longValue());
        model.addAttribute("event", eventMapper.mapToDTO(drive));
        model.addAttribute("drive", driveMapper.mapToDTO(drive));
        model.addAttribute("now", LocalDateTime.now());
        model.addAttribute("schedule", scheduleMapper.mapToDTO(drive.getSchedule()));

        List<User> currentUsers = drive.getUsers();
        List<User> possibleUsers = userService.listByRole(Role.DRIVER);
        possibleUsers.addAll(userService.listByRole(Role.ATTENDEE));
        possibleUsers.addAll(userService.listByRole(Role.DEPOTHELP));
        possibleUsers.removeAll(currentUsers);
        model.addAttribute("currentUsers", userMapper.mapToDTO(currentUsers));
        model.addAttribute("possibleUsers", userMapper.mapToDTO(possibleUsers));
        List<Destination> currentDestinations = drive.getDestinations();
        List<Destination> possibleDestinations = (List<Destination>)destinationService.listAll();
        possibleDestinations.removeAll(currentDestinations);
        model.addAttribute("currentDestinations" ,destinationMapper.mapToDTO(currentDestinations));
        model.addAttribute("possibleDestinations" ,destinationMapper.mapToDTO(possibleDestinations));
        return "drive/form";
    }

    @RequestMapping("/new/{idSchedule}")
    public String newDrive(@PathVariable Integer idSchedule) throws UnsupportedOperationException{

        if (idSchedule==null)
            throw new UnsupportedOperationException("schedule needs to be set, id can not be null ");
        Schedule schedule = scheduleService.getById(idSchedule.longValue());
        Drive drive = new Drive();
        drive.setStartTime(LocalDateTime.now().withSecond(0).withNano(0));
        drive.setEndTime(LocalDateTime.now().withSecond(0).withNano(0));
        drive.setDescription("");
        drive = driveService.saveOrUpdate(drive);
        schedule.addDrive(drive);
        scheduleService.saveOrUpdate(schedule);
        return "redirect:/drive/edit/" + drive.getId();
    }


    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String saveOrUpdate(EventDTO dto) {
        Drive drive = eventMapper.mapToObj(dto);
        driveService.saveOrUpdate(drive);
        return "redirect:/drive/edit/" + drive.getId();
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        Drive drive = driveService.getById(id.longValue());
        drive.clear();
        driveService.saveOrUpdate(drive);
        driveService.delete(id.longValue());
        return "redirect:/drive/list";
    }

    @RequestMapping("{idDrive}/deleteuser/{idUser}")
    public String deletUserFromDrive(@PathVariable Integer idDrive, @PathVariable Integer idUser) {
        Drive drive = driveService.getById(idDrive.longValue());
        User user = userService.getById(idUser.longValue());
        drive.removeUser(user);
         driveService.saveOrUpdate(drive);
        return "redirect:/drive/edit/" + drive.getId();
    }

    @RequestMapping("{idDrive}/adduser/{idUser}")
    public String addUserFromDrive(@PathVariable Integer idDrive, @PathVariable Integer idUser) {
        Drive drive = driveService.getById(idDrive.longValue());
        User user = userService.getById(idUser.longValue());
        drive.addUser(user);
        driveService.saveOrUpdate(drive);
        return "redirect:/drive/edit/" + drive.getId();
    }

    @RequestMapping("{idDrive}/deletedestination/{idDestination}")
    public String deleteDestinationFromDrive(@PathVariable Integer idDrive, @PathVariable Integer idDestination) {
        Drive drive = driveService.getById(idDrive.longValue());
        Destination destination = destinationService.getById(idDestination.longValue());
        drive.removeDestination(destination);
        driveService.saveOrUpdate(drive);
        return "redirect:/drive/edit/" + drive.getId();
    }

    @RequestMapping("{idDrive}/adddestination/{idDestination}")
    public String addDestinationFromDrive(@PathVariable Integer idDrive, @PathVariable Integer idDestination) {
        Drive drive = driveService.getById(idDrive.longValue());
        Destination destination = destinationService.getById(idDestination.longValue());
        drive.addDestination(destination);
        driveService.saveOrUpdate(drive);
        return "redirect:/drive/edit/" + drive.getId();
    }

}

package be.voedsaam.vzw.controller;

import be.voedsaam.vzw.business.Destination;
import be.voedsaam.vzw.business.Drive;
import be.voedsaam.vzw.business.Schedule;
import be.voedsaam.vzw.business.impl.Volunteer;
import be.voedsaam.vzw.service.EmailService;
import be.voedsaam.vzw.enums.Role;
import be.voedsaam.vzw.service.DestinationService;
import be.voedsaam.vzw.service.DriveService;
import be.voedsaam.vzw.service.ScheduleService;
import be.voedsaam.vzw.service.UserService;
import be.voedsaam.vzw.service.dto.EventDTO;
import be.voedsaam.vzw.service.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/drive")
public class DriveController {

    private ScheduleService scheduleService;
    private UserService userService;
    private ScheduleMapper scheduleMapper;
    private DriveService driveService;
    private DriveMapper driveMapper;
    private EventMapper eventMapper;
    private DestinationMapper destinationMapper;
    private DestinationService destinationService;
    private VolunteerMapper volunteerMapper;
    private EmailService emailService;

    @Autowired
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    @Autowired
    public void setEmployeeMapper(VolunteerMapper volunteerMapper) {
        this.volunteerMapper = volunteerMapper;
    }

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
    public void setScheduleService(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @Autowired
    public void setScheduleMapper(ScheduleMapper scheduleMapper) {
        this.scheduleMapper = scheduleMapper;
    }
    @Secured({"ROLE_COORDINATOR","ROLE_LOGISTICS"})
    @RequestMapping({"list", "/"})
    public String listDrives(Model model) {
        model.addAttribute("drives", driveMapper.mapToDTO((List<Drive>) driveService.listAll()));
        //model.addAttribute("events", eventMapper.mapToDTO((List<Drive>) driveService.listAll()));
        return "drive/list";
    }
    @Secured({"ROLE_DRIVER"})
    @RequestMapping("listbyuser")
    public String listDrivesbyUser(Model model, Principal user) {
        model.addAttribute("drives", driveMapper.mapToDTO(driveService.listAllByUser(user.getName())));
        return "drive/list";
    }
    @Secured({"ROLE_LOGISTICS","ROLE_COORDINATOR"})
    @RequestMapping("listBySchedule/{scheduleId}")
    public String listDrivesbyUser(Model model, @PathVariable Integer scheduleId) {
        Schedule schedule = scheduleService.getById(scheduleId.longValue());
        model.addAttribute("drives", driveMapper.mapToDTO(schedule.getDrives()));
        return "drive/list";
    }


    @RequestMapping("/show/{id}")
    public String getDrive(@PathVariable Integer id, Model model) {
        Drive drive = driveService.getById(id.longValue());
        if (drive==null)
            return "redirect:/error";
        model.addAttribute("event", eventMapper.mapToDTO(drive));
        model.addAttribute("drive", driveMapper.mapToDTO(drive));
        model.addAttribute("now", LocalDateTime.now());
        model.addAttribute("schedule", scheduleMapper.mapToDTO(drive.getSchedule()));
        List<Volunteer> currentUsers = drive.getUsers();
        model.addAttribute("currentUsers", volunteerMapper.mapToDTO(currentUsers));
        List<Destination> currentDestinations = drive.getDestinations();
        model.addAttribute("currentDestinations" ,destinationMapper.mapToDTO(currentDestinations));
        return "drive/show";
    }

    @RequestMapping("/mail/{id}")
    public String mailDrive(@PathVariable Integer id, Model model) {
        Drive drive = driveService.getById(id.longValue());
        if (drive==null)
            return "redirect:/error";
        model.addAttribute("event", eventMapper.mapToDTO(drive));
        model.addAttribute("drive", driveMapper.mapToDTO(drive));
        model.addAttribute("now", LocalDateTime.now());
        model.addAttribute("schedule", scheduleMapper.mapToDTO(drive.getSchedule()));
        List<Volunteer> currentUsers = drive.getUsers();
        model.addAttribute("currentUsers", volunteerMapper.mapToDTO(currentUsers));

        return "drive/mail";
    }

    @Secured({"ROLE_COORDINATOR","ROLE_LOGISTICS"})
    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Drive drive = driveService.getById(id.longValue());
        model.addAttribute("event", eventMapper.mapToDTO(drive));
        model.addAttribute("drive", driveMapper.mapToDTO(drive));
        model.addAttribute("now", LocalDateTime.now());
        model.addAttribute("schedule", scheduleMapper.mapToDTO(drive.getSchedule()));

        List<Volunteer> currentUsers = drive.getUsers();
        List<Volunteer> possibleUsers = userService.listVolunteerByRole(Role.DRIVER);
        possibleUsers.addAll(userService.listVolunteerByRole(Role.ATTENDEE));
        possibleUsers.addAll(userService.listVolunteerByRole(Role.DEPOTHELP));
        possibleUsers.removeAll(currentUsers);
        model.addAttribute("currentUsers", volunteerMapper.mapToDTO(currentUsers));
        model.addAttribute("possibleUsers", volunteerMapper.mapToDTO(possibleUsers));
        List<Destination> currentDestinations = drive.getDestinations();
        List<Destination> possibleDestinations = (List<Destination>)destinationService.listAll();
        possibleDestinations.removeAll(currentDestinations);
        model.addAttribute("currentDestinations" ,destinationMapper.mapToDTO(currentDestinations));
        model.addAttribute("possibleDestinations" ,destinationMapper.mapToDTO(possibleDestinations));

        return "drive/form";
    }

    @Secured({"ROLE_COORDINATOR","ROLE_LOGISTICS"})
    @RequestMapping("/new/{idSchedule}")
    public String newDrive(@PathVariable Integer idSchedule) throws UnsupportedOperationException{

        if (idSchedule==null)
            throw new UnsupportedOperationException("schedule needs to be set, id can not be null ");
        Schedule schedule = scheduleService.getById(idSchedule.longValue());
        Drive drive = new Drive();
        LocalDate date = LocalDate.now();
        drive.setStartTime(date.atTime(9,00));
        drive.setEndTime(date.atTime(17,0));
        drive.setDescription("");
        drive = driveService.saveOrUpdate(drive);
        schedule.addDrive(drive);
        scheduleService.saveOrUpdate(schedule);
        return "redirect:/drive/edit/" + drive.getId();
    }

    @Secured({"ROLE_COORDINATOR","ROLE_LOGISTICS"})
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String saveOrUpdate(EventDTO dto) {
        Drive drive = eventMapper.mapToObj(dto);
        driveService.saveOrUpdate(drive);
        return "redirect:/drive/show/" + drive.getId();
    }
    @Secured({"ROLE_COORDINATOR"})
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        Drive drive = driveService.getById(id.longValue());
        drive.clear();
        drive = driveService.saveOrUpdate(drive);
        driveService.delete(drive.getId());
        return "redirect:/drive/list";
    }

    @Secured({"ROLE_COORDINATOR", "ROLE_LOGISTICS"})
    @RequestMapping("/clear/{id}")
    public String clear(@PathVariable Integer id) {
        Drive drive = driveService.getById(id.longValue());
        Schedule schedule = scheduleService.getById(drive.getSchedule().getId());
        drive.clear();
        drive = driveService.saveOrUpdate(drive);
        schedule.removeDrive(drive);
        scheduleService.saveOrUpdate(schedule);
        return "redirect:/drive/list";
    }

    @Secured({"ROLE_COORDINATOR"})
    @RequestMapping("/removeOrphans")
    public String deleteOrphans() {
        driveService.deleteAllDrivesWithoutSchedule();
        return "redirect:/drive/list";
    }

    @RequestMapping("{idDrive}/deleteuser/{idUser}")
    public String deletUserFromDrive(@PathVariable Integer idDrive, @PathVariable Integer idUser) {
        Drive drive = driveService.getById(idDrive.longValue());
        Volunteer user = userService.getVolunteerById(idUser.longValue());
        drive.removeUser(user);
        driveService.saveOrUpdate(drive);
        return "redirect:/drive/show/" + drive.getId();
    }

    @RequestMapping("{idDrive}/adduser/{idUser}")
    public String addUserFromDrive(@PathVariable Integer idDrive, @PathVariable Integer idUser) {
        Drive drive = driveService.getById(idDrive.longValue());
        Volunteer user = userService.getVolunteerById(idUser.longValue());
        drive.addUser(user);
        driveService.saveOrUpdate(drive);
        return "redirect:/drive/show/" + drive.getId();
    }

    @RequestMapping("{idDrive}/deletedestination/{idDestination}")
    public String deleteDestinationFromDrive(@PathVariable Integer idDrive, @PathVariable Integer idDestination) {
        Drive drive = driveService.getById(idDrive.longValue());
        Destination destination = destinationService.getById(idDestination.longValue());
        drive.removeDestination(destination);
        driveService.saveOrUpdate(drive);
        return "redirect:/drive/show/" + drive.getId();
    }

    @RequestMapping("{idDrive}/adddestination/{idDestination}")
    public String addDestinationFromDrive(@PathVariable Integer idDrive, @PathVariable Integer idDestination) {
        Drive drive = driveService.getById(idDrive.longValue());
        Destination destination = destinationService.getById(idDestination.longValue());
        drive.addDestination(destination);
        driveService.saveOrUpdate(drive);
        return "redirect:/drive/show/" + drive.getId();
    }




}

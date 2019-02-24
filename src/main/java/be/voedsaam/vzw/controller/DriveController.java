package be.voedsaam.vzw.controller;

import be.voedsaam.vzw.business.Drive;
import be.voedsaam.vzw.business.Schedule;
import be.voedsaam.vzw.business.User;
import be.voedsaam.vzw.enums.Role;
import be.voedsaam.vzw.service.DriveService;
import be.voedsaam.vzw.service.ScheduleService;
import be.voedsaam.vzw.service.UserService;
import be.voedsaam.vzw.service.dto.DriveDTO;
import be.voedsaam.vzw.service.mapper.DriveMapper;
import be.voedsaam.vzw.service.mapper.ScheduleMapper;
import be.voedsaam.vzw.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
        return "drive/list";
    }

    @RequestMapping("/show/{id}")
    public String getSchedule(@PathVariable Integer id, Model model) {
        model.addAttribute("drive", driveMapper.mapToDTO(driveService.getById(id.longValue())));
        return "drive/show";
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("drive", driveMapper.mapToDTO(driveService.getById(id.longValue())));
        List<User> currentUsers = driveService.getById(id.longValue()).getUsers();
        List<User> possibleUsers = userService.listByRole(Role.DRIVER);
        possibleUsers.addAll(userService.listByRole(Role.ATTENDEE));
        possibleUsers.addAll(userService.listByRole(Role.DEPOTHELP));
        possibleUsers.removeAll(currentUsers);
        model.addAttribute("currentUsers", userMapper.mapToDTO(currentUsers));
        model.addAttribute("possibleUsers", userMapper.mapToDTO(possibleUsers));
        return "drive/form";
    }

    @RequestMapping("/new")
    public String newDrive(Model model) {
        model.addAttribute("drive", new DriveDTO());
        model.addAttribute("Schedules", scheduleMapper.mapToDTO((List<Schedule>) scheduleService.listAll()));
        return "drive/newdriveform";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String saveOrUpdate(DriveDTO dto) {
        Drive drive = driveMapper.mapToObj(dto);
        driveService.saveOrUpdate(drive);
        return "redirect:/drive/show/" + drive.getId();
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

}

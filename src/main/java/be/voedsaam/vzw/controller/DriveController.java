package be.voedsaam.vzw.controller;

import be.voedsaam.vzw.service.dto.DriveDTO;
import be.voedsaam.vzw.service.dto.UserDTO;

import be.voedsaam.vzw.service.manager.DriveManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.DriverManager;
import java.time.LocalDateTime;

/**
 * Created by jt on 12/17/15.
 */
@Controller
@RequestMapping("/drive")
public class DriveController {

    private DriveManagement driveManagement;
    @Autowired
    public void setDriveManagement(DriveManagement driveManagement) {
        this.driveManagement = driveManagement;
    }

    @RequestMapping({"/list", "/"})
    public String listUsers(Model model){
        model.addAttribute("drives", driveManagement.getDriveList(LocalDateTime.MIN,LocalDateTime.MAX));
        return "drive/list";
    }

    @RequestMapping("/show/{id}")
    public String getUser(@PathVariable Integer id, Model model){
        model.addAttribute("drive", driveManagement.getById(id.longValue()));
        return "drive/show";
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("user",driveManagement.getById(id.longValue()));
        return "drive/driveform";
    }

    @RequestMapping("/new")
    public String newUser(Model model){
        model.addAttribute("user", new UserDTO());
        return "user/userform";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String saveOrUpdate(DriveDTO drive){
        DriveDTO savedDrive = driveManagement.addDrive(drive);
        return "redirect:/drive/show/" + savedDrive.getId();
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        driveManagement.removeDrive(id.longValue());
        return "redirect:/drive/list";
    }
}

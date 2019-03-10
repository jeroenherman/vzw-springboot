package be.voedsaam.vzw.controller;

import be.voedsaam.vzw.business.User;
import be.voedsaam.vzw.enums.Color;
import be.voedsaam.vzw.enums.Role;
import be.voedsaam.vzw.service.UserService;
import be.voedsaam.vzw.service.dto.UserDTO;

import be.voedsaam.vzw.service.mapper.EmployeeMapper;
import be.voedsaam.vzw.service.mapper.VolunteerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jt on 12/17/15.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private EmployeeMapper employeeMapper;
    private VolunteerMapper volunteerMapper;
    @Autowired
    public void setVolunteerMapper(VolunteerMapper volunteerMapper) {
        this.volunteerMapper = volunteerMapper;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    public void setUserMapper(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    @RequestMapping({"/list", "/"})
    public String listUsers(Model model){
        model.addAttribute("employees", employeeMapper.mapToDTO(userService.listAllEmployees()));
        model.addAttribute("volunteers", volunteerMapper.mapToDTO(userService.listAllVolunteers()));
        return "user/list";
    }

    @RequestMapping("/showemployee/{id}")
    public String getEmployee(@PathVariable Integer id, Model model){
        model.addAttribute("user", employeeMapper.mapToDTO(userService.getEmployeeById(id.longValue())));
        return "user/show";
    }
    @RequestMapping("/showvolunteer/{id}")
    public String getVolunteer(@PathVariable Integer id, Model model){
        model.addAttribute("user", volunteerMapper.mapToDTO(userService.getVolunteerById(id.longValue())));
        return "user/show";
    }

    @RequestMapping("/editemployee/{id}")
    public String editEmployee(@PathVariable Integer id, Model model){
        model.addAttribute("user",employeeMapper.mapToDTO(userService.getEmployeeById(id.longValue())));
        List<Role> roles = new ArrayList<>();
        roles.add(Role.COORDINATOR); roles.add(Role.LOGISTICS);
        model.addAttribute("roles",roles);
        model.addAttribute("colors",Color.values());
        return "user/form";
    }

    @RequestMapping("/editvolunteer/{id}")
    public String editVolunteer(@PathVariable Integer id, Model model){
        model.addAttribute("user",volunteerMapper.mapToDTO(userService.getVolunteerById(id.longValue())));
        List<Role> roles = new ArrayList<>();
        roles.add(Role.DRIVER); roles.add(Role.ATTENDEE); roles.add(Role.DEPOTHELP); roles.add(Role.VOLUNTEER);
        model.addAttribute("roles",roles);
        model.addAttribute("colors",Color.values());
        return "user/form";
    }

    @RequestMapping("/newemployee")
    public String newEmployee(Model model){
        model.addAttribute("user", new UserDTO());
        List<Role> roles = new ArrayList<>();
        roles.add(Role.COORDINATOR); roles.add(Role.LOGISTICS);
        model.addAttribute("roles",roles);
        model.addAttribute("colors",Color.values());
        return "user/form";
    }
    @RequestMapping("/newvolunteer")
    public String newVolunteer(Model model){
        model.addAttribute("user", new UserDTO());
        List<Role> roles = new ArrayList<>();
        roles.add(Role.DRIVER); roles.add(Role.ATTENDEE); roles.add(Role.DEPOTHELP); roles.add(Role.VOLUNTEER);
        model.addAttribute("roles",roles);
        model.addAttribute("colors",Color.values());
        return "user/form";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String saveOrUpdate(UserDTO dto){
        User user;
        String path ="";
        if (dto.getRole().equals(Role.LOGISTICS) || dto.getRole().equals(Role.COORDINATOR)){
         user = employeeMapper.mapToObj(dto);
        path = "employee";
        }
        else{
            user = volunteerMapper.mapToObj(dto);
            path = "volunteer";
            }
        user = userService.saveOrUpdate(user);
        return "redirect:/user/show"+path+"/" + user.getId();
    }

    @RequestMapping("/delete/{id}")
    @Transactional
    public String delete(@PathVariable Integer id){
        userService.delete(id.longValue());
        return "redirect:/user/list";
    }
}

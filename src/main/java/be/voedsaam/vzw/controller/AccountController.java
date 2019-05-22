package be.voedsaam.vzw.controller;

import be.voedsaam.vzw.business.User;
import be.voedsaam.vzw.business.impl.Volunteer;
import be.voedsaam.vzw.enums.Color;
import be.voedsaam.vzw.enums.Role;
import be.voedsaam.vzw.service.UserService;
import be.voedsaam.vzw.service.dto.UserDTO;
import be.voedsaam.vzw.service.mapper.EmployeeMapper;
import be.voedsaam.vzw.service.mapper.PartnerMapper;
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


@Controller
@RequestMapping("/account")
public class AccountController {

    private UserService userService;
    private EmployeeMapper employeeMapper;
    private VolunteerMapper volunteerMapper;
    private PartnerMapper partnerMapper;
    @Autowired
    public void setEmployeeMapper(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }
    @Autowired
    public void setPartnerMapper(PartnerMapper partnerMapper) {
        this.partnerMapper = partnerMapper;
    }

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


    @RequestMapping("/showemployee/{id}")
    public String getEmployee(@PathVariable Integer id, Model model){
        model.addAttribute("user", employeeMapper.mapToDTO(userService.getEmployeeById(id.longValue())));
        return "account/show";
    }
    @RequestMapping("/showvolunteer/{id}")
    public String getVolunteer(@PathVariable Integer id, Model model){
        model.addAttribute("user", volunteerMapper.mapToDTO(userService.getVolunteerById(id.longValue())));
        return "account/show";
    }
    @RequestMapping("/showpartner/{id}")
    public String getPartner(@PathVariable Integer id, Model model){
        model.addAttribute("user", partnerMapper.mapToDTO(userService.getPartnerById(id.longValue())));
        return "account/show";
    }

    @RequestMapping("/editemployee/{id}")
    public String editEmployee(@PathVariable Integer id, Model model){
        model.addAttribute("user",employeeMapper.mapToDTO(userService.getEmployeeById(id.longValue())));
        model.addAttribute("colors",Color.values());
        return "account/form";
    }

    @RequestMapping("/editvolunteer/{id}")
    public String editVolunteer(@PathVariable Integer id, Model model){
        model.addAttribute("user",volunteerMapper.mapToDTO(userService.getVolunteerById(id.longValue())));
        model.addAttribute("colors",Color.values());
        return "account/form";
    }
    @RequestMapping("/editpartner/{id}")
    public String editPartner(@PathVariable Integer id, Model model){
        model.addAttribute("user",partnerMapper.mapToDTO(userService.getPartnerById(id.longValue())));
        model.addAttribute("colors",Color.values());
        return "account/form";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String saveOrUpdate(UserDTO dto){
        User user = new Volunteer();
        String path ="";
        if (dto.getRole().equals(Role.LOGISTICS) || dto.getRole().equals(Role.COORDINATOR)){
         user = employeeMapper.mapToObj(dto);
        path = "employee";
        }
        if (dto.getRole().equals(Role.VOLUNTEER) || dto.getRole().equals(Role.DEPOTHELP) || dto.getRole().equals(Role.ATTENDEE)|| dto.getRole().equals(Role.DRIVER)){
            user = volunteerMapper.mapToObj(dto);
            path = "volunteer";
        }
        if (dto.getRole().equals(Role.PARTNER)){
            user = partnerMapper.mapToObj(dto);
            path = "partner";
        }
        user = userService.saveOrUpdate(user);
        return "redirect:/account/show"+path+"/" + user.getId();
    }


}

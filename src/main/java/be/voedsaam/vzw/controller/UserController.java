package be.voedsaam.vzw.controller;

import be.voedsaam.vzw.business.User;
import be.voedsaam.vzw.enums.Color;
import be.voedsaam.vzw.enums.Role;
import be.voedsaam.vzw.service.UserService;
import be.voedsaam.vzw.service.dto.UserDTO;
import be.voedsaam.vzw.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by jt on 12/17/15.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private UserMapper userMapper;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @RequestMapping({"/list", "/"})
    public String listUsers(Model model){
        model.addAttribute("users", userMapper.mapToDTO((List<User>)userService.listAll()));
        return "user/list";
    }

    @RequestMapping("/show/{id}")
    public String getUser(@PathVariable Integer id, Model model){
        model.addAttribute("user", userMapper.mapToDTO(userService.getById(id.longValue())));
        return "user/show";
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("user",userMapper.mapToDTO(userService.getById(id.longValue())));
        model.addAttribute("roles",Role.values());
        model.addAttribute("colors",Color.values());
        return "user/userform";
    }

    @RequestMapping("/new")
    public String newUser(Model model){
        model.addAttribute("user", new UserDTO());
        model.addAttribute("roles",Role.values());
        model.addAttribute("colors",Color.values());
        return "user/userform";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String saveOrUpdate(UserDTO dto){
        User user = userMapper.mapToObj(dto);
        userService.saveOrUpdate(user);
        return "redirect:/user/show/" + user.getId();
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        userService.delete(id.longValue());
        return "redirect:/user/list";
    }
}

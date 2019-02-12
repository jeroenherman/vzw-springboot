package be.voedsaam.vzw.controller;

import be.voedsaam.vzw.service.dto.UserDTO;
import be.voedsaam.vzw.service.manager.VzwManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by jt on 12/17/15.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private VzwManagement vzwManagement;
    @Autowired
    public void setVzwManagement(VzwManagement vzwManagement) {
        this.vzwManagement = vzwManagement;
    }

    @RequestMapping({"/list", "/"})
    public String listUsers(Model model){
        model.addAttribute("users", vzwManagement.getAllUsers());
        return "user/list";
    }

    @RequestMapping("/show/{id}")
    public String getUser(@PathVariable Integer id, Model model){
        model.addAttribute("user", vzwManagement.getById(id.longValue()));
        return "user/show";
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("user",vzwManagement.getById(id.longValue()));
        return "user/userform";
    }

    @RequestMapping("/new")
    public String newUser(Model model){
        model.addAttribute("user", new UserDTO());
        return "user/userform";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String saveOrUpdate(UserDTO user){
        UserDTO savedUser = vzwManagement.addUser(user);
        return "redirect:/user/show/" + savedUser.getId();
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        vzwManagement.removeUser(id.longValue());
        return "redirect:/user/list";
    }
}

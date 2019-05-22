package be.voedsaam.vzw.controller;


import be.voedsaam.vzw.service.dto.LoginCommand;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@Controller
public class LoginController {

    @RequestMapping("/login")
    public String showLoginForm(Model model){

        model.addAttribute("loginCommand", new LoginCommand());

        return "login";
    }

    @RequestMapping("logout-success")
    public String yourLoggedOut(){

        return "logout-success";
    }
    @RequestMapping("access-denied")
    public String accessDenied(){

        return "access-denied";
    }
  //  @RequestMapping(value = "/dologin", method = RequestMethod.POST)
    public String doLogin(@Valid LoginCommand loginCommand, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "loginform";
        }

        return "redirect:portal";
    }
}

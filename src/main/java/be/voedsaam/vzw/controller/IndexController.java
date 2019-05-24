package be.voedsaam.vzw.controller;

import be.voedsaam.vzw.business.User;
import be.voedsaam.vzw.enums.Color;
import be.voedsaam.vzw.service.ArticleService;
import be.voedsaam.vzw.service.ContactService;
import be.voedsaam.vzw.service.UserService;
import be.voedsaam.vzw.service.dto.ContactDTO;
import be.voedsaam.vzw.service.dto.DonationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;


@Controller
public class IndexController {

    ArticleService articleService;
    ContactService contactService;
    UserService userService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setContactService(ContactService contactService) {
        this.contactService = contactService;
    }

    @Autowired
    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }

    @RequestMapping({"/", "", "index.html"})
    public String draft(Model model){
        model.addAttribute("articles", articleService.listHome());
        return "index";
    }
    @RequestMapping({"/draft"})
    public String index(Model model){
        model.addAttribute("articles", articleService.listDraft());
        return "draft";
    }
    @RequestMapping({"/portal"})
    public String drive(Model model, Principal principal){
        String color = Color.LIGHTRED.getHex();
        if (principal!=null && !(principal.getName().equals("anonymousUser"))) {
            User user = userService.findByEmail(principal.getName());
            model.addAttribute("nrOfContacts", contactService.listAll().size());
            model.addAttribute("userId", user.getId());
            model.addAttribute("userName", user.getFullName());
            model.addAttribute("articles", articleService.listPortal());
            if (user.getColor()!=null)
            color = user.getColor().getHex();
        }
        model.addAttribute("color", color);
        return "portal";
    }
    @RequestMapping({"/about"})
    public String about(Model model){
        model.addAttribute("articles", articleService.listAbout());
        return "about";
    }

    @RequestMapping({"/news"})
    public String news(Model model){
        model.addAttribute("articles", articleService.listNews());
        return "news";
    }

    @RequestMapping({"/causes"})
    public String goals(Model model){
        model.addAttribute("articles", articleService.listGoals());
        model.addAttribute("donation", new DonationDTO());
        return "causes";
    }
    @RequestMapping({"/contact"})
    public String contact(Model model){
        model.addAttribute("contact", new ContactDTO());
        model.addAttribute("success", false);
        return "contact";
    }
    @RequestMapping({"/contactSuccess"})
    public String contactSuccess(Model model){
        model.addAttribute("contact", new ContactDTO());
        model.addAttribute("success", true);
        return "contact";
    }

}

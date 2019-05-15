package be.voedsaam.vzw.controller;

import be.voedsaam.vzw.service.ArticleService;
import be.voedsaam.vzw.service.ContactService;
import be.voedsaam.vzw.service.dto.ContactDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class IndexController {

    ArticleService articleService;
    ContactService contactService;
    @Autowired
    public void setContactService(ContactService contactService) {
        this.contactService = contactService;
    }

    @Autowired
    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }

    @RequestMapping({"/", "", "index.html"})
    public String index(Model model){
        model.addAttribute("articles", articleService.listHome());
        return "index";
    }
    @RequestMapping({"/portal"})
    public String drive(Model model){
        model.addAttribute("nrOfContacts", contactService.listAll().size());
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

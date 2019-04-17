package be.voedsaam.vzw.controller;

import be.voedsaam.vzw.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class IndexController {

    ArticleService articleService;
    @Autowired
    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }

    @RequestMapping({"/", "", "index.html"})
    public String index(Model model){
        model.addAttribute("articles", articleService.listHome());

        return "index";
    }
    @RequestMapping({"/drive"})
    public String drive(Model model){
        model.addAttribute("articles", articleService.listHome());

        return "drive";
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
        return "contact";
    }

}

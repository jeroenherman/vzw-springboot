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

    @RequestMapping({"/", ""})
    public String index(Model model){
        model.addAttribute("articles", articleService.listHome());

        return "index";
    }
}

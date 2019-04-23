package be.voedsaam.vzw.controller;

import be.voedsaam.vzw.business.Article;
import be.voedsaam.vzw.business.Paragraph;
import be.voedsaam.vzw.business.User;
import be.voedsaam.vzw.enums.ArticleType;
import be.voedsaam.vzw.enums.Color;
import be.voedsaam.vzw.enums.Role;
import be.voedsaam.vzw.service.ArticleService;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Controller
@RequestMapping("/article")
public class ArticleController {

    private ArticleService articleService;

    @Autowired
    public void setUserService(ArticleService articleService) {
        this.articleService = articleService;
    }

    @RequestMapping({"/list", "/"})
    public String listArticles(Model model){
        model.addAttribute("articles", articleService.listAll());

        return "article/list";
    }

    @RequestMapping("/showarticle/{id}")
    public String getEmployee(@PathVariable Integer id, Model model){
        Article article = articleService.getById(id.longValue());
        model.addAttribute("article", article);
        //todo remove bugfix article service duplicates paragraphs on number of links
        Set<Paragraph> paragraphs = new HashSet<>();
        paragraphs.addAll(article.getParagraphs());
        model.addAttribute("paragraphs", paragraphs);
        return "article/show";
    }

    @RequestMapping("/editarticle/{id}")
    public String editEmployee(@PathVariable Integer id, Model model){
        model.addAttribute("article", articleService.getById(id.longValue()));
        model.addAttribute("types",ArticleType.values());
        return "article/form";
    }

    @RequestMapping("/newarticle")
    public String newEmployee(Model model){
        model.addAttribute("article", new Article());
        model.addAttribute("types",ArticleType.values());
        return "article/form";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String saveOrUpdate(Article article){

        article = articleService.saveOrUpdate(article);
        return "redirect:/article/show/" + article.getId();
    }

    @RequestMapping("/delete/{id}")
    @Transactional
    public String delete(@PathVariable Integer id){
        articleService.delete(id.longValue());
        return "redirect:/article/list";
    }
}

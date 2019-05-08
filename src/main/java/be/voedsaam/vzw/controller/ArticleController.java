package be.voedsaam.vzw.controller;

import be.voedsaam.vzw.business.*;
import be.voedsaam.vzw.enums.ArticleType;
import be.voedsaam.vzw.enums.Color;
import be.voedsaam.vzw.enums.Role;
import be.voedsaam.vzw.service.ArticleService;
import be.voedsaam.vzw.service.UserService;
import be.voedsaam.vzw.service.dto.ArticleDTO;
import be.voedsaam.vzw.service.dto.UserDTO;
import be.voedsaam.vzw.service.mapper.*;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.*;


@Controller
@RequestMapping("/article")
public class ArticleController {

    private ArticleService articleService;
    private ArticleMapper articleMapper;
    private ParagraphMapper paragraphMapper;
    private LinkMapper linkMapper;
    private  PictureMapper pictureMapper;
    @Autowired
    public void setParagraphMapper(ParagraphMapper paragraphMapper) {
        this.paragraphMapper = paragraphMapper;
    }
    @Autowired
    public void setLinkMapper(LinkMapper linkMapper) {
        this.linkMapper = linkMapper;
    }
    @Autowired
    public void setPictureMapper(PictureMapper pictureMapper) {
        this.pictureMapper = pictureMapper;
    }

    @Autowired
    public void setArticleMapper(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }

    @Autowired
    public void setUserService(ArticleService articleService) {
        this.articleService = articleService;
    }

    @RequestMapping({"/list", "/"})
    public String listArticles(Model model){
        model.addAttribute("articles", articleMapper.mapToDTO((List<Article>)articleService.listAll()));

        return "article/list";
    }

    @RequestMapping("/show/{id}")
    public String get(@PathVariable Integer id, Model model){
        Article article = articleService.getById(id.longValue());
        model.addAttribute("article", articleMapper.mapToDTO(article));
        model.addAttribute("paragraphs",paragraphMapper.mapToDTO(article.getParagraphs()));
        model.addAttribute("links",linkMapper.mapToDTO(article.getLinks()));
        model.addAttribute("picture",pictureMapper.mapToDTO(article.getPicture()));
        return "article/show";
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        Article article = articleService.getById(id.longValue());
        model.addAttribute("paragraphs",paragraphMapper.mapToDTO(article.getParagraphs()));
        model.addAttribute("article", articleMapper.mapToDTO(article));
        model.addAttribute("links",linkMapper.mapToDTO(article.getLinks()));
        model.addAttribute("picture",pictureMapper.mapToDTO(article.getPicture()));
        model.addAttribute("types",ArticleType.values());
        return "article/form";
    }

    @RequestMapping("/new")
    public String newArticle(Model model){
        Article article = new Article();
        article.setArticleType(ArticleType.HOME);
        model.addAttribute("article", articleMapper.mapToDTO(article));
        model.addAttribute("types",ArticleType.values());
        return "article/form";
    }

    @RequestMapping("/newparagraph/{id}")
    public String newParagraph(@PathVariable Integer id , Model model){
        Article article = articleService.getById(id.longValue());
        Paragraph paragraph = new Paragraph();
        article.addParagraph(paragraph);
        article = articleService.saveOrUpdate(article);
        return "redirect:/article/edit/"+ article.getId();

    }
    @RequestMapping("/newlink/{id}")
    public String newLink(@PathVariable Integer id , Model model){
        Article article = articleService.getById(id.longValue());
        Link link = new Link();
        article.addLink(link);
        article = articleService.saveOrUpdate(article);
        return "redirect:/article/edit/"+ article.getId();

    }
    @RequestMapping("/newpicture/{id}")
    public String newPicture(@PathVariable Integer id , Model model){
        Article article = articleService.getById(id.longValue());
        Picture picture = new Picture();
        picture.setUrl("url");
        picture.setAlternateText("alt");
        article.setPicture(picture);
        article = articleService.saveOrUpdate(article);
        return "redirect:/article/edit/"+ article.getId();

    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String saveOrUpdate(ArticleDTO dto){
        Article article = articleMapper.mapToObj(dto);
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

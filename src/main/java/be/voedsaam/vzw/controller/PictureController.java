package be.voedsaam.vzw.controller;

import be.voedsaam.vzw.business.Article;
import be.voedsaam.vzw.business.Picture;
import be.voedsaam.vzw.service.ArticleService;
import be.voedsaam.vzw.service.PictureService;
import be.voedsaam.vzw.service.StorageService;
import be.voedsaam.vzw.service.dto.PictureDTO;
import be.voedsaam.vzw.service.mapper.PictureMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/picture")
public class PictureController {
    private PictureService pictureService;
    private ArticleService articleService;
    private PictureMapper pictureMapper;
    private StorageService storageService;
    private Path path;
    @Autowired
    public void setStorageService(StorageService storageService) {
        this.storageService = storageService;
    }

    @Autowired
    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }
    @Autowired
    public void setPictureMapper(PictureMapper pictureMapper) {
        this.pictureMapper = pictureMapper;
    }

    @Autowired
    public void setPictureService(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String saveOrUpdate(PictureDTO dto){
        Picture picture = pictureMapper.mapToObj(dto);
        picture = pictureService.saveOrUpdate(picture);
        return "redirect:/article/edit/" + picture.getArticle().getId();
    }

    @RequestMapping("/edit/{id}")
    public String editTask(@PathVariable Integer id, Model model){
        Picture picture = pictureService.getById(id.longValue());
        model.addAttribute("picture", pictureMapper.mapToDTO(picture));
        return "picture/form";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        Picture picture = pictureService.getById(id.longValue());
        Article article = picture.getArticle();
        article.setPicture(null);
        article.removeLink(picture);
        articleService.saveOrUpdate(article);
        return "redirect:/article/edit/" + article.getId();
    }

    @PostMapping("/upload/{id}")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes, @PathVariable Integer id) {
        Picture picture = pictureService.getById(id.longValue());
        picture.setUrl(file.getOriginalFilename());
        picture.setAlternateText(file.getName());
        pictureService.saveOrUpdate(picture);
        storageService.store(file, Paths.get("src/main/resources/static/public-pictures/" +picture.getArticle().getArticleType()));
        redirectAttributes.addFlashAttribute("message",
                "afbeelding " + file.getOriginalFilename() + " successvol toegevoegd!");

        return "redirect:/article/edit/" + picture.getArticle().getId();
    }
}

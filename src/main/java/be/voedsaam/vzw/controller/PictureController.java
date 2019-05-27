package be.voedsaam.vzw.controller;

import be.voedsaam.vzw.business.Article;
import be.voedsaam.vzw.business.Picture;
import be.voedsaam.vzw.business.Product;
import be.voedsaam.vzw.service.ArticleService;
import be.voedsaam.vzw.service.PictureService;
import be.voedsaam.vzw.service.StorageService;
import be.voedsaam.vzw.service.dto.PictureDTO;
import be.voedsaam.vzw.service.mapper.PictureMapper;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
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
    @Secured("ROLE_COORDINATOR")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String saveOrUpdate(PictureDTO dto){
        Picture picture = pictureMapper.mapToObj(dto);
        picture = pictureService.saveOrUpdate(picture);
        return "redirect:/article/edit/" + picture.getArticle().getId();
    }
    @Secured("ROLE_COORDINATOR")
    @RequestMapping("/edit/{id}")
    public String editTask(@PathVariable Integer id, Model model){
        Picture picture = pictureService.getById(id.longValue());
        model.addAttribute("picture", pictureMapper.mapToDTO(picture));
        return "picture/form";
    }
    @Secured("ROLE_COORDINATOR")
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        Picture picture = pictureService.getById(id.longValue());
        Article article = picture.getArticle();
        article.setPicture(null);
        article.removeLink(picture);
        articleService.saveOrUpdate(article);
        return "redirect:/article/edit/" + article.getId();
    }
    @Secured("ROLE_COORDINATOR")
    @PostMapping("/upload/{id}")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes, @PathVariable Integer id) {
        Picture picture = pictureService.getById(id.longValue());
        picture.setUrl(file.getOriginalFilename());
        picture.setAlternateText(file.getName());
        try {
            picture.setData(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        pictureService.saveOrUpdate(picture);
//        storageService.store(file, Paths.get("src/main/resources/static/public-pictures/" +picture.getArticle().getArticleType()));
//        redirectAttributes.addFlashAttribute("message",
//                "afbeelding " + file.getOriginalFilename() + " successvol toegevoegd!");

        return "redirect:/article/edit/" + picture.getArticle().getId();
    }

    @GetMapping("/getData/{id}")
    public void showProductImage(@PathVariable Integer id,
                               HttpServletResponse response) throws IOException {
        response.setContentType("image/jpeg"); // Or whatever format you wanna use

        Picture picture = pictureService.getById(id.longValue());

        InputStream is = new ByteArrayInputStream(picture.getData());
        IOUtils.copy(is,response.getOutputStream());
    }
}

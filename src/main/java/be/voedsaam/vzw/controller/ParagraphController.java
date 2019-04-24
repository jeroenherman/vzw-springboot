package be.voedsaam.vzw.controller;

import be.voedsaam.vzw.business.Article;
import be.voedsaam.vzw.business.Paragraph;
import be.voedsaam.vzw.business.Task;
import be.voedsaam.vzw.enums.Role;
import be.voedsaam.vzw.service.ParagraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/paragraph")
public class ParagraphController {
    private ParagraphService paragraphService;
    @Autowired
    public void setParagraphService(ParagraphService paragraphService) {
        this.paragraphService = paragraphService;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String saveOrUpdate(Paragraph paragraph){
        paragraph = paragraphService.saveOrUpdate(paragraph);
        return "redirect:/article/edit/" + paragraph.getArticle().getId();
    }

    @RequestMapping("/edit/{id}")
    public String editTask(@PathVariable Integer id, Model model){
        Paragraph paragraph = paragraphService.getById(id.longValue());
        model.addAttribute("paragraph", paragraph);
        return "paragraph/form";
    }

    @RequestMapping("/delete/{id}")
    @Transactional
    public String delete(@PathVariable Integer id){
        Paragraph paragraph = paragraphService.getById(id.longValue());
        Article article = paragraph.getArticle();
        paragraphService.delete(id.longValue());
        return "redirect:/article/edit/" + article.getId();
    }
}

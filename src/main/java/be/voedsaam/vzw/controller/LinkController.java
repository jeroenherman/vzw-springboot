package be.voedsaam.vzw.controller;

import be.voedsaam.vzw.business.Article;
import be.voedsaam.vzw.business.Link;
import be.voedsaam.vzw.service.LinkService;
import be.voedsaam.vzw.service.dto.LinkDTO;
import be.voedsaam.vzw.service.mapper.LinkMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/link")
public class LinkController {
    private LinkService linkService;
    private LinkMapper linkMapper;
    @Autowired
    public void setLinkMapper(LinkMapper linkMapper) {
        this.linkMapper = linkMapper;
    }

    @Autowired
    public void setLinkService(LinkService linkService) {
        this.linkService = linkService;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String saveOrUpdate(LinkDTO dto){
        Link link = linkMapper.mapToObj(dto);
        link = linkService.saveOrUpdate(link);
        return "redirect:/article/edit/" + link.getArticle().getId();
    }

    @RequestMapping("/edit/{id}")
    public String editTask(@PathVariable Integer id, Model model){
        Link link = linkService.getById(id.longValue());
        model.addAttribute("link", linkMapper.mapToDTO(link));
        return "link/form";
    }

    @RequestMapping("/delete/{id}")
    @Transactional
    public String delete(@PathVariable Integer id){
        Link link = linkService.getById(id.longValue());
        Article article = link.getArticle();
        article.removeLink(link);
        linkService.delete(id.longValue());
        return "redirect:/article/edit/" + article.getId();
    }
}

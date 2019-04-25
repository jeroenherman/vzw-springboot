package be.voedsaam.vzw.service.mapper;

import be.voedsaam.vzw.business.Article;
import be.voedsaam.vzw.business.repository.ArticleRepository;
import be.voedsaam.vzw.commons.AbstractMapper;
import be.voedsaam.vzw.service.dto.ArticleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ArticleMapper extends AbstractMapper<Article, ArticleDTO> {

    private ArticleRepository articleRepository;
    @Autowired
    public void setArticleRepository(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;


    }

    @Override
    public ArticleDTO mapToDTO(Article b) {
       if (b == null)
        return null;
       ArticleDTO d = new ArticleDTO();
       d.setId(b.getId());
       if (b.getPicture()!=null)
       d.setPicture(b.getPicture().getUrl());
       d.setTitle(b.getTitle());
       d.setArticleType(b.getArticleType());
       return  d;
    }

    @Override
    public Article mapToObj(ArticleDTO d) {
        if (d==null)
        return null;
        Article b = new Article();
        Optional<Article> o = Optional.empty();
        if (d.getId() != null)
            o = articleRepository.findById(d.getId());

        if (o.isPresent())
            b = o.get();

        b.setTitle(d.getTitle());
        b.setArticleType(d.getArticleType());

        return b;
    }
}

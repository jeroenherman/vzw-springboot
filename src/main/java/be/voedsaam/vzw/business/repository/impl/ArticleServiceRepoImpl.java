package be.voedsaam.vzw.business.repository.impl;

import be.voedsaam.vzw.business.Article;
import be.voedsaam.vzw.business.repository.ArticleRepository;
import be.voedsaam.vzw.enums.ArticleType;
import be.voedsaam.vzw.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Profile("springdatajpa")
public class ArticleServiceRepoImpl implements ArticleService{

    private ArticleRepository articleRepository;
    @Autowired
    public void setArticleRepository(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public List<?> listAll() {
        List<Article> tasks = new ArrayList<>();
        articleRepository.findAll().forEach(tasks::add);
        return tasks;
    }

    @Override
    public Article getById(Long id) {
        Optional<Article> o = articleRepository.findById(id);
        if (o.isPresent())
            return o.get();
        return null;
    }
    @Override
    public Article saveOrUpdate(Article domainObject) {
        return articleRepository.save(domainObject);
    }

    @Override
    public void delete(Long id) {
        if (id != null) {
            Optional<Article> o = articleRepository.findById(id);
            if (o.isPresent())
                articleRepository.delete(o.get());
        }
    }

    @Override
    public List<Article> listHome() {
        return articleRepository.findAllByArticleType(ArticleType.HOME) ;
    }

    @Override
    public List<Article> listGoals() {
        return articleRepository.findAllByArticleType(ArticleType.GOAL) ;
    }

    @Override
    public List<Article> listAbout() {
        return articleRepository.findAllByArticleType(ArticleType.ABOUT) ;
    }

    @Override
    public List<Article> listNews() {
        return articleRepository.findAllByArticleType(ArticleType.NEWS) ;
    }

    @Override
    public List<Article> listPortal() {
        return articleRepository.findAllByArticleType(ArticleType.PORTAL) ;
    }

    @Override
    public List<Article> listDraft() {
        return articleRepository.findAllByArticleType(ArticleType.DRAFT) ;
    }
}

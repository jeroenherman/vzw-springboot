package be.voedsaam.vzw.service;

import be.voedsaam.vzw.business.Article;
import be.voedsaam.vzw.commons.CRUDService;

import java.util.List;

public interface ArticleService extends CRUDService<Article>{
    List<Article> listHome();
    List<Article> listGoals();
    List<Article> listAbout();
    List<Article> listNews();
    List<Article> listPortal();
    List<Article> listDraft();
}

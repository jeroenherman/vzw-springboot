package be.voedsaam.vzw.service.dto;

import be.voedsaam.vzw.enums.ArticleType;

import java.time.LocalDate;
import java.util.Date;

public class ArticleDTO {
    private Long id;
    private String title, picture;
    private ArticleType articleType;
    private Date date;

    public ArticleType getArticleType() {
        return articleType;
    }

    public void setArticleType(ArticleType articleType) {
        this.articleType = articleType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

package be.voedsaam.vzw.business;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Link extends AbstractDomainClass{
    private String title;
    private String url;
    @ManyToOne
    private Article article;

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

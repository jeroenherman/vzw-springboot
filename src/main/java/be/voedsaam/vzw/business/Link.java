package be.voedsaam.vzw.business;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Link extends AbstractDomainClass implements Comparable<Link>{
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Link link = (Link) o;

        if (getTitle() != null ? !getTitle().equals(link.getTitle()) : link.getTitle() != null) return false;
        if (getUrl() != null ? !getUrl().equals(link.getUrl()) : link.getUrl() != null) return false;
        return getArticle() != null ? getArticle().equals(link.getArticle()) : link.getArticle() == null;
    }

    @Override
    public int hashCode() {
        int result = getTitle() != null ? getTitle().hashCode() : 0;
        result = 31 * result + (getUrl() != null ? getUrl().hashCode() : 0);
        result = 31 * result + (getArticle() != null ? getArticle().hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Link o) {
        return this.getId().compareTo(o.getId());
    }
}

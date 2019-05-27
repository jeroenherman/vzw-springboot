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

        if (!title.equals(link.title)) return false;
        return url.equals(link.url);
    }

    @Override
    public int hashCode() {
        if (title!=null && url!=null ) {
            int result = title.hashCode();
            result = 31 * result + url.hashCode();
            return result;
        }
        return 0;
    }

    @Override
    public int compareTo(Link o) {
        if (this.getId()!=null && o.getId()!=null)
        return this.getId().compareTo(o.getId());
        return 0;
    }
}

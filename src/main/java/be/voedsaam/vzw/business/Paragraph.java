package be.voedsaam.vzw.business;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class Paragraph extends AbstractDomainClass implements Comparable<Paragraph> {
    @Lob
    private String title;
    @Lob
    private String text;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Paragraph paragraph = (Paragraph) o;

        if (title != null ? !title.equals(paragraph.title) : paragraph.title != null) return false;
        return text != null ? text.equals(paragraph.text) : paragraph.text == null;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Paragraph o) {
        if (this.getId()!=null&&o.getId()!=null)
        return this.getId().compareTo(o.getId());
        return 0;
    }
}

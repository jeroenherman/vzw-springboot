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

        if (getTitle() != null ? !getTitle().equals(paragraph.getTitle()) : paragraph.getTitle() != null) return false;
        if (getText() != null ? !getText().equals(paragraph.getText()) : paragraph.getText() != null) return false;
        return getArticle() != null ? getArticle().equals(paragraph.getArticle()) : paragraph.getArticle() == null;
    }

    @Override
    public int hashCode() {
        int result = getTitle() != null ? getTitle().hashCode() : 0;
        result = 31 * result + (getText() != null ? getText().hashCode() : 0);
        result = 31 * result + (getArticle() != null ? getArticle().hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Paragraph o) {
        return this.getId().compareTo(o.getId());
    }
}

package be.voedsaam.vzw.business;

import be.voedsaam.vzw.enums.ArticleType;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
public class Article extends AbstractDomainClass {

	private String title;
	@Enumerated(EnumType.STRING)
	private ArticleType articleType;
	@OneToOne(mappedBy = "article", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Picture picture;
	@OneToMany(mappedBy = "article", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Paragraph> paragraphs = new ArrayList<>();
	@OneToMany(mappedBy = "article", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Link> links= new ArrayList<>();

	public Article() {}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ArticleType getArticleType() {
		return articleType;
	}

	public Picture getPicture() {
		return picture;
	}

	public void setPicture(Picture picture) {
		picture.setArticle(this);
		this.picture = picture;
	}

	public void setArticleType(ArticleType articleType) {
		this.articleType = articleType;
	}

	public void addParagraph(Paragraph paragraph){
		paragraph.setArticle(this);
		paragraphs.add(paragraph);
	}
	public void removeParagraph(Paragraph paragraph){
		paragraph.setArticle(null);
		paragraphs.remove(paragraph);
	}

	public void addLink(Link link){
		link.setArticle(this);
		links.add(link);
	}
	public void removeLink(Link link){
		link.setArticle(null);
		links.remove(link);
	}

	public List<Paragraph> getParagraphs() {
		return Collections.unmodifiableList(paragraphs);
	}

	public List<Link> getLinks() {
		return  Collections.unmodifiableList(links);
	}
}

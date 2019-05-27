package be.voedsaam.vzw.business;

import be.voedsaam.vzw.enums.ArticleType;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ArticleTest {
    private Article classUnderTest;

    @Before
    public void setUp() throws Exception {
        classUnderTest = new Article();

    }

    @Test
    public void getTitle() throws Exception {
        assertNull(classUnderTest.getTitle());
    }

    @Test
    public void setTitle() throws Exception {
        classUnderTest.setTitle("test1");
        assertEquals("test1", classUnderTest.getTitle());
        classUnderTest.setTitle("test2");
        assertEquals("test2", classUnderTest.getTitle());
    }

    @Test
    public void getArticleType() throws Exception {
        assertNull(classUnderTest.getArticleType());
    }

    @Test
    public void getPicture() throws Exception {
        assertNull(classUnderTest.getPicture());
    }

    @Test
    public void setPicture() throws Exception {
        Picture picture = new Picture();
        picture.setUrl("/fileLocation");
        picture.setAlternateText("altText");
        classUnderTest.setPicture(picture);
        assertEquals("/fileLocation",classUnderTest.getPicture().getUrl());
        assertEquals("altText",classUnderTest.getPicture().getAlternateText());

    }

    @Test
    public void setArticleType() throws Exception {
        classUnderTest.setArticleType(ArticleType.HOME);
        assertEquals(ArticleType.HOME,classUnderTest.getArticleType());
        classUnderTest.setArticleType(ArticleType.ABOUT);
        assertEquals(ArticleType.ABOUT,classUnderTest.getArticleType());
    }

    @Test
    public void addParagraph() throws Exception {
        assertEquals(0,classUnderTest.getParagraphs().size());
        Paragraph paragraph = new Paragraph();
        paragraph.setTitle("title1");
        paragraph.setText("text1");
        Paragraph paragraph2 = new Paragraph();
        paragraph.setTitle("title");
        paragraph.setText("text2");
        classUnderTest.addParagraph(paragraph);
        assertEquals(1,classUnderTest.getParagraphs().size());
        classUnderTest.addParagraph(paragraph2);
        assertEquals(2,classUnderTest.getParagraphs().size());
        // test links and paragraphs
        addLink();
        Link link = new Link();

        assertEquals(2,classUnderTest.getParagraphs().size());
        classUnderTest.addLink(link);
        assertEquals(2,classUnderTest.getLinks().size());
        assertEquals(2,classUnderTest.getParagraphs().size());
        classUnderTest.addParagraph(paragraph);
        assertEquals(2,classUnderTest.getParagraphs().size());

    }

    @Test
    public void removeParagraph() throws Exception {
        assertEquals(0,classUnderTest.getParagraphs().size());
        Paragraph paragraph = new Paragraph();
        paragraph.setTitle("title1");
        paragraph.setText("text1");
        Paragraph paragraph2 = new Paragraph();
        paragraph.setTitle("title");
        paragraph.setText("text2");

        classUnderTest.addParagraph(paragraph);
        assertEquals(1,classUnderTest.getParagraphs().size());
        classUnderTest.addParagraph(paragraph2);
        assertEquals(2,classUnderTest.getParagraphs().size());
        classUnderTest.removeParagraph(paragraph2);
        assertEquals(1,classUnderTest.getParagraphs().size());

    }

    @Test
    public void addLink() throws Exception {
        Link l1 = new Link();
        l1.setId(Long.valueOf("1"));
        l1.setUrl("url1");
        l1.setTitle("title1");
        Link l2 = new Link();
        l2.setId(Long.valueOf("2"));
        l2.setUrl("url2");
        l2.setTitle("title2");
        classUnderTest.addLink(l1);
        classUnderTest.addLink(l2);
        assertEquals(2,classUnderTest.getLinks().size());
        classUnderTest.removeLink(l2);
        assertEquals(1,classUnderTest.getLinks().size());

    }


    @Test
    public void getParagraphs() throws Exception {
        assertEquals(0,classUnderTest.getParagraphs().size());
    }

    @Test
    public void getLinks() throws Exception {
        assertEquals(0,classUnderTest.getLinks().size());
    }

}
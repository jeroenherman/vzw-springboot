package be.voedsaam.vzw.business;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParagraphTest {
    private Paragraph classUndertest;
    private Article article;
    @Before
    public void setUp() throws Exception {
        classUndertest = new Paragraph();
        classUndertest.setId(2l);
        article = new Article();
    }

    @Test
    public void getArticle() throws Exception {
        assertNull(classUndertest.getArticle());
    }

    @Test
    public void setArticle() throws Exception {
        classUndertest.setArticle(article);
        assertEquals(article,classUndertest.getArticle());
    }

    @Test
    public void getTitle() throws Exception {
        assertNull(classUndertest.getTitle());
    }

    @Test
    public void setTitle() throws Exception {
        classUndertest.setTitle("test1");
        assertEquals("test1", classUndertest.getTitle());
        classUndertest.setTitle("test2");
        assertEquals("test2", classUndertest.getTitle());
    }

    @Test
    public void getText() throws Exception {
        assertNull(classUndertest.getText());
    }

    @Test
    public void setText() throws Exception {
        classUndertest.setText("test1");
        assertEquals("test1", classUndertest.getText());
        classUndertest.setText("test2");
        assertEquals("test2", classUndertest.getText());
    }

    @Test
    public void compareTo() throws Exception {
        Paragraph paragraph = new Paragraph();
        paragraph.setId(1l);
        assertEquals(1, classUndertest.compareTo(paragraph));
        paragraph.setId(3l);
        assertEquals(-1, classUndertest.compareTo(paragraph));
        paragraph.setId(2l);
        assertEquals(0, classUndertest.compareTo(paragraph));

    }

}
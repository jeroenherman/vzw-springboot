package be.voedsaam.vzw.business.repository.impl;

import be.voedsaam.vzw.business.Article;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class ArticleServiceRepoImplTest {
    @Autowired
    private ArticleServiceRepoImpl classUnderTest;
    private Article article;

    @Before
    public void setUp() throws Exception {

        article = new Article();
        article.setTitle("Test Artikel");
    }



    @Test
    public void listAll() throws Exception {
        assertEquals(5,classUnderTest.listAll().size());
    }

    @Test
    public void getById() throws Exception {
        article = classUnderTest.saveOrUpdate(article);
        assertEquals( article , classUnderTest.getById(article.getId()));
    }


    @Test
    public void delete() throws Exception {
        article = classUnderTest.saveOrUpdate(article);
        Long id = article.getId();
        classUnderTest.delete(null);
        classUnderTest.delete(article.getId());
        assertNull( classUnderTest.getById(article.getId()));
        assertNull( classUnderTest.getById(id));
    }

    @Test
    public void listHome() throws Exception {
        assertEquals(1,classUnderTest.listHome().size());
    }

    @Test
    public void listGoals() throws Exception {
        assertEquals(1,classUnderTest.listGoals().size());
    }

    @Test
    public void listAbout() throws Exception {
        assertEquals(1,classUnderTest.listAbout().size());
    }

    @Test
    public void listNews() throws Exception {
        assertEquals(1,classUnderTest.listNews().size());
    }

    @Test
    public void listPortal() throws Exception {
        assertEquals(1,classUnderTest.listPortal().size());
    }

    @Test
    public void listDraft() throws Exception {
        assertEquals(0,classUnderTest.listDraft().size());
    }

}
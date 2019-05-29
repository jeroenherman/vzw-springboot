package be.voedsaam.vzw.service.mapper;

import be.voedsaam.vzw.business.Article;
import be.voedsaam.vzw.business.Link;
import be.voedsaam.vzw.business.Paragraph;
import be.voedsaam.vzw.business.Picture;
import be.voedsaam.vzw.business.repository.ArticleRepository;
import be.voedsaam.vzw.enums.ArticleType;
import be.voedsaam.vzw.service.dto.ArticleDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ArticleMapperTest {
    private ArticleMapper classUnderTest;
    private Article b;
    private ArticleDTO d;

    @Mock
    ArticleRepository mockRepository;
    @Before
    public void setUp() throws Exception {
        classUnderTest = new ArticleMapper();
        classUnderTest.setArticleRepository(mockRepository);
        b = new Article();
        b.setTitle("title");
        b.setId(123l);
        b.setArticleType(ArticleType.HOME);
        Paragraph paragraph = new Paragraph();
        paragraph.setTitle("pragraaf titel");
        paragraph.setText("bla bla");

        b.addParagraph(paragraph);
        Link link = new Link();
        link.setTitle("title link");
        link.setUrl("url link");
        b.addLink(link);
        Picture picture = new Picture();
        picture.setUrl("foto.jpg");
        b.setPicture(picture);
        d = new ArticleDTO();

    }

    @Test
    public void testNullBusinessObject() {
        b = null;
        d = classUnderTest.mapToDTO(b);
        assertNull(d);
    }

    @Test
    public void testNullDTO() {
        d = null;
        b = classUnderTest.mapToObj(d);
        assertNull(b);
    }


    @Test
    public void mapToDTO() throws Exception {
        d = null;
        d = classUnderTest.mapToDTO(b);
        assertEquals(d.getId(),b.getId());
        assertEquals(d.getTitle(),b.getTitle());
        assertEquals(d.getArticleType(),b.getArticleType());
        assertEquals(d.getPicture(),b.getPicture().getUrl());

    }

    @Test
    public void mapToObj() throws Exception {
        d.setTitle("title dto");
        d.setArticleType(ArticleType.PORTAL);
        b = classUnderTest.mapToObj(d);
        assertEquals(d.getArticleType(), b.getArticleType());
        assertEquals(d.getTitle(), b.getTitle());

    }

    @Test
    public void mapToObjWithRepo() throws Exception {
        Optional<Article> o = Optional.of(b);
        Mockito.when(mockRepository.findById(new Long(123))).thenReturn(o);
        d.setId(new Long(123));
        b = classUnderTest.mapToObj(d);
        assertEquals(d.getId(), b.getId());
        assertEquals(d.getTitle(), b.getTitle());
        assertEquals(d.getArticleType(), b.getArticleType());

    }
}
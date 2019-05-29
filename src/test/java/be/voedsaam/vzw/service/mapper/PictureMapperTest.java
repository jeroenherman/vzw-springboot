package be.voedsaam.vzw.service.mapper;

import be.voedsaam.vzw.business.Article;
import be.voedsaam.vzw.business.Link;
import be.voedsaam.vzw.business.Picture;
import be.voedsaam.vzw.business.repository.LinkRepository;
import be.voedsaam.vzw.business.repository.PictureRepository;
import be.voedsaam.vzw.service.dto.LinkDTO;
import be.voedsaam.vzw.service.dto.PictureDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.*;
@RunWith(MockitoJUnitRunner.class)
public class PictureMapperTest {
    private PictureMapper classUnderTest;
    private Picture b;
    private PictureDTO d;
    private Article article;

    @Mock
    PictureRepository mockRepository;

    @Before
    public void setUp() throws Exception {
        classUnderTest = new PictureMapper();
        classUnderTest.setPictureRepository(mockRepository);
        b = new Picture();
        article = new Article();
        article.setTitle("title article");
        b.setArticle(article);
        b.setTitle("title");
        b.setId(123l);
        b.setAlternateText("title dto");

        d = new PictureDTO();
        d.setAlternateText("title dto");
        d.setUrl("url dto");
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
    public void mapToDTO() {
        d = null;
        d= classUnderTest.mapToDTO(b);


    }

    @Test
    public void mapToObj() {
        b = null;
        b = classUnderTest.mapToObj(d);
    }

    @Test
    public void mapToObjWithRepo() throws Exception {
        Optional<Picture> o = Optional.of(b);
        Mockito.when(mockRepository.findById(new Long(123))).thenReturn(o);
        d.setId(new Long(123));
        b = classUnderTest.mapToObj(d);
        assertEquals(d.getId(), b.getId());
        assertEquals(d.getAlternateText(), b.getAlternateText());
        assertEquals(d.getUrl(), b.getUrl());


    }
}
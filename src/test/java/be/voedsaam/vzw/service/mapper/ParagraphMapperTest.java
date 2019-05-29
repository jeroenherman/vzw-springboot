package be.voedsaam.vzw.service.mapper;

import be.voedsaam.vzw.business.Article;
import be.voedsaam.vzw.business.Paragraph;
import be.voedsaam.vzw.business.repository.ParagraphRepository;
import be.voedsaam.vzw.service.dto.ParagraphDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.*;
@RunWith(MockitoJUnitRunner.class)
public class ParagraphMapperTest {
    private ParagraphMapper classUnderTest;
    private Paragraph paragraph;
    private Paragraph b;
    private ParagraphDTO d;
    private Article article;

    @Mock
    ParagraphRepository mockRepository;

    @Before
    public void setUp() throws Exception {
        classUnderTest = new ParagraphMapper();
        classUnderTest.setParagraphRepository(mockRepository);
        b = new Paragraph();
        b.setId(123l);
        b.setText("blabla");
        b.setTitle("title");
        article = new Article();
        article.setTitle("title article");
        b.setArticle(article);
        d = new ParagraphDTO();
        d.setText("blabla");
        d.setTitle("title");
        d.setId(123l);

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
        d = classUnderTest.mapToDTO(b);
        assertEquals(d.getId(),b.getId());
        assertEquals(d.getText(),b.getText());
        assertEquals(d.getTitle(),b.getTitle());
    }

    @Test
    public void mapToObj() {
        b = null;
        b = classUnderTest.mapToObj(d);
        assertEquals(d.getId(),b.getId());
        assertEquals(d.getText(),b.getText());
        assertEquals(d.getTitle(),b.getTitle());
        assertNull(b.getArticle());
    }

    @Test
    public void mapToObjWithRepo() throws Exception {
        Optional<Paragraph> o = Optional.of(b);
        Mockito.when(mockRepository.findById(new Long(123))).thenReturn(o);
        d.setId(new Long(123));
        b = classUnderTest.mapToObj(d);
        assertEquals(d.getId(),b.getId());
        assertEquals(d.getText(),b.getText());
        assertEquals(d.getTitle(),b.getTitle());
        assertNotNull(b.getArticle());
        assertEquals(article, b.getArticle());


    }
}
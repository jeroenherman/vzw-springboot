package be.voedsaam.vzw.service.mapper;

import be.voedsaam.vzw.business.Article;
import be.voedsaam.vzw.business.Paragraph;
import be.voedsaam.vzw.business.repository.ArticleRepository;
import be.voedsaam.vzw.business.repository.ParagraphRepository;
import be.voedsaam.vzw.commons.AbstractMapper;
import be.voedsaam.vzw.service.dto.ArticleDTO;
import be.voedsaam.vzw.service.dto.ParagraphDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ParagraphMapper extends AbstractMapper<Paragraph, ParagraphDTO> {

    private ParagraphRepository paragraphRepository;
    @Autowired
    public void setParagraphRepository(ParagraphRepository paragraphRepository) {
        this.paragraphRepository = paragraphRepository;


    }

    @Override
    public ParagraphDTO mapToDTO(Paragraph b) {
       if (b == null)
        return null;
       ParagraphDTO d = new ParagraphDTO();
       d.setId(b.getId());
       d.setTitle(b.getTitle());
       d.setText(b.getText());

       return  d;
    }

    @Override
    public Paragraph mapToObj(ParagraphDTO d) {
        if (d==null)
        return null;
        Paragraph b = new Paragraph();
        Optional<Paragraph> o = Optional.empty();
        if (d.getId() != null)
            o = paragraphRepository.findById(d.getId());

        if (o.isPresent())
            b = o.get();

        b.setTitle(d.getTitle());
        b.setText(d.getText());

        return b;
    }
}

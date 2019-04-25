package be.voedsaam.vzw.service.mapper;

import be.voedsaam.vzw.business.Link;
import be.voedsaam.vzw.business.Picture;
import be.voedsaam.vzw.business.repository.LinkRepository;
import be.voedsaam.vzw.business.repository.PictureRepository;
import be.voedsaam.vzw.commons.AbstractMapper;
import be.voedsaam.vzw.service.dto.LinkDTO;
import be.voedsaam.vzw.service.dto.PictureDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PictureMapper extends AbstractMapper<Picture, PictureDTO> {

    private PictureRepository pictureRepository;
    @Autowired
    public void setPictureRepository(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    @Override
    public PictureDTO mapToDTO(Picture b) {
       if (b == null)
        return null;
       PictureDTO d = new PictureDTO();
       d.setId(b.getId());
       d.setAlternateText(b.getAlternateText());
       d.setUrl(b.getUrl());

       return  d;
    }

    @Override
    public Picture mapToObj(PictureDTO d) {
        if (d==null)
        return null;
        Picture b = new Picture();
        Optional<Picture> o = Optional.empty();
        if (d.getId() != null)
            o = pictureRepository.findById(d.getId());

        if (o.isPresent())
            b = o.get();

       b.setAlternateText(b.getAlternateText());
        b.setUrl(d.getUrl());

        return b;
    }
}

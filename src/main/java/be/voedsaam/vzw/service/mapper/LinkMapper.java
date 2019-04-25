package be.voedsaam.vzw.service.mapper;

import be.voedsaam.vzw.business.Link;
import be.voedsaam.vzw.business.repository.LinkRepository;
import be.voedsaam.vzw.commons.AbstractMapper;
import be.voedsaam.vzw.service.dto.LinkDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LinkMapper extends AbstractMapper<Link, LinkDTO> {

    private LinkRepository linkRepository;
    @Autowired
    public void setLinkRepository(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    @Override
    public LinkDTO mapToDTO(Link b) {
       if (b == null)
        return null;
       LinkDTO d = new LinkDTO();
       d.setId(b.getId());
        d.setTitle(b.getTitle());
        d.setUrl(b.getUrl());

       return  d;
    }

    @Override
    public Link mapToObj(LinkDTO d) {
        if (d==null)
        return null;
        Link b = new Link();
        Optional<Link> o = Optional.empty();
        if (d.getId() != null)
            o = linkRepository.findById(d.getId());

        if (o.isPresent())
            b = o.get();

        b.setTitle(d.getTitle());
        b.setUrl(d.getUrl());

        return b;
    }
}

package be.voedsaam.vzw.business.repository.impl;

import be.voedsaam.vzw.business.Link;
import be.voedsaam.vzw.business.repository.LinkRepository;
import be.voedsaam.vzw.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Profile("springdatajpa")
public class LinkServiceRepoImpl implements LinkService {

    private LinkRepository linkRepository;
    @Autowired
    public void setLinkRepository(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    @Override
    public List<?> listAll() {
        List<Link> tasks = new ArrayList<>();
        linkRepository.findAll().forEach(tasks::add);
        return tasks;
    }

    @Override
    public Link getById(Long id) {
        Optional<Link> o = linkRepository.findById(id);
        if (o.isPresent())
            return o.get();
        return null;
    }
    @Override
    public Link saveOrUpdate(Link domainObject) {
        return linkRepository.save(domainObject);
    }

    @Override
    public void delete(Long id) {
        if (id != null) {
            Optional<Link> o = linkRepository.findById(id);
            if (o.isPresent())
                linkRepository.delete(o.get());
        }
    }

}

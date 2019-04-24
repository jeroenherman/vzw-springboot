package be.voedsaam.vzw.business.repository.impl;

import be.voedsaam.vzw.business.Paragraph;
import be.voedsaam.vzw.business.repository.ParagraphRepository;

import be.voedsaam.vzw.service.ParagraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Profile("springdatajpa")
public class ParagraphServiceRepoImpl implements ParagraphService{

    private ParagraphRepository paragraphRepository;
    @Autowired
    public void setParagraphRepository(ParagraphRepository paragraphRepository) {
        this.paragraphRepository = paragraphRepository;
    }

    @Override
    public List<?> listAll() {
        List<Paragraph> tasks = new ArrayList<>();
        paragraphRepository.findAll().forEach(tasks::add);
        return tasks;
    }

    @Override
    public Paragraph getById(Long id) {
        Optional<Paragraph> o = paragraphRepository.findById(id);
        if (o.isPresent())
            return o.get();
        return null;
    }
    @Override
    public Paragraph saveOrUpdate(Paragraph domainObject) {
        return paragraphRepository.save(domainObject);
    }

    @Override
    public void delete(Long id) {
        if (id != null) {
            Optional<Paragraph> o = paragraphRepository.findById(id);
            if (o.isPresent())
                paragraphRepository.delete(o.get());
        }
    }

}
